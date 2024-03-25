package org.skillspark.onlinelearningplatform.model;

public class Chapter {
    private int id;
    private int course_id;
    private String title;
    private String name;
    private String videopath;
    private String attachmentpath;
    private String description;
    private int status;
    private String level;
    private String course_name;

    // this for joint table.Lazy to query back course soo,dump course name here
    public Chapter(int id ,int course_id ,String title ,String name ,String videopath ,String attachmentpath ,String description ,int status ,String level ,String course_name)
    {
        this.id = id;
        this.course_id = course_id;
        this.title = title;
        this.name = name;
        this.videopath = videopath;
        this.attachmentpath = attachmentpath;
        this.description = description;
        this.status = status;
        this.level = level;
        this.course_name = course_name;
    }

    public Chapter(int id ,int course_id ,String title ,String name ,String videopath ,String attachmentpath ,String description ,int status ,String level)
    {
        this.id = id;
        this.course_id = course_id;
        this.title = title;
        this.name = name;
        this.videopath = videopath;
        this.attachmentpath = attachmentpath;
        this.description = description;
        this.status = status;
        this.level = level;
    }

    public Chapter(int id ,int course_id ,String title ,String name ,String description ,int status ,String level)
    {
        this.id = id;
        this.course_id = course_id;
        this.title = title;
        this.name = name;
        this.description = description;
        this.status = status;
        this.level = level;
    }

    public Chapter(int id )
    {
        this.id = id;
    }

    public Chapter() {

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
    public String getVideopath() {
        return videopath;
    }
    public void setVideopath(String videopath) {
        this.videopath = videopath;
    }
    public String getAttachmentpath() {
        return attachmentpath;
    }
    public void setAttachmentpath(String attachmentpath) {
        this.attachmentpath = attachmentpath;
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
    public String getCourse_name() {
        return course_name;
    }
    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

}