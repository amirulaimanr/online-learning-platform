package org.skillspark.onlinelearningplatform.model;

import java.util.Date;

public class Users {
    private int id;
    private int role_id;
    private String name;
    private String email;
    private String password;
    private String profile_picture;
    private Date join_date;

    public Users() {
    }

    public Users(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
    public Users(int id, int role_id, String name, String email) {
        this.id = id;
        this.role_id = role_id;
        this.name = name;
        this.email = email;
    }


    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public Date getJoin_date() {
        return join_date;
    }

    public void setJoin_date(Date join_date) {
        this.join_date = join_date;
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
}
