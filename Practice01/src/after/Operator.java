package after;

import common.IOperable;

/**
 * 自動販売機（{@link after.VendingMachine}）の操作者（顧客）
 *
 */
public class Operator implements IOperable{
	
	// afterのクラスのテストを行う
	//private VendingMachine vm = new VendingMachine();
	
	/**
	 * 自販機に10円硬貨を投入する
	 */
	public void deposit10Yen() {
	}
	
	/**
	 * 自販機に50円硬貨を投入する
	 */
	public void deposit50Yen() {
	}
	
	/**
	 * 自販機に100円硬貨を投入する
	 */
	public void deposit100Yen() {
	}
	
	/**
	 * 自販機に500円硬貨を投入する
	 */
	public void deposit500Yen() {
	}
	
	/**
	 * 残金を表示する
	 * @return 残金
	 */
	public int remainingMoney() {
		return 0;
	}
	
	/**
	 * 入金した（未使用の）金額を出金する。
	 * ※このメソッド実行後は残金が0円になる。
	 * @return 残金
	 */
	public int refund() {
		return 0;
	}
	
	/**
	 * コーラを買う
	 * @return 買えた場合true,買えなかった場合false
	 */
	public boolean buyCoke() {
		return false;
	}
	
	/**
	 * 紅茶を買う
	 * @return 買えた場合true,買えなかった場合false
	 */
	public boolean buyTea() {
		return false;
	}
	
	/**
	 * コーヒーを買う
	 * @return 買えた場合true,買えなかった場合false
	 */
	public boolean buyCoffee() {
		return false;
	}

}
