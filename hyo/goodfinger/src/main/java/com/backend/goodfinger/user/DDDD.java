package com.backend.goodfinger.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DDDD {
    Logger logger = LoggerFactory.getLogger(DDDD.class);

    @Autowired
    UserRepository userRepository;

    public void find () throws Exception {
        logger.info(userRepository.findByName("hyo1").toString());
//        logger.info(userRepository.findById("hyo2").toString());
    }

    public void insert() throws Exception {

    }

    public void update() throws Exception {

    }

    public void delete() throws Exception {

    }
}
