package com.swamy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

	private String employeeId;
	private String employeeName;
	private Double employeeSalary;
	private String employeeAddress;
}
