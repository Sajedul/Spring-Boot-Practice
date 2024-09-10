package com.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.PostDto;
import com.blog.repositories.CategoryRepo;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepo;
import com.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		Post post= this.modelMapper.map(postDto, Post.class);
		
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user", "userId",userId));
		Category category= this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "categoryId", categoryId));
		
		post.setImageName("dafault.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post createdPost= this.postRepo.save(post);
		return this.modelMapper.map(createdPost, PostDto.class);
		
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post= this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","post id",postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setContent(postDto.getImageName());
		
		Post updatePost= this.postRepo.save(post);
		return this.modelMapper.map(updatePost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post= this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","post id",postId));
		this.postRepo.delete(post);
	}

	@Override
	public List<PostDto> getAllPost() {
		
		List<Post>allpost = this.postRepo.findAll();
		List<PostDto>postDtos= allpost.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}
	
	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","post id",postId));
		
		return this.modelMapper.map(post, PostDto.class);
	}
	
	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		//find category from database with their Id and store it in a category type variable
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","categoryId",categoryId));
		
		//now find/get post according to post category and its need to store in a list type data because it can return lost of post under a specific category
		List<Post> posts= this.postRepo.findByCategory(category);
		//convert posts into postDto using modelMapper because of security[when we use it or test it for generating response environment like postman or swagger then there is a possibility of leakage or modifying of data directly.so we use modelMapper to convert original data into demo type object data for preventing direct access of data] 
		
		//PostDto postDtoMapper = this.modelMapper.map(post, PostDto.class);
		
		//perform mapping operation on each item or object using stream operation
		List<PostDto> postByCategory= posts.stream().map(post->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postByCategory;
	}
	
	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		//get user from database
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user", "UserId", userId));
		
		//using this user we need to find his/her post from database and stored them on post type data
		List<Post> posts= this.postRepo.findByUser(user);
		
		//convert those each posts into postDto object using modelMapper and perform stream operation
		//List<PostDto> postDtoMapper = (List<PostDto>) this.modelMapper.map(posts, PostDto.class);
		List<PostDto>postByUser =posts.stream().map(post->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postByUser;
	}

	@Override
	public List<Post> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
