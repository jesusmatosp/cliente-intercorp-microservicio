// Desafio Intercorp - 2020 
package com.intercorp.jmp.repository;

import java.util.List;
import com.intercorp.jmp.domain.Customer;
import com.intercorp.jmp.exception.CustomerException;

import net.thegreshams.firebase4j.error.FirebaseException;

/**
 * Name: CustomerRepository
 * @author JESUS MATOS
 * @date 2020-12-21
 * @version 1.0.0
 * 
 * **/
public interface CustomerRepository {

	/**
	 * Create at Customer
	 * @param Customer customer
	 * @return Customer object
	 * @throws CustomerException
	 * 
	 * **/
	public Customer createCustomer(Customer customer) throws CustomerException, FirebaseException;
	
	/**
	 * 
	 * List of Customers
	 * @return List of customers
	 * @throws CustomerException
	 * 
	 * */
	public List<Customer> allCustomers() throws CustomerException, FirebaseException;
}
