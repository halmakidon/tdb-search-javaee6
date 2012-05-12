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

import tdb.search.util.Log;
import tdb.search.util.Page;

/**
 * スクレイピングサービス
 * @author sekky
 *
 */
@Stateless
public class ScrapeService {

	private static Logger LOG = Log.getLogger(ScrapeService.class);

	public SearchResult search(String word, Page page) {

		String encodedWord = null;
		try {
			encodedWord = URLEncoder.encode(word, "Windows-31J");
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException(e);
		}

		String requestURI = "http://www.tdb.co.jp/service/u/1005.jsp"
				+ "?page_count=" + page.getPage() + "&companyName=" + encodedWord
				+ "&companyNameAccord=1&address_sikugun=&freeWord=";

		LOG.info(requestURI);

		return searchCompany(requestURI, word, page);
	}

	protected SearchResult searchCompany(String url, String word, Page page) {

		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			SearchResult result = new SearchResult();
			result.setSuccess(false);
			return result;
		}

		SearchResult result = new SearchResult();
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

		Elements hitArea = doc.select(".searchHit:eq(0)");
		result.setSearchHit(Integer.parseInt(hitArea.select("div.left > span").text()));

		String pageText = hitArea.select("div.center").text();
	    int sliceIndex = pageText.indexOf("ページ中");
	    result.setMaxPage(Integer.parseInt(pageText.substring(sliceIndex-1, sliceIndex)));

	    result.setCurrentPage(Integer.parseInt(hitArea.select("div.center select option[selected]").val()));

	    result.setSuccess(true);

	    return result;
	}
}
