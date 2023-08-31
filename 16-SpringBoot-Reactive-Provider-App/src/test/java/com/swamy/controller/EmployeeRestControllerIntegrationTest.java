package com.swamy.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import com.swamy.dto.EmployeeDto;
import com.swamy.repository.EmployeeRepository;
import com.swamy.service.EmployeeService;

import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeRestControllerIntegrationTest {

	@Autowired
	private WebTestClient webTestClient;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeRepository employeeRepository;

	private EmployeeDto employeeDto;

	@BeforeEach
	public void setup() {
		employeeRepository.deleteAll();

		employeeDto = EmployeeDto.builder().employeeId("1").employeeName("Swamy").employeeSalary(5600.00)
				.employeeAddress("Hyd").build();
	}

	@Test
	public void testSaveEmployee() {

		employeeService.saveEmployee(employeeDto).block();

		ResponseSpec response = webTestClient.post().uri("/api/employees").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).body(Mono.just(employeeDto), EmployeeDto.class).exchange();

		response.expectStatus().isCreated().expectBody().consumeWith(System.out::println).jsonPath("$.employeeName")
				.isEqualTo(employeeDto.getEmployeeName()).jsonPath("$.employeeSalary")
				.isEqualTo(employeeDto.getEmployeeSalary()).jsonPath("$.employeeAddress")
				.isEqualTo(employeeDto.getEmployeeAddress());
	}

	@Test
	public void testGetAllEmployees() {

		employeeService.saveEmployee(employeeDto).block();
		employeeService.saveEmployee(employeeDto).block();

		ResponseSpec response = webTestClient.get().uri("/api/employees").exchange();

		response.expectStatus().isOk().expectBody().consumeWith(System.out::println);
	}

	@Test
	public void testGetEmployeeById() {
		EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto).block();

		ResponseSpec response = webTestClient.get().uri("/api/employees/" + savedEmployee.getEmployeeId()).exchange();

		response.expectStatus().isOk().expectBody().consumeWith(System.out::println);
	}

	@Test
	public void testUpdateEmployee() {

		EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto).block();

		savedEmployee.setEmployeeName("Victory");
		savedEmployee.setEmployeeSalary(78000.00);
		savedEmployee.setEmployeeAddress("Vizag");

		ResponseSpec response = webTestClient.put().uri("/api/employees/" + savedEmployee.getEmployeeId())
				.contentType(MediaType.APPLICATION_JSON).body(Mono.just(savedEmployee), EmployeeDto.class).exchange();

		response.expectStatus().isOk().expectBody().consumeWith(System.out::println);
	}

	@Test
	public void testDeleteEmployeeById() {

		EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto).block();

		ResponseSpec response = webTestClient.delete().uri("/api/employees/" + savedEmployee.getEmployeeId())
				.exchange();

		response.expectStatus().isOk().expectBody().consumeWith(System.out::println);
	}

}
