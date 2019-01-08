package com.graphstory.service;

import com.graphstory.model.Status;
import com.graphstory.model.mapped.MappedStatus;

import java.util.List;

public interface StatusService {

    // create a status
    Status create(String statusText, String tagstr, String userId);

    // update a status
    Status update(String statusText, String tagstr, String statusId, String userId);

    // delete a status
    void delete(String statusId, String userId);

    // get the statuses of user and connected users
    List<MappedStatus> getStatuses(String userId);

    // user liked something
    void like(String statusId, String userId);
    // user unliked something
    void unlike(String statusId, String userId);


}
