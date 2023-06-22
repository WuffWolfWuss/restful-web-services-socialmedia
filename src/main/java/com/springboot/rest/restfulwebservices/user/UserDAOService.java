package com.springboot.rest.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDAOService {

	private static List<User> users = new ArrayList<>();
	private static int userCount = 0;

	static {
		users.add(new User(++userCount, "Adam", LocalDate.now().minusYears(21)));
		users.add(new User(++userCount, "James", LocalDate.now().minusMonths(2).minusYears(18)));
		users.add(new User(++userCount, "Jack", LocalDate.now().minusYears(15)));
	}

	public List<User> findAllUser() {
		return users;
	}

	public User findUser(int id) {
		Predicate<? super User> predicate = users -> users.getId() == id;
		return users.stream().filter(predicate).findFirst().orElse(null);
	}

	public User saveUser(User user) {
		user.setId(++userCount);
		users.add(user);
		return user;
	}

	public void deleteUser(int id) {
		Predicate<? super User> predicate = users -> users.getId() == id;
		users.removeIf(predicate);
	}

}
