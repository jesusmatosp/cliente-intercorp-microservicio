// Desafio Intercorp - 2020 
package com.intercorp.jmp.services;

import java.util.List;
import com.intercorp.jmp.dto.CustomerDTO;
import com.intercorp.jmp.dto.CustomerKPIDTO;
import com.intercorp.jmp.exception.CustomerException;

/**
 * Name: CustomerService
 * @author JESUS MATOS
 * @date 2020-12-21
 * @version 1.0.0
 * 
 * **/
public interface CustomerService {

	/**
	 * Create a Customer
	 * @param customer CustomerDTO
	 * @return at customerDTO
	 * @throws CustomerException
	 * 
	 * */
	public CustomerDTO createCustomer(CustomerDTO customer) throws CustomerException ;
	
	/**
	 * Get All Customers
	 * @return List CustomerDTO
	 * @throws CustomerException
	 * 
	 * **/
	public List<CustomerDTO> getAllCustomers() throws CustomerException;
	
	/**
	 * Calculate Customer KPI
	 * @return Customer KPI
	 * @throws CustomerException
	 * 
	 * */
	public CustomerKPIDTO calCustomerKPI() throws CustomerException;
	
}
