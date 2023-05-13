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

import model.dao.ItemDAO;
import model.entity.ItemBean;

/**
 * Servlet implementation class ItemEditServlet
 */
/**
 * 商品を編集するサーブレット
 * @author emtech-koushi
 *
 */
@WebServlet("/item-edit")
@MultipartConfig(location = "/tempUpload", maxFileSize = 1048576)
public class ItemEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemEditServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// リクエストエンコーディングの指定
		request.setCharacterEncoding("UTF-8");
		
		String seachWord = request.getParameter("seachWord"); // 検索ワード
		String seachCheck = request.getParameter("seachCheck"); // 検索チェック
		String url = "item-edit.jsp"; // 遷移先格納
		ItemBean item = null; // 商品情報
		
		// 検索されていなければ空文字を代入
		if (seachCheck == null) {
			seachCheck = "";
		}

		// セッションオブジェクト取得
		HttpSession session = request.getSession();
		
		// ログイン済み認証
		if (session.getAttribute("admin") != null) {

			try {
				int itemCode = Integer.parseInt(request.getParameter("itemCode")); // 商品コード

				// 商品情報を1件取得
				item = ItemDAO.detailItem(itemCode);

			} catch (ClassNotFoundException | SQLException | NumberFormatException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

		// ログイン済み認証失敗でログイン画面へ
		} else {
			url = "login";
		}

		// リクエストスコープへ値をセットしリクエストを転送
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
//		doGet(request, response);

		// リクエストエンコーディングの指定
		request.setCharacterEncoding("UTF-8");
		
		String seachWord = request.getParameter("seachWord"); // 検索ワード
		String seachCheck = request.getParameter("seachCheck"); // 検索チェック
		String url = "item-edit.jsp"; // 遷移先格納
		int itemCode = Integer.parseInt(request.getParameter("itemCode")); // 商品コード
		String button = request.getParameter("button"); // 押下されたボタン
		String pageCheck = request.getParameter("pageCheck"); // 遷移前のページの確認
		
		ItemBean item = null; // 編集前の商品情報
		ItemBean newItem = null; // 編集後の商品情報
		
		// 検索されていなければ空文字を代入
		if (seachCheck == null) {
			seachCheck = "";
		}

		try {
			// 商品1つの情報を取得
			item = ItemDAO.detailItem(itemCode);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

		// 変数定義・初期化
		String itemName = request.getParameter("itemName"); // 商品名
		int tar = 0; // タール
		double nicotine = 0; // ニコチン
		int volume = 0; // 入数
		int price = 0; // 価格
		String bio = request.getParameter("bio"); // 商品説明
		
		if (itemName != null) {
			itemName= itemName.replace("&", "&amp;" );
			itemName= itemName.replace("<", "&lt;"  );
			itemName= itemName.replace(">", "&gt;"  );
			itemName= itemName.replace("\\", "&quat;");
			itemName= itemName.replace("'", "&#39;" );
	   }
		
		if (bio != null) {
			bio= bio.replace("&", "&amp;" );
			bio= bio.replace("<", "&lt;"  );
			bio= bio.replace(">", "&gt;"  );
			bio= bio.replace("\\", "&quat;");
			bio= bio.replace("'", "&#39;" );
	   }

		// 入力時のエラーメッセージ
		String itemNameErrorMsg = null;
		String tarErrorMsg = null;
		String nicotineErrorMsg = null;
		String volumeErrorMsg = null;
		String priceErrorMsg = null;

		// 編集失敗エラーメッセージ
		String editErr = "";

		// 編集を押下した場合
		if ("編集".equals(button)) {
			// 入力された値をセットするインスタンス
			item = new ItemBean();

			// 空文字判定
			if ("".equals(itemName)) {
				itemNameErrorMsg = "商品名が不正です。";
				url = "item-edit.jsp";
			}

			// タールの入力判定
			try {
				tar = Integer.parseInt(request.getParameter("tar"));
				if (tar <= 0) {
					tarErrorMsg = "タールが不正です。";
					url = "item-edit.jsp";
				}
			} catch (NumberFormatException e) {
				tarErrorMsg = "タールが不正です。";
				url = "item-edit.jsp";
			}

			// ニコチンの入力判定
			try {
				nicotine = Double.parseDouble(request.getParameter("nicotine"));
				if (nicotine <= 0) {
					nicotineErrorMsg = "ニコチンが不正です。";
					url = "item-edit.jsp";
				}
			} catch (NumberFormatException e) {
				nicotineErrorMsg = "ニコチンが不正です。";
				url = "item-edit.jsp";
			}

			// 入数の入力判定
			try {
				volume = Integer.parseInt(request.getParameter("volume"));
				if (volume <= 0) {
					volumeErrorMsg = "入数が不正です。";
					url = "item-edit.jsp";
				}
			} catch (NumberFormatException e) {
				volumeErrorMsg = "入数が不正です。";
				url = "item-edit.jsp";
			}

			// 価格の入力判定
			try {
				price = Integer.parseInt(request.getParameter("price"));
				if (price <= 0) {
					priceErrorMsg = "価格が不正です。";
					url = "item-edit.jsp";
				}
			} catch (NumberFormatException e) {
				priceErrorMsg = "価格が不正です。";
				url = "item-edit.jsp";
			}

			// 編集確認画面で表示する値をインスタンスにセット
			item.setItemCode(itemCode);
			item.setItemName(itemName);
			item.setTar(tar);
			item.setNicotine(nicotine);
			item.setVolume(volume);
			item.setPrice(price);
			item.setBio(bio);

			// 全ての項目が正しく入力されたら編集確認画面を遷移先に指定
			if (!"".equals(item.getItemName()) && item.getTar() > 0 && item.getNicotine() > 0 && item.getVolume() > 0
					&& item.getPrice() > 0) {

				url = "item-edit-check.jsp";
			}

		}

		// 戻るボタンを押下したら詳細画面へ遷移
		if ("戻る".equals(button) && "edit".equals(pageCheck)) {
			url = "item-detail";
		}

		// 確定ボタンを押下
		if ("確定".equals(button)) {
			// 編集確定画面へ遷移
			url = "item-edit-result.jsp";

			// 確定する値のリクエストのパラメータの取得
			itemCode = Integer.parseInt(request.getParameter("itemCode")); // 商品コード
			itemName = request.getParameter("itemName"); // 商品名
			tar = Integer.parseInt(request.getParameter("tar")); // タール
			nicotine = Double.parseDouble(request.getParameter("nicotine")); // ニコチン
			volume = Integer.parseInt(request.getParameter("volume")); // 入数
			price = Integer.parseInt(request.getParameter("price")); // 価格
			bio = request.getParameter("bio"); // 商品詳細
			
			if (itemName != null) {
				itemName= itemName.replace("&", "&amp;" );
				itemName= itemName.replace("<", "&lt;"  );
				itemName= itemName.replace(">", "&gt;"  );
				itemName= itemName.replace("\\", "&quat;");
				itemName= itemName.replace("'", "&#39;" );
		   }
			
			if (bio != null) {
				bio= bio.replace("&", "&amp;" );
				bio= bio.replace("<", "&lt;"  );
				bio= bio.replace(">", "&gt;"  );
				bio= bio.replace("\\", "&quat;");
				bio= bio.replace("'", "&#39;" );
		   }

			try {
				// 編集前後を比較するため値をセット
				newItem = ItemDAO.detailItem(itemCode);
				newItem.setItemCode(itemCode);
				newItem.setItemName(itemName);
				newItem.setTar(tar);
				newItem.setNicotine(nicotine);
				newItem.setVolume(volume);
				newItem.setPrice(price);
				newItem.setBio(bio);

				// DAOを呼び出し値を更新
				int count = ItemDAO.editItem(newItem);

				// 更新失敗
				if (count == 0) {
					editErr = "編集できませんでした。";
				}

			} catch (ClassNotFoundException | SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				editErr = "編集できませんでした。";
			}
		}

		// キャンセルボタンを押下
		if ("キャンセル".equals(button)) {
			url = "item-edit.jsp";
		}

		// リクエストスコープへ値をセットし、リクエストを転送
		request.setAttribute("item", item);
		request.setAttribute("newItem", newItem);
		request.setAttribute("itemNameErrorMsg", itemNameErrorMsg);
		request.setAttribute("tarErrorMsg", tarErrorMsg);
		request.setAttribute("nicotineErrorMsg", nicotineErrorMsg);
		request.setAttribute("volumeErrorMsg", volumeErrorMsg);
		request.setAttribute("priceErrorMsg", priceErrorMsg);
		request.setAttribute("editErr", editErr);
		request.setAttribute("seachWord", seachWord);
		request.setAttribute("seachCheck", seachCheck);

		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
