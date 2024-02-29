package com.example.blockingapi.user.neo4j;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface Neo4jUserRepository extends Neo4jRepository<Neo4jUser, Long> {
    @Query("MATCH (u:User)-[:FRIENDS_WITH]->(f) WHERE u.name = $name RETURN f")
    List<Neo4jUser> findFriendsOfUser(String name);
}