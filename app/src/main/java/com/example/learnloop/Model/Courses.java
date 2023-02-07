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
    private String courseDesc;
    private String courseUniversity;
    private String courseUniversityDesc;
    private String coursePublishedDate;
    private String courseLink;


    public Courses(int id, int courseImage, int hostImage, String courseTitle, String hostName, String hostDesc, String courseDuration, String courseLessons, String courseDesc, String courseUniversity, String courseUniversityDesc, String coursePublishedDate, String courseLink) {
        this.id = id;
        this.courseImage = courseImage;
        this.hostImage = hostImage;
        this.courseTitle = courseTitle;
        this.hostName = hostName;
        this.hostDesc = hostDesc;
        this.courseDuration = courseDuration;
        this.courseLessons = courseLessons;
        this.courseDesc = courseDesc;
        this.courseUniversity = courseUniversity;
        this.courseUniversityDesc = courseUniversityDesc;
        this.coursePublishedDate = coursePublishedDate;
        this.courseLink = courseLink;
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

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    public String getCourseUniversity() {
        return courseUniversity;
    }

    public void setCourseUniversity(String courseUniversity) {
        this.courseUniversity = courseUniversity;
    }

    public String getCourseUniversityDesc() {
        return courseUniversityDesc;
    }

    public void setCourseUniversityDesc(String courseUniversityDesc) {
        this.courseUniversityDesc = courseUniversityDesc;
    }

    public String getCoursePublishedDate() {
        return coursePublishedDate;
    }

    public void setCoursePublishedDate(String coursePublishedDate) {
        this.coursePublishedDate = coursePublishedDate;
    }

    public String getCourseLink() {
        return courseLink;
    }

    public void setCourseLink(String courseLink) {
        this.courseLink = courseLink;
    }
}
