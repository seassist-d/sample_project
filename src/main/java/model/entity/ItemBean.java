/**
 * nonameAdmin
 * ItemBean.java
 */
package model.entity;

import java.io.Serializable;

/**
 * ItemのBeanクラス
 * 
 * @author 山田裕也
 * @version 1.00
 * 
 */
public class ItemBean implements Serializable {

	/**
	 * 商品コード
	 */
	private int itemCode;

	/**
	 * 商品名
	 */
	private String itemName;

	/**
	 * タール
	 */
	private int tar;

	/**
	 * ニコチン
	 */
	private double nicotine;

	/**
	 * 入数
	 */
	private int volume;

	/**
	 * 価格
	 */
	private int price;

	/**
	 * 商品説明
	 */
	private String bio;

	/**
	 * 画像
	 */
	private String pic;

	/**
	 * デフォルトコンストラクタ
	 */
	public ItemBean() {

	}

	/**
	 * フィールドitemCodeの値を返します。
	 * 
	 * @return itemCode
	 */
	public int getItemCode() {
		return itemCode;
	}

	/**
	 * フィールドitemCodeの値を設定します。
	 * 
	 * @param itemCode
	 */
	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}

	/**
	 * フィールドitemNameの値を返します。
	 * 
	 * @return itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * フィールドitemNameの値を設定します。
	 * 
	 * @param itemName
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * フィールドtarの値を返します。
	 * 
	 * @return tar
	 */
	public int getTar() {
		return tar;
	}

	/**
	 * フィールドtarの値を設定します。
	 * 
	 * @param tar
	 */
	public void setTar(int tar) {
		this.tar = tar;
	}

	/**
	 * フィールドnicotineの値を返します。
	 * 
	 * @return nicotine
	 */
	public double getNicotine() {
		return nicotine;
	}

	/**
	 * フィールドnicotineの値を設定します。
	 * 
	 * @param nicotine
	 */
	public void setNicotine(double nicotine) {
		this.nicotine = nicotine;
	}

	/**
	 * フィールドvolumeの値を返します。
	 * 
	 * @return volume
	 */
	public int getVolume() {
		return volume;
	}

	/**
	 * フィールドvolumeの値を設定します。
	 * 
	 * @param volume
	 */
	public void setVolume(int volume) {
		this.volume = volume;
	}

	/**
	 * フィールドpriceの値を返します。
	 * 
	 * @return price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * フィールドpriceの値を設定します。
	 * 
	 * @param price
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * フィールドbioの値を返します。
	 * 
	 * @return bio
	 */
	public String getBio() {
		return bio;
	}

	/**
	 * フィールドbioの値を設定します。
	 * 
	 * @param bio
	 */
	public void setBio(String bio) {
		this.bio = bio;
	}

	/**
	 * フィールドpicの値を返します。
	 * 
	 * @return pic
	 */
	public String getPic() {
		return pic;
	}

	/**
	 * フィールドpicの値を設定します。
	 * 
	 * @param pic
	 */
	public void setPic(String pic) {
		this.pic = pic;
	}

}
