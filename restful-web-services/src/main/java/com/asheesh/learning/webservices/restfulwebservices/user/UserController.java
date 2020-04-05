package com.asheesh.learning.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

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
import io.swagger.annotations.Tag;

@RestController
public class UserController {

	@Autowired
	private UserServiceDao userService;

	@GetMapping("/users")
	@ApiOperation(value = "This API allows to retrieve all users in the system")
	public List<User> retrieveAllUsers() {
		return userService.findAll();
	}

	@GetMapping("/users/{id}")
	@ApiOperation(value = "This API allows to retrieve a user based on ID from the system")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		User searchedUser = userService.findUser(id);
		if (searchedUser == null) {
			throw new UserNotFoundException("Id: " + id);
		}

		// HATEOAS Implementation
		WebMvcLinkBuilder allUsersLinkBuilder = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());

		WebMvcLinkBuilder thisUserLinkBuilder = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveUser(id));

		EntityModel<User> entityModel = new EntityModel<User>(searchedUser, thisUserLinkBuilder.withSelfRel(),
				allUsersLinkBuilder.withRel("all-users"));

		return entityModel;
	}

	@PostMapping("/users")
	@ApiOperation(value = "This API allows to create a new user in the system")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User createdUser = userService.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdUser.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/users/{id}")
	@ApiOperation(value = "This API allows to delete a new user by ID in the system")
	public void deleteUser(@PathVariable int id) {
		User user = userService.deleteById(id);
		if (user == null) {
			throw new UserNotFoundException("Id: " + id);
		}

		// As part of Delete response, we can also send the status code as 204
		// return ResponseEntity.noContent().build();
	}

}
