package com.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

	
	private Integer categoryId;
	@NotBlank
	@Size(min=4,message="Category Title must be min of 4")
	private String categoryTitle;
	@NotBlank
	@Size(min=10,message="Category Title must be min of 10")
	private String categoryDescription;
		
}
