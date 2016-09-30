package after.money;


/**
 * 額面を管理するオブジェクト(Immutable)
 *
 */
public class Money implements Comparable<Money> {
	
	/** 額面（マイナスもありえる事に留意） */
	private final int amount;
	
	public Money(int amount) {
		this.amount = amount;
	}
	
	/**
	 * 指定した通貨の額面でオブジェクトを作成します。
	 * 
	 * @param currency 通貨
	 */
	public Money(Currency currency) {
		final Money other = currency.amount();
		amount = other.amount;
	}
	
	/**
	 * 指定した通貨の額面を合計した値でオブジェクトを作成します。
	 * 
	 * @param currencies 額面を合算する通貨
	 */
	public Money(Iterable<Currency> currencies) {
		int total = 0;
		for(Currency currency : currencies) {
			final Money other = currency.amount();
			total += other.amount;
		}
		amount = total;
	}
	
	/**
	 * このオブジェクトの額面に指定したオブジェクトの額面を加算して、
	 * 新たなMoneyオブジェクトを生成して返します。
	 * 
	 * @param money 加算する額面
	 * @return 加算結果(新しいオブジェクト)
	 */
	public Money add(Money money) {
		return new Money(amount + money.amount);
	}
	
	/**
	 * このオブジェクトの額面に指定したオブジェクトの額面を減算して、
	 * 新たなMoneyオブジェクトを生成して返します。
	 * ただし、マイナスになる場合は額面0のオブジェクトを返します。
	 * 
	 * @param money 減算する額面
	 * @return 減算結果(新しいオブジェクト)
	 */
	public Money decrease(Money money) {
		return new Money(amount - money.amount);
	}

	/**
	 * 額面の比較を行います。
	 */
	@Override
	public int compareTo(Money other) {
		final int otherAmount = other != null ? other.amount : 0;
		return amount - otherAmount;
	}
	
	/**
	 * 額面がマイナスかチェックします。
	 * 
	 * @return 額面がマイナスの場合true
	 */
	public boolean isNegative() {
		return amount < 0;
	}
	
	/**
	 * 額面を返します。
	 * <br>
	 * <b>※テスト用の唯一のgetterメソッド</b>
	 * @return 額面
	 */
	public int getAmount() {
		return amount;
	}
}
