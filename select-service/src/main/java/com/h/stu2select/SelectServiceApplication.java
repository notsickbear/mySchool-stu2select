package com.h.stu2select;

import com.h.stu2select.common.SimpleJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(value = "com.h.stu2select", repositoryBaseClass = SimpleJpaRepositoryImpl.class)
@SpringBootApplication
@EnableDiscoveryClient
/*@EnableOAuth2Client
@EnableFeignClients
@EnableGlobalMethodSecurity(prePostEnabled = true)*/
public class SelectServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SelectServiceApplication.class, args);
	}

}
