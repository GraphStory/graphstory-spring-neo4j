package com.graphstory.service;

import com.graphstory.model.mapped.MappedProduct;

public interface ProductService {

    // user searched for something

    // user clicked product
    void click(String productId, String userId);

    // user liked product
    void like(String productId, String userId);
    // user unliked product
    void unlike(String productId, String userId);

    // get product

    MappedProduct getProductForView(String productId);
}
