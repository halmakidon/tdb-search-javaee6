package tdb.search.ejb.scrape;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import tdb.search.rsentity.SearchResult;
import tdb.search.util.Page;

public class ScrapeServiceTest {

	@Test
	public void test() throws UnsupportedEncodingException {

		String word = "帝国";

		Page page = new Page();

		ScrapeService scraper1 = new ScrapeService();
		SearchResult result1 = scraper1.search(word, page);

		ScrapeService scraper2 = new ScrapeService();
		SearchResult result2 = scraper2.search(word, page.next());

		assertEquals(result1.getMaxPage(), result2.getMaxPage());
		assertEquals(result1.getSearchHit(), result2.getSearchHit());
		assertEquals(result1.getCurrentPage(), 1);
		assertEquals(result2.getCurrentPage(), 2);
		assertTrue(result1.getList().length >= 1);
		assertTrue(result2.getList().length >= 1);
		assertTrue(result1.isSuccess());
		assertTrue(result2.isSuccess());
	}
	@Test
	public void test2() throws UnsupportedEncodingException {

		String word = "東京";

		Page page = new Page();

		ScrapeService scraper1 = new ScrapeService();
		SearchResult result1 = scraper1.search(word, page);

		ScrapeService scraper2 = new ScrapeService();
		SearchResult result2 = scraper2.search(word, page.next());

		assertEquals(result1.getMaxPage(), result2.getMaxPage());
		assertEquals(result1.getSearchHit(), result2.getSearchHit());
		assertEquals(result1.getCurrentPage(), 1);
		assertEquals(result2.getCurrentPage(), 2);
		assertTrue(result1.getList().length >= 1);
		assertTrue(result2.getList().length >= 1);
		assertTrue(result1.isSuccess());
		assertTrue(result2.isSuccess());
	}
}
