package com.swamy.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import com.swamy.dto.EmployeeDto;
import com.swamy.service.EmployeeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest
public class EmployeeRestControllerTest {

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private EmployeeService employeeService;

	private EmployeeDto employeeDto;

	@BeforeEach
	public void setup() {

		employeeDto = EmployeeDto.builder().employeeId("12").employeeName("Swamy").employeeSalary(5600.0)
				.employeeAddress("Hyd").build();
	}

	@DisplayName("Testing Save Employee Operation")
	@Test
	public void whenSaveEmployee_thenReturnSavedEmployee() {

		when(employeeService.saveEmployee(ArgumentMatchers.any(EmployeeDto.class))).thenReturn(Mono.just(employeeDto));

		ResponseSpec response = webTestClient.post().uri("/api/employees")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.body(Mono.just(employeeDto), EmployeeDto.class)
				.exchange();

		response.expectStatus().isCreated()
		.expectBody()
		.consumeWith(System.out::println)
		.jsonPath("$.employeeName").isEqualTo(employeeDto.getEmployeeName())
		.jsonPath("$.employeeSalary").isEqualTo(employeeDto.getEmployeeSalary())
		.jsonPath("$.employeeAddress").isEqualTo(employeeDto.getEmployeeAddress());
	}

	@DisplayName("Testing Update Employee Operation")
	@Test
	public void whenUpdateEmployee_thenReturnUpdatedEmployee() {

		when(employeeService.updateEmployee(employeeDto.getEmployeeId(), employeeDto)).thenReturn(Mono.just(employeeDto));

		ResponseSpec response = webTestClient.put().uri("/api/employees/" + employeeDto.getEmployeeId())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.body(Mono.just(employeeDto), EmployeeDto.class)
				.exchange();

		response.expectStatus().isOk()
		.expectBody()
		.consumeWith(System.out::println)
		.jsonPath("$.employeeName").isEqualTo(employeeDto.getEmployeeName())
		.jsonPath("$.employeeSalary").isEqualTo(employeeDto.getEmployeeSalary())
		.jsonPath("$.employeeAddress").isEqualTo(employeeDto.getEmployeeAddress());
	}

	@DisplayName("Testing Get All Employees Operation")
	@Test
	public void whenGetAllEmployees_thenReturnEmployeesList() {

		when(employeeService.getAllEmployees()).thenReturn(Flux.just(employeeDto));

		ResponseSpec response = webTestClient.get().uri("/api/employees")
				.exchange();

		response.expectStatus().isOk()
		.expectBody()
		.consumeWith(System.out::println)
		.jsonPath("$.size()").isNotEmpty();
	}

	@DisplayName("Testing Employee By Id Operation")
	@Test
	public void whenGetEmployeeById_thenReturnEmployee() {

		when(employeeService.getEmployeeById(employeeDto.getEmployeeId())).thenReturn(Mono.just(employeeDto));

		ResponseSpec response = webTestClient.get().uri("/api/employees/" + employeeDto.getEmployeeId())
				.exchange();

		response.expectStatus().isOk()
		.expectBody()
		.consumeWith(System.out::println)
		.jsonPath("$.employeeName").isEqualTo(employeeDto.getEmployeeName())
		.jsonPath("$.employeeSalary").isEqualTo(employeeDto.getEmployeeSalary())
		.jsonPath("$.employeeAddress").isEqualTo(employeeDto.getEmployeeAddress());
	}

	@DisplayName("Testing Delete Employee By Id Operation")
	@Test
	public void whenGetEmployeeById_thenReturnNothing() {

		when(employeeService.deleteEmployee(employeeDto.getEmployeeId())).thenReturn(Mono.empty());

		ResponseSpec response = webTestClient.delete().uri("/api/employees/" + employeeDto.getEmployeeId())
				.exchange();

		response.expectStatus().isOk()
		.expectBody()
		.consumeWith(System.out::println);

	}

}
