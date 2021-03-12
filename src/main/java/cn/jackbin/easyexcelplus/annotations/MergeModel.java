package cn.jackbin.easyexcelplus.annotations;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface MergeModel {

    /**
     * 1 means col merge
     * 2 means row merge
     */
    int type() default 1;
}
