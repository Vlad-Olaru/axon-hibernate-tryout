package com.example.axon;

import com.example.axon.domain.CourseDTO;
import com.example.axon.domain.commands.CreateCourseCommand;
import com.example.axon.model.Student;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;
import java.util.Collections;
import java.util.UUID;

@SpringBootApplication
public class AxonApplication implements CommandLineRunner {

	@Autowired
	private CommandGateway commandGateway;

	public static void main(String[] args) {
		SpringApplication.run(AxonApplication.class, args);
	}

	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server h2Server() throws SQLException {
		return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
	}

	@Override
	public void run(String... args) {
		Student student1 = new Student();
		student1.setName("vlad");
		Student student2 = new Student();
		student2.setName("matei");
		commandGateway.send(new CreateCourseCommand(UUID.randomUUID().toString(), new CourseDTO(Collections.singletonList(student1))));
		commandGateway.send(new CreateCourseCommand(UUID.randomUUID().toString(), new CourseDTO(Collections.singletonList(student2))));
		commandGateway.send(new CreateCourseCommand(UUID.randomUUID().toString(), new CourseDTO(Collections.singletonList(student1))));
	}
}
