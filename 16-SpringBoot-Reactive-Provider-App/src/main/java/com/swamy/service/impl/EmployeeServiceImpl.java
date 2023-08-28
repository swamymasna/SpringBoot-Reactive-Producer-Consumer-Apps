package com.swamy.service.impl;

import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.swamy.dto.EmployeeDto;
import com.swamy.entity.Employee;
import com.swamy.repository.EmployeeRepository;
import com.swamy.service.EmployeeService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	private ModelMapper modelMapper;

	@Override
	public Mono<EmployeeDto> saveEmployee(EmployeeDto employeeDto) {

		Employee employee = modelMapper.map(employeeDto, Employee.class);

		String employeeId = String.valueOf(new Random().nextInt(1000));

		employee.setEmployeeId(employeeId);

		Mono<Employee> savedEmployee = employeeRepository.save(employee);

		return savedEmployee.map(emp -> modelMapper.map(emp, EmployeeDto.class));
	}

	@Override
	public Flux<EmployeeDto> getAllEmployees() {

		Flux<Employee> employees = employeeRepository.findAll();

		return employees.map(employee -> modelMapper.map(employee, EmployeeDto.class)).switchIfEmpty(Flux.empty());
	}

	@Override
	public Mono<EmployeeDto> getEmployeeById(String employeeId) {

		Mono<Employee> employee = employeeRepository.findById(employeeId);

		return employee.map(e -> modelMapper.map(e, EmployeeDto.class)).switchIfEmpty(Mono.empty());
	}

	@Override
	public Mono<EmployeeDto> getEmployeeByName(String employeeName) {
		Mono<Employee> employee = employeeRepository.findByEmployeeNameIgnoreCase(employeeName)
				.switchIfEmpty(Mono.empty());

		return employee.map(e -> modelMapper.map(e, EmployeeDto.class)).switchIfEmpty(Mono.empty());
	}

	@Override
	public Mono<EmployeeDto> updateEmployee(String employeeId, EmployeeDto employeeDto) {

		Mono<Employee> employeeMono = employeeRepository.findById(employeeId);

		Mono<Employee> updatedEmployee = employeeMono.flatMap((existingEmployee) -> {

			existingEmployee.setEmployeeName(employeeDto.getEmployeeName());
			existingEmployee.setEmployeeSalary(employeeDto.getEmployeeSalary());
			existingEmployee.setEmployeeAddress(employeeDto.getEmployeeAddress());

			return employeeRepository.save(existingEmployee);
		});

		return updatedEmployee.map(employee -> modelMapper.map(employee, EmployeeDto.class))
				.switchIfEmpty(Mono.empty());

	}

	@Override
	public Mono<Void> deleteEmployee(String employeeId) {
		return employeeRepository.deleteById(employeeId);
	}

}
