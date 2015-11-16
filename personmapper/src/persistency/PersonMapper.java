package persistency;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import storage.Database;
import domain.Person;

/**
 * Singleton class: maps Person objects to Person DB-table.
 * @author Koen Pelsmaekers
 */
public enum PersonMapper {

	//private static PersonMapper uniqueInstance = null;
	UNIQUEINSTANCE;

	/**
	 * Private constructor for Singleton design pattern
	 */
	private PersonMapper() {
		// private Constructor
	}

	/**
	 * Get a Person object by its id
	 * 
	 * @param id The id of the Person to be found
	 * @return Person object or null if it was not found
	 */
	public Person getPersonById(int id) {
		String select = "SELECT PersonId, Name, Firstname, Birthdate FROM Person where PersonId = ?";
		Person person = null;
		try {
			PreparedStatement prepstat = Database.UNIQUEINSTANCE.getConnection().prepareStatement(select);
			prepstat.setInt(1, id);
			person = queryPerson(prepstat);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return person;
	}

	/**
	 * Get all person names
	 * 
	 * @return A Collection of all person names
	 */
	public List<String> getPersonNames() {
		List<String> names = new LinkedList<String>();
		try {
			Statement stmt = Database.UNIQUEINSTANCE.getConnection().createStatement();
			ResultSet rset = stmt.executeQuery("SELECT name FROM Person ORDER BY name");
			while (rset.next()) {
				names.add(rset.getString(1)); // scroll trough the data and fill
												// the collection
			}
			rset.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return names;
	}
	
	/**
	 * Query persons from the database older than "age"
	 * 
	 * @param age The age to query the Person records with
	 * @return A Collection of Person objects older than age
	 */
	public List<Person> getPersonsOlderThan(int age) {
		String sql = "SELECT PersonId, Name, Firstname, Birthdate FROM Person WHERE DATEDIFF(now(),birthdate)/365 > ?";
		try {
			PreparedStatement prepstat = Database.UNIQUEINSTANCE.getConnection().prepareStatement(sql);
			prepstat.setInt(1, age);
			return queryPersons(prepstat);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Store a person in the database; new Java 7 try-with-resources used
	 * 
	 * @param person The Person object that needs to be stored
	 * @return The ID for the customer that is inserted, -1 if the insert did
	 *         not succeed
	 */
	public int createPerson(Person person) {
		int id = -1;
		String sql = "INSERT INTO Person (name, firstname, birthdate) VALUES (?,?,?)";
		try (PreparedStatement pstmt = Database.UNIQUEINSTANCE.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, person.getName());
			pstmt.setString(2, person.getFirstname());
			pstmt.setDate(3, java.sql.Date.valueOf(person.getBirthday()));
			 // executeUpdate() should be called to change something in the database
			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs != null) {
					if (rs.next()) {
						id = rs.getInt(1);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	/**
	 * Delete a person from de database
	 * 
	 * @param id The id of the Person to be deleted
	 * @return Number of rows affected (should be 1)
	 */
	public int deletePerson(int id) {
		int rowsAffected = 0;
		String sql = "DELETE FROM Person WHERE PersonId = ?";
		try {
			PreparedStatement prepstat = Database.UNIQUEINSTANCE.getConnection().prepareStatement(sql);
			prepstat.setInt(1, id);
			rowsAffected = prepstat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}

	/**
	 * Update the columns of a person in de database
	 * 
	 * @param person The Person object with the new data
	 * @return Number of rows affected (should be 1)
	 */
	public int updatePerson(Person person) {
		int rowsAffected = 0;
		String sql = "UPDATE Person SET name = ?, firstname = ?, birthdate = ? WHERE PersonId = ?";
		try (PreparedStatement pstmt = Database.UNIQUEINSTANCE.getConnection().prepareStatement(sql)) {
			pstmt.setString(1, person.getName());
			pstmt.setString(2, person.getFirstname());
			pstmt.setDate(3, java.sql.Date.valueOf(person.getBirthday()));
			pstmt.setInt(4, person.getId());
			 // executeUpdate() should be called to change something in the database
			rowsAffected = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}

	/*
	 * Private helper method to query a Person object
	 */
	private Person queryPerson(PreparedStatement prepstat) {
		Person person = null;
		ResultSet rs = null;
		try {
			rs = prepstat.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("PersonID");
				String name = rs.getString("Name");
				String firstname = rs.getString("firstname");
				Date birthdate = rs.getDate("birthdate");
				person = new Person(id, name, firstname, new java.sql.Date(birthdate.getTime()).toLocalDate());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				prepstat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return person;
	}
	
	/*
	 * Private helper method to query a list of Person objects
	 */
	private List<Person> queryPersons(PreparedStatement prepstat) {
		List<Person> persons = new LinkedList<>();
		ResultSet rs = null;
		try {
			rs = prepstat.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("PersonID");
				String name = rs.getString("Name");
				String firstname = rs.getString("firstname");
				Date birthdate = rs.getDate("birthdate");
				persons.add(new Person(id, name, firstname, birthdate.toLocalDate()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				prepstat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return persons;
	}
}
