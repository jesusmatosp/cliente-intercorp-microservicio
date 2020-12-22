// Desafio Intercorp - 2020 
package com.intercorp.jmp.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intercorp.jmp.domain.Customer;
import com.intercorp.jmp.exception.CustomerException;
import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.error.JacksonUtilityException;
import net.thegreshams.firebase4j.model.FirebaseResponse;
import net.thegreshams.firebase4j.service.Firebase;

/**
 * Customer Repository Implement
 * @author JESUS MATOS
 * @version 1.0.0
 * @date 2020-12-21
 * 
 * **/
@Component
public class CustomerRepositoryImpl implements CustomerRepository {

	private Logger logger = LoggerFactory.getLogger(CustomerRepositoryImpl.class);
	
	@Value("${firebase.baseurl}")
	private String url;
	
	/**
	 * Create Customer
	 * @param Customer customer
	 * @return Customer
	 * @throws CustomerException
	 * @throws FirebaseException
	 * 
	 * **/
	@Override
	public Customer createCustomer(Customer customer) throws CustomerException, FirebaseException {
		try {
			Firebase firebase = ConfigFirebaseDataBase.getConfigRealDataBase(url).getFirebaseConnect();
			Map<String, Object> mapCustomer = new HashMap<>();
			mapCustomer.put("id", customer.getId());
			mapCustomer.put("nombre", customer.getNombre());
			mapCustomer.put("apellido", customer.getApellido());
			mapCustomer.put("fechaNacimiento", customer.getFechaNacimiento());
			mapCustomer.put("edad", customer.getEdad());
			FirebaseResponse response = firebase.post(customer.getId(), mapCustomer);
			if(!response.getSuccess()) throw new CustomerException("Ocurrió un error al registrar el cliente");
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		} catch (JacksonUtilityException e) {
			logger.error(e.getLocalizedMessage());
		}
		return customer;
	}

	/***
	 * Get List of Customers
	 * @return List of  customers
	 * @throws CustomerException
	 * @throws FirebaseException
	 * 
	 * */
	@Override
	public List<Customer> allCustomers() throws CustomerException, FirebaseException {
		List<Customer> list = new ArrayList<>();
		try {
			Firebase firebase = ConfigFirebaseDataBase.getConfigRealDataBase(url).getFirebaseConnect();
			FirebaseResponse response = firebase.get();
			Map<String, Object> mapResponse = response.getBody();
			for(int i = 0; i<mapResponse.size(); i++) {		
				ArrayList<Object> a = new ArrayList<Object>(mapResponse.keySet());
                Object o=a.get(i);
                @SuppressWarnings("unchecked")
				Map<String, Object> map2 = (Map<String, Object>) mapResponse.get(o);
                ObjectMapper mapper = new ObjectMapper();
                Customer customer = mapper.convertValue(map2, Customer.class);
                customer.setId(o.toString());
                list.add(customer);
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		}
		return list;
	}

	
}
