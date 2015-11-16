package domain;

import java.time.LocalDate;
import java.time.Period;

/**
 * Class representation for a Person
 * @author Koen Pelsmaekers
 */
public class Person {

	private int id;
	private String name;
	private String firstname;
	private LocalDate birthday;
	
	/**
	 * Constructor for creating a Person with a given id
	 * @param id The id coming from the database
	 * @param name The name of the person
	 * @param firstname The firstname of the person
	 * @param birthday The persons birthday
	 */
	public Person(int id, String name, String firstname, LocalDate birthday) {
		this.id = id;
		this.name = name;
		this.firstname = firstname;
		this.birthday = birthday;
	}
	
	/**
	 * Constructor for creating a new Person that is not yet loaded in the database
	 * @param name The name of the person
	 * @param firstname The firstname of the person
	 * @param birthday The persons birthday
	 */
	public Person(String name, String firstname, LocalDate birthday) {
		this.name = name;
		this.firstname = firstname;
		this.birthday = birthday;
	}

	public int getAge() {
		return Period.between(birthday, LocalDate.now()).getYears();
	}
	
	/**
	 * Gets the id
	 * @return The id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the name
	 * @return The name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the firstname
	 * @return The firstname
	 */
	public String getFirstname() {
		return firstname;
	}
	
	/**
	 * Sets the firstname
	 * @param firstname The firstname
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * Gets the birthday
	 * @return The birthday
	 */
	public LocalDate getBirthday() {
		return birthday;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer( "Person [id=" + id + ", name=" + name + ", firstname="
				+ firstname + ", birthday=" + birthday);
		
		if (birthday != null) {
			sb.append(", age=" + getAge());
		}
		sb.append("]");
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result
				+ ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
