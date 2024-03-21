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
public class Category {
    private int id;
    private String name;
    private String description;
    
    public Category(int id,String name, String description)
    {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    
    public Category(String name, String description)
    {
        this.name = name;
        this.description = description;
    }
       public Category(int id)
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
    
}
