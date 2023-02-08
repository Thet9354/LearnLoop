package com.example.learnloop.Model;

public class InProgress {

    private int id;
    private int InProgressPic;

    private String InProgressCourse;
    private String InProgressHost;
    private String InProgressHostDesc;
    private String InProgressLink;

    public InProgress(int id, int inProgressPic, String inProgressCourse, String inProgressHost, String inProgressHostDesc, String inProgressLink) {
        this.id = id;
        this.InProgressPic = inProgressPic;
        this.InProgressCourse = inProgressCourse;
        this.InProgressHost = inProgressHost;
        this.InProgressHostDesc = inProgressHostDesc;
        this.InProgressLink = inProgressLink;
    }

    public InProgress() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInProgressPic() {
        return InProgressPic;
    }

    public void setInProgressPic(int inProgressPic) {
        InProgressPic = inProgressPic;
    }

    public String getInProgressCourse() {
        return InProgressCourse;
    }

    public void setInProgressCourse(String inProgressCourse) {
        InProgressCourse = inProgressCourse;
    }

    public String getInProgressHost() {
        return InProgressHost;
    }

    public void setInProgressHost(String inProgressHost) {
        InProgressHost = inProgressHost;
    }

    public String getInProgressHostDesc() {
        return InProgressHostDesc;
    }

    public void setInProgressHostDesc(String inProgressHostDesc) {
        InProgressHostDesc = inProgressHostDesc;
    }

    public String getInProgressLink() {
        return InProgressLink;
    }

    public void setInProgressLink(String inProgressLink) {
        InProgressLink = inProgressLink;
    }
}
