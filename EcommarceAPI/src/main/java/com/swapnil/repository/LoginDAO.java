package com.swapnil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.swapnil.model.CurrentUserSession;

@Repository
public interface LoginDAO extends JpaRepository<CurrentUserSession, Integer>{

	
	public List<CurrentUserSession> findByUuid(String uuid);
}
