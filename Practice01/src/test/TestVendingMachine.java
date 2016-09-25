package test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import common.IOperable;

public class TestVendingMachine {
	
	private IOperable operator;
	
	/**
	 * このテストクラスが実行されるとき１度だけ呼ばれます。
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("setUpBeforeClass");
	}

	/**
	 * このテストクラスのテスト対象メソッドがすべて終了した後１度だけ呼ばれます。
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("tearDownAfterClass");
	}
	
	/**
	 * 各テスト対象メソッドの実行前に呼ばれます。
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("setUp");
		
		//リファクタリング前のテスト
		operator = new before.Operator();
		//リファクタリング後のテスト
		//operator = new after.Operator();
	}
	
	/**
	 * 各テスト対象メソドの実行後に呼ばれます。
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		System.out.println("tearDown");
	}
	
	/**
	 * 入金と出金のテスト
	 */
	@Test
	public void testDepositAndRefund() {
		assertThat(operator.remainingMoney(), is(0));
		
		// 10円入金テスト
		operator.deposit10Yen();
		assertThat(operator.remainingMoney(), is(10));
		// 10円出金テスト
		assertThat(operator.refund(), is(10));
		assertThat(operator.refund(), is(0));
		
		// 50円入金テスト
		operator.deposit50Yen();
		assertThat(operator.remainingMoney(), is(50));
		// 50円出金テスト
		assertThat(operator.refund(), is(50));
		assertThat(operator.refund(), is(0));
		
		// 100円入金テスト
		operator.deposit100Yen();
		assertThat(operator.remainingMoney(), is(100));
		// 100円出金テスト
		assertThat(operator.refund(), is(100));
		assertThat(operator.refund(), is(0));
		
		// 500円入金テスト
		operator.deposit500Yen();
		assertThat(operator.remainingMoney(), is(500));
		// 500円出金テスト
		assertThat(operator.refund(), is(500));
		assertThat(operator.refund(), is(0));
		
		// 全部
		operator.deposit10Yen();
		operator.deposit50Yen();
		operator.deposit100Yen();
		operator.deposit500Yen();
		assertThat(operator.remainingMoney(), is(660));
		assertThat(operator.refund(), is(660));
		assertThat(operator.refund(), is(0));
		
	}
	
	/**
	 * 購入テスト1
	 */
	@Test
	public void testBuy1() {
		assertThat(operator.buyCoke(), is(false));
		
		// 10円硬貨8枚投入
		for (int i = 0; i < 8; i++) operator.deposit10Yen();
		assertThat(operator.buyCoke(), is(true));
		assertThat(operator.remainingMoney(), is(0));
		
		// 50円硬貨1枚,10円硬貨3枚投入
		operator.deposit50Yen();
		for (int i = 0; i < 3; i++) operator.deposit10Yen();
		assertThat(operator.buyCoke(), is(true));
		assertThat(operator.remainingMoney(), is(0));
		
		// 50円硬貨2枚投入
		operator.deposit50Yen();
		operator.deposit50Yen();
		assertThat(operator.buyCoke(), is(true));
		assertThat(operator.remainingMoney(), is(20));
		// 払い戻ししない
		
		// 100円硬貨1枚投入
		operator.deposit100Yen();
		assertThat(operator.buyCoke(), is(true));
		assertThat(operator.remainingMoney(), is(40));
		assertThat(operator.refund(), is(40));
		
		// 500円硬貨1枚投入
		operator.deposit500Yen();
		assertThat(operator.buyCoke(), is(true));
		assertThat(operator.remainingMoney(), is(420));
		
		// 在庫切れ
		assertThat(operator.buyCoke(), is(false));
		assertThat(operator.remainingMoney(), is(420));
		
		// 在庫がある
		assertThat(operator.buyCoffee(), is(true));
		assertThat(operator.remainingMoney(), is(300));
		assertThat(operator.refund(), is(300));
	}
	
	/**
	 * 購入テスト2(釣り銭切れ)</br>
	 * 10円硬貨は初期で10枚入っている状態。
	 * 100円で購入すると20円(2枚)消費するので6回目は釣り銭切れとなるはず
	 */
	@Test
	public void testBuy2() {
		operator.deposit100Yen();
		assertThat(operator.buyCoke(), is(true));
		assertThat(operator.remainingMoney(), is(20));
		assertThat(operator.refund(), is(20));
		
		operator.deposit100Yen();
		assertThat(operator.buyCoke(), is(true));
		assertThat(operator.remainingMoney(), is(20));
		assertThat(operator.refund(), is(20));
		
		operator.deposit100Yen();
		assertThat(operator.buyCoke(), is(true));
		assertThat(operator.remainingMoney(), is(20));
		assertThat(operator.refund(), is(20));
		
		operator.deposit100Yen();
		assertThat(operator.buyCoke(), is(true));
		assertThat(operator.remainingMoney(), is(20));
		assertThat(operator.refund(), is(20));
		
		operator.deposit100Yen();
		assertThat(operator.buyCoke(), is(true));
		assertThat(operator.remainingMoney(), is(20));
		assertThat(operator.refund(), is(20));
		
		// 釣り銭切れで購入出来ないはず
		operator.deposit100Yen();
		assertThat(operator.buyCoke(), is(false));
		assertThat(operator.remainingMoney(), is(100));
		assertThat(operator.refund(), is(100));
	}
	
	/**
	 * 釣り銭の払い戻しが正しく行えるかのテスト
	 */
	@Test
	public void testBuy3() {
		operator.deposit500Yen();
		assertThat(operator.buyTea(), is(true));
		assertThat(operator.remainingMoney(), is(400));
		assertThat(operator.refund(), is(400));
		//100円硬貨の在庫数が1枚となる
		
		operator.deposit500Yen();
		assertThat(operator.buyTea(), is(true));
		assertThat(operator.remainingMoney(), is(400));
		assertThat(operator.refund(), is(400));
		//残った100円硬貨を使っても300円分足りないが
		//50円硬貨5枚と10円硬貨5枚を使って払い戻し出来る
		//(10円硬貨残り5枚)
		
		operator.deposit100Yen();
		assertThat(operator.buyCoke(), is(true));
		assertThat(operator.remainingMoney(), is(20));
		assertThat(operator.refund(), is(20));
		//(10円硬貨残り3枚)
		
		operator.deposit100Yen();
		assertThat(operator.buyCoke(), is(true));
		assertThat(operator.remainingMoney(), is(20));
		assertThat(operator.refund(), is(20));
		//(10円硬貨残り1枚)
		
		// 釣り銭切れで購入出来ないはず
		operator.deposit100Yen();
		assertThat(operator.buyCoke(), is(false));
		assertThat(operator.remainingMoney(), is(100));
		assertThat(operator.refund(), is(100));
	}

}
