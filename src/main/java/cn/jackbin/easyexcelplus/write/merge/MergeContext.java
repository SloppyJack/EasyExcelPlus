package cn.jackbin.easyexcelplus.write.merge;

import cn.jackbin.easyexcelplus.annotations.MergeModel;
import com.alibaba.excel.write.handler.WriteHandler;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;

/**
 * 合并上下文
 */
public class MergeContext<T> {
    // 具体的策略对象
    private MergeStrategy<T> strategy;
    private Class<?> clazz;

    private MergeContext(MergeStrategy<T> strategy) {
        this.strategy = strategy;
    }

    /**
     * 外部需走此处
     */
    public static class Holder {
        /**
         * 构建上下文
         */
        public static MergeContext build(Class clazz) {
            MergeContext context = null;
            if (clazz.isAnnotationPresent(MergeModel.class)) {
                MergeModel model = (MergeModel) clazz.getAnnotation(MergeModel.class);
                switch (model.type()) {
                    case 1:
                        context = new MergeContext(new ColRepeatStrategy<>());
                        break;
                    case 2:
                        context = new MergeContext(new RowRepeatStrategy());
                        break;
                }
            }
            return context;
        }
    }



    /**
     * 构建写入处理者
     */
    public WriteHandler buildWriteHandler(List<T> data, Class<T> clazz){
        return buildWriteHandler(data, clazz, true);
    }

    /**
     * 构建写入处理者
     */
    public WriteHandler buildWriteHandler(List<T> data, Class<T> clazz, boolean hasTitle){
        List<CellRangeAddress> l = strategy.handle(data, clazz, hasTitle);
        return strategy.build(l);
    }
}
