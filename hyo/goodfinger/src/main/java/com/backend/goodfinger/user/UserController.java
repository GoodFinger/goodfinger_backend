package com.backend.goodfinger.user;

import com.backend.goodfinger.auth.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @GetMapping("/{email}")
    public ResponseEntity<?> getUser(@PathVariable String email) throws Exception {
        logger.debug("getUser started.");

        Optional<User> user = userRepository.findByEmail(email);

        if (user == null) {
            return new ResponseEntity<>("user is not exist.", HttpStatus.NOT_FOUND);
        }

        logger.debug("getUser ended.");

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getUsers() throws Exception {
        logger.debug("getUsers started.");

        List<User> users = userRepository.findAll();

        logger.debug("getUsers ended.");

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editUser(@RequestBody User user) throws Exception {
        logger.debug("editUser started. email = " + user.getEmail());

//        userRepository.update(user);

        logger.debug("editUser ended.");

        return new ResponseEntity<>("edit user successfully.", HttpStatus.OK);
    }

    @GetMapping("/corsTest")
    public ResponseEntity<?> corsTest() throws Exception {
        logger.debug("corsTest");
        return new ResponseEntity<>("구우우우웃", HttpStatus.OK);
    }
}
