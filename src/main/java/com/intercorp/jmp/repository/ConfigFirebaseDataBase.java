// Desafio Intercorp - 2020 
package com.intercorp.jmp.repository;

import lombok.Getter;
import lombok.Setter;
import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.service.Firebase;

/**
 * Config Firebase database
 * @author JESUS MATOS
 * @version 1.0.0
 * @date 2020-12-21
 * 
 * **/
public class ConfigFirebaseDataBase {

	@Setter
	@Getter
	private String url;
	
	private static ConfigFirebaseDataBase config;
	
	/**
	 * Constructor
	 * @para url
	 * 
	 * */
	private ConfigFirebaseDataBase(String url) {
		this.url = url;
	}
	
	/**
	 * Get Config Firebase Real Database
	 * @param url String
	 * @return ConfigFirebaseDateBase
	 * 
	 * */
	public static ConfigFirebaseDataBase getConfigRealDataBase(String url) {
		if(config == null) {
			config = new ConfigFirebaseDataBase(url);
		}
		return config;
	}
	
	/**
	 * Get Firebase Connect
	 * @return Firebase
	 * @throws FirebaseException
	 * 
	 * **/
	public Firebase getFirebaseConnect() throws FirebaseException {
		Firebase firebase = new Firebase(url);
		return firebase;
	}
	
}
