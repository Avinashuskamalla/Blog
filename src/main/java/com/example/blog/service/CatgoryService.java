package com.example.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.blog.payloads.CategoryDto;


public interface CatgoryService {

	
	 //create
	public CategoryDto  createCategory(CategoryDto categoryDto);
	
	//update
	public  CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	
	//delete
	public void deleteCategory(Integer categoryId);
	
	//get
	public CategoryDto getCategory(Integer categoryId);
	
	//get All
	public List<CategoryDto> getAllCategory();
	
	
	
	
}
