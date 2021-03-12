package cn.jackbin.easyexcelplus;

import cn.jackbin.easyexcelplus.write.merge.MergeContext;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.handler.WriteHandler;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.util.List;

public class EasyExcelPlusFactory {

    /**
     * write with data
     * @param data
     * @param clazz
     * @param fileName
     * @param sheetNo
     */
    public static void write(String fileName, List<?> data, Class<?> clazz, int sheetNo) {
        write(fileName, data, clazz, true, sheetNo, sheetNo + "");
    }

    /**
     * write
     * @param fileName
     * @param data
     * @param clazz
     * @param sheetNo
     */
    public static void write(String fileName, List<?> data, Class<?> clazz, boolean hasTitle, int sheetNo, String sheetName) {
        WriteHandler handler = MergeContext.Holder.build(clazz).buildWriteHandler(data, clazz, hasTitle);
        EasyExcel.write(fileName, clazz)
                //注册合并策略
                .registerWriteHandler(handler)
                .sheet(sheetNo, sheetName)
                .doWrite(data);
    }

    public static ExcelWriter buildWriter(String pathName) {
        return buildWriter(pathName, null);
    }

    /**
     * build writer 2 write multi sheet
     */
    public static ExcelWriter buildWriter(String pathName, Class<?> head) {
        ExcelWriterBuilder excelWriterBuilder = new ExcelWriterBuilder();
        excelWriterBuilder.file(pathName);
        if (head != null) {
            excelWriterBuilder.head(head);
        }
        return excelWriterBuilder.build();
    }

    public static WriteSheet buildWriterSheet(List<?> data, Class<?> head, boolean hasTitle, int index, String sheetName) {
        WriteHandler handler = MergeContext.Holder.build(head).buildWriteHandler(data, head, hasTitle);
        return EasyExcel.writerSheet(index, sheetName).registerWriteHandler(handler).build();
    }
}
