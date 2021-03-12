import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.read.listener.ReadListener;
import entity.PartsData;
import org.junit.Test;

import java.util.Map;

public class ReadTest {

    /**
     * 简单的读
     */
    @Test
    public void simpleRead() {
        String fileName = "/home/wbb/IdeaProjects/EasyExcelPlus/read_simple.xlsx";
        ExcelReader excelReader = null;
        EasyExcel.read(fileName, PartsData.class, new ReadListener() {

            @Override
            public void onException(Exception exception, AnalysisContext context) throws Exception {

            }

            @Override
            public void invoke(Object data, AnalysisContext context) {
                System.out.println("解析到一个数据");
            }

            @Override
            public void extra(CellExtra extra, AnalysisContext context) {

            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {

            }

            @Override
            public boolean hasNext(AnalysisContext context) {
                return true;
            }

            @Override
            public void invokeHead(Map headMap, AnalysisContext context) {

            }
        }).sheet().doRead();
    }
}
