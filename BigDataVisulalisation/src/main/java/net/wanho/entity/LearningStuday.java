package net.wanho.entity;

public class LearningStuday {
    private String dateValue;
    private String avgStudyTime;

    public LearningStuday(String dateValue, String avgStudyTime) {
        this.dateValue = dateValue;
        this.avgStudyTime = avgStudyTime;
    }


    public String getDateValue() {
        return dateValue;
    }

    public void setDateValue(String dateValue) {
        this.dateValue = dateValue;
    }

    public String getAvgStudyTime() {
        return avgStudyTime;
    }

    public void setAvgStudyTime(String avgStudyTime) {
        this.avgStudyTime = avgStudyTime;
    }
}
