package cn.jackbin.easyexcelplus.read;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 少量数据读取监听
 * @param <T>
 */
public class FewDataListener<T> extends AnalysisEventListener<T> {
    private List<T> list = new ArrayList<>();
    Consumer<Object> consumer;

    public FewDataListener(Consumer<Object> consumer) {
        this.consumer = consumer;
    }

    /**
     * 解析到数据
     */
    @Override
    public void invoke(Object data, AnalysisContext context) {
        list.add((T) data);
    }

    /**
     * 全部解析完成
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
