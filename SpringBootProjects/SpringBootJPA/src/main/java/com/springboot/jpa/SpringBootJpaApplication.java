package com.springboot.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.springboot.jpa.dao.UserRepository;
import com.springboot.jpa.entities.User;

@SpringBootApplication
public class SpringBootJpaApplication {

	public static void main(String[] args) {
		
	ApplicationContext context=SpringApplication.run(SpringBootJpaApplication.class, args);
	UserRepository userRepository = context.getBean(UserRepository.class);
	/*
	 * //Saving Single User
	 *  User user =new User(); user.setName("Manzil");
	 * user.setCity("Mymensging"); user.setStatus("taking exam preparation"); 
	 * User user1= userRepository.save(user);
	 *  System.out.println(user1);
	 */
     //If we want to save multiple user at a time than we need to create List of object
		
		/*
		 * User user1 = new User(); user1.setName("Shohel"); user1.setCity("Nator");
		 * user1.setStatus("Enjoy policing");
		 * 
		 * User user2 = new User(); user2.setName("Minu"); user2.setCity("Tangail");
		 * user2.setStatus("enjoy banking"); List<User> users = List.of(user1, user2);
		 * Iterable<User> itr = userRepository.saveAll(users);
		 * 
		 * itr.forEach(user -> System.out.println(user));
		 * System.out.println("Saving multiple user is done");
		 */
		//Update user by id
		/*
		 * Optional<User> optional = userRepository.findById(352);
		 * 
		 * User user=optional.get(); user.setName("Sajedul"); User result =
		 * userRepository.save(user); System.out.println(result);
		 */
	//get all user
	Iterable<User>userData= userRepository.findAll();
	userData.forEach(user->System.out.println(user));
	
	//delete user by id
	userRepository.deleteById(353);
	Iterable<User> updatedData = userRepository.findAll();
	updatedData.forEach(user->System.out.println(user));
	//delete All user
	/* userRepository.deleteAll(updatedData); */
	
	//fatch user data from databse using custom finder or derived query method
	
	List<User> data = userRepository.findByName("rahat");
	System.out.println(data);
	
	List<User> item = userRepository.findByNameAndCity("showeb", "nator");
	item.forEach(i->System.out.println(i));
	
	List<User> dataUsingCustomFinder = userRepository.getAllUsers();
	dataUsingCustomFinder.forEach(e->System.out.println(e));
	
	}

}
