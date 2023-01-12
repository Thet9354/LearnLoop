package com.example.learnloop.Model;

public class Courses {

    private int id;
    private int courseImage;
    private int hostImage;

    private String courseTitle;
    private String hostName;
    private String hostDesc;

    private String courseDuration;
    private String courseLessons;

    public Courses(int id, int courseImage, int hostImage, String courseTitle, String hostName, String hostDesc, String courseDuration, String courseLessons) {
        this.id = id;
        this.courseImage = courseImage;
        this.hostImage = hostImage;
        this.courseTitle = courseTitle;
        this.hostName = hostName;
        this.hostDesc = hostDesc;
        this.courseDuration = courseDuration;
        this.courseLessons = courseLessons;
    }

    public Courses() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseImage() {
        return courseImage;
    }

    public void setCourseImage(int courseImage) {
        this.courseImage = courseImage;
    }

    public int getHostImage() {
        return hostImage;
    }

    public void setHostImage(int hostImage) {
        this.hostImage = hostImage;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostDesc() {
        return hostDesc;
    }

    public void setHostDesc(String hostDesc) {
        this.hostDesc = hostDesc;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getCourseLessons() {
        return courseLessons;
    }

    public void setCourseLessons(String courseLessons) {
        this.courseLessons = courseLessons;
    }
}
