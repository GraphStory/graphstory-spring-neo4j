package com.graphstory.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.graphstory.util.RelationshipType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.neo4j.ogm.annotation.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@ToString
@NodeEntity
public class User{


    @Id
    @GeneratedValue
    @Getter @Setter
    @ApiModelProperty(hidden = true)
    private Long id;
    @Getter @Setter @Index
    @ApiModelProperty(hidden = true)
    private String userId;
    @Getter @Setter
    @ApiModelProperty(hidden = true)
    private String customerId;
    @Getter @Setter private String username;
    @Getter @Setter private String firstname;
    @Getter @Setter private String lastname;
    @Getter @Setter
    @ApiModelProperty(hidden = true)
    private Long lastModified;

    @Transient
    private  String lastModifiedDate;
    public String getLastModifiedDate() {
        if (lastModified != null)
        {
            lastModifiedDate=GraphStory.convertLongtoStrDate(lastModified);
        }
        return lastModifiedDate;
    }

    @Relationship(type = RelationshipType.FOLLOWS, direction = Relationship.INCOMING)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty(hidden = true)
    @Getter @Setter private Set<User> followers = new HashSet<>();

    @Relationship(type = RelationshipType.MADE_PURCHASE)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty(hidden = true)
    @Getter @Setter private Set<Purchase> purchases;

    @Relationship(type = RelationshipType.CLICKED)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty(hidden = true)
    @Getter @Setter private Set<Product> clicked;

    @Relationship(type = RelationshipType.LIKED)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty(hidden = true)
    @Getter @Setter private Set<Product> likedProducts;

    @Relationship(type = RelationshipType.VIEWED)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty(hidden = true)
    @Getter @Setter private Set<Location> viewedLocations;

    @Relationship(type = RelationshipType.LIKED)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty(hidden = true)
    @Getter @Setter private Set<Status> likedStatus;

    @Relationship(type = RelationshipType.SEARCHED_FOR)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty(hidden = true)
    @Getter @Setter private Set<Word> searchedWords;

    public void addFollower(User u) {
        this.followers.add(u);
    }

    public void removeFollower(User u) {
        this.followers.remove(u);
    }
}