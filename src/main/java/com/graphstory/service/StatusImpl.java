package com.graphstory.service;

import com.graphstory.model.Status;
import com.graphstory.model.User;
import com.graphstory.model.mapped.MappedStatus;
import com.graphstory.repository.StatusRepository;
import com.graphstory.repository.UserRepository;
import com.graphstory.util.model.EntityNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StatusImpl extends GraphStoryService implements StatusService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRepository userRepository;

    @Autowired
    StatusRepository statusRepository;

    public List<MappedStatus> getStatuses(String userId){

        return statusRepository.getStatus(userId);
    }

    // create Status
    @Transactional
    public Status create(String statusText, String tagstr, String userId){

        if(StringUtils.isEmpty(statusText)){
            throw new EntityNotFoundException("No status text provided");
        }

        User user = userRepository.findByUserId(userId);

        if(user == null){
            throw new EntityNotFoundException("No status text provided");
        }

        Status currentStatus = statusRepository.currentStatus(userId);

        Status status = new Status();
        status.setStatusId(uuidGen());
        status.setUserIdStatusId(StringUtils.join(userId,status.getStatusId()));
        status.setStatusText(statusText);
        status.setTagstr(tagstr);
        status.setTimestamp(dateAsLong());

        try{

            // has the user posted content before? if so, then get the "currentStatus", remove that REL, set THIS status as CURRENT_STATUS by creating REL with user,
            // then make currentStatus the "next" status and save it all
            if(currentStatus!=null){
                statusRepository.removeCurrentStatusRel(userId);
                status.setUser(user);
                status.setNext(currentStatus);
                status = statusRepository.save(status);
            }
            // or is the first content post for this user?
            else {
                status.setUser(user);
                status = statusRepository.save(status);
            }

        }
        catch(Exception e){
            logger.error(e.toString());
        }

        return status;
    }

    @Transactional
    public Status update(String statusText, String tagstr, String statusId, String userId) {

        Status status = statusRepository.findByUserIdStatusId(StringUtils.join(userId,statusId));

        if(status==null){
            throw new EntityNotFoundException("No status matches id provided");
        }

        status.setStatusText(statusText);
        status.setTagstr(tagstr);

        statusRepository.save(status);

        return status;
    }

    @Transactional
    public void delete(String statusId, String userId){
        statusRepository.deleteStatus(statusId,userId);
    }
}
