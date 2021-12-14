package com.example.usermanagementservice;

import com.example.usermanagementservice.config.KeycloakRestConfiguration;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class UserManagementServiceApplication {

	private static KeycloakRestConfiguration restConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(UserManagementServiceApplication.class, args);
	}

}
