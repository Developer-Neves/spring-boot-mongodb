package com.jdnevesti.mongodb.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jdnevesti.mongodb.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
