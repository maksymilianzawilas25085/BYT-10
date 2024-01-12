package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		assertEquals("SweBank", SweBank.getName());
		assertEquals("Nordea", Nordea.getName());
		assertEquals("DanskeBank", DanskeBank.getName());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SEK, SweBank.getCurrency());
		assertEquals(SEK, Nordea.getCurrency());
		assertEquals(DKK, DanskeBank.getCurrency());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		DanskeBank.openAccount("Marian");
		assertEquals(0, (int) DanskeBank.getBalance("Marian"));
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		Nordea.deposit("Bob", new Money(10000, SEK));
		assertEquals(10000, (int) Nordea.getBalance("Bob"));
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		Nordea.withdraw("Bob", new Money(10000, SEK));
		assertEquals(-10000, (int) Nordea.getBalance("Bob"));
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		assertEquals(0, (int) Nordea.getBalance("Bob"));
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		SweBank.transfer("Ulrika", "Bob", new Money(10000, SEK));
		assertEquals(-10000, (int) SweBank.getBalance("Ulrika"));
		assertEquals(10000, (int) SweBank.getBalance("Bob"));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		SweBank.addTimedPayment("Ulrika", "1", 200, 0, new Money(10000, SEK), SweBank, "Bob");
		assertEquals(0, (int) SweBank.getBalance("Ulrika"));
		assertEquals(0, (int) SweBank.getBalance("Bob"));
		SweBank.tick();
		assertEquals(-10000, (int) SweBank.getBalance("Ulrika"));
		assertEquals(10000, (int) SweBank.getBalance("Bob"));
	}
}
