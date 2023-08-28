package com.swamy.service.impl;

import org.springframework.stereotype.Service;

import com.swamy.client.EmployeeClient;
import com.swamy.dto.EmployeeDto;
import com.swamy.service.EmployeeService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeClient employeeClient;

	@Override
	public Flux<EmployeeDto> getAllEmployees() {
		return employeeClient.fetchAllEmployees();
	}

	@Override
	public Mono<EmployeeDto> getEmployeeById(String employeeId) {
		return employeeClient.fetchEmployeeById(employeeId);
	}

	@Override
	public Mono<EmployeeDto> saveEmployee(EmployeeDto employeeDto) {
		return employeeClient.createEmployee(employeeDto);
	}

	@Override
	public Mono<EmployeeDto> updateEmployee(String employeeId, EmployeeDto employeeDto) {
		return employeeClient.updateEmployee(employeeId, employeeDto);
	}
	
	@Override
	public Mono<Void> deleteEmployeeById(String employeeId) {
		return employeeClient.deleteEmployeeById(employeeId);
	}
}

