package com.raks.psp.example02.api;

import com.raks.psp.example02.data.User;
import com.raks.psp.example02.data.UserRepository;
import com.raks.psp.example02.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserRepository  _userRepository;
    private final PasswordEncoder _passwordEncoder;
    private final JwtService      _jwtService;

    @Autowired
    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        _userRepository  = userRepository;
        _passwordEncoder = passwordEncoder;
        _jwtService      = jwtService;
    }

    @PostMapping("/user")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest body) {
        User user = _userRepository.findByUsername(body.username);
        if (user == null)
            return ResponseEntity.notFound().build();

        if (!_passwordEncoder.matches(body.password, user.get_encryptedPassword()))
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(_jwtService.createJwt(user));
    }

}