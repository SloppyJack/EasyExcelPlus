import cn.jackbin.easyexcelplus.EasyExcelPlus;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import entity.PartsData;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class WriteTest {

    /**
     * 简单写入
     */
    @Test
    public void simpleWriteTest() {
        List<PartsData> data = PartsData.buildList();
        String fileName = new Date() + "_simple.xlsx";
        EasyExcelPlus.write(fileName, data, PartsData.class, 10);
    }

    /**
     * 多个Sheet写入
     */
    @Test
    public void multiWriteTest() {
        List<PartsData> list = PartsData.buildList();
        String fileName = new Date() + "_multi.xlsx";
        ExcelWriter writer = EasyExcelPlus.buildWriter(fileName, PartsData.class);
        for (int i=0; i<10; i++) {
            WriteSheet sheet = EasyExcelPlus.buildWriterSheet(list, PartsData.class, true, i, "模板"+i);
            writer.write(list, sheet);
        }
        writer.finish();
    }
}
