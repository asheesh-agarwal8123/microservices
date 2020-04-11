package com.asheesh.learning.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import com.asheesh.learning.webservices.restfulwebservices.user.exception.UserNotFoundException;

import io.swagger.annotations.ApiOperation;

@RestController
public class UserJPAController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@GetMapping("/jpa/users")
	@ApiOperation(value = "This API allows to retrieve all users in the system")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/jpa/users/{id}")
	@ApiOperation(value = "This API allows to retrieve a user based on ID from the system")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		Optional<User> searchedUser = userRepository.findById(id);

		if (!searchedUser.isPresent()) {
			throw new UserNotFoundException("Id: " + id);
		}

		// HATEOAS Implementation
		WebMvcLinkBuilder allUsersLinkBuilder = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());

		WebMvcLinkBuilder thisUserLinkBuilder = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveUser(id));

		EntityModel<User> entityModel = new EntityModel<User>(searchedUser.get(), thisUserLinkBuilder.withSelfRel(),
				allUsersLinkBuilder.withRel("all-users"));

		return entityModel;
	}

	@PostMapping("/jpa/users")
	@ApiOperation(value = "This API allows to create a new user in the system and return the URI for the created User")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User createdUser = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdUser.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/jpa/users/{id}")
	@ApiOperation(value = "This API allows to delete a new user by ID in the system")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);

		// As part of Delete response, we can also send the status code as 204
		// return ResponseEntity.noContent().build();
	}

	@GetMapping("/jpa/users/{user_id}/posts")
	public List<Post> getUserPosts(@PathVariable(name = "user_id") int id) {
		Optional<User> optionalUser = userRepository.findById(id);

		if (!optionalUser.isPresent())
			throw new UserNotFoundException("Id: " + id);

		return optionalUser.get().getPosts();
	}

	@PostMapping("/jpa/users/{user_id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable(name = "user_id") int id, @RequestBody Post post) {
		Optional<User> optionalUser = userRepository.findById(id);

		if (!optionalUser.isPresent())
			throw new UserNotFoundException("Id: " + id);

		User user = optionalUser.get();

		post.setUser(user);

		postRepository.save(post);

		URI postURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{post_id}").buildAndExpand(post.getId())
				.toUri();

		return ResponseEntity.created(postURI).build();
	}

}
