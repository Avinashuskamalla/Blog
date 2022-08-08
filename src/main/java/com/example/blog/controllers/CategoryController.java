package com.example.blog.controllers;

import java.util.List;

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
import com.example.blog.payloads.CategoryDto;
import com.example.blog.service.CatgoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CatgoryService catgoryService;
	
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto)
	{
      CategoryDto	createCategory=	this.catgoryService.createCategory(categoryDto);
		
		
		return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED );
	}
	
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto category,@PathVariable("catId") Integer catId)
	{
	CategoryDto updateCat=this.catgoryService.updateCategory(category,catId);
		
		return new ResponseEntity<CategoryDto>(updateCat,HttpStatus.CREATED) ;	
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer userId)
	{
		this.catgoryService.deleteCategory(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("delete successfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<CategoryDto > getCategory(@PathVariable Integer userId)
	{
	CategoryDto cat=this.catgoryService.getCategory(userId);
		return new ResponseEntity<CategoryDto>(cat,HttpStatus.FOUND);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory()
	{
	List<CategoryDto> cat=this.catgoryService.getAllCategory();
		return new ResponseEntity<List<CategoryDto>>(cat,HttpStatus.FOUND);
	}
	
	
}
