package com.graphstory.model;

import com.graphstory.util.RelationshipType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.neo4j.ogm.annotation.*;

import java.util.Set;

@NoArgsConstructor
@ToString
@NodeEntity
public class Purchase {

    @Id
    @GeneratedValue
    @Getter @Setter private Long id;
    @Getter @Setter private String purchaseId;
    @Getter @Setter Long timestamp;

    @Relationship(type = RelationshipType.PURCHASE_CONTAINS)
    @Getter @Setter private Set<Product> products;

}
