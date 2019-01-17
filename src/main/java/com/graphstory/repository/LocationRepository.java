package com.graphstory.repository;

import com.graphstory.model.Location;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface LocationRepository extends Neo4jRepository<Location,Long> {


    Location findByLocationId(String locationId);
    Location findByZip(String zip);

    @Query(" MATCH (l:Location {locationId: {locationId} }) " +
            "RETURN l")
    Location getByLocationId(@Param("locationId") String locationId);

    @Query("MATCH  (u:User {userId: {userId} }), (l:Location {locationId: {locationId} }) " +
            "RETURN EXISTS( (u)-[:VIEWED]-(l) ) ")
    Boolean hasBeenViewed(@Param("userId") String userId, @Param("locationId") String locationId);


    // location with products
    /*
    MATCH (l:Location)
    WHERE (l)-[:LOCATION_HAS_PRODUCT]->()
    RETURN l.zip as zipsThatHaveProducts
     */


    //TODO list of locations with 50 miles
    /*
    MATCH (l:Location {zip:"38119"})
    WITH toFloat(l.latitude) as lat, toFloat(l.longitude) as lon
    CALL spatial.withinDistance("geom",{latitude:lat,longitude:lon},50.0)
    YIELD node as location
    RETURN location
     */



    //TODO location with products clicked by user
    // show on main loc page and on loc id page

    //TODO location with products liked by user
    // show on main loc page and on loc id page

    //TODO stores closest to me and what products do they have, do they have products I liked, do they have products I viewd
    // this one above can help with inventory



}
