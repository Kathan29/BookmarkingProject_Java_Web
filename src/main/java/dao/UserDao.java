package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import browse.DataStore;
import constants.Gender;
import constants.UserType;
import entities.User;

public class UserDao {
	public List<User> getUser() {
		return DataStore.getUser();
	}

	public User getUser(long userId) {
		
		User user = null;
		
		try {
		    // This line tells the JVM to explicitly load the MySQL driver class
		    Class.forName("com.mysql.cj.jdbc.Driver"); 
		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		}
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/thrill_io", "root", "root");
				Statement stmt = conn.createStatement();) {
			String query = "Select * from user where id="+userId;
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {

				long id = rs.getLong("id");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				int gender_id = rs.getInt("gender_id");
				Gender gender = Gender.values()[gender_id];
				int user_type_id = rs.getInt("user_type_id");
				UserType userType = UserType.values()[user_type_id];

				user = User.newInstance(id, email, password, firstName, lastName, userType,
						gender);
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}

	public long authenticate(String email, String encodePassword) {
		try {
		    // This line tells the JVM to explicitly load the MySQL driver class
		    Class.forName("com.mysql.cj.jdbc.Driver"); 
		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		}
		
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/thrill_io", "root", "root");
				Statement stmt = conn.createStatement();){
			
			String query = "Select id from user where email='"+email+"' and password='"+encodePassword+"'";
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				return rs.getLong("id");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
}
