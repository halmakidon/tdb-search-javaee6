package tdb.search.ejb;

import javax.ejb.Asynchronous;
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

		asyncCreateCache(word, result);

		return result;
	}

	@Asynchronous
	public void asyncCreateCache(String word, SearchResult result) {
		int cnt = result.getCurrentPage() + 1;
		int max = result.getMaxPage();

		// maxのページも取得する必要有り
		for(; cnt <= max; cnt++) {
			SearchResult searchResult = scrape.search(word, new Page(cnt));
			cache.cache(word, searchResult.getList());
		}
		// 前のページを取得する
		cnt = result.getCurrentPage() - 1;
		for(; cnt > 0; cnt--) {
			SearchResult searchResult = scrape.search(word, new Page(cnt));
			cache.cache(word, searchResult.getList());
		}
	}
}
