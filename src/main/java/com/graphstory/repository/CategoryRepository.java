package com.graphstory.repository;

import com.graphstory.model.Category;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.LinkedList;

public interface CategoryRepository extends Neo4jRepository<Category,Long> {

    // autocomplete categories based on search
    @Query("MATCH (c:Category) WHERE lower(c.categoryName)=~lower({s})  RETURN c.categoryName ORDER BY c.categoryName")
    LinkedList<String> searchForCategory(@Param("s") String s);


}
