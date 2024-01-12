package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	Account testAccount2;

	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount2 = new Account("Solo", SEK);
		testAccount.deposit(new Money(10000000, SEK));
		testAccount2.deposit(new Money(100000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {
		testAccount.addTimedPayment("1", 10000, 200, new Money(10000, SEK), SweBank, "Alice");
		assertTrue(testAccount.timedPaymentExists("1"));

		testAccount.removeTimedPayment("1");
		assertFalse(testAccount.timedPaymentExists("1"));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		testAccount.addTimedPayment("1", 1, 2, new Money(10000, SEK), SweBank, "Alice");
		testAccount.tick();
		assertEquals("100000 SEK", testAccount.getBalance().toString());
	}

	@Test
	public void testAddWithdraw() {
		testAccount.withdraw(new Money(10000, SEK));
		assertEquals(9990000, (int) testAccount.getBalance().getAmount());
	}
	
	@Test
	public void testGetBalance() {
		assertEquals("100000 SEK", testAccount.getBalance().toString());
	}
}
