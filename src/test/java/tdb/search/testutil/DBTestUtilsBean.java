package tdb.search.testutil;

import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DBTestUtilsBean {

	private EntityManagerFactory emf;
	private EntityManager em;

	public DBTestUtilsBean() {

		emf = Persistence.createEntityManagerFactory("tdb-search-test");
		em = emf.createEntityManager();
	}

	public EntityManager getEntityManager() {
		return em;
	}

	public void start(Worker worker) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			worker.run();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException(e);
		}finally{
			em.close();
			emf.close();
		}
	}
}
