// Desafio Intercorp - 2020 
package com.intercorp.jmp.dto;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Name: CustomerDTO
 * @author JESUS MATOS
 * @date 2020-12-21
 * @version 1.0.0
 * 
 * **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
	
	private String id;
	
	@NotBlank
	private String nombre;
	@NotBlank
	private String apellido;
	private Date fechaNacimiento;
	private String formatFechaNacimiento;
	private Integer edad;
	private Date fechaProbMuerte;
	private String formatFechaProbMuerte;
	
}
