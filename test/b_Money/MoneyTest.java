package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	@Test
	public void testGetAmount() {
		assertEquals(10000, (int) SEK100.getAmount());
		assertEquals(1000, (int) EUR10.getAmount());
		assertEquals(20000, (int) SEK200.getAmount());
		assertEquals(2000, (int) EUR20.getAmount());
		assertEquals(0, (int) SEK0.getAmount());
		assertEquals(0, (int) EUR0.getAmount());
		assertEquals(-10000, (int) SEKn100.getAmount());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SEK, SEK100.getCurrency());
		assertEquals(EUR, EUR10.getCurrency());
		assertEquals(SEK, SEK200.getCurrency());
		assertEquals(EUR, EUR20.getCurrency());
		assertEquals(SEK, SEK0.getCurrency());
		assertEquals(EUR, EUR0.getCurrency());
		assertEquals(SEK, SEKn100.getCurrency());
	}

	@Test
	public void testToString() {
		assertEquals("100 SEK", SEK100.toString());
		assertEquals("10 EUR", EUR10.toString());
		assertEquals("200 SEK", SEK200.toString());
		assertEquals("20 EUR", EUR20.toString());
		assertEquals("0 SEK", SEK0.toString());
		assertEquals("0 EUR", EUR0.toString());
		assertEquals("-100 SEK", SEKn100.toString());
	}

	@Test
	public void testUniversalValue() {
		assertEquals(1500, (int) SEK100.universalValue());
		assertEquals(1500, (int) EUR10.universalValue());
		assertEquals(3000, (int) SEK200.universalValue());
		assertEquals(3000, (int) EUR20.universalValue());
		assertEquals(0, (int) SEK0.universalValue());
		assertEquals(0, (int) EUR0.universalValue());
		assertEquals(-1500, (int) SEKn100.universalValue());
	}

	@Test
	public void testEqualsMoney() {
		assertTrue(SEK100.equals(EUR10));
		assertTrue(SEK200.equals(EUR20));
		assertTrue(SEK0.equals(EUR0));
		assertFalse(SEKn100.equals(EUR10));
		assertFalse(SEK100.equals(EUR0));
	}

	@Test
	public void testAdd() {
		var res1 = SEK200.add(SEK100);
		var res2 = SEK200.add(SEKn100);

		assertEquals(30000, (int)res1.getAmount());
		assertEquals(10000, (int)res2.getAmount());
	}

	@Test
	public void testSub() {
		var res1 = SEK200.sub(SEK100);
		var res2 = SEK200.sub(SEKn100);

		assertEquals(10000, (int)res1.getAmount());
		assertEquals(30000, (int)res2.getAmount());
	}

	@Test
	public void testIsZero() {
		assertTrue(SEK0.isZero());
		assertTrue(EUR0.isZero());
		assertFalse(SEK100.isZero());
		assertFalse(EUR10.isZero());
		assertFalse(SEKn100.isZero());
	}

	@Test
	public void testNegate() {
		assertEquals(10000, (int) SEKn100.negate().getAmount());
		assertEquals(-10000, (int) SEK100.negate().getAmount());
		assertEquals(0, (int) SEK0.negate().getAmount());
	}

	@Test
	public void testCompareTo() {
        assertEquals(0, SEK100.compareTo(EUR10));
        assertEquals(0, SEK200.compareTo(EUR20));
        assertEquals(0, SEK0.compareTo(EUR0));
		assertTrue(SEK0.compareTo(EUR10)<0);
		assertTrue(SEK200.compareTo(EUR10)>0);
	}
}
