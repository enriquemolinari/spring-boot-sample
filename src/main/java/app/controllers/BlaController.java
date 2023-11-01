package app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import port.UseCase;

@RestController
public class BlaController {

	private UseCase useCase;

	// public BlaController(UseCase useCase) {
	// this.useCase = useCase;
	// }

	@GetMapping("/bla")
	public void bla() {
		this.useCase.doSomething();
	}

}
