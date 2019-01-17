package com.graphstory.service;

import com.graphstory.model.Location;

public interface LocationService {

    // user clicked product
    void viewed(String locationId, String userId);

    Location getloc(String locationId);

    //TODO:
    // add product to location

    //TODO:
    // add status to an existing location

    //TODO:
    // add status to an new location

    //TODO: add a new locations using some geo service
}
