package tests;

import lombok.ToString;

@ToString
public class Anschreiben {
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Anschreiben() {
		super();
	}
	
}
