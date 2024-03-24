package org.skillspark.onlinelearningplatform.model;

public class Chapters {
    private int id;
    private int course_id;
    private String title;
    private String name;
    private String videoPath;
    private String attachmentPath;
    private String description;
    private int status;
    private String level;

    public Chapters(int id, int course_id, String title, String name, String videoPath, String attachmentPath, String description, int status, String level) {
        this.id = id;
        this.course_id = course_id;
        this.title = title;
        this.name = name;
        this.videoPath = videoPath;
        this.attachmentPath = attachmentPath;
        this.description = description;
        this.status = status;
        this.level = level;
    }

    public Chapters() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
