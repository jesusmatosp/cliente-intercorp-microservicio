// Desafio Intercorp - 2020 
package com.intercorp.jmp.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.intercorp.jmp.dto.CustomerDTO;
import com.intercorp.jmp.dto.CustomerKPIDTO;
import com.intercorp.jmp.exception.CustomerException;
import com.intercorp.jmp.rest.response.CustomerResponse;
import com.intercorp.jmp.services.CustomerService;

/***
 * Name: CustomerRestController
 * @author JESUS MATOS
 * @version 1.0.0
 * @date 2020-12-21
 * 
 * */
@RestController
@RequestMapping("/customer")
public class CustomerRestController {

	@Autowired
	private CustomerService service;
	
	private Logger logger = LoggerFactory.getLogger(CustomerRestController.class);
	
	/**
	 * Post Create Customer Response
	 * @param CustomerDTO customer RequestBody
	 * @return ResponseEntity<CustomerDTO>
	 * 
	 * **/
	@PostMapping("/creacliente")
	public ResponseEntity<CustomerResponse> createCustomer(@Validated @RequestBody CustomerDTO customer) {
		ResponseEntity<CustomerResponse> response = null;
		CustomerResponse oCustomerResponse = null;
		HttpStatus httpStatus = null;
		try {
			customer = service.createCustomer(customer);
			oCustomerResponse = CustomerResponse.builder().code("0000")
					.message("Los datos se almacenaron Correctamente").data(customer).build();
			httpStatus = HttpStatus.CREATED;
		} catch (CustomerException e) {
			oCustomerResponse = CustomerResponse.builder().code("0001")
					.message(e.getMessage()).data(null).build();
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			logger.error(e.getMessage());
		} finally {
			response = new ResponseEntity<CustomerResponse>(oCustomerResponse, httpStatus);
		}
		return response;
	}
	
	/**
	 * Get List Customers
	 * @return ResponseEntity<CustomerDTO>
	 *  
	 * */
	@GetMapping("/listclientes")
	public ResponseEntity<CustomerResponse> listCustomers() {
		ResponseEntity<CustomerResponse> response = null;
		CustomerResponse oCustomerResponse = null;
		HttpStatus httpStatus = null;
		try {
			List<CustomerDTO> customers = service.getAllCustomers();
			oCustomerResponse = CustomerResponse
					.builder()
					.code("0000")
					.message("Se obtuvieron los datos correctamente")
					.data(customers).build();
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			logger.error(e.getMessage());
			oCustomerResponse = CustomerResponse.builder().code("0001")
					.message(e.getMessage()).data(null).build();
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}finally {
			response = new ResponseEntity<CustomerResponse>(oCustomerResponse, httpStatus);
		}
		return response;
	}
	
	/**
	 * Get Customer KPI
	 * @return Response Entity
	 * 
	 * **/
	@GetMapping("/kpiclientes")
	public ResponseEntity<CustomerResponse> getCustomerKPI() {
		ResponseEntity<CustomerResponse> response = null;
		CustomerResponse oCustomerResponse = null;
		HttpStatus httpStatus = null;
		try {
			CustomerKPIDTO customerKPI =  service.calCustomerKPI();
			oCustomerResponse = CustomerResponse.builder()
					.code("0000")
					.message("Se obtuvieron los datos correctamente")
					.data(customerKPI)
					.build();
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			logger.error(e.getMessage());
			oCustomerResponse = CustomerResponse.builder().code("0001")
					.message(e.getMessage()).data(null).build();
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}finally {
			response = new ResponseEntity<CustomerResponse>(oCustomerResponse, httpStatus);
		}
		return response;
	}
	
	
}
