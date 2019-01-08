package com.graphstory.repository;

import com.graphstory.model.Status;
import com.graphstory.model.mapped.MappedStatus;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatusRepository extends Neo4jRepository<Status,Long> {

    Status findByUserIdStatusId(String userIdStatusId);

    Status findByStatusId(String statusId);

    @Query("MATCH (u:User {userId: {userId}})-[:CURRENT_STATUS]-(c:Status) RETURN c")
    Status currentStatus(@Param("userId") String userId);

    @Query("MATCH (u:User {userId: {userId} })-[r:CURRENT_STATUS]-(c) DELETE r")
    void removeCurrentStatusRel(@Param("userId") String userId);

    @Query(" MATCH (u:User { userId: {userId}}) " +
            " WITH u " +
            " MATCH (u)-[:CURRENT_STATUS]->(c:Status {statusId: {statusId} })-[:NEXT_STATUS]->(nextStatus) " +
            " WHERE nextStatus is not null " +
            " MERGE (u)-[:CURRENT_STATUS]->(nextStatus) " +
            " WITH count(nextStatus) as cnt " +
            " MATCH (before)-[:NEXT_STATUS]->(c:Status { statusId: {statusId} })-[:NEXT_STATUS]->(after) " +
            " WHERE before is not null AND after is not null " +
            " MERGE (before)-[:NEXT_STATUS]->(after) " +
            " WITH count(before) as cnt " +
            " MATCH (c:Status { statusId: {statusId} })-[r]-() " +
            " DELETE c, r ")
    void deleteStatus(@Param("statusId") String statusId,@Param("userId") String userId);

    @Query(" MATCH (u:User { userId: {userId}}) " +
            "    WITH u " +
            "    MATCH (u)-[:FOLLOWS*0..1]->(f) " +
            "    WITH DISTINCT f,u " +
            "    MATCH (f)-[:CURRENT_STATUS]->(ls)-[:NEXT_STATUS*0..3]->(s) " +
            "    WITH f,s,u" +
            "    RETURN s.statusId as statusId, s.statusText as statusText, s.timestamp as timestamp, f.username as username, f=u as owner, EXISTS((u)-[:LIKED_STATUS]-(s)) as liked " +
            "    ORDER BY s.timestamp DESC ")
    List<MappedStatus> getStatus(@Param("userId") String userId);

    @Query("MATCH  (u:User {userId: {userId} }), (s:Status {statusId: {statusId} }) " +
            "RETURN EXISTS( (u)-[:LIKED_STATUS]-(s) ) ")
    Boolean Isliked(@Param("userId") String userId, @Param("statusId") String statusId);



}
