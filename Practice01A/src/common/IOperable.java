package common;

/**
 * 自動販売機（{@link before.VendingMachine}）の操作を定義したインターフェイス
 *
 */
public interface IOperable {
	
	/**
	 * 自販機に10円硬貨を投入する
	 */
	public void deposit10Yen();
	
	/**
	 * 自販機に50円硬貨を投入する
	 */
	public void deposit50Yen();
	
	/**
	 * 自販機に100円硬貨を投入する
	 */
	public void deposit100Yen();
	
	/**
	 * 自販機に500円硬貨を投入する
	 */
	public void deposit500Yen();
	
	/**
	 * 残金を表示する
	 * @return 残金
	 */
	public int remainingMoney();
	
	/**
	 * 入金した（未使用の）金額を出金する。
	 * ※このメソッド実行後は残金が0円になる。
	 * @return 残金
	 */
	public int refund();
	
	/**
	 * コーラを買う
	 * @return 買えた場合true,買えなかった場合false
	 */
	public boolean buyCoke();
	
	/**
	 * 紅茶を買う
	 * @return 買えた場合true,買えなかった場合false
	 */
	public boolean buyTea();
	
	/**
	 * コーヒーを買う
	 * @return 買えた場合true,買えなかった場合false
	 */
	public boolean buyCoffee();

}
