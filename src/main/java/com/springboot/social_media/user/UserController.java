package com.springboot.social_media.user;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Autowired
	UserDaoService userDaoService;
	
	// Read user -> GET /users -> returns back all users
		@GetMapping("/users")
		public List<User> getAllUsers(){
			return userDaoService.getAllUsers();
		}

		// GET /users/{id} -> returns back a user {id}
		@GetMapping("/users/{id}")
		public User getUser(@PathVariable Integer id){
			return userDaoService.getUser(id);
		}

		// Update -> UPDATE /users/{id} -> update user {id}
		@PostMapping("/users/{id}")
		public User getUserForId(@PathVariable Integer id, String name, String birthDate){
			return userDaoService.getUserForId(id, "Nikitha", LocalDate.now().minusYears(10));
		}

		// Delete -> DELETE /users/{id} -> delete user {id}
//		@DeleteMapping("/users/{id}")
//		public void deleteUserForId(@PathVariable Integer id){
//			userDaoService.deleteUserForId(id);
//		}
//		
//		// DELETE /users -> delete all users in DB
//		@DeleteMapping("/users/{id}")
//		public void deleteAllUsers(){
//			userDaoService.deleteAllUsers();
//		}

}
