package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
    public User registerNewUser(UserDTO userdto) {
    	
    	System.out.println(userdto.toString());
        User user = new User.Builder().withUsername(userdto.getUsername()).withPassword(userdto.getPassword()).build();
        
        user.setSocialSecurityNumber(Integer.parseInt(userdto.getSsn()));
        return userRepository.save(user);
    }
}
