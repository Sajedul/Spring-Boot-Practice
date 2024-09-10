package com.blog.controllers;

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

import com.blog.payloads.ApiResponse;
import com.blog.payloads.PostDto;
import com.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	//Creating Post

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> postHandler(@RequestBody PostDto postDto,
			                                   @PathVariable("userId")Integer userId,
			                                   @PathVariable("categoryId")Integer categoryId) {
		
		PostDto createPost= this.postService.createPost(postDto, userId, categoryId);
		
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
		
	}
	
	//get user post
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getUserPost(@PathVariable("userId") Integer userId) {
		//use service layer post service to get user posts
		List<PostDto>userPosts= this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(userPosts,HttpStatus.OK);
		
	}
	
	//get post by category
	
		@GetMapping("/category/{categoryId}/posts")
		public ResponseEntity<List<PostDto>> getUserPostByCategory(@PathVariable("categoryId") Integer categoryId) {
			//use service layer post service to get user posts
			List<PostDto>userPosts= this.postService.getPostByCategory(categoryId);
			return new ResponseEntity<List<PostDto>>(userPosts,HttpStatus.OK);
			
		}
		
		//get all post
		@GetMapping("/allpost")
		public ResponseEntity<List<PostDto>> getAllPost() {
			List<PostDto>allPost = this.postService.getAllPost();
			return new ResponseEntity<List<PostDto>>(allPost,HttpStatus.OK);
		}
		
		//get post details by id
		
		@GetMapping("/posts/{postId}")
		public ResponseEntity<PostDto> getPostById(@PathVariable("postId")Integer postId){
			PostDto postDto= this.postService.getPostById(postId);
			return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
		}
		
		//delete post
		
		@DeleteMapping("/posts/{postId}")
		public ApiResponse deletePost(@PathVariable("postId")Integer postId) {
			this.postService.deletePost(postId);
			return new ApiResponse("Post is successflly delete",true);
		}
		
		//update post
		@PutMapping("/posts/{postId}")
		public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,
				                                  @PathVariable("postId")Integer postId) {
			PostDto updatePost= this.postService.updatePost(postDto, postId);
			return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
			
		}
}
