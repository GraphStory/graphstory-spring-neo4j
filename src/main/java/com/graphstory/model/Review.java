package com.graphstory.model;

import com.graphstory.util.RelationshipType;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Relationship;

public class Review {

    @Id
    @GeneratedValue
    @Getter @Setter private Long id;
    @Getter @Setter private String reviewId;
    @Getter @Setter private String reviewDate;
    @Getter @Setter private String customer;
    @Getter @Setter private int rating;
    @Getter @Setter private int votes;
    @Getter @Setter private int helpful;


    @Relationship(type = RelationshipType.REVIEW_CREATED_BY_USER, direction = Relationship.INCOMING)
    @Getter @Setter private User user;

}
