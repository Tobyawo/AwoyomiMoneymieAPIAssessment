package com.awoyomi_moneymie_assessment;

import com.awoyomi_moneymie_assessment.model.Car;
import com.awoyomi_moneymie_assessment.repository.CarRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class MoneymieCarApplication {

	Logger logger = LoggerFactory.getLogger(MoneymieCarApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MoneymieCarApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(CarRepository repo){
	    return args -> {
			// read JSON and load json
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Car>> typeReference = new TypeReference<List<Car>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/cars-large.json");
			try {
				List<Car> cars = mapper.readValue(inputStream,typeReference);
				repo.save(cars);
				logger.info("Json cars object successfully persisted to DB");
			} catch (IOException e){
				logger.info("Failed to persist Json cars object to DB");
			}
	    };
	}
}
