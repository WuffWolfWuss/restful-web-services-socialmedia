package com.springboot.rest.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HellowWord {

	@GetMapping("/helloworld")
	public String helloWorld() {
		return "Hello world";
	}

	@GetMapping("/helloworld-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello world");
	}

	@GetMapping("/helloworld-varible/{name}")
	public HelloWorldBean helloWorlPathVarible(@PathVariable String name, @RequestParam String age) {
		return new HelloWorldBean("Hello " + name + " ,age: " + age); // String.format("Hello %s", name);
	}
}
