package com.UNI.Taxi;

import com.UNI.Taxi.service.DriverService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TaxiApplication {


//	@Bean
//	DriverService getDriverService(){
//		return new DriverService();
//	}
	public static void main(String[] args) {
		SpringApplication.run(TaxiApplication.class, args);
	}

}
