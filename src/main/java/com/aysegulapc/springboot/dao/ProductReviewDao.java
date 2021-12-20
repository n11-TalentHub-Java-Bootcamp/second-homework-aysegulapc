package com.aysegulapc.springboot.dao;

import com.aysegulapc.springboot.entity.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductReviewDao extends JpaRepository<ProductReview, Long> {

    @Query("select review from ProductReview review where review.user.id = :userId")
    List<ProductReview> findProductReviewByUserId(Long userId);

    @Query("select review from ProductReview review where review.product.id = :productId")
    List<ProductReview> findProductReviewByProductId(Long productId);
}
