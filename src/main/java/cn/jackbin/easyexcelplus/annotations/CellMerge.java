package cn.jackbin.easyexcelplus.annotations;


import cn.jackbin.easyexcelplus.constants.MergeType;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface CellMerge {
    // default:merger the col with same val
    MergeType type() default MergeType.Col_Repeat_Val;

    // col index
    int index() default -1;
}
