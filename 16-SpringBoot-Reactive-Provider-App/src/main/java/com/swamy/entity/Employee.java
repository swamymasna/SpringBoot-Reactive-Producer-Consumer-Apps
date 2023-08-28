package com.swamy.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "employees")
public class Employee {

	@Id
	private String employeeId;
	private String employeeName;
	private Double employeeSalary;
	private String employeeAddress;
}
