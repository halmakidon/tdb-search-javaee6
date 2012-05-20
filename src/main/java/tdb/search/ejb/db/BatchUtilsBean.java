package tdb.search.ejb.db;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class BatchUtilsBean {

	@PersistenceContext
	protected EntityManager em;

	public BatchUtilsBean() {
	}

	public BatchUtilsBean(EntityManager em) {
		this.em = em;
	}


	public static final int BATCH_SIZE = 25;

	public void cacheClearForBatch(int cnt) {
		if (cnt % BATCH_SIZE == 0) {
			em.flush();
			em.clear();
		}
	}
}
