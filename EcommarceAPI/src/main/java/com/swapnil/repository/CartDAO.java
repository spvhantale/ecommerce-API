package com.swapnil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swapnil.model.Cart;
@Repository
public interface CartDAO extends JpaRepository<Cart, Integer>{

}
