package after;

import after.money.Currency;
import after.money.Money;
import after.vendingmachine.VendingMachine;
import common.IOperable;

/**
 * 自動販売機（{@link after.VendingMachine}）の操作者（顧客）
 *
 */
public class Operator implements IOperable{
	
	private VendingMachine vm = new VendingMachine();
	
	/**
	 * 自販機に10円硬貨を投入する
	 */
	public void deposit10Yen() {
		vm.deposit(Currency.Y10);
	}
	
	/**
	 * 自販機に50円硬貨を投入する
	 */
	public void deposit50Yen() {
		vm.deposit(Currency.Y50);
	}
	
	/**
	 * 自販機に100円硬貨を投入する
	 */
	public void deposit100Yen() {
		vm.deposit(Currency.Y100);
	}
	
	/**
	 * 自販機に500円硬貨を投入する
	 */
	public void deposit500Yen() {
		vm.deposit(Currency.Y500);
	}
	
	/**
	 * 残金を表示する
	 * @return 残金
	 */
	public int remainingMoney() {
		final Money money = vm.remainingMoney();
		return money.getAmount();
	}
	
	/**
	 * 入金した（未使用の）金額を出金する。
	 * ※このメソッド実行後は残金が0円になる。
	 * @return 残金
	 */
	public int refund() {
		final Money money = vm.refund();
		return money.getAmount();
	}
	
	/**
	 * コーラを買う
	 * @return 買えた場合true,買えなかった場合false
	 */
	public boolean buyCoke() {
		return vm.buy(Drink.COKE);
	}
	
	/**
	 * 紅茶を買う
	 * @return 買えた場合true,買えなかった場合false
	 */
	public boolean buyTea() {
		return vm.buy(Drink.TEA);
	}
	
	/**
	 * コーヒーを買う
	 * @return 買えた場合true,買えなかった場合false
	 */
	public boolean buyCoffee() {
		return vm.buy(Drink.COFFEE);
	}

}
