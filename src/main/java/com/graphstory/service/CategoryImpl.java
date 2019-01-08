package com.graphstory.service;

import com.graphstory.repository.CategoryRepository;
import com.graphstory.util.model.EntityNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class CategoryImpl extends GraphStoryService implements CategoryService {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CategoryRepository categoryRepository;

    public LinkedList<String> search(String s){
        if(StringUtils.isEmpty(s)){
            throw new EntityNotFoundException("No search string provided");
        }else{
            s = s+".*";
        }

        logger.info(s);

        return  categoryRepository.searchForCategory(s);

    }
}
