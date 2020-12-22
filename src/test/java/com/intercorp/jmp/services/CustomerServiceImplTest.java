// Desafio Intercorp - 2020 
package com.intercorp.jmp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.intercorp.jmp.domain.Customer;
import com.intercorp.jmp.dto.CustomerDTO;
import com.intercorp.jmp.dto.CustomerKPIDTO;
import com.intercorp.jmp.repository.CustomerRepository;
import net.thegreshams.firebase4j.error.FirebaseException;

/**
 * Name: CustomerServiceImplTest
 * @author JESUS MATOS
 * @date 2020-12-21
 * @version 1.0.0
 * 
 * **/
public class CustomerServiceImplTest {

	@Mock
	CustomerRepository repository;
	
	@InjectMocks
	CustomerService service = new CustomerServiceImpl();
	
	CustomerDTO customerDTO;
	
	Customer customer;
	
	List<Customer> lstCustomer;
	
	/**
	 * Setup Method 
	 * 
	 * */
	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		lstCustomer = new ArrayList<Customer>();
		customerDTO = CustomerDTO
				.builder()
				.nombre("LANCE")
				.apellido("AMSTRONG COMBAT")
				.edad(20)
				.fechaNacimiento(new Date()).build();
		
		customer = Customer
				.builder()
				.nombre("LANCE")
				.apellido("AMSTRONG COMBAT")
				.edad(20)
				.fechaNacimiento(new Date()).build();
		lstCustomer.add(customer);
	}
	
	/**
	 * TestCase: Create a customer test
	 * 
	 * **/
	@DisplayName("Create a Customer Test")
	@Test
	public void createCustomerTest() throws Exception, FirebaseException {
		when(repository.createCustomer(Mockito.any())).thenReturn(customer);
		CustomerDTO resultCustomer = service.createCustomer(customerDTO);
		assertNotNull(resultCustomer);
		assertEquals(resultCustomer.getNombre(), customer.getNombre());
		assertEquals(resultCustomer.getApellido(), customer.getApellido());
		assertEquals(resultCustomer.getEdad(), customer.getEdad());
	}
	
	/**
	 * TestCase: Create a customer test With Error
	 * 
	 * **/
	@DisplayName("Create a Customer Test With Error")
	@Test
	public void createCustomerTestWithError() throws Exception, FirebaseException {
		when(repository.createCustomer(Mockito.any())).thenThrow(FirebaseException.class);
		assertThrows(Exception.class, () -> {
			service.createCustomer(customerDTO);
		});
	}
	
	/**
	 * Test Case: Get All Customers Test
	 * 
	 * **/
	@DisplayName("Get All Customers Test")
	@Test
	public void getAllCustomersTest() throws Exception, FirebaseException {
		when(repository.allCustomers()).thenReturn(lstCustomer);
		List<CustomerDTO> rsltCustomers = service.getAllCustomers();
		assertNotNull(rsltCustomers);
		assertTrue(rsltCustomers.size() > 0);
	}
	
	/**
	 * Test Case: Get All Customers Test
	 * 
	 * **/
	@DisplayName("Get All Customers Test With Errors")
	@Test
	public void getAllCustomersTestWithError() throws Exception, FirebaseException {
		when(repository.allCustomers()).thenThrow(FirebaseException.class);
		assertThrows(Exception.class, () -> {
			service.getAllCustomers();
		});
	}
	
	/**
	 * Test Case: Calculate KPI
	 * 
	 * **/
	@DisplayName("Calculate KPI")
	@Test
	public void calculateKPI() throws Exception, FirebaseException {
		when(repository.allCustomers()).thenReturn(lstCustomer);
		CustomerKPIDTO kpiData = service.calCustomerKPI();
		assertNotNull(kpiData);
	}
	
	/**
	 * Test Case: Calculate KPI
	 * 
	 * **/
	@DisplayName("Calculate KPI With")
	@Test
	public void calculateKPIWith() throws Exception, FirebaseException {
		when(repository.allCustomers()).thenThrow(FirebaseException.class);
		assertThrows(Exception.class, () -> {
			service.calCustomerKPI();
		});
	}
}
