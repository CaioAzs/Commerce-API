package com.azscaio.webproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.azscaio.webproject.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
    
}