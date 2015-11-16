package domain;

import java.time.LocalDate;
import java.util.List;

import persistency.PersonMapper;

/**
 * Class to illustrate a way to abstract away DB-related (= JDBC) code from the business logic.
 * @author Koen Pelsmaekers/Jeroen Van Aken
 *
 */
public class MainProgram {

	public static void main(String[] args) {
		PersonMapper pm = PersonMapper.UNIQUEINSTANCE;
		// query person with id = 1
		Person nr1 = pm.getPersonById(1);
		System.out.println(nr1);
		// get all person names
		List<String> names = pm.getPersonNames();
		for (String name: names) {
			System.out.println(name);
		}
		// insert Michael Jackson in db
		LocalDate michaelJacksonBirthdate = LocalDate.of(1958, 8, 29);
		Person michaelJackson = new Person("Jackson", "Michael", michaelJacksonBirthdate);
		int generatedID = pm.createPerson(michaelJackson);
		System.out.println("generated key: " + generatedID);
		// delete last Michael Jackson inserted from db
		pm.deletePerson(generatedID);
		// query people older than ...
		List<Person> seniors = pm.getPersonsOlderThan(39);
		if (seniors != null && seniors.size() > 0) {
			System.out.println("**** Seniors ****");
			for (Person senior: seniors) {
				System.out.println(senior);
			}
		}
	}

}
