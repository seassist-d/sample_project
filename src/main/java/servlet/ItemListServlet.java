/**
 * nonameAdmin
 * ItemListServlet.java
 */

package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
 * Servlet implementation class sample
 */
/**
 * 商品一覧をコントロールするクラス
 * 
 * @author 福尾大輔
 * @version 1.00
 *
 * 
 */
@WebServlet("/item-list")
public class ItemListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemListServlet() {
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

		String url = ""; // 画面遷移先
		String button = request.getParameter("button"); // ボタン判定
		String seachWord = request.getParameter("seachWord"); // 検索ワード
		String seachCheck = request.getParameter("seachCheck"); // 検索チェック
		String notListMsg = ""; // エラーメッセージ
		
		// 検索されていなければ空文字を代入
		if (seachCheck == null) {
			seachCheck = "";
		}

		// リストの初期化
		List<ItemBean> itemList = null; // 商品一覧リスト
		List<ItemBean> seachItemList = null; // 検索結果商品一覧リスト

		// セッションオブジェクト取得
		HttpSession session = request.getSession();

		// ログイン済み認証
		if (session.getAttribute("admin") != null) {

			try {
				// 商品全件取得
				itemList = ItemDAO.selectAllItem();
				
				// 商品が0件ならメッセージ格納
				if (itemList.size() == 0) {
					notListMsg = "検索結果0件です。";
				} else {
					url = "item-list.jsp";
				}

				// 検索ボタン押下
				if ("検索".equals(button) || !"".equals(seachCheck)) {

					seachItemList = ItemDAO.seachAllItem(seachWord); // 検索結果取得

					seachCheck = "seach"; // 検索されていればチェック
					
					// 商品がある時
					if (seachItemList.size() == 0) {
						notListMsg = "検索結果0件です。";
					} else {
						url = "item-list.jsp";
						
					}
				}

				

				// 例外処理の時
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				url = "item-list-failed.jsp";
			}

			// ログイン済み認証失敗でログイン画面へ
		} else {
			url = "login";
		}

		// リクエストの転送
		request.setAttribute("itemList", itemList);
		request.setAttribute("seachItemList", seachItemList);
		request.setAttribute("seachWord", seachWord);
		request.setAttribute("seachCheck", seachCheck);
		request.setAttribute("notListMsg", notListMsg);
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