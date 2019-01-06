package com.graphstory.model;

import com.graphstory.util.RelationshipType;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

public class Location {

    @Id
    @GeneratedValue
    @Getter @Setter private Long id;
    @Getter @Setter private String locationId;
    @Getter	@Setter	private String business;
    @Getter	@Setter	private String city;
    @Getter	@Setter	private String state;
    @Getter	@Setter	private String zip;
    @Getter	@Setter	private Double latitude;
    @Getter	@Setter	private Double longitude;


    @Relationship(type = RelationshipType.LOCATION_HAS_PRODUCT)
    @Getter @Setter private Set<Product> products;



}
