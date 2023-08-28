package com.swamy.service;

import com.swamy.dto.EmployeeDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {

	Mono<EmployeeDto> saveEmployee(EmployeeDto employeeDto);

	Flux<EmployeeDto> getAllEmployees();

	Mono<EmployeeDto> getEmployeeById(String employeeId);

	Mono<EmployeeDto> getEmployeeByName(String employeeName);

	Mono<EmployeeDto> updateEmployee(String employeeId, EmployeeDto employeeDto);

	Mono<Void> deleteEmployee(String employeeId);
}
