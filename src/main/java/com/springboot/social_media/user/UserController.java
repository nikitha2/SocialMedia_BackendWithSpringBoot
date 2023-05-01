package com.springboot.social_media.user;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	private UserDaoService userDaoService;

	/**
	 * If we don't want to use @@Autowired. I can use constructor as shown below.
	 */
//	public UserController(UserDaoService userDaoService) {
//		this.userDaoService = userDaoService;
//	}

	// Create user -> POST /users -> This should create a user and return back
	// users/{id}
	@PostMapping("/users")
	public ResponseEntity<UserEntity> createUser(@Valid @RequestBody UserBody userBody) {
		UserEntity userId = userDaoService.CreateUser(userBody);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userId).toUri();

		return ResponseEntity.created(location).build();

	}

	// Read user -> GET /users -> returns back all users
	@GetMapping("/users")
	public List<UserEntity> getAllUsers() {
		return userDaoService.getAllUsers();
	}

	// GET /users/{id} -> returns back a user {id}
	@GetMapping("/users/{id}")
	public EntityModel<Optional<UserEntity>> getUser(@PathVariable Long id) {
		Optional<UserEntity> user = userDaoService.getUserById(id);

		if (user.isEmpty()) {
			throw new UserNotFoundException("id: " + id);
		}

		EntityModel<Optional<UserEntity>> entityModel = EntityModel.of(user); // wraps user with entityModel
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());

		entityModel.add(link.withRel("all-users"));
		return entityModel;
	}

	// Update -> UPDATE /users/{id} -> update user {id}
	@PostMapping("/users/{id}")
	public void getUserForId(@PathVariable Long id, String name, String birthDate) {
		userDaoService.getUserForId(id, "Nikitha", LocalDate.now().minusYears(10));
	}

	// Delete -> DELETE /users/{id} -> delete user {id}
	@DeleteMapping("/users/{id}")
	public void deleteUserbyId(@PathVariable Long id) {
		userDaoService.deleteUserById(id);
	}

	// DELETE /users -> delete all users in DB
	@DeleteMapping("/users")
	public void deleteAllUsers() {
		userDaoService.deleteAllUsers();
	}

}
