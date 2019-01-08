package com.graphstory.repository;

import com.graphstory.model.Product;
import com.graphstory.model.mapped.MappedProduct;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends Neo4jRepository<Product,Long> {


    @Query("MATCH (p:Product {productId: {productId}  }) " +
            "RETURN p")
    Product getProductByProductId( @Param("productId") String productId);

    /*
MATCH (p:Product {productId: "078510870X"})-[:HAS_BASE_CATEGORY_OF]->(baseCats:Category)
WITH p,baseCats
MATCH catpaths=(baseCats)<-[:IS_PARENT_CATEGORY_OF*]-(c:Category)
WHERE c.depthFromParent=0
WITH p,EXTRACT(x IN NODES(catpaths)[..-1] | [x.categoryId,x.categoryName]) AS categoryName, c
WITH COLLECT(categoryName) as newc,c,  p
OPTIONAL MATCH (p)-[:PRODUCT_HAS_REVIEW]->(review:Review)
OPTIONAL MATCH (p)-[:LOCATION_HAS_PRODUCT]->(l)
OPTIONAL MATCH (p)-[:PRODUCT_SIMILAR_TO_PRODUCT]->(similar)
OPTIONAL MATCH (prods)-[:PURCHASE_CONTAINS]-(purchase)-[:PURCHASE_CONTAINS]->(p)
RETURn p,newc,COLLECT(review) as reviews,Collect(l) as locations, Collect(similar) as similar,collect(distinct prods) as purchase
MAY NEED TO DO SIMILAR PURCHASES SEPEARELY
    */

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
    // searched for category in products

    // similar products to products I've clicked on

    // similar products to products I've liked

    // base category to products I've clicked on

    // base category  to products I've liked

    // list of last products clicked

    // purchases by user's connections

    // customers who bought this also bought this things

    // purchases by user's connections' connections

    // purchases by user's connections who are near the user and have activated the same tag as the user

    @Query("MATCH (p:Product {productId: {productId}  })-[:HAS_BASE_CATEGORY_OF]-(bc)<-[:IS_PARENT_CATEGORY_OF*0..]-(c) " +
            "RETURN p")
    MappedProduct getProductByProductIdForView(@Param("productId") String productId);

    @Query("MATCH  (u:User {userId: {userId} }), (p:Product {productId: {productId} }) " +
            "RETURN EXISTS( (u)-[:LIKED_PRODUCT]-(p) ) ")
    Boolean Isliked(@Param("userId") String userId, @Param("productId") String productId);

    @Query("MATCH  (u:User {userId: {userId} }), (p:Product {productId: {productId} }) " +
            "RETURN EXISTS( (u)-[:CLICKED]-(p) ) ")
    Boolean hasBeenClicked(@Param("userId") String userId, @Param("productId") String productId);

}
