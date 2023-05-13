/**
 * nonameAdmin
 * ItemRegisterServlet.java
 */

package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.ItemDAO;
import model.entity.ItemBean;

/**
 * Servlet implementation class ItemRegisterServlet
 */
/**
 * 商品の登録をコントロールするクラス
 * 
 * @author 福尾大輔
 * @version 1.00
 *
 */
@WebServlet("/item-register")
public class ItemRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemRegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = ""; // 遷移先格納

		// セッションオブジェクト取得
		HttpSession session = request.getSession();

		// ログイン済み認証
		if (session.getAttribute("admin") != null) {
			url = "item-register.jsp";

			// ログイン済み認証済み失敗でログイン画面へ
		} else {
			url = "login";
		}

		// リクエスト転送
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストエンコーディングの指定
		request.setCharacterEncoding("UTF-8");

		// リクエストスコープから値を取得
		String url = "item-register.jsp"; // 遷移先格納
		String process = request.getParameter("process"); // 初期ページ判定
		String button = request.getParameter("button"); // ボタン判定
		String itemName = request.getParameter("itemName"); // 商品名
		int tar = 0; // タール
		double nicotine = 0.0; // ニコチン
		int volume = 0; // 入数
		int price = 0; // 価格
		String bio = request.getParameter("bio"); // 商品詳細
		int success = 0; // 登録結果
		
		if (itemName != null) {
			itemName = itemName.replace("&", "&amp;");
			itemName = itemName.replace("<", "&lt;");
			itemName = itemName.replace(">", "&gt;");
			itemName = itemName.replace("\\", "&quat;");
			itemName = itemName.replace("'", "&#39;");
		}

		if (bio != null) {
			bio = bio.replace("&", "&amp;");
			bio = bio.replace("<", "&lt;");
			bio = bio.replace(">", "&gt;");
			bio = bio.replace("\\", "&quat;");
			bio = bio.replace("'", "&#39;");
		}
		

		// 空文字を代入しておく
		if (process == null) {
			itemName = "";
			bio = "";
		}

		// エラーメッセージ
		String itemNameErrorMsg = "";
		String tarErrorMsg = "";
		String nicotineErrorMsg = "";
		String volumeErrorMsg = "";
		String priceErrorMsg = "";

		ItemBean item = null;

		// セッションオブジェクト取得
		HttpSession session = request.getSession();

		// ログイン済み認証
		if (session.getAttribute("admin") != null) {

			try {
				// 入力画面でクリアボタンを押された場合
				if ("input-end".equals(process) && "クリア".equals(button)) {
					item = null;
				}

				// 入力画面で登録ボタンを押された場合
				if ("input-end".equals(process) && "登録".equals(button)) {

					item = new ItemBean();

					// 未入力の場合エラーメッセージを代入
					if ("".equals(itemName)) {
						itemNameErrorMsg = "商品名が不正です。";
					}

					// 0以下かつ数字以外の場合エラーメッセージを代入
					try {
						tar = Integer.parseInt(request.getParameter("tar"));
						if (tar <= 0) {
							tarErrorMsg = "タールが不正です。";
						}
					} catch (NumberFormatException e) {
						tarErrorMsg = "タールが不正です。";
					}

					// 0以下かつ数字以外の場合エラーメッセージを代入
					try {
						nicotine = Double.parseDouble(request.getParameter("nicotine"));
						if (nicotine <= 0) {
							nicotineErrorMsg = "ニコチンが不正です。";
						}
					} catch (NumberFormatException | NullPointerException e) {
						nicotineErrorMsg = "ニコチンが不正です。";
					}

					// 0以下かつ数字以外の場合エラーメッセージを代入
					try {
						volume = Integer.parseInt(request.getParameter("volume"));
						if (volume <= 0) {
							volumeErrorMsg = "入数が不正です。";
						}
					} catch (NumberFormatException e) {
						volumeErrorMsg = "入数が不正です。";
					}

					// 0以下かつ数字以外の場合エラーメッセージを代入
					try {
						price = Integer.parseInt(request.getParameter("price"));
						if (price <= 0) {
							priceErrorMsg = "価格が不正です。";
						}
					} catch (NumberFormatException e) {
						priceErrorMsg = "価格が不正です。";
					}

					// インスタンスに入力された値をセット
					item.setItemName(itemName);
					item.setTar(tar);
					item.setNicotine(nicotine);
					item.setVolume(volume);
					item.setPrice(price);
					item.setBio(bio);

					// 入力が全て正しく入力された場合、登録確認画面へ遷移
					if (!"".equals(itemName) && tar > 0 && nicotine > 0 && volume > 0 && price > 0) {
						url = "item-register-check.jsp";
					}
				}

				// 入力確認画面で登録ボタンを押下
				if ("register-end".equals(process) && "登録".equals(button)) {
					// リクエストスコープから値を取得
					itemName = request.getParameter("itemName"); // 商品名
					tar = Integer.parseInt(request.getParameter("tar")); // タール
					nicotine = Double.parseDouble(request.getParameter("nicotine")); // ニコチン
					volume = Integer.parseInt(request.getParameter("volume")); // 入数
					price = Integer.parseInt(request.getParameter("price")); // 価格
					bio = request.getParameter("bio"); // 商品詳細

					// インスタンスに値をセット
					item = new ItemBean();
					item.setItemName(itemName);
					item.setTar(tar);
					item.setNicotine(nicotine);
					item.setVolume(volume);
					item.setPrice(price);
					item.setBio(bio);

					// 商品登録
					success = ItemDAO.insertItem(item);

					// 商品登録成功判定
					if (success > 0) {
						url = "item-list"; // 成功時商品一覧画面へ
					} else {
						url = "item-register-failed.jsp"; // 失敗時商品登録失敗画面へ
					}
				}

				// 戻るボタン押下
				if ("戻る".equals(button)) {

					// リクエストスコープの値を取得
					itemName = request.getParameter("itemName"); // 商品名
					tar = Integer.parseInt(request.getParameter("tar")); // タール
					nicotine = Double.parseDouble(request.getParameter("nicotine")); // ニコチン
					volume = Integer.parseInt(request.getParameter("volume")); // 入数
					price = Integer.parseInt(request.getParameter("price")); // 価格
					bio = request.getParameter("bio"); // 商品詳細

					// インスタンスに値をセット
					item = new ItemBean();
					item.setItemName(itemName);
					item.setTar(tar);
					item.setNicotine(nicotine);
					item.setVolume(volume);
					item.setPrice(price);
					item.setBio(bio);

					url = "item-register.jsp"; // 商品登録画面へ遷移
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				url = "item-register-failed.jsp";
			}

			// ログイン済み認証失敗でログイン画面
		} else {
			url = "login";
		}

		// リクエストスコープへ値をセットし、リクエストを転送
		request.setAttribute("item", item);
		request.setAttribute("itemNameErrorMsg", itemNameErrorMsg);
		request.setAttribute("tarErrorMsg", tarErrorMsg);
		request.setAttribute("nicotineErrorMsg", nicotineErrorMsg);
		request.setAttribute("volumeErrorMsg", volumeErrorMsg);
		request.setAttribute("priceErrorMsg", priceErrorMsg);

		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
}