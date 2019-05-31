package tests;

import java.util.ArrayList;
import java.util.List;

public class Adresse {
	String lastname;
	String street;
	String firstname;
	String town;
	String sex;
	String name;
	String email;
	List<Anschreiben> anschreiben = new ArrayList<>();

	public Adresse() {
		super();
	}
	
	@Override
	public String toString() {
		return "Adresse [lastname=" + lastname + ", street=" + street + ", firstname=" + firstname + ", town=" + town
				+ ", sex=" + sex + ", name=" + name + ", email=" + email + ", Anzahl Anschreiben: " + anschreiben.size()+ "]";
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void addAnschreiben(Anschreiben a) {
		anschreiben.add(a);
	}
}
