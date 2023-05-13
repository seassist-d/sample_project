/*
 * nonameAdmin
 * ItemDAO.java
 * 
 */

package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.ItemBean;

/**
 * 
 */

/**
 * ItemのDAOクラス
 * 
 * @author 山田裕也
 * @version 1.00
 * 
 */
public class ItemDAO {

	/**
	 * 商品情報全件取得
	 * 
	 * @return 商品情報が入ったリスト
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static List<ItemBean> selectAllItem() throws ClassNotFoundException, SQLException {

		// SQL文
		String sql = "SELECT * FROM m_item ORDER BY item_code DESC";

		// リストの生成
		List<ItemBean> itemList = new ArrayList<ItemBean>();

		// DBへ接続、SQL文のセット
		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			ResultSet res = pstmt.executeQuery();

			while (res.next()) {

				ItemBean item = new ItemBean();
				item.setItemCode(res.getInt("item_code"));
				item.setItemName(res.getString("item_name"));
				item.setTar(res.getInt("tar"));
				item.setNicotine(res.getDouble("nicotine"));
				item.setVolume(res.getInt("volume"));
				item.setPrice(res.getInt("price"));
				item.setBio(res.getString("bio"));
				item.setPic(res.getString("pic"));

				itemList.add(item);
			}

		}
		return itemList;
	}

	/**
	 * 検索したワードが含まれる商品一覧を取得
	 * 
	 * @param seachWord 検索ワード
	 * @return 商品リスト
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static List<ItemBean> seachAllItem(String seachWord) throws ClassNotFoundException, SQLException {

		// SQL文
		String sql = "";
		if ("".equals(seachWord)) {
			sql = "SELECT * FROM m_item ORDER BY item_code  DESC";

		} else {
			sql = "SELECT * FROM m_item WHERE item_name LIKE ? ORDER BY item_code DESC";

		}
		// リストの生成
		List<ItemBean> itemList = new ArrayList<ItemBean>();

		// DBへ接続、SQL文のセット
		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

			if (!"".equals(seachWord)) {
				pstmt.setString(1, "%" + seachWord + "%");
			}
			ResultSet res = pstmt.executeQuery();

			while (res.next()) {

				ItemBean item = new ItemBean();
				item.setItemCode(res.getInt("item_code"));
				item.setItemName(res.getString("item_name"));
				item.setTar(res.getInt("tar"));
				item.setNicotine(res.getDouble("nicotine"));
				item.setVolume(res.getInt("volume"));
				item.setPrice(res.getInt("price"));
				item.setBio(res.getString("bio"));
				item.setPic(res.getString("pic"));

				itemList.add(item);

			}

		}
		return itemList;
	}

	/**
	 * 商品1件削除
	 * 
	 * @param itemCode 商品コード
	 * @return 削除された件数
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int deleteItem(int itemCode) throws ClassNotFoundException, SQLException {

		// 処理件数
		int count = 0;

		// SQL文の用意
		String sql1 = "DELETE FROM m_item WHERE item_code = ?";

		// DBへ接続、SQL文のセット
		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql1)) {

			// プレースホルダへ値を設定
			pstmt.setInt(1, itemCode);

			// SQLの実行
			count = pstmt.executeUpdate();
		}
		return count;
	}

	/**
	 * 商品情報を1件取得
	 * 
	 * @param itemCode 商品コード
	 * @return 商品情報
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ItemBean detailItem(int itemCode) throws ClassNotFoundException, SQLException {
		// SQL文の用意
		ItemBean item = new ItemBean();
		String sql = "SELECT * FROM m_item WHERE item_code = ?";

		// DBへ接続、SQL文のセット
		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setInt(1, itemCode);

			ResultSet res = pstmt.executeQuery();

			while (res.next()) {
				item.setItemCode(res.getInt("item_code"));
				item.setItemName(res.getString("item_name"));
				item.setTar(res.getInt("tar"));
				item.setNicotine(res.getDouble("nicotine"));
				item.setVolume(res.getInt("volume"));
				item.setPrice(res.getInt("price"));
				item.setBio(res.getString("bio"));
				item.setPic(res.getString("pic"));
			}
		}
		return item;
	}

	/**
	 * 商品編集
	 * 
	 * @param item 商品情報
	 * @return 更新結果
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int editItem(ItemBean item) throws ClassNotFoundException, SQLException {
		int count = 0;
		String sql = "UPDATE m_item SET " + "item_name = ?, " + "tar = ?, " + "nicotine = ?," + "volume = ?, "
				+ "price = ?," + "bio = ? " + "WHERE item_code = ?";

		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, item.getItemName());
			pstmt.setInt(2, item.getTar());
			pstmt.setDouble(3, item.getNicotine());
			pstmt.setInt(4, item.getVolume());
			pstmt.setInt(5, item.getPrice());
			pstmt.setString(6, item.getBio());
			pstmt.setInt(7, item.getItemCode());

			count = pstmt.executeUpdate();
		}
		return count;
	}

	/**
	 * 商品追加
	 * 
	 * @param item 商品情報
	 * @return 追加結果
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int insertItem(ItemBean item) throws ClassNotFoundException, SQLException {

		int success = 0;

		String sql = "INSERT INTO m_item(item_name, tar, nicotine, volume, price, bio, pic)\n"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?);";

		Connection con = ConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, item.getItemName());
		ps.setInt(2, item.getTar());
		ps.setDouble(3, item.getNicotine());
		ps.setInt(4, item.getVolume());
		ps.setInt(5, item.getPrice());
		ps.setString(6, item.getBio());
		ps.setString(7, item.getPic());

		success = ps.executeUpdate();

		return success;
	}

	/**
	 * 画像の更新
	 * 
	 * @param pic      画像ファイル
	 * @param itemCode 商品コード
	 * @return 更新結果
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static int editPicItem(String pic, int itemCode) throws ClassNotFoundException, SQLException {

		// 更新処理件数
		int success = 0;

		// SQL文
		String sql = "UPDATE m_item SET pic = ? WHERE item_code = ?";

		// データベースに接続、SQL文セット
		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

			// プレースホルダに値を設定
			pstmt.setString(1, pic);
			pstmt.setInt(2, itemCode);

			// 処理件数を代入
			success = pstmt.executeUpdate();
		}
		return success;
	}
}
