package com.graphstory.repository;

import com.graphstory.model.Location;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface LocationRepository extends Neo4jRepository<Location,Long> {


    Location findByLocationId(String locationId);
    Location findByZip(String zip);

    // list of locations with 50 miles

    // location with products

    // location with products clicked by user
    // show on main loc page and on loc id page

    // location with products liked by user
    // show on main loc page and on loc id page

    //stores closest to me and what products do they have, do they have products I liked, do they have products I viewd
    // this one above can help with inventory




    @Query("MATCH  (u:User {userId: {userId} }), (l:Location {locationId: {locationId} }) " +
            "RETURN EXISTS( (u)-[:VIEWED]-(l) ) ")
    Boolean hasBeenViewed(@Param("userId") String userId, @Param("locationId") String locationId);
}
