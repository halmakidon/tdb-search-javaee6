package tdb.search.ejb;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.resource.spi.work.Work;

import org.junit.Test;

import tdb.search.testutil.DBTestUtilsBean;
import tdb.search.testutil.Worker;
import tdb.search.util.Page;

public class SearchServiceTest {

	@Test
	public void testSearch() {

		DBTestUtilsBean dbtest = new DBTestUtilsBean();

		DBCacheService cache = new DBCacheService();
		cache.em = dbtest.getEntityManager();

		DBSearchService db = new DBSearchService();
		db.em = dbtest.getEntityManager();

		ScrapeService scrape = new ScrapeService();

		SearchService search = new SearchService();
		search.cache = cache;
		search.db = db;
		search.scrape = scrape;

		TestSearch work1 = new TestSearch(search);
		dbtest.start(work1);

		dbtest.close();
	}

	class TestSearch implements Worker {

		SearchService search;

		public TestSearch(SearchService search) {
			this.search = search;
		}

		@Override
		public void run() {
			try {
				search.search("帝国", new Page());
				//TODO テストを書く
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	};
}
