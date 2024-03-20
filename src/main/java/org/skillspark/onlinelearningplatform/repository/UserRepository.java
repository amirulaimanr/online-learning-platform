package org.skillspark.onlinelearningplatform.repository;

public interface UserRepository {

    String COUNT_USERS_BY_EMAIL_AND_PASSWORD = "SELECT COUNT(*) FROM users WHERE email = ? AND password = ?";

}
