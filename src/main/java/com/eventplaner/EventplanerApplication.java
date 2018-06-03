package com.eventplaner;

import com.eventplaner.tasks.userTasks.CreateUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class EventplanerApplication {

	public static void main(String[] args) {
		new CreateUser("seas@seas.seas", "seas", "seas").execute();
		SpringApplication.run(EventplanerApplication.class, args);
	}
}
