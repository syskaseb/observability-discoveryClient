package com.example.discoveryclient.user.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongodbUserRepository extends MongoRepository<MongodbUser, String> {
}