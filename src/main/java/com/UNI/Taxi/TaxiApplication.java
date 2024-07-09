package com.UNI.Taxi;

import com.UNI.Taxi.service.DriverService;
import com.UNI.Taxi.service.KafkaSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TaxiApplication {


//	@Bean
//	DriverService getDriverService(){
//		return new DriverService();
//	}

//	@Bean
//	KafkaSender getKafkaSender(){
//		return new KafkaSender();
//	}
	public static void main(String[] args) {
		SpringApplication.run(TaxiApplication.class, args);
	}

}
