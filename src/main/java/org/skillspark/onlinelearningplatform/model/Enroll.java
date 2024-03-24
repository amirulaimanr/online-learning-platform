/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.skillspark.onlinelearningplatform.model;

/**
 *
 * @author lolip
 */
public class Enroll {
    private int id;
    private int course_id;
    private int student_id;
    private String date_enroll;
    private String course_name;
    private String student_name;
    private int totalStudent;
    
    public Enroll(int id, int course_id, int student_id, String date_enroll, String course_name, String student_name){
        this.id = id;
        this.student_id = student_id;
        this.course_id = course_id;
        this.date_enroll = date_enroll;
        this.student_name = student_name;
        this.course_name = course_name;
    }
    
     public Enroll(String course_name, int totalStudent){
        this.course_name = course_name;
        this.totalStudent = totalStudent;
    }
    
    public Enroll(){
        
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
     * @return the student_id
     */
    public int getStudent_id() {
        return student_id;
    }

    /**
     * @param student_id the student_id to set
     */
    public void setStudent_id(int student_id) {
        this.student_id = student_id;
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

    /**
     * @return the student_name
     */
    public String getStudent_name() {
        return student_name;
    }

    /**
     * @param student_name the student_name to set
     */
    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    /**
     * @return the date_enroll
     */
    public String getDate_enroll() {
        return date_enroll;
    }

    /**
     * @param date_enroll the date_enroll to set
     */
    public void setDate_enroll(String date_enroll) {
        this.date_enroll = date_enroll;
    }

    /**
     * @return the totalStudent
     */
    public int getTotalStudent() {
        return totalStudent;
    }

    /**
     * @param totalStudent the totalStudent to set
     */
    public void setTotalStudent(int totalStudent) {
        this.totalStudent = totalStudent;
    }
    
    
}
