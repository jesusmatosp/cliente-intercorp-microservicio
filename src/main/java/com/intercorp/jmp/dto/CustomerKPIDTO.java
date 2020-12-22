// Desafio Intercorp - 2020 
package com.intercorp.jmp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Name: CustomerKPIReportDTO
 * @author JESUS MATOS
 * @date 2020-12-21
 * @version 1.0.0
 * 
 * **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerKPIDTO {
	private float promedioEdad;
	private double desvEstandar;
}
