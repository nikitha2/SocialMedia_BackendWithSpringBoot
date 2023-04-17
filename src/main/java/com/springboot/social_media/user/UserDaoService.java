package com.springboot.social_media.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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
		users.add(new User(1, "Pink", LocalDate.now().minusYears(30)));
		users.add(new User(2, "Red", LocalDate.now().minusYears(20)));
		users.add(new User(3, "Blue", LocalDate.now().minusYears(25)));
		users.add(new User(4, "Yellow", LocalDate.now().minusYears(30)));
	}
	
	//Create user -> POST /users  -> This should create a user and return back an {id}
	public Integer CreateUser(String userName, LocalDate userBirthDate) {
		Integer userId= users.size()+1;
		try {
			users.add(new User(userId,userName,userBirthDate));
			return userId;
		}catch(Exception e) {
			return -1;
		}
	}

	// Read user -> GET /users -> returns back all users
	public List<User> getAllUsers() {
		return users;
	}

	// GET /users/{id} -> returns back a user {id}
	public User getUser(Integer id) {
		//functional programming to convert list to stream and filter for id
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		return users.stream().filter(predicate).findFirst().get();
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
