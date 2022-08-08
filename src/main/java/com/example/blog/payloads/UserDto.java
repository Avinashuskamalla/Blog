package com.example.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter 
public class UserDto {

	
	public int id;
	
	@NotNull
	@Size(min=4,message = "Username must be min of 4 character") 
	public  String name;
	
	@Email(message = "Email adddress is not valid")
	public String email;
	
	@NotEmpty
	@Size(min=3,max = 10,message = "Password must be min of 3 chars and max of 10 char")
	public String password;
	
	@NotEmpty
	public String about;

	
}
