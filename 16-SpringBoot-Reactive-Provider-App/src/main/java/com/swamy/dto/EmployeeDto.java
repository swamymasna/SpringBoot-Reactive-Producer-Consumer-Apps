package com.swamy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

	private String employeeId;
	private String employeeName;
	private Double employeeSalary;
	private String employeeAddress;
}
