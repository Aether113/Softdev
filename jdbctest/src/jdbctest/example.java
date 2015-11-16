package jdbctest;
import java.sql.*;

public class example {
	
	public static void main(String[] args){
		
		new example().go();
		
	}
	

	private void go() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver loaded...");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String url = "jdbc:mysql://127.0.0.1:3306/quiz";
		String sql = "SELECT * FROM Person;";
		
		try(Connection con = DriverManager.getConnection(url, "root", "")){
			System.out.println("Connected to database: " + url);
			Statement statement = con.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			System.out.println(rs.getFetchSize());
			while(rs.next()){
				System.out.println("whut");
				System.out.print(rs.getInt(1));
				System.out.print("\t" + rs.getString("FirstName"));
				System.out.print("\t" + rs.getString("Name"));
			}
			
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
}
