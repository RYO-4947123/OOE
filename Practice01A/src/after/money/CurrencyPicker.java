package after.money;

/**
 * Currenciesクラスへの操作を提供するクラス 
 * <br>
 * このクラスは外部には公開せず、Currenciesクラスのメソッドの実装を目的としています。
 *
 */
/* package default */ class CurrencyPicker {
	
	/**
	 * 一方の通貨セットにもう一方の通貨セットから通貨を移動します。
	 * 
	 * @param src 移動元通貨セット
	 * @param dst 移動先通貨セット
	 * @param currency 移動する通貨の種類
	 * @return 移動元に移動する通貨がある場合true
	 */
	public static boolean moveCurrency(Currencies src, Currencies dst, Currency currency) {
		if (src == null || dst == null || currency == null) {
			return false;
		}
		if (!src.pickup(currency)) {
			return false;
		}
		dst.addCurrency(currency);
		return true;
	}
	
	/**
	 * 一方の通貨セットにもう一方の通貨セットから指定した額面以下で可能な限り大きくなるように移動します。
	 * 
	 * @param src 移動元通貨セット
	 * @param dst 移動先通貨セット
	 * @param amount 移動する額面
	 * @return 丁度移動出来た場合true
	 */
	public static boolean moveCurrencies(Currencies src, Currencies dst, Money amount) {
		final Currency[] currencyList = Currency.values();
		int currentIndex = 0;
		while (currencyList.length > currentIndex) {
			currentIndex += (moveCurrency(src, dst, currencyList[currentIndex], amount) ? 0 : 1);
		}
		// 処理結果の額面と取り出す額面が一致しているかチェック（不一致の場合は取り出せなかったと判定）
		final Money result = dst.getMoney();
		return result.compareTo(amount) == 0;
	}
	
	/**
	 * 一方の通貨セットにもう一方の通貨セットから通貨を移動します。
	 * ただし、通貨が指定した額より多い場合は処理を行いません。
	 * 
	 * @param src 移動元通貨セット
	 * @param dst 移動先通貨セット
	 * @param currency 移動する通貨の種類
	 * @param amount 移動可能な額
	 * @return 移動出来た場合true
	 */
	private static boolean moveCurrency(Currencies src, Currencies dst, Currency currency, Money amount) {
		if (!moveCheck(dst, currency, amount)) {
			return false;
		}
		return moveCurrency(src, dst, currency);
	}
	
	/**
	 * 指定した額面と通貨セットの合計額の差額が通貨の額面以上かチェックします。
	 * @param dst 通貨セット
	 * @param currency 通貨
	 * @param amount 額面
	 * @return 額面(amount) - 通貨セットの合計額(dst) >= 通貨の額面(currency)の場合true
	 */
	private static boolean moveCheck(Currencies dst, Currency currency, Money amount) {
		final Money dstMoney = dst.getMoney();
		final Money ramaining = amount.decrease(dstMoney);
		final Money money = currency.amount();
		return ramaining.compareTo(money) >= 0;
	}
}
