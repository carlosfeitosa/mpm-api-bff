package com.skull.bff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * BFF main class.
 * 
 * @author Carlos Feitosa (carlos.feitosa.nt@gmail.com)
 * @since 2020-08-17
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MpmBffApplication {

	/**
	 * Default constructor.
	 * 
	 * @param args args from command line
	 */
	public static void main(String[] args) {

		SpringApplication.run(MpmBffApplication.class, args);
	}

}
