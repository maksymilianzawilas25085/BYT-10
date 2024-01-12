package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	@Test
	public void testGetName() {
		assertEquals("SEK", SEK.getName());
		assertEquals("DKK", DKK.getName());
		assertEquals("EUR", EUR.getName());
	}
	
	@Test
	public void testGetRate() {
		assertEquals(0.15, SEK.getRate(), 0);
		assertEquals(0.20, DKK.getRate(), 0);
		assertEquals(1.5, EUR.getRate(), 0);
	}
	
	@Test
	public void testSetRate() {
		SEK.setRate(0.40);
		DKK.setRate(0.80);
		EUR.setRate(0.20);

		assertEquals(0.40, SEK.getRate(), 0);
		assertEquals(0.80, DKK.getRate(), 0);
		assertEquals(0.20, EUR.getRate(), 0);
	}
	
	@Test
	public void testUniversalValue() {
		assertEquals(150, (int) SEK.universalValue(1000));
		assertEquals(200, (int) DKK.universalValue(1000));
		assertEquals(1500, (int) EUR.universalValue(1000));
	}
	
	@Test
	public void testValueInThisCurrency() {
		assertEquals(1200, (int) SEK.valueInThisCurrency(900, DKK));
		assertEquals(9000, (int) SEK.valueInThisCurrency(900, EUR));
		assertEquals(1800, (int) DKK.valueInThisCurrency(2400, SEK));
		assertEquals(900, (int) DKK.valueInThisCurrency(120, EUR));
		assertEquals(10, (int) EUR.valueInThisCurrency(100, SEK));
		assertEquals(13, (int) EUR.valueInThisCurrency(100, DKK));

	}

}
