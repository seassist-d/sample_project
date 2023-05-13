/**
 * nonameAdmin
 * ItemDeleteServlet.java
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
 * Servlet implementation class ItemDeleteServlet
 */
/**
 * 商品削除をコントロールするクラス
 * 
 * @author 福尾大輔
 * @version 1.00
 * 
 *
 */
@WebServlet("/item-delete")
public class ItemDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemDeleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 画面遷移先を格納
		String url = "item-delete-check.jsp";

		// リクエストのエンコーディング指定
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメーターの取得
		String seachWord = request.getParameter("seachWord"); // 検索ワード
		String seachCheck = request.getParameter("seachCheck"); // 検索チェック
		int code = Integer.parseInt(request.getParameter("itemCode")); // 商品コード

		// 検索していなければ空文字を代入
		if (seachCheck == null) {
			seachCheck = "";
		}

		// インスタンス化
		ItemBean item = new ItemBean();

		// セッションオブジェクトを取得
		HttpSession session = request.getSession();

		// ログイン済み認証
		if (session.getAttribute("admin") != null) {

			try {
				// 選択された商品の情報を取得するメソッド
				item = ItemDAO.detailItem(code);

				// 削除確認画面のURLを格納
				url = "item-delete-check.jsp";

			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}

			// ログイン済み認証失敗でログイン画面へ遷移
		} else {
			url = "login";
		}
		// リクエストを転送
		request.setAttribute("item", item);
		request.setAttribute("seachWord", seachWord);
		request.setAttribute("seachCheck", seachCheck);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 画面遷移先を格納
		String url = "item-delete-result.jsp";

		// リクエストのエンコーディング指定
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメーターの取得
		String seachWord = request.getParameter("seachWord"); // 検索ワード
		String seachCheck = request.getParameter("seachCheck"); // 検索チェック
		int code = Integer.parseInt(request.getParameter("itemCode")); // 商品コード
		String button = request.getParameter("button"); // 押下ボタン判定
		int count = 0; // 処理件数

		// 検索していなければ空文字代入
		if (seachCheck == null) {
			seachCheck = "";
		}

		// インスタンス化
		ItemBean item = new ItemBean();

		// セッションオブジェクト取得
		HttpSession session = request.getSession();

		// ログイン済み認証
		if (session.getAttribute("admin") != null) {

			try {
				// 削除ボタンを押下された場合
				if ("削除".equals(button)) {
					// 選択された商品の情報を取得
					item = ItemDAO.detailItem(code);

					// 選択された商品を削除
					count = ItemDAO.deleteItem(code);

				}

				// 例外処理
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}

			// キャンセルボタンを押下された場合
			if ("キャンセル".equals(button)) {
				url = "item-detail";
			}
			// ログイン済み認証失敗でログイン画面へ遷移
		} else {
			url = "login";
		}

		// リクエストを転送
		// count jspで削除されたか確認する為
		// item 商品情報
		request.setAttribute("count", count);
		request.setAttribute("item", item);
		request.setAttribute("seachWord", seachWord);
		request.setAttribute("seachCheck", seachCheck);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
}