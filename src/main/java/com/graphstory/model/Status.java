package com.graphstory.model;

import com.graphstory.util.RelationshipType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.neo4j.ogm.annotation.*;

@NoArgsConstructor
@ToString
@NodeEntity
public class Status {

    @Id
    @GeneratedValue
    @ApiModelProperty(hidden = true)
    @Getter @Setter private Long id;
    @Getter @Setter @Index private String statusId;
    @ApiModelProperty(hidden = true)
    @Getter @Setter @Index private String userIdStatusId;
    @Getter @Setter private String statusText;
    @Getter @Setter private String tagstr;
    @ApiModelProperty(hidden = true)
    @Getter @Setter private Long timestamp;

    @Transient
    private  String timestampDate;
    public String getTimestampDate() {
        if (timestamp != null)
        {
            timestampDate=GraphStory.convertLongtoStrDate(timestamp);
        }
        return timestampDate;
    }

    @Relationship(type = RelationshipType.CURRENT_STATUS, direction = Relationship.INCOMING)
    @ApiModelProperty(hidden = true)
    @Getter @Setter private User user;

    @Relationship(type = RelationshipType.NEXT_STATUS)
    @ApiModelProperty(hidden = true)
    @Getter @Setter private Status next;
}
/*

    // how do i get the statuses of the user and user's followers and order them by date DESC (aka newest first, then next)
    MATCH (u:User {username: {u} })
    WITH u
    MATCH (u)-[:FOLLOWS*0..1]->(f)
    WITH DISTINCT f,u
    MATCH (f)-[:CURRENT_STATUS]-(cs:Status)->[:NEXT_STATUS*0..]->(ns)
    RETURN the things as a mapped object
    ORDER BY p.timestamp DESC
    SKIP

*/