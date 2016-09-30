package after.vendingmachine;

import java.util.HashMap;
import java.util.Map;

import common.Define;
import after.Drink;
import after.money.Currencies;
import after.money.Currency;

/**
 * 自販機の初期化時に必要な処理を纏めたクラス
 *
 */
public class Initializer {
	
	/**
	 * 自販機に始めから入れておく通貨を返します。
	 * 
	 * @return 初期在庫
	 */
	public static Currencies initCurrencies() {
		final Currencies currencies = new Currencies();
		// 10円硬貨の初期在庫設定
		for (int initCount = 0; initCount < Define.INITIAL_10YEN_STOCK; initCount++) {
			currencies.addCurrency(Currency.Y10);
		}
		// 50円硬貨の初期在庫設定
		for (int initCount = 0; initCount < Define.INITIAL_50YEN_STOCK; initCount++) {
			currencies.addCurrency(Currency.Y50);
		}
		// 100円硬貨の初期在庫設定
		for (int initCount = 0; initCount < Define.INITIAL_100YEN_STOCK; initCount++) {
			currencies.addCurrency(Currency.Y100);
		}
		// 500円硬貨の初期在庫設定
		for (int initCount = 0; initCount < Define.INITIAL_500YEN_STOCK; initCount++) {
			currencies.addCurrency(Currency.Y500);
		}
		return currencies;
	}
	
	/**
	 * 自販機に始めから入れておく飲み物を返します。
	 * 
	 * @return 初期在庫
	 */
	public static Map<Drink, Integer> initDrinks() {
		final Map<Drink, Integer> stock = new HashMap<Drink, Integer>();
		stock.put(Drink.COKE, Define.INITIAL_COKE_STOCK);
		stock.put(Drink.TEA, Define.INITIAL_TEA_STOCK);
		stock.put(Drink.COFFEE, Define.INITIAL_COFFEE_STOCK);
		return stock;
	}
}
