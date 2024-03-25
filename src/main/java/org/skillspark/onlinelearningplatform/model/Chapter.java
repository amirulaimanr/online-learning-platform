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

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the course_id
     */
    public int getCourse_id() {
        return course_id;
    }

    /**
     * @param course_id the course_id to set
     */
    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the videoPath
     */
    public String getVideoPath() {
        return videopath;
    }

    /**
     * @param videoPath the videoPath to set
     */
    public void setVideoPath(String videopath) {
        this.videopath = videopath;
    }

    /**
     * @return the attachmentPath
     */
    public String getAttachmentPath() {
        return attachmentpath;
    }

    /**
     * @param attachmentpath the attachmentPath to set
     */
    public void setAttachmentPath(String attachmentpath) {
        this.attachmentpath = attachmentpath;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the level
     */
    public String getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * @return the course_name
     */
    public String getCourse_name() {
        return course_name;
    }

    /**
     * @param course_name the course_name to set
     */
    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

}