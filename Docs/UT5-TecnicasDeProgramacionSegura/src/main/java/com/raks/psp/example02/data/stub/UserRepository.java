package com.raks.psp.example02.data.stub;

import com.raks.psp.example02.data.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository implements com.raks.psp.example02.data.UserRepository {
    private Map<String, User> _usersByName = new HashMap<>();

    public UserRepository(PasswordEncoder passwordEncoder) {
        saveUser(new User("elpepe", passwordEncoder.encode("pass1234")));
    }

    @Override
    public Boolean exists(String username) {
        return _usersByName.containsKey(username);
    }

    @Override
    public void saveUser(User user) {
        _usersByName.putIfAbsent(user.get_username(), user);
    }

    @Override
    public User findByUsername(String username) {
        return _usersByName.get(username);
    }
}
