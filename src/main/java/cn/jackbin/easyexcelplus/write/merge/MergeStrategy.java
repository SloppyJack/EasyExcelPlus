package cn.jackbin.easyexcelplus.write.merge;

import com.alibaba.excel.write.handler.WriteHandler;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;

/**
 * 合并策略
 */
public interface MergeStrategy<T> {

    List<CellRangeAddress> handle(List<T> list, Class<T> clazz);

    List<CellRangeAddress> handle(List<T> list, Class<T> clazz, boolean hasTitle);

    WriteHandler build(List<CellRangeAddress> list);
}
