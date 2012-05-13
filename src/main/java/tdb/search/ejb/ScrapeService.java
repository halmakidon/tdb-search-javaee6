package tdb.search.ejb;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.ejb.Stateless;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import tdb.search.ScrapingException;
import tdb.search.util.Log;
import tdb.search.util.Page;

/**
 * スクレイピングサービス
 *
 * @author sekky
 *
 */
@Stateless
public class ScrapeService {

	private static Logger LOG = Log.getLogger(ScrapeService.class);

	public static final int TIMEOUT = 3000;
	public static final int MAX_RETRY_COUNT = 2;

	public SearchResult search(String word, Page page) {

		String encodedWord = null;
		try {
			encodedWord = URLEncoder.encode(word, "Windows-31J");
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException(e);
		}

		String requestURI = "http://www.tdb.co.jp/service/u/1005.jsp"
				+ "?page_count=" + page.getPage() + "&companyName="
				+ encodedWord
				+ "&companyNameAccord=1&address_sikugun=&freeWord=";

		LOG.info(requestURI);

		return searchCompany(requestURI, word, page);
	}

	private static Document getDocument(String url) {
		Document doc = null;
		for (int i = 0; i < MAX_RETRY_COUNT; i++) {
			try {
				doc = Jsoup.connect(url).timeout(TIMEOUT).get();
			} catch (IOException e) {
				LOG.warning(e.toString());
				continue;
			}
			// 例外が発生しない場合は即ブレイクする
			break;
		}

		if (doc == null) {
			throw new ScrapingException();
		}
		return doc;
	}

	protected SearchResult searchCompany(String url, String word, Page page) {

		Document doc = getDocument(url);

		SearchResult result = new SearchResult();
		// 検索が成功したかを検索件数ヒット領域から判定する
		Elements hitArea = doc.select(".searchHit:eq(0)");
		if (hitArea.isEmpty()) {
			result.setCurrentPage(0);
			result.setList(new CompanyResult[0]);
			result.setMaxPage(0);
			result.setSearchHit(0);
			result.setSuccess(true);
			return result;
		}
		// 成功した場合は各行を取得する
		Elements rows = doc.select(".searchResult tr:gt(0)");
		ArrayList<CompanyResult> companyList = new ArrayList<CompanyResult>();
		for (Element row : rows) {
			CompanyResult company = new CompanyResult();
			company.setCode(row.select("td:eq(0)").text());
			company.setName(row.select("p.company").text());
			company.setAddress(row.select("p.companyPlace").text());
			company.setType(row.select("td:eq(2)").text());
			companyList.add(company);
		}
		result.setList(companyList.toArray(new CompanyResult[companyList.size()]));

		// 以降ページ数を検索数ヒット領域から取得する
		String hitCount = hitArea.select("div.left > span").text();
		int endPoint = hitCount.indexOf("件までの");
		if (endPoint > -1) {
			result.setSearchHit(Integer.parseInt(hitCount.substring(0, endPoint)));
		}else{
			result.setSearchHit(Integer.parseInt(hitCount));
		}

		// div.centerがない場合は1ページのみ
		Elements divCenter = hitArea.select("div.center");
		if (divCenter.isEmpty()) {
			result.setMaxPage(0);
			result.setCurrentPage(0);
		} else {
			String pageText = divCenter.text();
			int sliceIndex = pageText.indexOf("ページ中");
			result.setMaxPage(Integer.parseInt(pageText.substring(
					sliceIndex - 1, sliceIndex)));

			result.setCurrentPage(Integer.parseInt(hitArea.select(
					"div.center select option[selected]").val()));
		}
		result.setSuccess(true);

		return result;
	}
}
