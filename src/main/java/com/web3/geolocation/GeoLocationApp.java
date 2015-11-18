/**
 * 
 * @since: Oct 31, 2015
 *
 */
package com.web3.geolocation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.web3.geolocation.service.GeoLocationServiceI;
import com.web3.geolocation.service.GeoExtractorServiceI;
import com.web3.geolocation.service.impl.GeoLocationService;
import com.web3.geolocation.service.impl.GeoExtractorService;

/**
 * @author Jayanth
 *
 */
@SpringBootApplication
public class GeoLocationApp {

	@Bean
	GeoLocationServiceI geoLocationService(){
		return new GeoLocationService();
	}
	
	@Bean
	GeoExtractorServiceI geoExtractorService(){
		return new GeoExtractorService();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(GeoLocationApp.class, args);
	}
}
