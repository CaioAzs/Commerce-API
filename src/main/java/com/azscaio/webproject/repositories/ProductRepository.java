package com.azscaio.webproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.azscaio.webproject.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}