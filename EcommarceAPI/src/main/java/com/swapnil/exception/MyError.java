package com.swapnil.exception;


import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MyError {
	
	private String name;
	private LocalTime localtime;
	private String description;

}
