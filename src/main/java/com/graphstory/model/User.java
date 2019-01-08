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
    @Getter	@Setter	private String zip;
    @Getter	@Setter	private Double latitude;
    @Getter	@Setter	private Double longitude;

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

    @Relationship(type = RelationshipType.LIKED_STATUS)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty(hidden = true)
    @Getter @Setter private Set<Status> likedStatus = new HashSet<>();

    @Relationship(type = RelationshipType.LIKED_PRODUCT)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty(hidden = true)
    @Getter @Setter private Set<Product> likedProducts= new HashSet<>();

    @Relationship(type = RelationshipType.CLICKED)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty(hidden = true)
    @Getter @Setter private Set<Product> clicked= new HashSet<>();

    @Relationship(type = RelationshipType.VIEWED)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty(hidden = true)
    @Getter @Setter private Set<Location> viewedLocations= new HashSet<>();

    @Relationship(type = RelationshipType.MADE_PURCHASE)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty(hidden = true)
    @Getter @Setter private Set<Purchase> purchases = new HashSet<>();

    @Relationship(type = RelationshipType.SEARCHED_FOR_CATEORY)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty(hidden = true)
    @Getter @Setter private Set<Category> categories= new HashSet<>();

    //TODO
    @Relationship(type = RelationshipType.USER_SEARCHED_WORD)
    @ApiModelProperty(hidden = true)
    @Getter @Setter private Set<Word> searchedWords;

    //TODO
    @Relationship(type = RelationshipType.USER_TAGGED_WORD)
    @ApiModelProperty(hidden = true)
    @Getter @Setter private Set<Word> taggedWords;

    public void addFollower(User o) { this.followers.add(o); }

    public void removeFollower(User o) { this.followers.remove(o);}

    public void likeStatus(Status o) {
        this.likedStatus.add(o);
    }

    public void unlikeStatus(Status o) {
        this.likedStatus.remove(o);
    }

    public void likeProduct(Product o) {
        this.likedProducts.add(o);
    }

    public void unlikeProduct(Product o) { this.likedProducts.remove(o); }

    public void clicked(Product o) { this.clicked.add(o) ; }

    public void viewed(Location o) { this.viewedLocations.add(o) ; }

    public void searched(Category o) { this.categories.add(o) ; }
}