/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.skillspark.onlinelearningplatform.util;

import java.util.ArrayList;
import java.util.List;
import org.skillspark.onlinelearningplatform.model.Category;
import org.skillspark.onlinelearningplatform.model.Chapter;
import org.skillspark.onlinelearningplatform.model.Course;
import org.skillspark.onlinelearningplatform.model.Enroll;

/**
 *
 * @author lolip
 */
public class Pagination {

    public List<Category> categoryPaginate(List<Category> result, int offset, int limit) {
        List<Category> data = new ArrayList<>();
        for (int i = offset; i < Math.min(offset + limit, result.size()); i++) {
            data.add(result.get(i));
        }
        return data;
    }

    public int getTotalRecordsCategory(List<Category> result) {
        return result.size();
    }
    
    public List<Course> coursePaginate(List<Course> result, int offset, int limit) {
        List<Course> data = new ArrayList<>();
        for (int i = offset; i < Math.min(offset + limit, result.size()); i++) {
            data.add(result.get(i));
        }
        return data;
    }

    public int getTotalRecordsCourse(List<Course> result) {
        return result.size();
    }
    
    public List<Chapter> chapterPaginate(List<Chapter> result, int offset, int limit) {
        List<Chapter> data = new ArrayList<>();
        for (int i = offset; i < Math.min(offset + limit, result.size()); i++) {
            data.add(result.get(i));
        }
        return data;
    }

    public int getTotalRecordsChapter(List<Chapter> result) {
        return result.size();
    }
    
    public List<Enroll> enrollPaginate(List<Enroll> result, int offset, int limit) {
        List<Enroll> data = new ArrayList<>();
        for (int i = offset; i < Math.min(offset + limit, result.size()); i++) {
            data.add(result.get(i));
        }
        return data;
    }

    public int getTotalRecordsEnroll(List<Enroll> result) {
        return result.size();
    }
    
     public List<Course> coursePaginateMoreData(List<Course> result, int offset, int limit) {
        List<Course> data = new ArrayList<>();
        for (int i = 0; i < Math.min(offset + limit, result.size()); i++) {
            data.add(result.get(i));
        }
        return data;
    }

}
