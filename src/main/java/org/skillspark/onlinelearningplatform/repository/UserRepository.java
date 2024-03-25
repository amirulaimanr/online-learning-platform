package org.skillspark.onlinelearningplatform.repository;

public interface UserRepository {

    String COUNT_USERS_BY_EMAIL_AND_PASSWORD = "SELECT COUNT(*) FROM users WHERE email = ? AND password = ?";
    String INSERT_USERS_BY_EMAIL_PASSWORD_ROLE = "INSERT INTO users (email, password, role_id, name, join_date, created_at) VALUES (?, ?, ?, ?, ?, ?)";

}
