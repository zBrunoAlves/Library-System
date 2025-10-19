package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	private static Connection conn = null;

	public static Connection getConnection() {
		if (conn == null) {
			try {
				Properties prop = loadProperties();
				String url = prop.getProperty("dburl");
				conn = DriverManager.getConnection(url, prop);
				return conn;
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
		
	}

	public static Properties loadProperties() {
		try (FileInputStream fr = new FileInputStream("db.properties")) {
			Properties prop = new Properties();
			prop.load(fr);
			return prop;
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	public static void closeConection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	public static void closeStatement(Statement st) {
		if(st != null) {
			try {
				st.close();
			}catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	public static void closeResultSet(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			}catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
