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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.blog.payloads.ApiResponse;
import com.example.blog.payloads.PostDto;
import com.example.blog.payloads.PostResponse;
import com.example.blog.service.PostService;



@RestController
@RequestMapping("/api/")
public class PostController {
	
	
	@Autowired
	private PostService postService;
	
	

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto dto,@PathVariable Integer userId,@PathVariable Integer categoryId)
	{
		
      PostDto createPost=this.postService.createPost(dto, userId, categoryId);
		
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/user/{catId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer catId)
	{
		
		List<PostDto> postsByCat = this.postService.getPostsByCategory(catId);
		
		return new ResponseEntity<List<PostDto>>(postsByCat,HttpStatus.OK);
	}
	
	
	@GetMapping("/category/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId)
	{
		
		List<PostDto> postsByUser = this.postService.getPostsByUser(userId);
		
		return new ResponseEntity<List<PostDto>>(postsByUser,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete/{postId}")
	public ResponseEntity<ApiResponse>  delete(@PathVariable Integer postId)
	{
		
		this.postService.deletePost(postId);
		
		return new   ResponseEntity<ApiResponse>(new ApiResponse("Post is successfully deleted",true),HttpStatus.OK);
	}
	
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId)
	{
		
		PostDto updatePost=this.postService.updatePost(postDto, postId);
		
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
		
	}
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageNumber",defaultValue = "1",required = false) Integer pageNumber,@RequestParam(value ="pageSize",defaultValue = "5",required = false) Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = "psotId",required = false) String sortBy)
	{
		PostResponse allPost = this.postService.getAllPost(pageNumber, pageSize);
		
		return  new ResponseEntity<PostResponse> (allPost,HttpStatus.OK);
		
		
	}
	
	
	
	
}
 