// Desafio Intercorp - 2020 
package com.intercorp.jmp.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.intercorp.jmp.dto.CustomerDTO;
import com.intercorp.jmp.dto.CustomerKPIDTO;
import com.intercorp.jmp.exception.CustomerException;
import com.intercorp.jmp.rest.response.CustomerResponse;
import com.intercorp.jmp.services.CustomerService;

/**
 * Customer CustomerRestControllerTest
 * @author JESUS MATOS
 * @version 1.0.0
 * @date 2020-12-21
 * 
 * **/
@WebMvcTest
public class CustomerRestControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	CustomerService service;
	
	CustomerResponse response;
	CustomerDTO customerDTO;
	CustomerKPIDTO customerKPI;
	List<CustomerDTO> listCustomerDTO;
	
	/**
	 * Populate Customer Data
	 * 
	 * */
	public void populateCustomerData() {
	   customerDTO = CustomerDTO
				.builder()
				.nombre("LANCE")
				.apellido("AMSTRONG COMBAT")
				.edad(20)
				.fechaNacimiento(new Date()).build();		
		response = CustomerResponse
				.builder()
				.code("0000")
				.message("Se guardo el registro correctamente")
				.data(customerDTO)
				.build();
		listCustomerDTO = new ArrayList<CustomerDTO>();
		listCustomerDTO.add(customerDTO);
		customerKPI = CustomerKPIDTO
				.builder()
				.desvEstandar(30.0d)
				.promedioEdad(30f)
				.build();
				
	}
	
	/**
	 * Test Case: Create Customer Test
	 * 
	 * */
	@DisplayName("Create Customer Test")
	@Test
	public void createCustomerTest() throws CustomerException, Exception {
		populateCustomerData();
		when(service.createCustomer(Mockito.any())).thenReturn(customerDTO);
		String uri = "/customer/creacliente";
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(customerDTO);
	    MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", 
	    		"json", java.nio.charset.Charset.forName("UTF-8"));
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri)
				.accept(MEDIA_TYPE_JSON_UTF8)
				.contentType(MEDIA_TYPE_JSON_UTF8)
				.content(requestJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}
	
	/**
	 * Test Case: Create Customer Test With 500 Error
	 * 
	 * */
	@DisplayName("Create Customer With Error 500")
	@Test
	public void createCustomerWithError500() throws CustomerException, Exception {
		populateCustomerData();
		when(service.createCustomer(Mockito.any())).thenThrow(CustomerException.class);
		String uri = "/customer/creacliente";
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(customerDTO);
	    MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", 
	    		"json", java.nio.charset.Charset.forName("UTF-8"));
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri)
				.accept(MEDIA_TYPE_JSON_UTF8)
				.contentType(MEDIA_TYPE_JSON_UTF8)
				.content(requestJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(500, status);
	}
	
	/**
	 * Test Case: List Customer
	 * 
	 * */
	@DisplayName("Get List Customer")
	@Test
	public void getCustomerTest() throws CustomerException, Exception {
		populateCustomerData();
		when(service.getAllCustomers()).thenReturn(listCustomerDTO);
		String uri = "/customer/listclientes";
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	/**
	 * Test Case: List Customer Error 500
	 * 
	 * */
	@DisplayName("Get List Customer With Error 500")
	@Test
	public void getCustomerTestWithError500() throws CustomerException, Exception {
		populateCustomerData();
		when(service.getAllCustomers()).thenThrow(CustomerException.class);
		String uri = "/customer/listclientes";
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(500, status);
	}
	
	/**
	 * Test Case: Calculate KPI
	 * 
	 * */
	@DisplayName("Calculate KPI ")
	@Test
	public void calculateKPITest() throws CustomerException, Exception  {
		populateCustomerData();
		when(service.calCustomerKPI()).thenReturn(customerKPI);
		String uri = "/customer/kpiclientes";
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	/**
	 * Test Case: Calculate KPI
	 * 
	 * */
	@DisplayName("Calculate KPI With Error ")
	@Test
	public void calculateKPITestWithError500() throws CustomerException, Exception  {
		populateCustomerData();
		when(service.calCustomerKPI()).thenThrow(CustomerException.class);
		String uri = "/customer/kpiclientes";
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(500, status);
	}
	
}
