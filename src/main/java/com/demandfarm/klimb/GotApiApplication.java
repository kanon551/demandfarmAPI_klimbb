package com.demandfarm.klimb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EntityScan("com.demandfarm.klimb.models")
public class GotApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GotApiApplication.class, args);
	}

}
