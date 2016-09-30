package after.vendingmachine;

import after.money.Currencies;
import after.money.Currency;
import after.money.Money;

/**
 * 自販機のお金まわりを管理するクラス
 *
 */
public class MoneyManager {
	
	/** 入金残高 */
	private final Currencies deposit = new Currencies();
	/** 通貨の在庫 */
	private final Currencies stock = new Currencies();
	
	public MoneyManager() {
		// 初期在庫を追加
		final Currencies initial = Initializer.initCurrencies();
		stock.addCurrencies(initial);
	}
	
	/**
	 * 通貨を入金します。
	 * 
	 * @param currency 入金通貨
	 */
	public void deposit(Currency currency) {
		deposit.addCurrency(currency);
	}
	
	/**
	 * 払い戻し可能額を返します。
	 * 
	 * @return 払い出し可能な金額全て
	 */
	public Money remainingMoney() {
		return deposit.getMoney();
	}
	
	/**
	 * 払い戻しを行います。
	 * 
	 * @return 払い出し可能な金額全て（実行後払い出し可能額は0になります）
	 */
	public Money refund() {
		final Money money = deposit.getMoney();
		deposit.clear();
		return money;
	}
	
	/**
	 * 指定した金額が入金残高以下の場合、その金額分を入金残高から通貨在庫に移動します。
	 * <br>
	 * 通貨の移動は額面上の移動が可能かではなく、物理的に通貨を移動して調節可能かまでチェックします。
	 * 
	 * @param price 入金残高から通貨在庫に移動する額
	 * @return 通貨を丁度移動可能であればtrue、その場合は内部状態も変更する。falseの場合は変更しません。
	 */
	public boolean buy(Money price) {
		// 払い戻し予定額(投入額 - 代金)
		final Money depositMoney = deposit.getMoney();
		final Money refund = depositMoney.decrease(price);
		// お金が足りない
		if (refund.isNegative()) {
			return false;
		}
		return moveToDepositToStock(refund);
	}
	
	/**
	 * 入金残高に指定した残金を残して、残りを通貨在庫に移動します。
	 * 
	 * @param refund 最終的に入金残高に残す残金
	 * @return 移動可能であればtrue、その場合は内部状態も変更する。falseの場合は変更しません。
	 */
	private boolean moveToDepositToStock(Money refund) {
		final Currencies resultDeposit = new Currencies();
		// 払い戻し額ちょうどの通貨が在庫にあるかチェックする
		if (!new Currencies(stock, deposit).moveCurrencies(resultDeposit, refund)) {
			return false;
		}
		// 一旦ストックに入金を移動する
		deposit.moveCurrencies(stock);
		// 釣り銭をストックから取り出して入金に入れる
		stock.moveCurrencies(deposit, resultDeposit);
		return true;
	}
}
