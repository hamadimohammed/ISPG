package Database;

import java.sql.Connection;
import java.sql.SQLException;

public class DbConnect {
	public Connection connection;

	public DbConnect() {
		try {
			connection = SqlConnection.getConn();
			if (connection == null)
				System.exit(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public boolean isDbConnect() {
		try {
			return !connection.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
