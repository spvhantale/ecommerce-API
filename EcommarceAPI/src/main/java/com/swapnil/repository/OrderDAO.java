package com.swapnil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swapnil.model.Orders;

@Repository
public interface OrderDAO extends JpaRepository<Orders, Integer>{

}