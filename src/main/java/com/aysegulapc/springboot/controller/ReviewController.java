package com.aysegulapc.springboot.controller;

import com.aysegulapc.springboot.converter.ReviewConverter;
import com.aysegulapc.springboot.dto.ProductReviewDetailsDto;
import com.aysegulapc.springboot.entity.ProductReview;
import com.aysegulapc.springboot.entityService.ReviewEntityService;
import com.aysegulapc.springboot.exception.ReviewNotFoundException;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewEntityService reviewEntityService;

    @GetMapping("/users/{userId}")
    public MappingJacksonValue findAllReviewByUserId(@PathVariable Long userId) {
        List<ProductReview> productReviewList = reviewEntityService.findAllProductReviewByUserId(userId);
        if(productReviewList.size() == 0) {
            throw new ReviewNotFoundException(userId + " ID'li kullanıcı henüz bir yorum yapmamıştır.");
        }
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "review", "reviewDate");
        SimpleFilterProvider filters = new SimpleFilterProvider().addFilter("ReviewFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(productReviewList);
        mapping.setFilters(filters);
        return mapping;
    }

    @GetMapping("/products/{productId}")
    public List<ProductReviewDetailsDto> findAllReviewByProductId(@PathVariable Long productId) {
        List<ProductReview> productReviewList = reviewEntityService.findProductReviewByProductId(productId);
        if(productReviewList.size() == 0) {
            throw new ReviewNotFoundException(productId + " ID'li ürüne henüz bir yorum yapılmamıştır.");
        }
        List<ProductReviewDetailsDto> productReviewDetailsDtoList = ReviewConverter.INSTANCE.convertReviewListToProductReviewDetailsDtoList(productReviewList);
        return productReviewDetailsDtoList;
    }

    @PostMapping("")
    public ResponseEntity<Object> save(@RequestBody ProductReviewDetailsDto productReviewDetailsDto) {
        ProductReview productReview = ReviewConverter.INSTANCE.convertProductReviewDtoToProductReview(productReviewDetailsDto);
        if(productReview.getProduct().getId() == null || productReview.getUser().getId() == null) {
            productReview.setProduct(null);
            productReview.setUser(null);
        }
        productReview = reviewEntityService.save(productReview);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(productReview.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {
        ProductReview review = reviewEntityService.findById(id);
        if(review == null) {
            throw new ReviewNotFoundException("Yorum bulunamadı.");
        }
        reviewEntityService.deleteById(id);
    }
}
