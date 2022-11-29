package com.swapnil.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	
	@Size(min = 3,max=15 ,message = "first name should be greather than 3 character")
	private String firstName;
	@Size(min=3,max=15,message="last name should be greather than 3 character")
	private String lastName;
	@Email(message = "Enter valid email")
	private String email;
	@Size(min = 10, max = 10,message = "Please enter ten digit number")
	private String mobile;
	@Size(min=8,max=16,message="password length should be in between 8 to 16")
	private String password;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Address> addresses=new ArrayList<>();
}
