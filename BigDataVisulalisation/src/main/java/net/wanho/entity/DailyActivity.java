package net.wanho.entity;

public class DailyActivity {
    private String updatedDate;
    private String count;

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public DailyActivity(String updatedDate, String count) {
        this.updatedDate = updatedDate;
        this.count = count;
    }


    @Override
    public String toString() {
        return "DailyActivity{" +
                "updatedDate='" + updatedDate + '\'' +
                ", count=" + count +
                '}';
    }
}
