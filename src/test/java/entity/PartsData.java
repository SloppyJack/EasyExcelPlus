package entity;

import cn.jackbin.easyexcelplus.annotations.CellMerge;
import cn.jackbin.easyexcelplus.annotations.MergeModel;
import cn.jackbin.easyexcelplus.utils.ImageUtil;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@HeadRowHeight(60)
@MergeModel
public class PartsData {
    @ExcelProperty(value = "型号")
    @CellMerge
    private String partsCategory;


    @ExcelProperty(value = "编码")
    private int code;

    @ExcelProperty(value = "流水线Id")
    private String pipelineId;

    @ExcelProperty(value = "缺陷序号")
    private int num;

    @ExcelProperty(value = "缺陷类别")
    private String flawCategory;

    @ExcelProperty(value = "缺陷坐标")
    @CellMerge
    private String coordinate;

    @ExcelProperty(value = "图片")
    private byte[] byteArray;

    public static List<PartsData> buildList() {
        List<PartsData> list = new ArrayList<>();
        for (int i=0; i<3; i++) {
            PartsData temp = new PartsData();
            temp.setPartsCategory("A");
            temp.setCode(i);
            temp.setPipelineId(String.valueOf(System.currentTimeMillis()));
            temp.setNum(i);
            temp.setFlawCategory("test");
            temp.setCoordinate("[(1.1,2.2),(3.3,4.4)]");
            temp.setByteArray(ImageUtil.convert2Bytes("/home/wbb/IdeaProjects/EasyExcelPlus/test.png"));
            list.add(temp);
        }
        for (int i=0; i<4; i++) {
            PartsData temp = new PartsData();
            temp.setPartsCategory("B");
            temp.setCode(i);
            temp.setPipelineId(String.valueOf(System.currentTimeMillis()));
            temp.setNum(i);
            temp.setFlawCategory("test1");
            temp.setCoordinate("[(1.1,2.2),(3.3,4.4)]");
            temp.setByteArray(ImageUtil.convert2Bytes("/home/wbb/IdeaProjects/EasyExcelPlus/test.png"));
            list.add(temp);
        }
        for (int i=0; i<1; i++) {
            PartsData temp = new PartsData();
            temp.setPartsCategory("C");
            temp.setCode(i);
            temp.setPipelineId(String.valueOf(System.currentTimeMillis()));
            temp.setNum(i);
            temp.setFlawCategory("test2");
            temp.setCoordinate("[(1.1,2.2),(3.3,4.4)]");
            temp.setByteArray(ImageUtil.convert2Bytes("/home/wbb/IdeaProjects/EasyExcelPlus/test.png"));
            list.add(temp);
        }
        return list;
    }

}
