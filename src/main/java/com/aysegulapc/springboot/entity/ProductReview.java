package com.aysegulapc.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * id
 * yorum
 * yorumTarihi
 * urunId
 * kullaniciId
 * */
@Entity
@Table(name = "PRODUCTREVIEW")
@JsonFilter("ReviewFilter")
@Component
@JsonIgnoreProperties({"hibernateLazyInitializer", "user", "product"})
public class ProductReview implements Serializable {
    @SequenceGenerator(name = "generator", sequenceName = "FIRST_ENTITY_ID_SEQ")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "REVIEW", length = 500)
    private String review;

    @Column(name = "REVIEW_DATE")
    private Date review_date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRODUCT_ID",
            foreignKey = @ForeignKey(name = "FK_PRODUCT_ID"))
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID",
            foreignKey = @ForeignKey(name = "FK_PRODUCT_REVIEW_USER_ID"))
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Date getReviewDate() {
        return review_date;
    }

    public void setReviewDate(Date review_date) {
        this.review_date = review_date;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return id == null ? "" : id.toString();
    }

}
