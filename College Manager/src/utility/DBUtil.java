package utility;

import java.sql.*;
import java.util.ResourceBundle;

public class DBUtil {

	private static String url;
	private static String driverName;
	private static String username;
	private static String password;

	static {

		ResourceBundle rb = ResourceBundle.getBundle("DBDetails");

		url = rb.getString("database.url");
		driverName = rb.getString("database.driverName");
		username = rb.getString("database.username");
		password = rb.getString("database.password");

	}
	public static Connection provideConnection() {

		Connection conn = null;


		try {
			Class.forName(driverName);
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		try {
			conn = DriverManager.getConnection(url, username, password);
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return conn;

	}

}
