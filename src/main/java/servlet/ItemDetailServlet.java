/**
 * nonameAdmin
 * ItemDetailServlet.java
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
 * Servlet implementation class ItemDetail
 */
/**
 * 商品詳細をコントロールするクラス
 * 
 * @author 福尾大輔
 * @version 1.00
 *
 * 
 */
@WebServlet("/item-detail")
public class ItemDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemDetailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストのエンコーディング指定
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメーターの取得
		int itemCode = Integer.parseInt(request.getParameter("itemCode")); // 商品コード
		String button = request.getParameter("button"); // ボタン判定
		String pageCheck = request.getParameter("pageCheck"); // ページ判定
		String url = "item-detail.jsp"; // 画面遷移先を格納
		String seachWord = request.getParameter("seachWord"); // 検索ワード
		String seachCheck = request.getParameter("seachCheck"); // 検索チェック

		// 検索されていなければ空文字を代入
		if (seachCheck == null) {
			seachCheck = "";
		}

		// インスタンス化
		ItemBean item = new ItemBean();

		try {

			// 選択された商品の情報を取得するメソッド
			item = ItemDAO.detailItem(itemCode);

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		// セッションオブジェクト取得
		HttpSession session = request.getSession();

		// ログイン済み認証
		if (session.getAttribute("admin") != null) {

			try {

				// 選択された商品の情報を取得するメソッド
				item = ItemDAO.detailItem(itemCode);

			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}

			// 編集ボタンを押下
			if ("編集".equals(button)) {
				url = "item-edit";
			}

			// 画像編集ボタンを押下
			if ("画像編集".equals(button)) {
				url = "item-edit-pic";
			}

			// 削除ボタンを押下
			if ("削除".equals(button)) {
				url = "item-delete";
			}

			// 戻るボタンを押下
			if ("戻る".equals(button) && "detail".equals(pageCheck)) {
				url = "item-list";
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
		doGet(request, response);
	}
}