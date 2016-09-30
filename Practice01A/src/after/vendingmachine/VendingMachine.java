package after.vendingmachine;

import after.Drink;
import after.money.Currency;
import after.money.Money;

/**
 * 自動販売機
 *
 */
public class VendingMachine {
	
	/** 通貨を管理するオブジェクト */
	private MoneyManager currencyStock = new MoneyManager();
	/** 飲み物の在庫を管理するオブジェクト */
	private DrinkStock drinkStock = new DrinkStock();
	
	/**
	 * 通貨を入金します。
	 * 
	 * @param currency 入金する通貨
	 */
	public void deposit(Currency currency) {
		currencyStock.deposit(currency);
	}
	
	/**
	 * 投入した金額を表示します。
	 * 
	 * @return 入金残高
	 */
	public Money remainingMoney() {
		return currencyStock.remainingMoney();
	}
	
	/**
	 * 入金した（未使用の）金額を払い戻します。<br/>
	 * <b>※このメソッド実行後は残金が0円になります。</b>
	 * 
	 * @return 払戻額
	 */
	public Money refund() {
		return currencyStock.refund();
	}
	
	/**
	 * 飲み物を買います。
	 * 
	 * @param drinkType 飲み物の種類
	 * @return 買えた場合true,買えなかった場合false
	 */
	public boolean buy(Drink drink) {
		// 在庫が無い
		if (drinkStock.stockCount(drink) == 0) {
			return false;
		}
		// 釣り銭切れ
		final Money price = Price.getPrice(drink);
		if (!currencyStock.buy(price)) {
			return false;
		}
		// 在庫を1つ減らす
		drinkStock.decrease(drink);
		return true;
	}
}
