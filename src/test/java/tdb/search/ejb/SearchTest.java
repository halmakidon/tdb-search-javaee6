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

public class SearchTest {

	@Test
	public void testSearch() {

		DBTestUtilsBean dbtest = new DBTestUtilsBean();
		Target search = new Target(dbtest.getEntityManager());
		Worker1 work1 = new Worker1(search);
		dbtest.start(work1);

	}

	class Worker1 implements Worker {
		Target search;
		public Worker1(Target search) {
			this.search = search;
		}
		@Override
		public void run() {
			search.search("TEST", new Page());
		}
	};

	class Target extends Search {
		public Target(EntityManager em) {
			super();
			super.em = em;
		}
	}
}
