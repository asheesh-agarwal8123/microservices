package com.asheesh.learning.webservices.restfulwebservices.version;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

	// URI versioning
	
	@GetMapping("/version/person/v1")
	public PersonV1 personV1() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping("/version/person/v2")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

	// Request parameter versioning
	
	@GetMapping(value = "/version/person/param", params = "version=1")
	public PersonV1 personParamV1() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(value = "/version/person/param", params = "version=2")
	public PersonV2 personParamV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	// Header param versioning
	
	@GetMapping(value = "/version/person/header", headers = "X-CUSTOM-VERSION=1")
	public PersonV1 personHeaderV1() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(value = "/version/person/header", headers = "X-CUSTOM-VERSION=2")
	public PersonV2 personHeaderV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	// Media type or Mime type or Accept Header versioning
	
	@GetMapping(value = "/version/person/type", produces = "application/versioning.v1+json")
	public PersonV1 personTypeV1() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(value = "/version/person/type", produces = "application/versioning.v2+json")
	public PersonV2 personTypeV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

}
