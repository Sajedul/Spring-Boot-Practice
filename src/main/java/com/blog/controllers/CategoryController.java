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
import com.blog.payloads.CategoryDto;
import com.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	@Autowired
	private  CategoryService categoryService;
	
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto createCategoryDto = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategoryDto,HttpStatus.CREATED);
	}
	
	//Update
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable("categoryId")Integer categoryId) {
		CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, categoryId);
		
		return new ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.OK);
		
	}
	
	//Delete
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId")Integer categoryId) {
		
		this.categoryService.deleteCategory(categoryId);
		ApiResponse response= new ApiResponse("Category successfully Deleted",true);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
		
	}
	
	//Get Category
		@GetMapping("/")
		public ResponseEntity<List<CategoryDto>> getAllCategory() {
			
			List<CategoryDto> categories=this.categoryService.getCategories();
			return ResponseEntity.ok(categories);
			//return ResponseEntity.ok(this.categoryService.getCategories());
			
		}
		
	//Get Single Category
		@GetMapping("/{categoryId}")
		public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable("categoryId")Integer categoryId ) {
			
			CategoryDto singleCategory= this.categoryService.getCategory(categoryId);
			return new ResponseEntity<CategoryDto>(singleCategory,HttpStatus.OK);
			
		}
}
