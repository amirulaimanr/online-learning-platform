package org.skillspark.onlinelearningplatform.repository;

public interface CourseRepository {

    String LIST_3_COURSE_PER_CATEGORY = "SELECT cs.id, cs.category_id, cs.tutor_id, cs.name, cs.durations, cs.description, cs.status, cs.difficulties, cat.name as category_name FROM courses cs INNER JOIN categories cat ON cs.category_id = cat.id WHERE cat.id IN (SELECT cat2.id FROM categories cat2 ORDER BY cat2.id ASC LIMIT 5) AND cs.id IN (SELECT c2.id FROM courses c2 WHERE c2.category_id = cs.category_id ORDER BY c2.id ASC LIMIT 3) ORDER BY cs.category_id ASC, cs.id ASC;";
    String LIST_ALL_COURSE = "SELECT * FROM courses";
    String LIST_ALL_COURSE_BY_CATEGORY = "SELECT cs.id, cs.category_id, cs.tutor_id, cs.name, cs.durations, cs.description, cs.status, cs.difficulties, cat.name AS category_name FROM courses cs INNER JOIN categories cat ON cs.category_id = cat.id WHERE cat.name = ? ORDER BY cs.id ASC";
    String LIST_UNIQUE_COURSE_NAME = "SELECT DISTINCT cat.name AS category_name FROM courses cs INNER JOIN categories cat ON cs.category_id = cat.id";
    String ALL_COURSE_BY_SELECTED_ID = "SELECT * FROM courses WHERE id = ?";
}
