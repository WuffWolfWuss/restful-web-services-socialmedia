package com.springboot.rest.restfulwebservices.user;

import java.net.URI;
import java.util.List;

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

	private UserDAOService service;

	public UserController(UserDAOService service) {
		this.service = service;
	}

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return service.findAllUser();
	}

	@GetMapping("/users/{id}")
	public User findUser(@PathVariable int id) {
		User user = service.findUser(id);
		if (user == null) {
			throw new UserNotFoundException("user not exist");
		}
		return user;
	}

	@PostMapping("/users")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		User saveUser = service.saveUser(user);

		// users/4 = /users , user.getId
		URI locationUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(saveUser.getId()).toUri();
		return ResponseEntity.created(locationUri).build();
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		service.deleteUser(id);
	}
}
