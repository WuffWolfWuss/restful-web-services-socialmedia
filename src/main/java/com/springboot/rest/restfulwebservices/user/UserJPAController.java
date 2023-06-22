package com.springboot.rest.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.rest.restfulwebservices.jpa.PostRepository;
import com.springboot.rest.restfulwebservices.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJPAController {

	private UserRepository userRepository;
	private PostRepository postRepository;

	public UserJPAController(UserRepository userRepository, PostRepository postRepository) {
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}

	@GetMapping("/jpa/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/jpa/users/{id}")
	public User findUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException("user not exist");
		}
		return user.get();
	}

	@PostMapping("/jpa/users")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		User saveUser = userRepository.save(user);

		// users/4 = /users , user.getId
		URI locationUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(saveUser.getId()).toUri();
		return ResponseEntity.created(locationUri).build();
	}

	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}

	//// POST CONTROL ////
	@GetMapping("/jpa/users/{id}/posts")
	public List<Posts> getUserPosts(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException("user not exist");
		}
		return user.get().getPosts();
	}

	@PostMapping("/jpa/users/{id}/posts")
	public List<Posts> createPost(@PathVariable int id, @Valid @RequestBody Posts post) {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException("user not exist");
		}
		post.setUser(user.get());
		postRepository.save(post);

		return user.get().getPosts();
	}
}
