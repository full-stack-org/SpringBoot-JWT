package com.springboot.jwt.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.jwt.entity.UserEntity;
import com.springboot.jwt.repository.UserRepository;

@Service
public class UserDataService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByUserName(username);

		return new org.springframework.security.core.userdetails.User(userEntity.getUserName(),
				userEntity.getPassword(), new ArrayList<>());
	}

}
