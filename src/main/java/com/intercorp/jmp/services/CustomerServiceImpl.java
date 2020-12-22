// Desafio Intercorp - 2020 
package com.intercorp.jmp.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intercorp.jmp.domain.Customer;
import com.intercorp.jmp.dto.CustomerDTO;
import com.intercorp.jmp.dto.CustomerKPIDTO;
import com.intercorp.jmp.exception.CustomerException;
import com.intercorp.jmp.repository.CustomerRepository;
import com.intercorp.jmp.utilities.Util;

import net.thegreshams.firebase4j.error.FirebaseException;

/**
 * Name: CustomerServiceImpl
 * @author JESUS MATOS
 * @date 2020-12-21
 * @version 1.0.0
 * 
 * **/
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository repository;
	
	// Logger App.
	private Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	/**
	 * Create a Customer
	 * @param customer CustomerDTO
	 * @return at customerDTO
	 * @throws CustomerException
	 * 
	 * */
	@Override
	public CustomerDTO createCustomer(CustomerDTO customer) throws CustomerException {
		try {
			Customer domainCustomer = new Customer();
			BeanUtils.copyProperties(customer, domainCustomer);
			customer.setId(UUID.randomUUID().toString());
			domainCustomer = repository.createCustomer(domainCustomer);
		} catch (FirebaseException e) {
			logger.error(e.getCause().getMessage());
			throw new CustomerException("Ocurrio un error interno en el servicio.");
		}
		return customer;
	}

	/**
	 * Get All Customers
	 * @return List CustomerDTO
	 * @throws CustomerException
	 * 
	 * **/
	@Override
	public List<CustomerDTO> getAllCustomers() throws CustomerException {
		List<CustomerDTO> lstCustomers = new ArrayList<CustomerDTO>();
		try {
			List<Customer> list = repository.allCustomers();
			list.forEach(customer -> {
				CustomerDTO oCustomerDTO = CustomerDTO.builder().build();
				BeanUtils.copyProperties(customer, oCustomerDTO);
				oCustomerDTO.setFormatFechaNacimiento(Util.getDateToString(oCustomerDTO.getFechaNacimiento()));
				Random random = new Random();
				long year = random.nextInt(30);
				Date fecMort = Util.addYearToDate(year, new Date());
				oCustomerDTO.setFechaProbMuerte(fecMort);
				oCustomerDTO.setFormatFechaProbMuerte(Util.getDateToString(fecMort));
				lstCustomers.add(oCustomerDTO);
			});
		} catch (FirebaseException e) {
			logger.error(e.getCause().getMessage());
			throw new CustomerException("Ocurrio un error interno en el servicio.");
		}
		return lstCustomers;
	}

	/**
	 * Calculate Customer KPI
	 * @return Customer KPI
	 * @throws CustomerException
	 * 
	 * */
	@Override
	public CustomerKPIDTO calCustomerKPI() throws CustomerException {
		CustomerKPIDTO customerKPI = CustomerKPIDTO.builder().build();
		double varianza = 0.0;
		double desviacion= 0.0; 
		float media = 0;
		try {
			List<Customer> list = repository.allCustomers();
			if(list != null) {
				int acumEdad = 0;
				int edadPromedio = 0;
				for(Customer customer: list) {
					acumEdad += customer.getEdad();
				}
				if(list.size() > 0) media = acumEdad/list.size();
				for(Customer customer: list) {
					double rango;
					rango = Math.pow(customer.getEdad() - edadPromedio, 2f);
					varianza = varianza + rango;
				}
				varianza = varianza / list.size();
				desviacion = Math.sqrt(varianza);
				customerKPI.setDesvEstandar(desviacion);
			}
			customerKPI.setDesvEstandar(desviacion);
			customerKPI.setPromedioEdad(media);
		} catch (FirebaseException e) {
			logger.error(e.getCause().getMessage());
			throw new CustomerException("Ocurrio un error interno en el servicio.");
		}
		return customerKPI;
	}

}
