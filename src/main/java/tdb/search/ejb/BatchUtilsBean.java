package tdb.search.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class BatchUtilsBean {

	@PersistenceContext
	protected EntityManager em;

	public static final int BATCH_SIZE = 25;

	public void cacheClearForBatch(int cnt) {
		if (cnt % BATCH_SIZE == 0) {
			em.flush();
			em.clear();
		}
	}
}
