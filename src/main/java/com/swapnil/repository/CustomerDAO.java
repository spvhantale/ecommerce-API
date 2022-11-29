package com.swapnil.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.swapnil.model.Customer;

@Repository
public interface CustomerDAO extends JpaRepository<Customer, Integer> {

	public Customer findByMobile(String mobile);
	
	@Query("from Customer")
	public List<Customer> getCustomers();
}
