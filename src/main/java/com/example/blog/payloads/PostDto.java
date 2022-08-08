package com.example.blog.payloads;

import java.util.Date;

import com.example.blog.entities.Category;
import com.example.blog.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
	
	private String title;
	
	private String content;
	
	private String image="default.png";
	
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;

}
