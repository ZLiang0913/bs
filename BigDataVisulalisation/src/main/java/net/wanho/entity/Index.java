package net.wanho.entity;

public class Index{
    private String rowkey;
    private String num;

    public String getRowkey() {
        return rowkey;
    }

    public void setRowkey(String rowkey) {
        this.rowkey = rowkey;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Index(String rowkey, String num) {
        this.rowkey = rowkey;
        this.num = num;
    }
}
