package com.uren.extranet.api.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.uren.extranet.api.model.Users;

public interface UserRepository extends MongoRepository<Users, Integer> {
}