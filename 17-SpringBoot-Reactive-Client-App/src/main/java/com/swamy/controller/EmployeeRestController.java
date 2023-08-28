package com.swamy.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swamy.dto.EmployeeDto;
import com.swamy.service.EmployeeService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeRestController {

	private EmployeeService employeeService;

	@GetMapping
	public Flux<EmployeeDto> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@GetMapping("/{id}")
	public Mono<EmployeeDto> getEmployeeById(@PathVariable("id") String employeeId) {
		return employeeService.getEmployeeById(employeeId);
	}

	@PostMapping
	public Mono<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto) {
		return employeeService.saveEmployee(employeeDto);
	}

	@PutMapping("/{id}")
	public Mono<EmployeeDto> saveEmployee(@PathVariable("id") String employeeId, @RequestBody EmployeeDto employeeDto) {
		return employeeService.updateEmployee(employeeId, employeeDto);
	}

	@DeleteMapping("/{id}")
	public Mono<Void> deleteEmployeeById(@PathVariable("id") String employeeId) {
		return employeeService.deleteEmployeeById(employeeId);
	}
}
