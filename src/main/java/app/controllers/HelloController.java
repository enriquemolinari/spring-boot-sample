package app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	private AService aservice;

	public HelloController(AService aservice) {
		this.aservice = aservice;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/hello")
	public String hello() {
		return "hola mundo!";
	}

	@GetMapping(path = "/service")
	public void aService() {
		aservice.bla();
	}

	@GetMapping(path = "/hello2")
	public String hello2() {
		return "hola mundo 2!";
	}

	@GetMapping(path = "/path/{name}")
	public String helloPath(@PathVariable String name) {
		return "hola: " + name;
	}
}
