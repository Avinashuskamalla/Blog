package com.example.blog.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.execeptions.*;
import com.example.blog.entities.User;
import com.example.blog.payloads.UserDto;
import com.example.blog.repositories.UserReo;
import com.example.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserReo userRepo;
	
	@Autowired
	private ModelMapper modelmapper; 
	
	
	@Override
	public UserDto createUser(UserDto user) {
		// TODO Auto-generated method stub
		
		User mappedUser=this.modelmapper.map(user,User.class );
		User  savedUser=this.userRepo.save(mappedUser);
		return this.modelmapper.map(savedUser,UserDto.class);
	
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
	
	
		// TODO Auto-generated method stub
		
	     User user=this.userRepo.findById(userId).orElseThrow( ()->new ResourceNotFoundException("user","id",userId));
		
		user.setName(userDto.getName());
		userDto.setEmail(userDto.getEmail());
		userDto.setPassword(userDto.getPassword());
		userDto.setAbout(userDto.getAbout());
		
        User updatedUser=this.userRepo.save(user);
		
		return this.modelmapper.map(updatedUser, UserDto.class);
	
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		
		 User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","id",userId));
		 
		 return this.modelmapper.map(user, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		
		List<User> users=this.userRepo.findAll();
		
	    List<UserDto> userDtos=users.stream().map(user->this.modelmapper.map(user, UserDto.class)).collect(Collectors.toList());
		
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		
		
	User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user", "Id", userId));
		
	this.userRepo.delete(user);
	
	
	
		
	}

}
