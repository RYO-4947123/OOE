package after.vendingmachine;

import java.util.HashMap;
import java.util.Map;

import after.Drink;
import after.money.Money;
import common.Define;

/**
 * 飲み物の値段を定義したクラス
 *
 */
public class Price {
	
	/** 飲み物と金額の対応表 */
	private static final Map<Drink, Money> prices;
	
	static {
		prices = new HashMap<Drink, Money>();
		prices.put(Drink.COKE, new Money(Define.PRICE_COKE));
		prices.put(Drink.TEA, new Money(Define.PRICE_TEA));
		prices.put(Drink.COFFEE, new Money(Define.PRICE_COFFEE));
	}
	
	/**
	 * 指定した飲み物の値段を返します。
	 * @param drink 飲み物の種類
	 * @return 値段が設定されたMoneyオブジェクト
	 */
	public static Money getPrice(Drink drink) {
		return prices.get(drink);
	}
}
