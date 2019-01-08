package com.graphstory.service;

import com.graphstory.model.Location;
import com.graphstory.model.User;
import com.graphstory.model.Word;
import com.graphstory.repository.LocationRepository;
import com.graphstory.repository.UserRepository;
import com.graphstory.util.model.EntityNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


@Service
public class UserImpl extends GraphStoryService implements UserService  {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRepository userRepository;

    @Autowired
    LocationRepository locationRepository;

    // create user
    public User create(String username){

        if(StringUtils.isEmpty(username)){
            throw new EntityNotFoundException("No username provided");
        }

        User user = new User();

        try{

            user.setUsername(username);
            user.setUserId(uuidGen());
            user.setLastModified(dateAsLong());
            // we populate this because we imported users/customers from external data that had different customerId
            // when creating new users, we just use the userId
            user.setCustomerId(user.getUserId());

            user = userRepository.save(user);
        }
        catch(Exception e){
            logger.error(e.toString());
        }

        return user;
    }

    // update user
    public User update(String firstname,String lastname, String userId){

        User obj = userRepository.findByUserId(userId);

        if (obj == null) {
            throw new EntityNotFoundException("No user matches id provided");
        }

        try{
            obj.setFirstname(firstname);
            obj.setLastname(lastname);
            obj.setLastModified(dateAsLong());
            obj = userRepository.save(obj);
        }
        catch(Exception e){
            logger.error(e.toString());
        }

        return obj;
    }

    // find by userId
    public User findByUserId(String userId){

        User obj = userRepository.findByUserId(userId);

        if (obj == null) {
            throw new EntityNotFoundException("No user matches id provided");
        }

        return obj;
    }

    private Location getLocationByZip(String zip){
        // find the location
        Location location = locationRepository.findByZip(zip);

        if (location == null) {
            throw new EntityNotFoundException("No zip matches zip provided");
        }

        return location;
    }

    // find by username
    public User findByUsername(String username){

        User obj = userRepository.findByUsername(username);

        if (obj == null) {
            throw new EntityNotFoundException("No user matches username provided");
        }

        return obj;

    }

    // collection of who follows user
    public Set<User> whoIsFollowingUser(String userId){
        return userRepository.followers(userId);
    }

    // collection of who user follows
    public Set<User> whoIsUserFollowing(String userId){
        return userRepository.following(userId);
    }

    // follow a user
    @Transactional
    public void follow(String userId, String userIdToFollow){

        // find the user that's logged in
        User user = findByUserId(userId);

        // find the user that the user that's logged in wants to follow
        User userBeingFollowed = userRepository.findByUserId(userIdToFollow);

        if (userBeingFollowed == null) {
            throw new EntityNotFoundException("No user matches userIdToFollow provided");
        }

        // add that new follower to their follower collection
        userBeingFollowed.addFollower(user);

        // save the userBeingFollowed, so they now have a new follower
        userRepository.save(userBeingFollowed);

    }

    // unfollow a user
    @Transactional
    public void unfollow(String userId, String userIdToUnFollow){

        // find the user that's logged in
        User user = findByUserId(userId);

        // find the user that the user that's logged in wants to UNfollow
        User userBeingUnFollowed = userRepository.findByUserId(userIdToUnFollow);

        if (userBeingUnFollowed == null) {
            throw new EntityNotFoundException("No user matches userIdToUnFollow provided");
        }

        // remove that existing follower from their follower collection
        userBeingUnFollowed.removeFollower(user);

        // save the userBeingUnFollowed, so they now that follower is removed
        userRepository.save(userBeingUnFollowed);
    }

    // add zip code to a user
    @Transactional
    public void addZip(String userId, String zip){

        // find the user that's logged in
        User user = findByUserId(userId);
        Location location = getLocationByZip(zip);

        user.setZip(location.getZip());
        user.setLongitude(location.getLongitude());
        user.setLatitude(location.getLatitude());

        // save the userBeingUnFollowed, so they now that follower is removed
        userRepository.save(user);
    }


    //TODO
    // words connected to a user (searches and tags)
    public Set<Word> userWords(String userId){
        return null;
    }

    //TODO
    // tags connected to a user
    public Set<Word> userTags(String userId){
        return null;
    }

    //TODO
    // searches connected to a user
    public Set<Word> userSearches(String userId){
        return null;
    }
}
