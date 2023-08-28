package com.swamy.service;

import com.swamy.dto.EmployeeDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {

	Flux<EmployeeDto> getAllEmployees();

	Mono<EmployeeDto> getEmployeeById(String employeeId);

	Mono<EmployeeDto> saveEmployee(EmployeeDto employeeDto);

	Mono<EmployeeDto> updateEmployee(String employeeId, EmployeeDto employeeDto);

	Mono<Void> deleteEmployeeById(String employeeId);
}
