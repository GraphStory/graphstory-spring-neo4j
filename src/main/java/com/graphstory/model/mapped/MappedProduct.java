package com.graphstory.model.mapped;

import com.graphstory.model.Category;
import com.graphstory.model.Location;
import com.graphstory.model.Product;
import com.graphstory.model.Review;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.Set;

@QueryResult
public class MappedProduct {
    @Getter @Setter Product product;
    @Getter @Setter Set<Category> categories;
    @Getter @Setter Set<Review> reviews;
    @Getter @Setter Set<Location> locations;
    @Getter @Setter Set<Product> similar;

}