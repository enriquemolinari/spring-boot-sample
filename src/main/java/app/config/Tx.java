package app.config;

import port.UseCase;

public class Tx implements UseCase {

	private UseCase useCase;
	
	public Tx (UseCase useCase) {
		this.useCase = useCase;
	}
	
	@Override
	public void doSomething() {
		System.out.println("Antes");
		
		this.useCase.doSomething();
		
		System.out.println("Despues");
	}
}
