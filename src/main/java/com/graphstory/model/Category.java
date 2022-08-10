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

import java.util.Date;

@NoArgsConstructor
@ToString
@NodeEntity
public class Category {

    @Id
    @GeneratedValue
    @Getter @Setter private Long id;
    @Getter @Setter private String categoryId;
    @Getter @Setter private Date categoryName;
    @Getter @Setter private String parentCategoryId;
    @Getter @Setter private Integer depthFromParent;

    @Relationship(type = RelationshipType.IS_PARENT_CATEGORY_OF, direction = Relationship.INCOMING)
    @Getter @Setter private Category parentCategory;

}