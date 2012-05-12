package tdb.search.ejb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;

import org.junit.Test;

import tdb.search.entity.Company;
import tdb.search.entity.Key;
import tdb.search.testutil.DBTestUtilsBean;
import tdb.search.testutil.Worker;

public class DBCacheServiceTest {

	@Test
	public void testHasCache() {
		DBTestUtilsBean dbTest = new DBTestUtilsBean();

		DBCacheService cache = new DBCacheService();
		cache.em = dbTest.getEntityManager();

		dbTest.start(new CreateKey(dbTest.getEntityManager()));
		dbTest.start(new TestHasCache(cache));

		dbTest.close();

	}

	class CreateKey implements Worker {

		EntityManager em;

		public CreateKey(EntityManager em) {
			this.em = em;
		}

		@Override
		public void run() {
			em.persist(new Key("TRUE"));
		}
	}

	class TestHasCache implements Worker {

		DBCacheService cache;

		public TestHasCache(DBCacheService cache) {
			this.cache = cache;
		}

		@Override
		public void run() {
			boolean hasCache = cache.hasCache("TRUE");
			assertTrue(hasCache);
			hasCache = cache.hasCache("FALSE");
			assertFalse(hasCache);
		}
	}


	@Test
	public void testCache() {
		DBTestUtilsBean dbTest = new DBTestUtilsBean();

		DBCacheService cache = new DBCacheService();
		cache.em = dbTest.getEntityManager();

		dbTest.start(new PersisteCompany(cache));
		dbTest.start(new TestCompany(dbTest.getEntityManager()));

		dbTest.close();
	}
	class PersisteCompany implements Worker {

		DBCacheService cache;

		public PersisteCompany(DBCacheService cache) {
			this.cache = cache;
		}

		@Override
		public void run() {
			CompanyResult[] list = new CompanyResult[2];
			list[0] = new CompanyResult();
			list[0].setAddress("Address1");
			list[0].setCode("Code1");
			list[0].setName("Name1");
			list[0].setType("Type1");

			list[1] = new CompanyResult();
			list[1].setAddress("Address2");
			list[1].setCode("Code2");
			list[1].setName("Name2");
			list[1].setType("Type2");


			cache.cache("Name", list);
		}
	}

	class TestCompany implements Worker {

		EntityManager em;

		public  TestCompany(EntityManager em) {
			this.em = em;
		}

		@Override
		public void run() {
			Key key = em.find(Key.class, "Name");
			assertEquals("Name", key.getWord());

			Company company1 = em.find(Company.class, "Code1");
			assertEquals("Address1", company1.getAddress());
			assertEquals("Code1", company1.getCode());
			assertEquals("Name1", company1.getName());
			assertEquals("Type1", company1.getType());

			Company company2 = em.find(Company.class, "Code2");
			assertEquals("Address2", company2.getAddress());
			assertEquals("Code2", company2.getCode());
			assertEquals("Name2", company2.getName());
			assertEquals("Type2", company2.getType());
		}
	}
}
