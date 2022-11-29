package com.swapnil.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.swapnil.model.Orders;

@Repository
public interface OrderDAO extends JpaRepository<Orders, Integer>{

	public List<Orders> findByOrderDate(LocalDate orderDate);
	
	@Query("select o from Orders o where o.orderAddress.city=?1")
	public List<Orders> getOrderByCity(String city);
	
	@Query("select o from Orders o where o.customer.customerId=?1")
	public List<Orders> getOrdersByuserId(Integer useId);
}
