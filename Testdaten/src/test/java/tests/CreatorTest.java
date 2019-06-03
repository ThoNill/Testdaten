package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import thomas.nill.testdaten.BeanCreator;
import thomas.nill.testdaten.ConstantCreator;
import thomas.nill.testdaten.IdCreator;
import thomas.nill.testdaten.LookupCreator;
import thomas.nill.testdaten.NumberCreator;
import thomas.nill.testdaten.RangeCreator;
import thomas.nill.testdaten.ResourceCreatorFabric;
import thomas.nill.testdaten.ScriptCreator;
import thomas.nill.testdaten.StringListCreator;
import thomas.nill.testdaten.SwitchCreator;
import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.ValueCreatorFabrik;
import thomas.nill.testdaten.basis.Values;

@Slf4j(topic = "test")
public class CreatorTest {

	public CreatorTest() {
	}

	@Test
	public void testNummernBereichCreator() {
		RangeCreator c = new RangeCreator(20);
		Values v = new Values();
		for (int i = 0; i < 100; i++) {
			int erg = c.generateValue(v);
			if (erg > 20 || erg < 0) {
				fail("Value " + erg + " is not between 0,.. 20");
			}
		}
	}

	@Test
	public void testStringListeCreator() {
		testStringListeCreator("eins|zwei|drei");
		testStringListeCreator("eins");
	}

	public void testStringListeCreator(String text) {
		StringListCreator c = new StringListCreator(text);
		Values v = new Values();
		for (int i = 0; i < 100; i++) {
			String erg = c.generateValue(v);
			if (!text.contains(erg)) {
				fail("Value " + erg + " is not in " + text);
			}
		}
	}

	@Test
	public void testConstantCreator() {
		testConstantCreator("eins");
		testConstantCreator("zwei");
	}

	public void testConstantCreator(Object obj) {
		ConstantCreator<Object> c = new ConstantCreator(obj);
		Values v = new Values();
		for (int i = 0; i < 100; i++) {
			Object erg = c.generateValue(v);
			if (!obj.equals(erg)) {
				fail("Value " + erg + " is not " + obj);
			}
		}
	}

	@Test
	public void testLookupCreator() {
		testLookupCreator("eins|zwei|drei|vier");
	}

	public void testLookupCreator(String text) {
		StringListCreator c = new StringListCreator(text);
		LookupCreator<String> lc = new LookupCreator<String>("test", c);
		Values v = new Values();
		String erg1 = lc.generateValue(v);
		String erg2 = lc.generateValue(v);
		assertEquals(erg1, erg2);

		int countDifferent = 0;
		for (int i = 0; i < 100; i++) {
			v = new Values();
			Object erg3 = lc.generateValue(v);
			v = new Values();
			Object erg4 = lc.generateValue(v);
			if (!erg4.equals(erg3)) {
				countDifferent++;
			}
		}
		if (countDifferent == 0) {
			fail("no differences");
		}

	}

	@Test
	public void testIdCreator() {
		IdCreator c = new IdCreator(1);
		Values v = new Values();
		for (long i = 1; i < 100; i++) {
			long l = c.generateValue(v);
			assertEquals(l, i);
		}
	}

	@Test
	public void testSwitchCreator() {
		ArrayList<ValueCreator<?>> liste = new ArrayList<>();
		liste.add(new StringListCreator("eins|zwei|drei"));
		liste.add(new RangeCreator(30));

		int countInt = 0;
		int countString = 0;
		SwitchCreator c = new SwitchCreator(liste);
		Values v = new Values();
		for (long i = 1; i < 100; i++) {
			Object o = c.generateValue(v);
			if (o instanceof Integer) {
				int n = ((Integer) o).intValue();
				if (n < 0 || n > 30) {
					fail("wrong number " + n);
				}
				countInt++;
			}
			if (o instanceof String) {
				String n = (String) o;
				if ("eins|zwei|drei".indexOf(n) < 0) {
					fail("wrong text " + n);
				}
				countString++;
			}

		}
		if (countInt == 0) {
			fail("no number");
		}
		if (countString == 0) {
			fail("no text");
		}

	}

	@Test
	public void testZahlCreator() {
		NumberCreator c = new NumberCreator(10);
		Values v = new Values();
		for (long i = 1; i < 100; i++) {
			String o = c.generateValue(v);
			if (o.length() > 10) {
				fail("wrong number " + o);

			}
		}

	}

	@Test
	public void testScriptCreator() {
		ScriptCreator scriptCreator = new ScriptCreator("testwords", "{name}",
				new ResourceCreatorFabric("tests.testwords"));
		log.debug(scriptCreator.auswerten("{name}").toString());
		log.debug(scriptCreator.auswerten("upper( name ) ").toString());
		log.debug(scriptCreator.auswerten("upper(name) ").toString());
		log.debug(scriptCreator.auswerten("firstChar({firstname}).{lastname}@{provider} ").toString());
		log.debug(scriptCreator.auswerten("thomas.nill.testdaten.RangeCreator[50]").toString());
	}

	@Test
	public void testScriptError() {
		testScriptError("{test");
		testScriptError("{test{sex}}");
	}

	public void testScriptError(String script) {
		ScriptCreator scriptCreator = new ScriptCreator("testwords", "{name}",
				new ResourceCreatorFabric("tests.testwords"));
		try {
			log.debug(scriptCreator.auswerten(script).toString());
			fail("i want a Exception");
		} catch (Exception e) {
			log.debug("Exception: " + e);
		}
	}

	@Test
	public void testBeanCreator() {
		BeanCreator<Adresse> bc = new BeanCreator<Adresse>(Adresse.class);
		Adresse a = bc.generateValue(new Values());
		log.debug(a.toString());
	}

	@Test
	public void testPhoneCreator() {
		ValueCreatorFabrik f = new ResourceCreatorFabric("tests.testwords");

		PhoneCreator c = new PhoneCreator();
		String phone = c.generateValue(new Values());
		log.debug("Telefon: " + phone);
		Pattern p = Pattern.compile("[0-9]+-[0-9]+");
		Matcher m = p.matcher(phone);
		assertTrue(m.matches());
	}

	@Test
	public void testStreetCreator() {
		ValueCreatorFabrik f = new ResourceCreatorFabric("tests.testwords");

		StreetCreator c = new StreetCreator(f);
		String street = c.generateValue(new Values());
		log.debug("Strasse: " + street);
		Pattern p = Pattern.compile("[A-Za-zƒ÷‹‰ˆ¸ﬂ]+ +[0-9]+");
		Matcher m = p.matcher(street);
		assertTrue(m.matches());
	}

}
