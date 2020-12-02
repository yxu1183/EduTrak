package com.example.edutrak;

public class EventHelperClass {
    String title, course, date, time, description;

    public EventHelperClass() {
    }

    public EventHelperClass(String title, String course, String date, String time, String description) {
        this.title = title;
        this.course = course;
        this.date = date;
        this.time = time;
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
