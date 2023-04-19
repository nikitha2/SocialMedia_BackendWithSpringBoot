package com.springboot.social_media.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	/**
	 * -> means talks to DAO -> JPA/HIbernate -> Database
	 * 
	 */

	private static List<User> users = new ArrayList<User>();
	private static int userCount=0;
	
	static {
		users.add(new User(userCount++, new UserBody("Pink", LocalDate.now().minusYears(30))));
		users.add(new User(userCount++, new UserBody( "Red", LocalDate.now().minusYears(20))));
		users.add(new User(userCount++, new UserBody( "Blue", LocalDate.now().minusYears(25))));
		users.add(new User(userCount++, new UserBody( "Yellow", LocalDate.now().minusYears(30))));
	}
	
	//Create user -> POST /users  -> This should create a user and return back an {id}
	public Integer CreateUser(UserBody userBody) {
		int id=userCount++;
		Boolean isAdded = users.add(new User(id,userBody));
		if(isAdded)
			return id;
		else
			return -1;
		
	}

	// Read user -> GET /users -> returns back all users
	public List<User> getAllUsers() {
		return users;
	}

	// GET /users/{id} -> returns back a user {id}
	public User getUser(Integer id) {
		//functional programming to convert list to stream and filter for id
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		return users.stream().filter(predicate).findFirst().orElse(null);
	}

	// Update -> UPDATE /users/{id} -> update user {id}
	public User getUserForId(Integer id, String name, LocalDate birthDate) {
		users.set(id, new User(id, new UserBody( name, birthDate)));
		return users.get(id);
	}

	// Delete -> DELETE /users/{id} -> delete user {id}
	public void deleteUserById(Integer id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		users.removeIf(predicate);
	}
//
//	// DELETE /users -> delete all users in DB
//	public Boolean deleteAllUsers() {
//		return users.(users);
//	}

}
