/**
 * nonameAdmin
 * MenuServlet.java
 */

package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.AdminBean;

/**
 * Servlet implementation class MenuServlet
 */
/**
 * メニュー画面をコントロールするクラス
 * 
 * @author 福尾大輔
 * @version 1.00
 *
 * 
 */
@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MenuServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// リクエストのエンコーディング
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの取得
		String button = request.getParameter("button"); // ページで押下されたボタン
		String url = ""; // 遷移先格納

		// セッションオブジェクト取得し、管理者の情報取得
		HttpSession session = request.getSession();
		AdminBean admin = (AdminBean) session.getAttribute("admin");

		// ログイン認証済みならメニュー画面表示
		if (admin != null) {

			// 商品登録ボタン押下
			if ("商品登録".equals(button)) {
				url = "item-register";
			}

			// 商品一覧ボタン押下
			if ("商品一覧".equals(button)) {
				url = "item-list";
			}

			// ログアウトボタン押下
			if ("ログアウト".equals(button)) {

				// ログアウト画面で表示する値をリクエストに値をセット
				request.setAttribute("admin", admin);

				// セッション破棄
				session.invalidate();

				url = "logout-success.jsp"; // ログアウト成功画面
			}

			// メニュー画面ボタンかheaderボタンを押下
			if (("メニュー画面".equals(button)) || ("header").equals(button)) {
				url = "menu.jsp"; // メニュー画面
			}

			// ログイン認証済みでなければログイン画面表示
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
		doGet(request, response);
	}
}