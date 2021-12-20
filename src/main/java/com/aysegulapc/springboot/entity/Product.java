package com.aysegulapc.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * id
 * adi
 * fiyat
 * kayitTarihi
 * KategoriId
 */
@Entity
@Table(name = "PRODUCT")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "category"})
@Component
//@JsonFilter("ProductFilter")
public class Product implements Serializable {

    @SequenceGenerator(name = "generator", sequenceName = "PRODUCT_ID_SEQ")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(precision = 19, scale = 2)
    private BigDecimal price;

    @Temporal(TemporalType.TIMESTAMP)
    private Date enrollment_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CATEGORY",
            foreignKey = @ForeignKey(name = "FK_PRODUCT_CATEGORY_ID"))
    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getEnrollmentDate() {
        return enrollment_date;
    }

    public void setEnrollmentDate(Date enrollment_date) {
        this.enrollment_date = enrollment_date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return id == null ? "" : id.toString();
    }
}
