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


    // get the statuses based on the a word or words of user and connected users

    // user searched for something

    // user clicked something

    // user liked something
}
