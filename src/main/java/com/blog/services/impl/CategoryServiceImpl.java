package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.CategoryDto;
import com.blog.repositories.CategoryRepo;
import com.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		//convert categoryDto object to Category object
		Category cat = this.modelMapper.map(categoryDto, Category.class);
		//save category type object into database
		Category addCategory = this.categoryRepo.save(cat);
		//As function return type is CategoryDto so we need to convert it into CategoryDto again
		return this.modelMapper.map(addCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
		
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updatedCategory= this.categoryRepo.save(cat);
		return this.modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "category id", categoryId));
		
		this.categoryRepo.delete(cat);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category id",categoryId));
		
		return this.modelMapper.map(category, CategoryDto.class);
		
	}

	@Override
	public List<CategoryDto> getCategories() {
		//find all category data from database and store it in a  Category type variable
		List<Category> categories= this.categoryRepo.findAll();
		//Converts the list of Category objects (retrieved from the repository) into a stream so that you can perform operations on it.
		List<CategoryDto> catDtos = (List<CategoryDto>) categories.stream().
				map((category)->this.modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());//map method takes two parameter source and destination 
		
		return catDtos;
	}

}
