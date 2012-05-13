package tdb.search.ejb;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Test;

import tdb.search.entity.Company;
import tdb.search.entity.Key;
import tdb.search.testutil.DBTestUtilsBean;
import tdb.search.testutil.Worker;
import tdb.search.util.Page;

public class DBSearchServiceTest {

	@Test
	public void testSearch() {
		DBTestUtilsBean testUtils = new DBTestUtilsBean();
		DBSearchService search = new DBSearchService();
		search.em = testUtils.getEntityManager();

		testUtils.start(new TestSearch(testUtils.getEntityManager(), search));


	}

	class TestSearch implements Worker {
		EntityManager em;
		DBSearchService service;

		public TestSearch (EntityManager em, DBSearchService service) {
			this.em = em;
			this.service = service;
		}

		@Override
		public void run() {
			Key k1 = new Key("帝国");
			Company c1 = new Company();
			c1.setAddress("Address1");
			c1.setCode("Code1");
			c1.setName("帝国データバンク");
			c1.setType("Type1");
			c1.getKeys().add(k1);

			Key k2 = new Key("王国");
			Company c2 = new Company();
			c2.setAddress("Address2");
			c2.setCode("Code2");
			c2.setName("王国データバンク");
			c2.setType("Type2");
			c2.getKeys().add(k2);

			Key k3 = new Key("帝");
			Company c3 = new Company();
			c3.setAddress("Address3");
			c3.setCode("Code3");
			c3.setName("帝");
			c3.setType("Type3");
			c3.getKeys().add(k3);

			c1.getKeys().add(k3);
			em.persist(k1);
			em.persist(k2);
			em.persist(k3);
			em.persist(c1);
			em.persist(c2);
			em.persist(c3);
			em.flush();
			em.clear();

			SearchResult result = service.search("帝国", new Page());
			assertEquals(1, result.getSearchHit());
			assertEquals(1, result.getList().length);
			assertEquals("帝国データバンク", result.getList()[0].getName());

			result = service.search("王国", new Page());
			assertEquals(1, result.getList().length);
			assertEquals("王国データバンク", result.getList()[0].getName());
			// 前方一致だからヒットしない
			result = service.search("データバンク", new Page());
			assertEquals(0, result.getList().length);
			// 2件ヒットする
			result = service.search("帝", new Page());
			assertEquals(2, result.getList().length);
		}
	}
}
