package com.springboot.social_media.user;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	private UserDaoService userDaoService;
	
	MessageSource messageSource;
	
	private UserController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/**
	 * If we don't want to use @@Autowired. I can use constructor as shown below.
	 */
//	public UserController(UserDaoService userDaoService) {
//		this.userDaoService = userDaoService;
//	}

	// Create user -> POST /users -> This should create a user and return back
	// users/{id}
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody UserBody userBody) {
		int userId = userDaoService.CreateUser(userBody);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userId).toUri();

		return ResponseEntity.created(location).build();

	}

	// Read user -> GET /users -> returns back all users
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userDaoService.getAllUsers();
	}

	// GET /users/{id} -> returns back a user {id}
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable Integer id) {
		User user = userDaoService.getUser(id);

		if (user == null) {
			throw new UserNotFoundException("id: " + id);
		}

		return user;
	}

	// Update -> UPDATE /users/{id} -> update user {id}
	@PostMapping("/users/{id}")
	public User getUserForId(@PathVariable Integer id, String name, String birthDate) {
		return userDaoService.getUserForId(id, "Nikitha", LocalDate.now().minusYears(10));
	}

	// Delete -> DELETE /users/{id} -> delete user {id}
	@DeleteMapping("/users/{id}")
	public void deleteUserbyId(@PathVariable Integer id) {
		userDaoService.deleteUserById(id);
	}
//		
//		// DELETE /users -> delete all users in DB
//		@DeleteMapping("/users/{id}")
//		public Boolean deleteAllUsers(){
//			return userDaoService.deleteAllUsers();
//		}

	// Update -> UPDATE /users/{id} -> update user {id}
	@GetMapping("/hello-world-i18n")
	public String getHelloWorldI18n() {
		
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null,"Default message", locale );
	}

}
