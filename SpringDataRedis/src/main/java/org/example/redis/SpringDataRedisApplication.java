package org.example.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringDataRedisApplication {

	public static void main (String[] args) {
		SpringApplication.run(SpringDataRedisApplication.class, args);
		log.info("----Redis Master Spring Application 시작----");
	}

}
