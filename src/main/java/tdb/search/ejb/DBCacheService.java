package tdb.search.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tdb.search.entity.Company;
import tdb.search.entity.Key;

@Stateless
public class DBCacheService {

	@PersistenceContext
	protected EntityManager em;

	/**
	 * 検索キーに存在するか？
	 *
	 * @param word
	 * @return
	 */
	public boolean hasCache(String word) {
		Key key = em.find(Key.class, word);
		return key != null;
	}

	/**
	 * キーと一緒に会社情報を格納する
	 *
	 * @param word
	 * @param companyArray
	 */
	public void cache(String word, CompanyResult[] companyArray) {
		Key key = em.find(Key.class, word);
		if (key == null) {
			key = new Key(word);
			em.persist(key);
		}
		Company[] companyList = Converter.companyResult(companyArray);
		for (Company company : companyList) {
			company.getKeys().add(key);
			em.merge(company);
		}
		em.flush();
	}
}
