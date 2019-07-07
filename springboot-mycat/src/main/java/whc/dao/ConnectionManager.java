package whc.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 仅供jdbc使用
 */
public class ConnectionManager {
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:8066/TESTDB";
	private static final String user = "root";
	private static final String password = "root";
	private static Connection cn = null;

	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("未找到mysql驱动", e);
		}
	}
	
	private ConnectionManager() {
	}
	
	public static Connection getConnection() throws Exception{ 
		if (cn == null) {
			return DriverManager.getConnection(url, user, password);
		}
		return cn;
	}

}
