package tdb.search.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class PageTest {

	@Test
	public void testNext() {
		Page page = new Page();
		assertEquals(20, page.getMaxResults());
		assertEquals(1, page.getPage());
		assertEquals(0, page.getFirstResult());

		page.next();
		assertEquals(2, page.getPage());
		assertEquals(20, page.getFirstResult());
		assertEquals(20, page.getMaxResults());
	}

	@Test
	public void testCalcuratMaxPage() {
		Page page = new Page();
		assertEquals(3, page.calcurateMaxPage(41));
		assertEquals(2, page.calcurateMaxPage(40));
		assertEquals(1, page.calcurateMaxPage(9));
	}
}
