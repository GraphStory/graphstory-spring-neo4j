package com.graphstory.model.mapped;


import com.graphstory.model.GraphStory;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.Transient;
import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class MappedStatus {

    @Getter @Setter String statusId;
    @Getter @Setter String statusText;
    @Getter @Setter Long timestamp;
    @Getter @Setter String username;
    @Getter @Setter Boolean owner;


    @Transient
    private  String timestampStr;
    public String getTimestampStr() {
        if (timestamp != null)
        {
            timestampStr=GraphStory.convertLongtoStrJustDate(timestamp);
        }
        return timestampStr;
    }
}
