package tdb.search.ejb;

import org.junit.Test;

import tdb.search.ejb.db.BatchUtilsBean;
import tdb.search.ejb.db.DBCacheService;
import tdb.search.ejb.db.DBSearchService;
import tdb.search.ejb.scrape.ScrapeService;
import tdb.search.testutil.DBTestUtilsBean;
import tdb.search.testutil.Worker;
import tdb.search.util.Page;

public class SearchServiceTest {

	@Test
	public void testSearch() {

		DBTestUtilsBean dbtest = new DBTestUtilsBean();

		DBCacheService cache = new DBCacheService(dbtest.getEntityManager());

		DBSearchService db = new DBSearchService(dbtest.getEntityManager());

		BatchUtilsBean batchUtils = new BatchUtilsBean(dbtest.getEntityManager());

		ScrapeService scrape = new ScrapeService();

		SearchService search = new SearchService(scrape, db, cache, batchUtils);

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
				// 1回目
				search.search("ステッチ", new Page());
				// 2回目
				search.search("ステッチ", new Page());
				// 存在しない
				search.search("UNKO", new Page());

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}
