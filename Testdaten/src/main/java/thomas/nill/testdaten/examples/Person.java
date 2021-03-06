package thomas.nill.testdaten.examples;

import java.time.LocalDate;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Person {
	String lastname;
	String street;
	String firstname;
	String town;
	String sex;
	String name;
	String email;
	LocalDate birthday;

	public Person() {
		super();
	}
	

}
