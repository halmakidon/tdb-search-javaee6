package tdb.search.testutil;

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

	public void close() {
		em.close();
		emf.close();
	}

	public EntityManager getEntityManager() {
		return em;
	}

	public void start(Worker worker) {
		if (!em.isOpen()) {
			em = emf.createEntityManager();
		}
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			worker.run();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException(e);
		}
	}
}
