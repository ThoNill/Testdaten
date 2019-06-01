package tests;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import thomas.nill.testdaten.BeanCreator;
import thomas.nill.testdaten.ScriptCreator;
import thomas.nill.testdaten.ResourceCreatorFabric;
import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.ValueCreatorFabrik;
import thomas.nill.testdaten.basis.Values;

@Slf4j(topic="test")
public class TextHolenTest {

	public TextHolenTest() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void test() {
		ResourceBundle labels = ResourceBundle.getBundle("thomas.nill.testdaten.testwords", Locale.getDefault());
		log.debug("Name " + labels.getString("lastname"));
	}

	@Test
	public void test2() {
		ValueCreatorFabrik f = new ResourceCreatorFabric("testwords");
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

	@Test
	public void auswerten() {
		ScriptCreator scriptCreator = new ScriptCreator("testwords","{name}",new ResourceCreatorFabric("testwords"));
		log.debug(scriptCreator.auswerten("{name}").toString());
		log.debug(scriptCreator.auswerten("upper( name ) ").toString());
		log.debug(scriptCreator.auswerten("upper(name) ").toString());
		log.debug(scriptCreator.auswerten("firstChar({firstname}).{lastname}@{provider} ").toString());
		log.debug(scriptCreator.auswerten("thomas.nill.testdaten.NummernBereichCreator[50]").toString());
	}

	@Test
	public void createBean() {
		BeanCreator<Adresse> bc = new BeanCreator<Adresse>(Adresse.class);
		Adresse a = bc.generateValue(new Values());
		log.debug(a.toString());		
		}
		
}
