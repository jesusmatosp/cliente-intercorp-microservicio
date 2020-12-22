// Desafio Intercorp - 2020 
package com.intercorp.jmp.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Name: Util
 * @author JESUS MATOS
 * @date 2020-12-21
 * @version 1.0.0
 * 
 * **/
public class Util {

	/***
	 * Get Date to String
	 * @param Date date
	 * @return string adte
	 * 
	 * */
	public static String getDateToString(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = dateFormat.format(date);
		return strDate;
	}
	
	/**
	 * Add Year Tp Date
	 * @param long year
	 * @param Date date
	 * @return to Date
	 * 
	 * **/
	public static Date addYearToDate(long year, Date date) {
		LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		localDateTime = localDateTime.plusYears(year);
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
}
