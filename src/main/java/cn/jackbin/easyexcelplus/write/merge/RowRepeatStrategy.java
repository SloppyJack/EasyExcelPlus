package cn.jackbin.easyexcelplus.write.merge;

import com.alibaba.excel.write.handler.WriteHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.util.CellRangeAddress;
import java.util.List;

/**
 * 行值重复合并策略
 */
@Slf4j
public class RowRepeatStrategy<T> implements MergeStrategy<T>{

    @Override
    public List<CellRangeAddress> handle(List<T> list, Class<T> clazz) {
        return handle(list, clazz, true);
    }

    @Override
    public List<CellRangeAddress> handle(List<T> list, Class<T> clazz, boolean hasTitle) {
        return null;
    }

    @Override
    public WriteHandler build(List<CellRangeAddress> list) {
        return null;
    }
}
