/**
 * nonameAdmin
 * AdminBean.java
 */
package model.entity;

import java.io.Serializable;

/**
 * 管理者情報のクラス
 * 
 * @author 福尾大輔
 *
 * @version version 1.00
 */
public class AdminBean implements Serializable {
	/**
	 * 管理者コード
	 */
	private int adminCode;

	/**
	 * 管理者キー
	 */
	private int adminKey;

	/**
	 * 管理者ID
	 */
	private String adminId;

	/**
	 * 管理者パスワード
	 */
	private String adminPassword;

	/**
	 * デフォルトコンストラクタ
	 */
	public AdminBean() {

	}

	/**
	 * フィールドadminCodeの値を返します
	 * 
	 * @return adminCode
	 */
	public int getAdminCode() {
		return adminCode;
	}

	/**
	 * フィールドadminCodeの値を設定します
	 * 
	 * @param adminCode
	 */
	public void setAdminCode(int adminCode) {
		this.adminCode = adminCode;
	}

	/**
	 * フィールドadminKeyの値を返します
	 * 
	 * @return adminKey
	 */
	public int getAdminKey() {
		return adminKey;
	}

	/**
	 * フィールドadminKeyの値を設定します
	 * 
	 * @param adminKey
	 */
	public void setAdminKey(int adminKey) {
		this.adminKey = adminKey;
	}

	/**
	 * フィールドadminIdの値を返します
	 * 
	 * @return adminId
	 */
	public String getAdminId() {
		return adminId;
	}

	/**
	 * フィールドadminIdの値を設定します
	 * 
	 * @param adminId
	 */
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	/**
	 * フィールドadminPasswordの値を返します
	 * 
	 * @return adminPassword
	 */
	public String getAdminPassword() {
		return adminPassword;
	}

	/**
	 * フィールドadminPasswordの値を設定します
	 * 
	 * @param adminPassword
	 */
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
}
