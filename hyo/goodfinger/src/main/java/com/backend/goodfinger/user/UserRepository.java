package com.backend.goodfinger.user;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends MongoRepository <User, Object> {
    @Override
    List<User> findAll(Sort sort);

    User findById(String id);

    List<User> findByName(String name);
}
