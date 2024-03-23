/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.skillspark.onlinelearningplatform.model;

import java.util.Date;

/**
 *
 * @author lolip
 */
public class Course {
    private int id;
    private int category_id;
    private int tutor_id;
    private String name;
    private int duration;
    private String description;
    private int status;
    private String difficulties;
    private String category_name;
    private String username;
    
    public Course(int id,int category_id, int tutor_id, String name, int duration, String description, int status, String difficulties, String category_name, String username)
    {
        this.id = id;
        this.category_id = category_id;
        this.tutor_id = tutor_id;
        this.name = name;
        this.duration = duration;
        this.description = description;
        this.status = status;
        this.difficulties = difficulties;
        this.category_name = category_name;
        this.username = username;
    }
    
    
    public Course(int id,int category_id, int tutor_id, String name, int duration, String description, int status, String difficulties, String category_name)
    {
        this.id = id;
        this.category_id = category_id;
        this.tutor_id = tutor_id;
        this.name = name;
        this.duration = duration;
        this.description = description;
        this.status = status;
        this.difficulties = difficulties;
        this.category_name = category_name;
    }
    
    public Course(int id,int category_id, int tutor_id, String name, int duration, String description, int status, String difficulties)
    {
        this.id = id;
        this.category_id = category_id;
        this.tutor_id = tutor_id;
        this.name = name;
        this.duration = duration;
        this.description = description;
        this.status = status;
        this.difficulties = difficulties;
    }
    
     public Course(int id)
    {
        this.id = id;
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
     * @return the category_id
     */
    public int getCategory_id() {
        return category_id;
    }

    /**
     * @param category_id the category_id to set
     */
    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    /**
     * @return the tutor_id
     */
    public int getTutor_id() {
        return tutor_id;
    }

    /**
     * @param tutor_id the tutor_id to set
     */
    public void setTutor_id(int tutor_id) {
        this.tutor_id = tutor_id;
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
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(int duration) {
        this.duration = duration;
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
     * @return the difficulties
     */
    public String getDifficulties() {
        return difficulties;
    }

    /**
     * @param difficulties the difficulties to set
     */
    public void setDifficulties(String difficulties) {
        this.difficulties = difficulties;
    }

    /**
     * @return the category_name
     */
    public String getCategory_name() {
        return category_name;
    }

    /**
     * @param category_name the category_name to set
     */
    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

}
