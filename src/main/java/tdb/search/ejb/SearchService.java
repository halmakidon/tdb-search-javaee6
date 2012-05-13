package tdb.search.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import tdb.search.ScrapingException;
import tdb.search.util.Page;

/**
 * 検索サービス
 *
 * @author hide
 *
 */
@Stateless
public class SearchService {

	@EJB
	protected ScrapeService scrape;

	@EJB
	protected DBSearchService db;

	@EJB
	protected DBCacheService cache;

	public SearchResult search(String word, Page page) {

		if (cache.hasCache(word)) {
			return db.search(word, page);
		}

		SearchResult result = scrape.search(word, page);
		cache.cache(word, result.getList());
		return result;
	}

}
