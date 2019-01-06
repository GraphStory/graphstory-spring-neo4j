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
public class Word {

    @Id
    @GeneratedValue
    @Getter @Setter private Long id;
    @Getter @Setter private String wordId;
    @Getter @Setter private String word;
    @Index(unique=true)
    @Getter @Setter private String wordLowerCase;

    @Relationship(type = RelationshipType.IS_SYNONYM_OF, direction = Relationship.INCOMING)
    @Getter @Setter private Set<Word> synonyms;

}