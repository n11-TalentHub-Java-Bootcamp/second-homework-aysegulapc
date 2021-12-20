package com.aysegulapc.springboot.entityService;

import com.aysegulapc.springboot.dao.ProductReviewDao;
import com.aysegulapc.springboot.entity.ProductReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewEntityService {

    @Autowired
    private ProductReviewDao productReviewDao;

    public List<ProductReview> findAll(){
        return (List<ProductReview>) productReviewDao.findAll();
    }

    public ProductReview findById(Long id) {
        Optional<ProductReview> optionalReview = productReviewDao.findById(id);

        ProductReview review = null;
        if(optionalReview.isPresent()) {
            review = optionalReview.get();
        }
        return review;
    }

    public ProductReview save(ProductReview review) {
        review = productReviewDao.save(review);
        return review;
    }

    public void delete(ProductReview review) {
        productReviewDao.delete(review);
    }

    public void deleteById(Long id) {
        productReviewDao.deleteById(id);
    }

    public List<ProductReview> findAllProductReviewByUserId(Long userId) {
        return productReviewDao.findProductReviewByUserId(userId);
    }

    public List<ProductReview> findProductReviewByProductId(Long productId) {
        return productReviewDao.findProductReviewByProductId(productId);
    }
}
