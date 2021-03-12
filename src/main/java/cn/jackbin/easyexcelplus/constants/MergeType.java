package cn.jackbin.easyexcelplus.constants;

public enum MergeType {
    // merge repeat val in col
    Col_Repeat_Val("colRV", 1),
    // merge repeat val in row
    Row_Repeat_Val("rowRV", 2),

    ;
    private String name;
    private int val;

    MergeType(String name, int val) {
        this.name = name;
        this.val = val;
    }

    public String getName() {
        return name;
    }

    public int getVal() {
        return val;
    }
}
