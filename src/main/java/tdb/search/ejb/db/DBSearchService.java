package tdb.search.ejb.db;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

import tdb.search.dto.Converter;
import tdb.search.entity.Company;
import tdb.search.entity.Company_;
import tdb.search.entity.Key;
import tdb.search.entity.Key_;
import tdb.search.rsentity.SearchResult;
import tdb.search.util.Page;

@Stateless
public class DBSearchService {

	@PersistenceContext
	protected EntityManager em;

	public DBSearchService() {}
	public DBSearchService(EntityManager em) {
		this.em = em;
	}

	/**
	 * DBからデータを取得する
	 *
	 * @param word
	 * @param page
	 * @return
	 */
	public SearchResult search(String word, Page page) {
		List<Company> companyList = getCompanyList(word, page);
		int maxCount = countCompany(word);

		SearchResult result = new SearchResult();
		result.setList(Converter.company(companyList.toArray(new Company[companyList.size()])));
		result.setCurrentPage(page.getPage());
		result.setMaxPage(page.calcurateMaxPage(maxCount));
		result.setSearchHit(companyList.size());
		result.setSuccess(true);

		return result;

	}

	/**
	 * 会社のリストを取得する
	 * @param word
	 * @param page
	 * @return
	 */
	protected List<Company> getCompanyList(String word, Page page) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Company> cq = cb.createQuery(Company.class);
		Root<Company> root = cq.from(Company.class);
		// key情報でジョインする
		SetJoin<Company, Key> joinKey = root.join(Company_.keys);

		ParameterExpression<String> wordParam = cb.parameter(String.class);
		cq.where(cb.equal(joinKey.get(Key_.word), wordParam));

		TypedQuery<Company> query = em.createQuery(cq);
		query.setParameter(wordParam, word);
		query.setFirstResult(page.getFirstResult());
		query.setMaxResults(page.getMaxResults());
		List<Company> companyList = query.getResultList();

		return companyList;
	}

	/**
	 * 会社の検索結果件数を取得する
	 * @param word
	 * @param page
	 * @return
	 */
	protected int countCompany(String word) {
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Long> cq = cb.createQuery(Long.class);

		Root<Company> root = cq.from(Company.class);
		// key情報でジョインする
		SetJoin<Company, Key> joinKey = root.join(Company_.keys);

		ParameterExpression<String> wordParam = cb.parameter(String.class);
		cq.where(cb.like(joinKey.get(Key_.word), wordParam));

		cq.select(cb.count(root));

		TypedQuery<Long> query = em.createQuery(cq);
		query.setParameter(wordParam, word);

		return query.getSingleResult().intValue();
	}
}
