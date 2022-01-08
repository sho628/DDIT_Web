package kr.or.ddit.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Factory Object[Method] Pattern
 * : consumer 에 의해 소비되는 객체 생성만을 전담하는 객체 운영.
 *
 */
public class ConnectionFactory {
	private static String url;
	private static String user;
	private static String password;
	static {
		ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.db.dbInfo", Locale.ENGLISH);
		url = bundle.getString("url");
		user = bundle.getString("user");
		password = bundle.getString("password");
		String message = bundle.getString("message");
		try {
			Class.forName(bundle.getString("driverClassName"));
			System.out.println(message);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Connection getConnection() throws SQLException{
//	 	3. Connection 생성
		return DriverManager.getConnection(url, user, password);
	}
}




