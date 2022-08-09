package com.example.blog.service;

import java.util.List;

import com.example.blog.entities.Post;
import com.example.blog.entities.User;
import com.example.blog.payloads.PostDto;
import com.example.blog.payloads.PostResponse;

public interface PostService {
	
	
	//create
	
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	
	//update
	PostDto updatePost(PostDto dto,Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
	//get
	
	
	//getByid
	PostDto getPostById(Integer postId);
	
	//getByCategory
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	
	List<PostDto> getPostsByUser(Integer userId);
	
	List<PostDto> searchPosts(String keyword);


	PostResponse  getAllPost(Integer pageNumber, Integer pageSize,String sortBy);
	

}
