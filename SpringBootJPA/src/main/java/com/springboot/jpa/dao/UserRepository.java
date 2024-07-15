package com.springboot.jpa.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.springboot.jpa.entities.User;
import java.util.List;


public interface UserRepository extends CrudRepository<User, Integer> {
	
	public List<User> findByNameAndCity(String name, String city);

	public List<User> findByName(String name);
	
	@Query("select u from User u")
	public List<User> getAllUsers();
	@Query("select u from User u where u.name=:n")
	public List<User> getUserByName(@Param("n")String name);
	
	@Query("select u from User u where u.name=:n and u.city=:c")
	public List<User> getUserByName(@Param("n")String name,@Param("c")String city);
	@Query(value="select*from User",nativeQuery=true)
	public List<User> getUser();

}
