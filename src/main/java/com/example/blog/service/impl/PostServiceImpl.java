package com.example.blog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.blog.entities.Category;
import com.example.blog.entities.Post;
import com.example.blog.entities.User;
import com.example.blog.execeptions.ResourceNotFoundException;
import com.example.blog.payloads.PostDto;
import com.example.blog.payloads.PostResponse;
import com.example.blog.repositories.CategoryRepo;
import com.example.blog.repositories.PostRepo;
import com.example.blog.repositories.UserReo;
import com.example.blog.service.PostService;



@Service
public class PostServiceImpl implements PostService  {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UserReo userReo;

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		// TODO Auto-generated method stub
		
		User user=this.userReo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user", "user id", userId));
		
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "category id", categoryId));
	
		Post post=this.mapper.map(postDto, Post.class);
		
		post.setImageName("default.png");
		
		post.setAddedDate(new Date());
		
		post.setCategory(category);
		
		post.setUser(user);
		
		Post savedPost=  this.postRepo.save(post);
		return this.mapper.map(savedPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto dto, Integer postId) {
		// TODO Auto-generated method stub
		
		
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","postId", postId));
		
		post.setTitle(dto.getTitle());
		post.setContent(dto.getContent());
		post.setImageName(dto.getImage());
		
		Post updatePost=this.postRepo.save(post);
		
		
		return this.mapper.map(updatePost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		
    Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post_id" , postId));
		
    this.postRepo.delete(post); 
		
	}

	@Override
	public PostResponse  getAllPost(Integer pageNumber,Integer pageSize,String sortBy) {
		// TODO Auto-generated method stub 
		
		Pageable p=PageRequest.of(pageNumber, pageSize,Sort.by(sortBy));
		
		Page<Post> pagePost = this.postRepo.findAll(p);
		
		  List<Post> content = pagePost.getContent();
		
		
		
	    List<PostDto> collect = content.stream().map(post->this.mapper.map(post, PostDto.class)).collect(Collectors.toList());
		
	    PostResponse  postResponse=new PostResponse();
	    postResponse.setContent(collect);
	    postResponse.setPageNumber(pagePost.getNumber());
	    postResponse.setPageSize(pagePost.getSize());
	    postResponse.setTotalElments(pagePost.getTotalElements());
	    postResponse.setTotalPages(pagePost.getTotalPages());
	    postResponse.setLastPage(pagePost.isLast());
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		// TODO Auto-generated method stub
		
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "post_id", postId));
		
		return this.mapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("cat", "category_id", categoryId));
		
        List<Post>   posts = this.postRepo.findByCategory(cat);

        List<PostDto> collect = posts.stream().map((post)->this.mapper.map(post, PostDto.class)).collect(Collectors.toList());
         
		return collect;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userReo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","user_id ",userId));
		
		List<Post> posts = this.postRepo.findByUser(user);
		
		List<PostDto> collect = posts.stream().map((post)->this.mapper.map(post,PostDto.class)).collect(Collectors.toList());
		
		return collect;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
