package com.swapnil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swapnil.model.FeedBack;

@Repository
public interface FeedBackDAO extends JpaRepository<FeedBack, Integer>{

}
