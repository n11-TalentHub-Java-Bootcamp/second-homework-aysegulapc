package com.aysegulapc.springboot.converter;

import com.aysegulapc.springboot.dto.ProductReviewDetailsDto;
import com.aysegulapc.springboot.entity.ProductReview;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewConverter {
    ReviewConverter INSTANCE = Mappers.getMapper(ReviewConverter.class);

    @Mapping(target = "userName", source = "user.userName")
    @Mapping(target = "productName", source = "product.name")
    List<ProductReviewDetailsDto> convertReviewListToProductReviewDetailsDtoList(List<ProductReview> reviewList);

    @Mapping(source = "userName", target = "user.userName")
    @Mapping(source = "productName", target = "product.name")
    ProductReview convertProductReviewDtoToProductReview(ProductReviewDetailsDto productReviewDetailsDto);

}
