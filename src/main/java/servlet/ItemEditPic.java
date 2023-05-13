/**
 * nonameAdmin
 * ItemEditPicServlet.java
 */

package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import lib.ImageFile;
import model.dao.ItemDAO;
import model.entity.ItemBean;

/**
 * Servlet implementation class ItemEditPic
 */
/**
 * 商品画像の編集をコントロールするクラス
 * 
 * @author 福尾大輔
 * @version 1.00
 *
 * 
 */
@WebServlet("/item-edit-pic")
@MultipartConfig(location = "/tempUpload", maxFileSize = 1048576)
public class ItemEditPic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemEditPic() {
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

		String seachWord = request.getParameter("seachWord"); // 検索ワード
		String seachCheck = request.getParameter("seachCheck"); // 検索チェック
		String url = "item-edit-pic.jsp"; // 画面遷移先を格納
		String button = request.getParameter("button"); // ボタン押下判定用
		int success = 0; // アップロード結果判定用
		int itemCode = Integer.parseInt(request.getParameter("itemCode")); // 商品コード
		String imagePath = ""; // 画像ファイルパス
		String err = ""; // エラーメッセージ
		String editPicErr = ""; // エラーメッセージ

		// 検索されていなければ空文字を代入
		if (seachCheck == null) {
			seachCheck = "";
		}

		ItemBean item = null; // リクエストスコープ設定用
		ItemBean newItem = null; // 編集後の商品情報

		try {

			// 商品1つの情報を取得
			item = ItemDAO.detailItem(itemCode);

		} catch (ClassNotFoundException | SQLException | NumberFormatException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

		// セッションオブジェクト取得
		HttpSession session = request.getSession();

		// ログイン済み認証
		if (session.getAttribute("admin") != null) {

			// 登録ボタン押下
			if ("登録".equals(button)) {
				Part file = request.getPart("pic");

				ImageFile imageFile = null;

				try {
					imageFile = new ImageFile(file, getServletContext(), file.getSubmittedFileName());

					if (imageFile != null) {
						imageFile.save();
						imagePath = imageFile.getPath();
						url = "item-pic-check.jsp";
					}
				} catch (StringIndexOutOfBoundsException e) {
					e.printStackTrace();
					err = "画像を選択してください";
					url = "item-edit-pic.jsp";
				}
			}

			// 戻るボタン押下
			if ("戻る".equals(button)) {
				url = "item-detail";
			}

			// 確定ボタンを押下されたら選択された画像をデータベースに登録
			if ("確定".equals(button)) {
				url = "item-pic-result.jsp"; // 遷移先
				imagePath = request.getParameter("imagePath"); // 画像パス

				try {
					// 編集前後を比較するため値をセット
					newItem = ItemDAO.detailItem(itemCode);
					newItem.setPic(imagePath);

					// 画像更新
					success = ItemDAO.editPicItem(imagePath, itemCode);

					// 画像更新失敗でエラーメッセージを代入
					if (success == 0) {
						editPicErr = "編集できませんでした。";
					}

				} catch (ClassNotFoundException | SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
					editPicErr = "編集できませんでした。";
				}
			}

			// キャンセルボタン押下
			if ("キャンセル".equals(button)) {
				url = "item-edit-pic.jsp"; // 画像編集画面へ遷移
			}

			// ログイン済み認証失敗でログイン画面へ遷移
		} else {
			url = "login";
		}

		// リクエストを転送
		request.setAttribute("item", item);
		request.setAttribute("newItem", newItem);
		request.setAttribute("imagePath", imagePath);
		request.setAttribute("err", err);
		request.setAttribute("editPicErr", editPicErr);
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