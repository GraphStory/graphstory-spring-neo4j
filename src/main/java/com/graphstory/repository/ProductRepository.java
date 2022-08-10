package com.graphstory.repository;

import com.graphstory.model.Product;
import com.graphstory.model.mapped.MappedProduct;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends Neo4jRepository<Product,Long> {


    @Query("MATCH (p:Product {productId: $productId  }) " +
            "RETURN p")
    Product getProductByProductId( @Param("productId") String productId);

    @Query("MATCH (p:Product {productId: $productId  })-[:HAS_BASE_CATEGORY_OF]-(baseCats:Category) " +
            "WITH p,baseCats " +
            "MATCH catpaths=(baseCats)<-[:IS_PARENT_CATEGORY_OF*]-(c:Category) " +
            "WHERE c.depthFromParent=0 " +
            "WITH p,EXTRACT(x IN NODES(catpaths)[..-1] | [x.categoryId,x.categoryName]) AS categoryName, c " +
            "WITH COLLECT(categoryName) as newc,c,  p " +
            "OPTIONAL MATCH (p)-[:PRODUCT_HAS_REVIEW]->(review:Review) " +
            "OPTIONAL MATCH (p)-[:LOCATION_HAS_PRODUCT]->(l) " +
            "OPTIONAL MATCH (p)-[:PRODUCT_SIMILAR_TO_PRODUCT]->(similar) " +
            "RETURN p as product,newc as categories,COLLECT(review) as reviews,Collect(l) as locations, Collect(similar) as similar ")
    MappedProduct getProductByProductIdForView(@Param("productId") String productId);

    @Query("MATCH  (u:User {userId: $userId }), (p:Product {productId: $productId }) " +
            "RETURN EXISTS( (u)-[:LIKED_PRODUCT]->(p) ) ")
    Boolean Isliked(@Param("userId") String userId, @Param("productId") String productId);

    @Query("MATCH  (u:User {userId: $userId }), (p:Product {productId: $productId }) " +
            "RETURN EXISTS( (u)-[:CLICKED]->(p) ) ")
    Boolean hasBeenClicked(@Param("userId") String userId, @Param("productId") String productId);

    @Query("MATCH  (u:User {userId: $userId }), (p:Product {productId: $productId }) " +
            "RETURN EXISTS( (u)-[:CLICKED]->(p) ) ")
    Boolean get(@Param("userId") String userId, @Param("productId") String productId);


    //TODO
    // best selling in same root category
    /*
    MATCH (p:Product {productId: "078510870X"})-[:HAS_BASE_CATEGORY_OF]->(baseCats:Category)
    WITH p, baseCats
    MATCH (otherProducts)-[:HAS_BASE_CATEGORY_OF]->(baseCats:Category)
    WHERE otherProducts.productId <> p.productId
    RETURN otherProducts
    ORDER BY otherProducts.salesrank ASC
    LIMIT 15
     */
    //TODO searched for category in products

    //TODO similar products to products I've clicked on

    //TODO similar products to products I've liked

    //TODO base category to products I've clicked on

    //TODO base category  to products I've liked

    //TODO list of last products clicked

    //TODO purchases by user's connections

    //TODO customers who bought this also bought this things

    //TODO purchases by user's connections' connections

    //TODO purchases by user's connections who are near the user and have activated the same tag as the user



}
