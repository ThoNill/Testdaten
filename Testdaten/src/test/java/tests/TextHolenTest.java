package tests;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

import thomas.nill.testdaten.BeanCreator;
import thomas.nill.testdaten.ScriptCreator;
import thomas.nill.testdaten.basis.ResourceCreatorFabric;
import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.ValueCreatorFabrik;
import thomas.nill.testdaten.basis.Values;

public class TextHolenTest {

	public TextHolenTest() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void test() {
		ResourceBundle labels = ResourceBundle.getBundle("thomas.nill.testdaten.testwords", Locale.getDefault());
		System.out.println("Name " + labels.getString("lastname"));
	}

	@Test
	public void test2() {
		ValueCreatorFabrik f = new ResourceCreatorFabric("testwords");
		ValueCreator<?> name = f.searchCreator("name");
		ValueCreator<?> email = f.searchCreator("email");
		ValueCreator<?> sex = f.searchCreator("sex");
		for (int i = 0; i < 20; i++) {
			Values v = new Values();
			System.out.println("Sex " + sex.generateValue(v));
			System.out.println("Name " + name.generateValue(v));
			System.out.println("Email " + email.generateValue(v));
		}
	}

	@Test
	public void auswerten() {
		ScriptCreator scriptCreator = new ScriptCreator("testwords","{name}",new ResourceCreatorFabric("testwords"));
		System.out.println(scriptCreator.auswerten("{name}"));
		System.out.println(scriptCreator.auswerten("upper( name ) "));
		System.out.println(scriptCreator.auswerten("upper(name) "));
		System.out.println(scriptCreator.auswerten("firstChar({firstname}).{lastname}@{provider} "));
		System.out.println(scriptCreator.auswerten("thomas.nill.testdaten.NummernBereichCreator[50]"));
	}

	@Test
	public void createBean() {
		BeanCreator<Adresse> bc = new BeanCreator<Adresse>(Adresse.class);
		Adresse a = bc.generateValue(new Values());
		System.out.println(a);		
		}
		
}
