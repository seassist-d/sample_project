package lib;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;

/**
 * 画像アップロードの処理を行うクラス
 * 
 * @author 大屋根加代
 * @version 1.00
 */
public class ImageFile {
	/**
	 * アップロードされたファイル情報
	 */
	private Part file;
	/**
	 * 最終的な保存先のフルパス(Tomcat内）
	 */
	private String save_path = "";

	/**
	 * 画像のURL（相対パス）
	 */
	private String img_path = "";
	/**
	 * ファイルの拡張子
	 */
	private String extension = "";
	/**
	 * ファイル種別
	 */
	private String contentType = "";

	// ============================================
	/**
	 * アップロードファイルのファイル名の取得、DBに保存する際のパスの取得等を行う
	 * 
	 * @param file     アップロードされたファイル情報
	 * @param context  サーブレットとtomcatの情報（パス含む）
	 * @param fileName サーバーに保存する時のファイル名
	 * @throws IOException 
	 */
	public ImageFile(Part file, ServletContext context, String fileName) throws IOException {
		// ファイルの名前取得("/(ディレクトリ)"が表示されないので注意！)
		// ※ユーザのローカル上にあった写真名
		String file_name = file.getSubmittedFileName();
		System.out.println("=====" + file_name);

		// ファイルのサイズ取得
		String file_size = String.valueOf(file.getSize());

		// 拡張子を取得
		int ext_leng = 4;
		extension = file_name.substring(file_name.length() - ext_leng, file_name.length());

		// アップロードされたファイルの種別を取得
		contentType = file.getContentType();

		// ファイルの保存先を指定(ローカル)
		// リソースパスを設定(WebContent内のimgディレクトリの絶対パスを取得)
		String save_path = context.getRealPath("/img");
		System.out.println(save_path);

		// "指定ディレクトリの配下"というのを追加記述
		save_path += "/";

		// 実際に保存するファイル名を指定
		String save_name = fileName;

		// ここまでの確認をしたいのでコンソールに出力
		System.out.println("FileName = " + file_name);
		System.out.println("FileSize = " + file_size);
		System.out.println("SavePath(保存先) = " + save_path);
		System.out.println("SaveName = " + save_name);
		System.out.println("Extension = " + extension);
		System.out.println("ContentType = " + contentType);

		// ファイル名＋拡張子
//		save_name += extension;
		System.out.println("SaveFile_Name = " + save_name);

		// 保存する時の最終パス
		save_path += save_name;
		System.out.println("SaveFile_Path = " + save_path);

		// フィールドに値を設定
		this.setSavePath(save_path);
		this.setFile(file);
		this.setImg_path("./img/" + save_name);

	}

	// ============================================
	/**
	 * ファイルを保存する
	 * 
	 * @throws IOException
	 */
	public void save() throws IOException {
		try {
			System.out.println("Part save to :" + this.getSavePath());
			Part file = this.getFile();
			file.write(this.getSavePath());
			
			System.out.println("save end");
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

	// ============================================
	// ※ファイル名が固定になるのでキャッシュが残って更新されないことがあるかも。。。
	/**
	 * ファイルのパスを取得する
	 * 
	 * @return
	 */
	public String getPath() {
		return img_path;
	}

	// ============================================
	/**
	 * ファイルのパスを取得する ※ファイル名にgetのパラメータに日付を足すことで、ブラウザに違うファイルだと認識させる
	 * 
	 * @return
	 */
	public String getFileUrl() {
		return img_path + "?ver=" + String.valueOf(System.currentTimeMillis());
	}

	// ============================================
	/**
	 * ファイルの拡張子を取得する
	 * 
	 * @return
	 */
	public String getExtention() {
		return extension;
	}

	// ============================================
	/**
	 * ファイルのコンテントタイプを取得する
	 * 
	 * @return
	 */
	public String getContentType() {
		return contentType;
	}

	// クラス内からのみ使用============================================
	private String getImg_path() {
		return img_path;
	}

	private void setImg_path(String img_path) {
		this.img_path = img_path;
	}

	private Part getFile() {
		return file;
	}

	private void setFile(Part file) {
		this.file = file;
	}

	private String getSavePath() {
		return save_path;
	}

	private void setSavePath(String path) {
		this.save_path = path;
	}
}
