package com.swapnil.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

	private String streetNo;
	
	private String houseName;
	
	
	private String city;
	
	
	private String state;
	
	
	private String country;
	
	private String pincode;
	
}
