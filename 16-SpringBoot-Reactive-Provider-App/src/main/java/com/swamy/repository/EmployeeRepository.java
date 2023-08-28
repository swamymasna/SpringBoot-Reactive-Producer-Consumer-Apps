package com.swamy.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.swamy.entity.Employee;

import reactor.core.publisher.Mono;

public interface EmployeeRepository extends ReactiveCrudRepository<Employee, String> {
	Mono<Employee> findByEmployeeNameIgnoreCase(String employeeName);
}
