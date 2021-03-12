package cn.jackbin.easyexcelplus.write.merge;

import cn.jackbin.easyexcelplus.annotations.CellMerge;
import cn.jackbin.easyexcelplus.constants.MergeType;
import cn.jackbin.easyexcelplus.utils.ClassUtil;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.WriteHandler;
import com.alibaba.excel.write.merge.AbstractMergeStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 列值重复合并策略
 */
@Slf4j
public class ColRepeatStrategy<T> implements MergeStrategy<T> {
    @Override
    public List<CellRangeAddress> handle(List list, Class clazz) {
        return null;
    }

    @Override
    public List<CellRangeAddress> handle(List list, Class clazz, boolean hasTitle) {
        List<CellRangeAddress> ret = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        List<Field> mergeFields = new ArrayList<>();   // field with merge annotation
        List<Integer> mergeFieldsIndex = new ArrayList<>(); // col index with merge cell
        for (int i=0; i<fields.length; i++) {
            Field field = fields[i];
            if (field.isAnnotationPresent(CellMerge.class)) {
                CellMerge cm = field.getAnnotation(CellMerge.class);
                if (MergeType.Col_Repeat_Val.getVal() == cm.type().getVal()) {
                    mergeFields.add(field);
                    mergeFieldsIndex.add(cm.index() == -1 ? i : cm.index());
                } else {
                    log.warn("currently only support merge row repeat val");
                }
            }
        }
        // row merger begin index
        int rowIndex = hasTitle ? 1 : 0;
        // dictionary
        Map<Field, RepeatCell> map = new HashMap<>();
        // generate CellRangeAddress 2 merge cells
        for (int i=0; i<list.size(); i++) {
            for (int j=0; j<mergeFields.size(); j++) {
                Field field = mergeFields.get(j);
                Object val = ClassUtil.getProperty(field, list.get(i), clazz);
                int colNum = mergeFieldsIndex.get(j);
                if (!map.containsKey(field)) {
                    // dictionary without the field
                    map.put(field, new RepeatCell(val, i));
                } else {
                    // current val not equal with the val in dictionary
                    if (map.get(field).getVal() != val) {
                        if (i - map.get(field).getCurrent() > 1)
                            ret.add(new CellRangeAddress(map.get(field).getCurrent()+rowIndex, i+rowIndex-1, colNum, colNum));
                        map.put(field, new RepeatCell(val, i));
                    } else if (i == list.size() - 1) {
                        if (i > map.get(field).getCurrent())
                            ret.add(new CellRangeAddress(map.get(field).getCurrent() + rowIndex, i+rowIndex, colNum, colNum));
                    }
                }
            }
        }
        return ret;
    }

    @Override
    public WriteHandler build(List<CellRangeAddress> list) {
        return new AbstractMergeStrategy() {
            // merge cells
            @Override
            protected void merge(Sheet sheet, Cell cell, Head head, Integer relativeRowIndex) {
                // judge the list is not null
                if (CollectionUtils.isNotEmpty(list)) {
                    // the judge is necessary
                    if (cell.getRowIndex() == 1 && cell.getColumnIndex() == 0) {
                        for (CellRangeAddress item : list) {
                            sheet.addMergedRegion(item);
                        }
                    }
                }
            }
        };
    }

    static class RepeatCell {
        private Object val;
        private int current;

        public RepeatCell(Object val, int current) {
            this.val = val;
            this.current = current;
        }

        public RepeatCell(Object val) {
            this.val = val;
        }

        public Object getVal() {
            return val;
        }

        public void setVal(Object val) {
            this.val = val;
        }

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }
    }
}
