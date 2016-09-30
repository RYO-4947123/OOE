package after.vendingmachine;

import java.util.Map;

import after.Drink;

/**
 * 飲み物の在庫を管理するクラス
 * (ファーストクラスコレクション)
 *
 */
public class DrinkStock {
	
	/** 飲み物の種類毎の在庫数対応表 */
	private final Map<Drink, Integer> stock = Initializer.initDrinks();
	
	/**
	 * 指定した飲み物の在庫を返します。
	 * 
	 * @param drink 飲み物の種類
	 * @return 在庫数
	 */
	public int stockCount(Drink drink) {
		return stock.get(drink);
	}
	
	/**
	 * 指定した飲み物の在庫を1つ減らします。
	 * 
	 * @param drink 減らす飲み物の種類
	 */
	public void decrease(Drink drink) {
		stock.put(drink, stock.get(drink)-1);
	}
}
