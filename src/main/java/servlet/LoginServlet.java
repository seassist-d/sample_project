/**
 * nonameAdmin
 * LoginServlet.java
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

import model.dao.AdminDAO;
import model.entity.AdminBean;

/**
 * Servlet implementation class LoginServlet
 */
/**
 * 
 * ログイン、ログアウトをコントロールするクラス
 * 
 * @author 福尾大輔
 * @version 1.00
 *
 * 
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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

		String url = "login.jsp"; // 遷移先格納

		// リクエストパラメータの取得
		String id = request.getParameter("id"); // 管理者ID
		String pw = request.getParameter("pw"); // パスワード
		String process = request.getParameter("process"); // 初期画面確認のための値
		String button = request.getParameter("button"); // 押下されたボタン

		// エラーメッセージ
		String err = "";
		String idErr = "";
		String pwErr = "";

		// セッションオブジェクト取得
		HttpSession session = request.getSession();

		// 管理者のインスタンス
		AdminBean admin = null;

		// ログアウトボタンを押下
		if (("ログアウト".equals(button)) || ("logout".equals(button))) {
			// セッションの値をリクエストスコープへセット
			admin = (AdminBean) session.getAttribute("admin");
			
			if (admin != null) {

			// リクエストスコープへ値をセット
			request.setAttribute("admin", admin);

			// セッション破棄
			session.invalidate();

			url = "logout-success.jsp"; // ログアウト画面へ遷移
			} else {
				url = "login.jsp";
			}
		}

		// 初期ページ判定
		if (process == null) {
			process = "";
			id = "";
			pw = "";
		}

		// ログインボタンを押下
		if ("end".equals(process)) {

			// id未入力
			if ("".equals(id)) {
				idErr = "※IDを入力してください";
				url = "login.jsp";
			}

			// パスワード未入力
			if ("".equals(pw)) {
				pwErr = "※パスワードを入力してください";
				url = "login.jsp";
			}

			try {
				// データベースのid,pwと一致するかチェック
				admin = AdminDAO.loginCheck(id, pw);

				// 一致している場合遷移先をメニュ画面へ
				if (admin != null) {
					url = "menu.jsp";

					// セッションスコープへ値をセット
					session.setAttribute("admin", admin);

					// 10分でセッション切れる設定
					session.setMaxInactiveInterval(600);

				} else {
					err = "ログイン失敗しました";
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}

		// クリアボタン押下
		if ("クリア".equals(button)) {
			// 入力した値を消すために空文字をセット
			id = "";
			pw = "";
			err = "";
			idErr = "";
			pwErr = "";
		}

		// リクエストスコープへ値をセットし、リクエスト転送
		request.setAttribute("id", id);
		request.setAttribute("pw", pw);
		request.setAttribute("err", err);
		request.setAttribute("idErr", idErr);
		request.setAttribute("pwErr", pwErr);

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