package com.example.blog.controllers;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.payloads.ApiResponse;
import com.example.blog.payloads.UserDto;
import com.example.blog.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	//Post-create user
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createDto(@Valid  @RequestBody UserDto userDto)
	{
		
	UserDto createUserDto=this.userService.createUser(userDto);
	return new ResponseEntity<UserDto>(createUserDto, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid  @RequestBody UserDto userDto,@PathVariable("userId") Integer userId)
	{
	
		UserDto dto=this.userService.updateUser(userDto, userId);
		
		return new ResponseEntity<UserDto>(dto,HttpStatus.OK);
		
	}
	 
	@DeleteMapping("{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid)
	{
		this.userService.deleteUser(uid);
		
	//	return new  ResponseEntity(Map.of("message","user Deleted"),HttpStatus.OK)  ;
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successfully",true),HttpStatus.OK);
		
		
	}
	
    @GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers()
	{
    	
    	
    	return new ResponseEntity<List<UserDto>>(this.userService.getAllUsers(),HttpStatus.OK);
    	
	}
    
    
    @GetMapping("/{userId}")
   	public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") Integer userId)
   	{
       	
       	
       	return new ResponseEntity<UserDto>(this.userService.getUserById(userId),HttpStatus.OK);
       	
   	}
    
	
	
}
