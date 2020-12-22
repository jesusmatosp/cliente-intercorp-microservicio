// Desafio Intercorp - 2020 
package com.intercorp.jmp.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * Name: CustomerResponse
 * @author JESUS MATOS
 * @version 1.0.0
 * @date 2020-12-21
 * 
 * */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

	private String code;
	private String message;
	private Object data;
}
