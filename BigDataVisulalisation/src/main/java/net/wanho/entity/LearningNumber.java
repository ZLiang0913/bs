package net.wanho.entity;

public class LearningNumber {
    private String updatedDate;
    private String studyTimes;

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getStudyTimes() {
        return studyTimes;
    }

    public void setStudyTimes(String studyTimes) {
        this.studyTimes = studyTimes;
    }

    public LearningNumber(String updatedDate, String studyTimes) {
        this.updatedDate = updatedDate;
        this.studyTimes = studyTimes;
    }
}
