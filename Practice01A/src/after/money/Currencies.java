package after.money;

import java.util.LinkedList;
import java.util.List;

/**
 * 複数の通貨をためるためのセット
 * (ファーストクラスコレクション)
 *
 */
public class Currencies {

	/** 入っている通貨 */
	private final LinkedList<Currency> currencies = new LinkedList<Currency>();
	
	public Currencies() {}
	
	/**
	 * 指定した通貨セットを全て加えた状態でインスタンスを生成します。
	 * 
	 * @param initial 初期通貨
	 */
	public Currencies(Currencies... initial) {
		for (Currencies currencies : initial) {
			addCurrencies(currencies);
		}
	}
	
	/**
	 * 通貨を追加します。
	 * 
	 * @param currency 追加する通貨
	 */
	public void addCurrency(Currency currency) {
		currencies.add(currency);
	}
	
	/**
	 * 通貨セットを合算します。
	 * 
	 * @param srcCurrencies 合算する通貨セット
	 */
	public void addCurrencies(Currencies srcCurrencies) {
		//Currencyは列挙型（Singleton）なのでディープコピー不要
		final List<Currency> srcList = srcCurrencies.currencies;
		currencies.addAll(srcList);
	}
	
	/**
	 * 指定した通貨セットにこの通貨セットから通貨を移動します。
	 * 
	 * @param dst 移動先通貨セット
	 * @param currency 移動する通貨
	 * @return 移動出来た場合true
	 */
	public boolean moveCurrency(Currencies dst, Currency currency) {
		return CurrencyPicker.moveCurrency(this, dst, currency);
	}
	
	/**
	 * 指定した通貨セットにこの通貨セットから通貨を移動します。
	 * 
	 * @param dst 移動先通貨セット
	 * @param currencies 移動する通貨
	 */
	public void moveCurrencies(Currencies dst, Currencies currencies) {
		final Currencies currenciesCopy = new Currencies(currencies);
		Currency currency;
		while ((currency = currenciesCopy.poll()) != null) {
			moveCurrency(dst, currency);
		}
	}
	
	/**
	 * 指定した通貨セットに額面以下で可能な限り大きくなるように移動します。
	 * 
	 * @param dst 移動先通貨セット
	 * @param amount 移動する額面
	 * @return 丁度移動出来た場合true
	 */
	public boolean moveCurrencies(Currencies dst, Money amount) {
		return CurrencyPicker.moveCurrencies(this, dst, amount);
	}
	
	/**
	 * 指定した通貨セットにこの通貨セットの全ての通貨を移動します。
	 * 
	 * @param dst 移動先通貨セット
	 */
	public void moveCurrencies(Currencies dst) {
		moveCurrencies(dst, this);
	}
	
	/**
	 * 任意の通貨を1つ取り出します。
	 * 
	 * @return 取り出せた場合true（実行後、在庫が1つ減ります）
	 */
	public boolean pickup(Currency currency) {
		return currencies.remove(currency);
	}
	
	/**
	 * 通貨を1つ取り出します。
	 * 
	 * @return 取り出した通貨（実行後、在庫が1つ減ります）
	 */
	public Currency poll() {
		return currencies.poll();
	}
	
	/**
	 * 空にします。
	 * 
	 */
	public void clear() {
		currencies.clear();
	}
	
	/**
	 * この通貨セットの合計額を返します。
	 * 
	 * @return 通貨の合計額
	 */
	public Money getMoney() {
		return new Money(currencies);
	}
}