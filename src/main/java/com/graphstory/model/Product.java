package com.graphstory.model;

import com.graphstory.util.RelationshipType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

@NoArgsConstructor
@ToString
@NodeEntity
public class Product {

    @Id
    @GeneratedValue
    @Getter @Setter private Long id;
    @Getter @Setter private String productId;
    @Getter @Setter private String sku;
    @Getter @Setter private String title;
    @Getter @Setter private String group;
    @Getter @Setter private Integer salesrank;

    @Relationship(type = RelationshipType.HAS_BASE_CATEGORY_OF)
    @Getter @Setter private Set<Category> categories;

    @Relationship(type = RelationshipType.PRODUCT_SIMILAR_TO_PRODUCT)
    @Getter @Setter private Set<Product> similarProducts;

    @Relationship(type = RelationshipType.PRODUCT_HAS_REVIEW)
    @Getter @Setter private Set<Review> reviews;

}
