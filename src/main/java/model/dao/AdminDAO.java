/**
 * nonameAdmin
 * UserDAO.java
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.AdminBean;

/**
 * 
 * AdminのDAOクラス
 * 
 * @author 福尾大輔
 *
 * @version version 1.00
 */
public class AdminDAO {
	/**
	 * ログインチェック
	 * 
	 * @param id 管理者ID
	 * @param pw 管理者パスワード
	 * @return 管理者情報
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static AdminBean loginCheck(String id, String pw) throws ClassNotFoundException, SQLException {
		AdminBean admin = null;
		String sql = "SELECT * FROM m_admin WHERE admin_id = ? AND admin_password = ?";

		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			ResultSet res = pstmt.executeQuery();

			if (res.next()) {
				admin = new AdminBean();
				admin.setAdminCode((res.getInt("admin_code")));
				admin.setAdminId(res.getString("admin_id"));
				admin.setAdminPassword(res.getString("admin_password"));
			}
		}

		return admin;
	}
}
