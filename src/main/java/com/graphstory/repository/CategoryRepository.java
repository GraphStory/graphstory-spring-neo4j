package com.graphstory.repository;

import com.graphstory.model.Category;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CategoryRepository extends Neo4jRepository<Category,Long> {



    // autocomplete categories based on search
}
