package cin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbCon {
	static Connection conn = null;
	public static Connection getConnection() {
		String url = "url";
		Properties props = new Properties();
		props.setProperty("user","un");
		props.setProperty("password","pw");
		props.setProperty("ssl","true");

		try {
			conn = DriverManager.getConnection(url, props);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
