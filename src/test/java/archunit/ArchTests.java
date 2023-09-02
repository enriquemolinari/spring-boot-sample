package archunit;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;

public class ArchTests {

	@Test
	// just an example
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
