package com.swapnil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swapnil.model.Product;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer>{

}
