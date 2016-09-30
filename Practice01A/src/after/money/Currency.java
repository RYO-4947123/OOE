package after.money;


/**
 * 通貨の定義
 *
 */
public enum Currency {
	/** 500円 */
	Y500(500),
	/** 100円 */
	Y100(100),
	/** 50円 */
	Y50(50),
	/** 10円 */
	Y10(10);
	
	/**
	 * 通貨の額面。
	 */
	private final int amount;
	
	private Currency(int amount) {
		this.amount = amount;
	}
	
	/**
	 * 額面に対応したMoneyオブジェクトを返します。
	 * 
	 * @return 額面オブジェクト
	 */
	public Money amount() {
		return new Money(amount);
	}
}
