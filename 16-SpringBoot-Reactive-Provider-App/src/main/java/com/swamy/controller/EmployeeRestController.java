package com.swamy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Mono<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
		return employeeService.saveEmployee(employeeDto);
	}

	@GetMapping
	public Flux<EmployeeDto> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@GetMapping("/{id}")
	public Mono<EmployeeDto> getEmployeeById(@PathVariable("id") String employeeId) {
		return employeeService.getEmployeeById(employeeId);
	}

	@GetMapping("/name/{name}")
	public Mono<EmployeeDto> getEmployeeByName(@PathVariable("name") String employeeName) {
		return employeeService.getEmployeeByName(employeeName);
	}

	@PutMapping("/{id}")
	public Mono<EmployeeDto> updateEmployee(@PathVariable("id") String employeeId,
			@RequestBody EmployeeDto employeeDto) {
		return employeeService.updateEmployee(employeeId, employeeDto);
	}

	@DeleteMapping("/{id}")
	public Mono<Void> deleteEmployee(@PathVariable("id") String employeeId) {
		return employeeService.deleteEmployee(employeeId);
	}
}
