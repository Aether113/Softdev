package be.kuleuven.groept.jens;

import java.time.LocalDate;
import java.time.Period;

public class Person {
	private String name;
	private LocalDate birthday;

	public Person(String name) {
		this.name = name;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return Period.between(birthday, LocalDate.now()).getYears();
	}
}
