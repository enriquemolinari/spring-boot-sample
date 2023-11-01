package archunit;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RestController;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;

public class ArchTests {

	@Test
	public void controllerNames() {
		JavaClasses importedClasses = new ClassFileImporter()
				.importPackages("..app..", "port");
		classes().that().areAnnotatedWith(RestController.class).should()
				.haveSimpleNameEndingWith("Controller").check(importedClasses);
	}

	@Test
	// just an example, does not imply any structure in this codebase
	public void controllersShouldOnlyDependOnRepositories() {
		JavaClasses importedClasses = new ClassFileImporter()
				.importPackages("..app..", "port");
		classes().that().resideInAPackage("app.controllers").should()
				.onlyDependOnClassesThat()
				.resideInAnyPackage("app.controllers", "app.repositories",
						"port", "java..", "javax..", "..springframework..")
				.check(importedClasses);
	}
}
