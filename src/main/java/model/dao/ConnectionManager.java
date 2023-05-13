/*
 * nonameAdmin
 * model.dao.ConnectionManager.java
 * 
 */
package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * コネクションマネージャ
 * 
 * @author 福尾
 * @version 1.00
 */
public class ConnectionManager {
	/**
	 * データベースURL
	 */
	private final static String URL = "jdbc:mysql://localhost:3306/noname_db";
//    private final static String URL = "jdbc:mysql://localhost:3306/noname_dbg";

	/**
	 * ユーザ
	 */
	private final static String USER = "noname";

	/**
	 * パスワード
	 */
	private final static String PW = "noname";

	/**
	 * データベースへの接続を取得して返します
	 * 
	 * @return コネクション
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static Connection getConnection() throws SQLException, ClassNotFoundException {

		// JDBCドライバの読み込み
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(URL, USER, PW);
	}
}