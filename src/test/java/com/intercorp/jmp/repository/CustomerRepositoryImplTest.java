// Desafio Intercorp - 2020 
package com.intercorp.jmp.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.intercorp.jmp.domain.Customer;
import net.thegreshams.firebase4j.error.FirebaseException;

/**
 * Name: CustomerRepositoryImplTest
 * @author JESUS MATOS
 * @date 2020-12-21
 * @version 1.0.0
 * 
 * **/
@SpringBootTest
public class CustomerRepositoryImplTest {
	
	@Autowired
	CustomerRepository repository;
	
	/**
	 * TestCase: Create a customer test
	 * 
	 * **/
	@DisplayName("[Repository Layer] - Create Customer Test")
	@Test
	public void createCustomerTest() throws Exception, FirebaseException {
		Customer customer = Customer
				.builder()
				.id("test-1")
				.apellido("APELLIDO 1")
				.nombre("NOMBRE PRUEBA")
				.edad(25)
				.fechaNacimiento(new Date())
				.build();
		Customer respCustomer = repository.createCustomer(customer);
		assertNotNull(customer);
		assertEquals( "test-1", respCustomer.getId());
		assertEquals(  "APELLIDO 1", respCustomer.getApellido());
		assertEquals( 25, respCustomer.getEdad());
	}
	
	/**
	 * TestCase: Get a customer test
	 * 
	 * **/
	@DisplayName("[Repository Layer] - Get Customer Test")
	@Test
	public void getAllCustomersTest() throws Exception, FirebaseException {
		List<Customer> customers = repository.allCustomers();
		assertNotNull(customers);
		assertTrue(customers.size() > 0 );
	}
	
	
}
