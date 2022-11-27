package com.swapnil.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GeneratorType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer proId;
	@Min(value = 3 ,message = "Characters should be greater than 3 of produtName")
	private String productName;
	@Min(value=1,message="Price should be greater than 1")
	private Integer productPrice;
	@Min(value = 3 ,message = "Characters should be greater than 3 of brand")
	private String brand;
	
	private String color;
	private String manufactorer;
	@OneToOne(cascade = CascadeType.ALL)
	private Category category;

}
