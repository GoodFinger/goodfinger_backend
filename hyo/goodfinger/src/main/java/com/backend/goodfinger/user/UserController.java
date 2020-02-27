package com.backend.goodfinger.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    DDDD dddd;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/test")
    public String restTest() {
        System.out.println("dddd");
        return "hello";
    }

    @GetMapping("/connectDB")
    public String connectDB() throws Exception {
        logger.info("connectDB started.");

        dddd.find();

        logger.info("connectDB started.");
        return "loglog";
    }
}
