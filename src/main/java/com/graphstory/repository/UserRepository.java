package com.graphstory.repository;

import com.graphstory.model.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface UserRepository extends Neo4jRepository<User,Long> {

    User findByUserId(String userId);

    User findByUsername(String username);

    @Query("MATCH (u:User {userId: {userId}})<-[:FOLLOWS]-(followers) RETURN followers LIMIT 25")
    Set<User> followers(@Param("userId") String userId);

    @Query("MATCH (u:User {userId: {userId}})-[:FOLLOWS]->(following) RETURN following LIMIT 25")
    Set<User> following(@Param("userId") String userId);

}
