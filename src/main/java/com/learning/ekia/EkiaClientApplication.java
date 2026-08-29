package com.learning.ekia;

import com.learning.ekia.properties.EKIAProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(EKIAProperties.class)
public class EkiaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EkiaClientApplication.class, args);
	}

}
