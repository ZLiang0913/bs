package net.wanho.entity;

public class DailyLogin {
    private String loginDate;
    private String count;

    public String getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public DailyLogin(String loginDate, String count) {
        this.loginDate = loginDate;
        this.count = count;
    }


    @Override
    public String toString() {
        return "DailyLogin{" +
                "loginDate='" + loginDate + '\'' +
                ", count=" + count +
                '}';
    }
}
