package tdb.search.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import tdb.search.entity.Company;
import tdb.search.entity.Company_;
import tdb.search.entity.Key;
import tdb.search.util.Page;

/**
 * 検索する
 *
 * @author hide
 *
 */
@Stateless
public class Search {

	@PersistenceContext
	protected EntityManager em;

	public List<Company> search(String word, Page page) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Company> cq = cb.createQuery(Company.class);
		Root<Company> root = cq.from(Company.class);
		cq.where(cb.like(root.get(Company_.name), "%"+word+"%"));

		TypedQuery<Company> query = em.createQuery(cq);
		query.setFirstResult(page.getStart());
		query.setMaxResults(page.getMax());
		List<Company> companyList = query.getResultList();

		return companyList;
	}
}
