package before;

import common.Define;

/**
 * 自動販売機
 *
 */
public class VendingMachine {
	
	/** ドリンクの種類定数（コーラ） */
	public static final int DRINK_TYPE_COKE = 0;
	/** ドリンクの種類定数（紅茶） */
	public static final int DRINK_TYPE_TEA = 1;
	/** ドリンクの種類定数（コーヒー） */
	public static final int DRINK_TYPE_COFFEE = 2;
	
	/** コーラの在庫 */
	private int stockOfCoke = Define.INITIAL_COKE_STOCK;
	/** コーラの在庫 */
	private int stockOfTea = Define.INITIAL_TEA_STOCK;
	/** コーラの在庫 */
	private int stockOfCoffee = Define.INITIAL_COFFEE_STOCK;
	
	/** 10円の在庫(投入分を除く) */
	private int stockOf10Yen = Define.INITIAL_10YEN_STOCK;
	/** 50円の在庫(投入分を除く) */
	private int stockOf50Yen = Define.INITIAL_50YEN_STOCK;
	/** 100円の在庫(投入分を除く) */
	private int stockOf100Yen = Define.INITIAL_100YEN_STOCK;
	/** 500円の在庫(投入分を除く) */
	private int stockOf500Yen = Define.INITIAL_500YEN_STOCK;
	
	/** 10円投入分 */
	private int depositOf10Yen = 0;
	/** 50円投入分 */
	private int depositOf50Yen = 0;
	/** 100円投入分 */
	private int depositOf100Yen = 0;
	/** 500円投入分 */
	private int depositOf500Yen = 0;
	
	public VendingMachine() {}
	
	/**
	 * お金を投入します。
	 * @param money 投入する金額
	 */
	public void deposit(int money) {
		if (money == 10) {
			depositOf10Yen++;
		} else if (money == 50) {
			depositOf50Yen++;
		} else if (money == 100) {
			depositOf100Yen++;
		} else if (money == 500) {
			depositOf500Yen++;
		}
	}

	/**
	 * 投入した金額を表示します。
	 * @return 残金
	 */
	public int remainingMoney() {
		return
				depositOf10Yen  * 10 +
				depositOf50Yen  * 50 +
				depositOf100Yen * 100 +
				depositOf500Yen * 500;
	}
	
	/**
	 * 入金した（未使用の）金額を払い戻します。<br/>
	 * <b>※このメソッド実行後は残金が0円になります。</b>
	 * @return 払戻額
	 */
	public int refund() {
		int refund = remainingMoney();
		depositOf10Yen = 0;
		depositOf50Yen = 0;
		depositOf100Yen = 0;
		depositOf500Yen = 0;
		return refund;
	}
	
	/**
	 * 飲み物を買います。
	 * @param drinkType 飲み物の種類
	 * @return 買えた場合true,買えなかった場合false
	 */
	public boolean buy(int drinkType) {
		if (drinkType == DRINK_TYPE_COKE) {
			// コーラの在庫がない
			if (stockOfCoke == 0) {
				return false;
			}
			// お金が足りない
			else if (remainingMoney() < Define.PRICE_COKE) {
				return false;
			}
			// 硬貨の在庫が無くておつりを出せない
			else if (!change(Define.PRICE_COKE, false)) {
				return false;
			} else {
				stockOfCoke--;
				change(Define.PRICE_COKE, true);
				return true;
			}
		}
		
		if (drinkType == DRINK_TYPE_TEA) {
			// 紅茶の在庫がない
			if (stockOfTea == 0) {
				return false;
			}
			// お金が足りない
			else if (remainingMoney() < Define.PRICE_TEA) {
				return false;
			}
			// 硬貨の在庫が無くておつりを出せない
			else if (!change(Define.PRICE_TEA, false)) {
				return false;
			} else {
				stockOfTea--;
				change(Define.PRICE_TEA, true);
				return true;
			}
		}
		
		if (drinkType == DRINK_TYPE_COFFEE) {
			// コーヒーの在庫がない
			if (stockOfCoffee == 0) {
				return false;
			}
			// お金が足りない
			else if (remainingMoney() < Define.PRICE_COFFEE) {
				return false;
			}
			// 硬貨の在庫が無くておつりを出せない
			else if (!change(Define.PRICE_COFFEE, false)) {
				return false;
			} else {
				stockOfCoffee--;
				change(Define.PRICE_COFFEE, true);
				return true;
			}
		}
		
		return false;
	}

	/**
	 * 飲み物を購入した場合、釣り銭が払い出し出来るかチェックします。
	 * 
	 * @param payments 支払い予定額
	 * @param change 釣り銭が払い出し出来るかチェックのみ行う場合false
	 * @return 払い出し出来るだけ硬貨の在庫がある場合true
	 */
	private boolean change(int payments, boolean change) {
		int prevStockOf10Yen = stockOf10Yen + depositOf10Yen;
		int prevStockOf50Yen = stockOf50Yen + depositOf50Yen;
		int prevStockOf100Yen = stockOf100Yen + depositOf100Yen;
		int prevStockOf500Yen = stockOf500Yen + depositOf500Yen;
		// 払い戻し予定額
		int refund = remainingMoney() - payments;
		
		// 払い戻し額分の硬貨がストックとデポジットにあるかチェック
		while (refund > 0) {
			// 500円以上残っている場合で500円硬貨がある場合はそれをあてがう
			if (refund >= 500) {
				if (prevStockOf500Yen > 0) {
					prevStockOf500Yen--;
					refund -= 500;
					continue;
				}
			}
			// 100円以上残っている場合で100円硬貨がある場合はそれをあてがう
			if (refund >= 100) {
				if (prevStockOf100Yen > 0) {
					prevStockOf100Yen--;
					refund -= 100;
					continue;
				}
			}
			// 50円以上残っている場合で50円硬貨がある場合はそれをあてがう
			if (refund >= 50) {
				if (prevStockOf50Yen > 0) {
					prevStockOf50Yen--;
					refund -= 50;
					continue;
				}
			}
			// 10円以上残っている場合で10円硬貨がある場合はそれをあてがう
			if (refund >= 10) {
				if (prevStockOf10Yen > 0) {
					prevStockOf10Yen--;
					refund -= 10;
					continue;
				}
			}
			
			return false;
		}
		
		// 変更モードならフィールドを更新
		if (change) {
			depositOf10Yen = depositOf10Yen + stockOf10Yen > prevStockOf10Yen ? depositOf10Yen + stockOf10Yen - prevStockOf10Yen : 0;
			depositOf50Yen = depositOf50Yen + stockOf50Yen > prevStockOf50Yen ? depositOf50Yen + stockOf50Yen - prevStockOf50Yen : 0;
			depositOf100Yen = depositOf100Yen + stockOf100Yen > prevStockOf100Yen ? depositOf100Yen + stockOf100Yen - prevStockOf100Yen : 0;
			depositOf500Yen = depositOf500Yen + stockOf500Yen > prevStockOf500Yen ? depositOf500Yen + stockOf500Yen - prevStockOf500Yen : 0;
			
			stockOf10Yen = prevStockOf10Yen;
			stockOf50Yen = prevStockOf50Yen;
			stockOf100Yen = prevStockOf100Yen;
			stockOf500Yen = prevStockOf500Yen;
		}
		
		return true;
	}

}
