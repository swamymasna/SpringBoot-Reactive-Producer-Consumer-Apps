package com.swamy.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.swamy.dto.EmployeeDto;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class EmployeeClient {

	private WebClient webClient;

	private static final String EMPLOYEE_API_BASE_URL = "http://localhost:8081/api/employees";

	public Flux<EmployeeDto> fetchAllEmployees() {
		return webClient.get().uri(EMPLOYEE_API_BASE_URL).retrieve().bodyToFlux(EmployeeDto.class);
	}

	public Mono<EmployeeDto> fetchEmployeeById(String employeeId) {
		return webClient.get().uri(EMPLOYEE_API_BASE_URL + "/" + employeeId).retrieve().bodyToMono(EmployeeDto.class);
	}

	public Mono<EmployeeDto> createEmployee(EmployeeDto employeeDto) {
		return webClient.post().uri(EMPLOYEE_API_BASE_URL).body(Mono.just(employeeDto), EmployeeDto.class).retrieve()
				.bodyToMono(EmployeeDto.class);
	}

	public Mono<EmployeeDto> updateEmployee(String employeeId, EmployeeDto employeeDto) {
		return webClient.put().uri(EMPLOYEE_API_BASE_URL + "/" + employeeId)
				.body(Mono.just(employeeDto), EmployeeDto.class).retrieve().bodyToMono(EmployeeDto.class);
	}

	public Mono<Void> deleteEmployeeById(String employeeId) {
		return webClient.delete().uri(EMPLOYEE_API_BASE_URL + "/" + employeeId).retrieve().bodyToMono(Void.class);
	}

}
