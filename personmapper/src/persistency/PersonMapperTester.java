package persistency;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import domain.Person;

public class PersonMapperTester {

	private Person myPerson;

	@Before
	public void setUp() throws Exception {
		LocalDate michaelJacksonBirthdate = LocalDate.of(1958, 8, 29);
		this.myPerson = new Person("Jackson", "Michael", michaelJacksonBirthdate);

	}
	
	/*
	 * Test method for 'persistency.PersonMapper.createPerson(Person)'
	 */
	@Test
	public void testCreatePerson() {
		int id = PersonMapper.UNIQUEINSTANCE.createPerson(this.myPerson);
		assertEquals("No consistent ID's when creating a person", id,
				PersonMapper.UNIQUEINSTANCE.getPersonById(id).getId());
		PersonMapper.UNIQUEINSTANCE.deletePerson(id);
	}

	/*
	 * Test method for 'persistency.PersonMapper.deletePerson(int)'
	 */
	@Test
	public void testDeletePerson() {
		int id = PersonMapper.UNIQUEINSTANCE.createPerson(this.myPerson);
		assertEquals("Person not deleted",1 , PersonMapper.UNIQUEINSTANCE.deletePerson(id));
	}

	/*
	 * Test method for 'persistency.PersonMapper.updatePerson(Person)'
	 */
	@Test
	public void testUpdatePerson() {
		int id = PersonMapper.UNIQUEINSTANCE.createPerson(this.myPerson);
		Person testPerson = PersonMapper.UNIQUEINSTANCE.getPersonById(id);
		testPerson.setFirstname("Janet");
		assertEquals("Update not succeded",1,PersonMapper.UNIQUEINSTANCE.updatePerson(testPerson));
		assertEquals("Updated value not changed",
				testPerson.getFirstname(),
				PersonMapper.UNIQUEINSTANCE.getPersonById(id).getFirstname());
		PersonMapper.UNIQUEINSTANCE.deletePerson(id);
	}

}
