package com.graphstory.service;

import com.graphstory.model.User;
import com.graphstory.model.Word;

import java.util.Set;

public interface UserService {

    // create user
    User create(String username);

    // update user
    User update(String firstname,String lastname, String userId);

    // find by userId
    User findByUserId(String userId);

    // find by username
    User findByUsername(String username);

    // follow a user
    void follow(String userId, String userIdToFollow);

    // unfollow a user
    void unfollow(String userId, String userIdToUnFollow);

    // collection of who follows user
    Set<User> whoIsFollowingUser(String userId);

    // collection of who user follows
    Set<User> whoIsUserFollowing(String userId);

    void addZip(String userId, String zip);

    //TODO
    // words connected to a user (searches and tags)
    Set<Word> userWords(String userId);

    //TODO
    // tags connected to a user
    Set<Word> userTags(String userId);

    //TODO
    // searches connected to a user
    Set<Word> userSearches(String userId);
}
