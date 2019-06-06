package tests;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import thomas.nill.testdaten.ResourceCreatorFabric;
import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.ValueCreatorFabrik;
import thomas.nill.testdaten.basis.Values;

@Slf4j(topic = "test")
public class FabricTest {

	public FabricTest() {
	}

	@Test
	public void testBundle() {
		ResourceBundle labels = ResourceBundle.getBundle("tests.testwords", Locale.getDefault());
		log.debug("Name " + labels.getString("lastname"));
	}

	@Test
	public void testFabric() {
		ValueCreatorFabrik f = new ResourceCreatorFabric("tests.testwords");
		ValueCreator<?> name = f.searchCreator("name");
		ValueCreator<?> email = f.searchCreator("email");
		ValueCreator<?> sex = f.searchCreator("sex");
		for (int i = 0; i < 20; i++) {
			Values v = new Values();
			log.debug("Sex " + sex.generateValue(v));
			log.debug("Name " + name.generateValue(v));
			log.debug("Email " + email.generateValue(v));
		}
	}


	

}
