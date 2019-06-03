package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import thomas.nill.testdaten.ConstantCreator;
import thomas.nill.testdaten.IdCreator;
import thomas.nill.testdaten.LookupCreator;
import thomas.nill.testdaten.NumberCreator;
import thomas.nill.testdaten.RangeCreator;
import thomas.nill.testdaten.StringListCreator;
import thomas.nill.testdaten.SwitchCreator;
import thomas.nill.testdaten.basis.ValueCreator;
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

}
