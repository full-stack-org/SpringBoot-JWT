package com.springboot.jwt;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springboot.jwt.entity.UserEntity;
import com.springboot.jwt.repository.UserRepository;

@SpringBootApplication
public class SpringBootJwtSecurityApplication {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJwtSecurityApplication.class, args);
	}

	@PostConstruct
	public void initialiseH2DataBaseData() {
		List<UserEntity> userEntityList = Stream.of(new UserEntity(0, "Sunny", "Pass123$", "sunny@test.com"),
				new UserEntity(0, "Bunny", "Pass123$", "bunny@test.com"),
				new UserEntity(0, "Sita", "Pass123$", "sita@test.com"),
				new UserEntity(0, "Rita", "Pass123$", "rita@test.com")).collect(Collectors.toList());
		userRepository.saveAll(userEntityList);
	}

}
