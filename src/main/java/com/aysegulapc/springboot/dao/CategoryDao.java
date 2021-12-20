package com.aysegulapc.springboot.dao;

import com.aysegulapc.springboot.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDao extends JpaRepository<Category, Long> {

    @Query("select category from Category category")
    List<Category> findAllByTopCategoryIsNullOrderByName();

    List<Category> findAllByNameEndsWith(String name);
}
