package com.springboot.social_media.user;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	/**
	 * Controller -> means talks to DAO -> JPA/HIbernate repository-> Database
	 * 
	 */

	@Autowired
	UsersRepository usersRepository;

	// Create user -> POST /users -> This should create a user and return back an
	// {id}
	public UserEntity CreateUser(UserBody userBody) {
		return usersRepository.save(new UserEntity(userBody.getName(), userBody.getBirthDate()));
	}

	// Read user -> GET /users -> returns back all users
	public List<UserEntity> getAllUsers() {
		return usersRepository.findAll();
	}

	// GET /users/{id} -> returns back a user {id}
	public Optional<UserEntity> getUserById(Long id) {
		// functional programming to convert list to stream and filter for id
		// Predicate<? super User> predicate = user -> user.getId().equals(id);
//		return users.stream().filter(predicate).findFirst().orElse(null);
		return usersRepository.findById(id);
	}

	// Update -> UPDATE /users/{id} -> update user {id}
	public void getUserForId(Long id, String name, LocalDate birthDate) {
//		users.set(id, new User(id, new UserBody( name, birthDate)));
//		return users.get(id);

		Optional<UserEntity> user = usersRepository.findById(id);
		if (user.isPresent()) {
			user.get().setBirthDate(birthDate);
			user.get().setName(name);
		}
		usersRepository.save(new UserEntity(id, user.get().getName(), user.get().getBirthDate()));
	}

	// Delete -> DELETE /users/{id} -> delete user {id}
	public void deleteUserById(Long id) {
//		Predicate<? super User> predicate = user -> user.getId().equals(id);
//		users.removeIf(predicate);
		usersRepository.deleteById(id);

	}

//
//	// DELETE /users -> delete all users in DB
	public void deleteAllUsers() {
		usersRepository.deleteAll();
	}

}
