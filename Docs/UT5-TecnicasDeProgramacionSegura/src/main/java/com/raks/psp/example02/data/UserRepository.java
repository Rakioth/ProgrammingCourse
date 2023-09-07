package com.raks.psp.example02.data;

public interface UserRepository {
    Boolean exists(String username);

    void saveUser(User user);

    User findByUsername(String username);
}
