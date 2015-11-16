package be.kuleuven.groept.jens;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class PersonTest {
	
	private Person person;

	@Before
	public void setUp() throws Exception {
		person = new Person("Jeff Nul");
	}

	@Test
	public void testGetName() {
		assertEquals("Jeff Nul", person.getName());
	}

	@Test
	public void testGetAge() {
		person.setBirthday(LocalDate.of(1993, 5, 14));
		assertEquals(22, person.getAge());
	}

}
