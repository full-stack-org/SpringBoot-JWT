package com.springboot.jwt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.jwt.entity.UserEntity;
import com.springboot.jwt.exception.handler.UserNotFoundException;
import com.springboot.jwt.repository.UserRepository;

@RestController
@RequestMapping("/user/jwt/v1")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/healthcheck")
	public String healthCheck() {

		return "Health Check Tested";
	}

	@GetMapping("/getUserById/{id}")
	public ResponseEntity<UserEntity> getUserById(@PathVariable int id) {

		Optional<UserEntity> userEntity = Optional.ofNullable(
				userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not found for: " + id)));

		return ResponseEntity.ok().body(userEntity.get());
	}

	@GetMapping("/getAllUsersDetails")
	public ResponseEntity<List<UserEntity>> getAllUsersDetails() {

		Optional<List<UserEntity>> usersList = Optional.ofNullable(userRepository.findAll());

		return ResponseEntity.ok().body(usersList.get());

	}

	@PostMapping("/addUser")
	public ResponseEntity<UserEntity> addUser(@RequestBody UserEntity userEntity) {
		UserEntity finalUserEntity = userRepository.save(userEntity);
		return ResponseEntity.ok().body(finalUserEntity);
	}

	@DeleteMapping("/deleteUserById/{id}")
	public boolean deleteUserById(@PathVariable int id) {
		userRepository.deleteById(id);
		return true;
	}
	
	

}
