package com.backend.goodfinger.login;

import com.backend.goodfinger.auth.JwtTokenProvider;
import com.backend.goodfinger.user.User;
import com.backend.goodfinger.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;

@RequiredArgsConstructor
@RestController
public class SignController {
    private Logger logger = LoggerFactory.getLogger(SignController.class);

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody User user) throws Exception {
        logger.debug("signUp started.");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(user.isBoss() ? new ArrayList<>(Arrays.asList("boss")) : new ArrayList<>(Arrays.asList("user")));
        userRepository.insert(user);

        logger.debug("signUp ended.");

        return new ResponseEntity<>(jwtTokenProvider.createToken(user.getEmail(), user.getRoles()), HttpStatus.CREATED);
    }

    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@RequestBody User user) throws Exception {
        User member = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "can't not found :: " + user.getEmail()));

        if (!passwordEncoder.matches(user.getPassword(), member.getPassword())) {
            new ResponseStatusException(HttpStatus.UNAUTHORIZED, "no matching userInfo");
        }

        return new ResponseEntity<>(jwtTokenProvider.createToken(member.getEmail(), member.getRoles()), HttpStatus.OK);
    }

    @PostMapping("/logOut")
    public ResponseEntity<?> logOut(@RequestBody User user) throws Exception {
        //TODO 들어오면 blacklist에 만료된 목록 넣어두고 체크

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
