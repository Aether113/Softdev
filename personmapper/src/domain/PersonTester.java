package domain;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for domain/Person
 * @author Koen Pelsmaekers/Jeroen Van Aken
 *
 */
public class PersonTester {
	
	Person myPerson;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		LocalDate michaelJacksonBirthdate = LocalDate.of(1958, 8, 29);
		this.myPerson = new Person("Jackson", "Michael", michaelJacksonBirthdate);
	}

	/**
	 * Test method for {@link domain.Person#getName()}.
	 */
	@Test
	public void testGetName() {
		assertEquals("Jackson", this.myPerson.getName());
	}

	/**
	 * Test method for {@link domain.Person#getFirstname()}.
	 */
	@Test
	public void testGetFirstname() {
		assertEquals("Michael", this.myPerson.getFirstname());
	}

	/**
	 * Test method for {@link domain.Person#getBirthday()}.
	 */
	@Test
	public void testGetBirthday() {
        LocalDate birthday = LocalDate.of(1958, 8, 29);
        assertEquals(birthday, this.myPerson.getBirthday());
	}
}
