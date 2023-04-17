package com.springboot.social_media.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class UserDaoService {

	/**
	 * -> means talks to DAO -> JPA/HIbernate -> Database
	 * 
	 */

	private static List<User> users = new ArrayList<User>();

	static {
		users.add(new User(0, "Pink", LocalDate.now().minusYears(30)));
		users.add(new User(1, "Red", LocalDate.now().minusYears(20)));
		users.add(new User(2, "Blue", LocalDate.now().minusYears(25)));
		users.add(new User(3, "Yellow", LocalDate.now().minusYears(30)));
	}

	// Read user -> GET /users -> returns back all users
	public List<User> getAllUsers() {
		return users;
	}

	// GET /users/{id} -> returns back a user {id}
	public User getUser(Integer id) {
		return users.get(id);
	}

	// Update -> UPDATE /users/{id} -> update user {id}
	public User getUserForId(Integer id, String name, LocalDate birthDate) {
		users.set(id, new User(id, name, birthDate));
		return users.get(id);
	}

	// Delete -> DELETE /users/{id} -> delete user {id}
//	public void deleteUserForId(Integer id) {
//		users.remove(id);
//	}
//
//	// DELETE /users -> delete all users in DB
//	public void deleteAllUsers() {
//		users.removeAll(users);
//	}

}
