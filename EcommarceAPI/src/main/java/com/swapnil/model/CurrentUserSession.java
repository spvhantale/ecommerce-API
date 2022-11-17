package com.swapnil.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CurrentUserSession {
	@Id
	@Column(unique = true)
	private Integer userId;
	private String Uuid;
	private LocalDateTime dateTime;
	
}
