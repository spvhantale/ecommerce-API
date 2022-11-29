package com.swapnil.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedBack {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String reason;
	private String message;
	
	private String mobileNumber;
	@OneToOne(cascade = CascadeType.ALL)
	private Customer customer;
}
