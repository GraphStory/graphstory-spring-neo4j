package com.graphstory.service;

import com.graphstory.model.Location;
import com.graphstory.model.User;
import com.graphstory.repository.LocationRepository;
import com.graphstory.repository.UserRepository;
import com.graphstory.util.model.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LocationImpl extends GraphStoryService implements LocationService {


    @Autowired
    LocationRepository locationRepository;

    @Autowired
    UserRepository userRepository;

    private User getUser(String userId){
        // find the user that's logged in
        User user = userRepository.findByUserId(userId);

        if (user == null) {
            throw new EntityNotFoundException("No user matches id provided");
        }

        return user;
    }

    private Location getLocation(String locationId){
        // find the location
        Location location = locationRepository.findByLocationId(locationId);

        if (location == null) {
            throw new EntityNotFoundException("No location matches id provided");
        }

        return location;
    }

    public void viewed(String locationId, String userId){
        // make sure it doesn't exist
        if(!locationRepository.hasBeenViewed(userId,locationId)){
            Location location = getLocation(locationId);
            User user   = getUser(userId);
            user.viewed(location);
            // save the user
            userRepository.save(user);
        }

    }
}
