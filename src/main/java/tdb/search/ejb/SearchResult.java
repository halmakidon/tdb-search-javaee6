package tdb.search.ejb;

import java.io.Serializable;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SearchResult")
public class SearchResult implements Serializable {

	private static final long serialVersionUID = 7482782076465580525L;
	private int searchHit;
	private int maxPage;
	private int currentPage;

	private CompanyResult[] list;
	private boolean success;

	/**
	 * @return the searchHit
	 */
	public final int getSearchHit() {
		return searchHit;
	}

	/**
	 * @param searchHit
	 *            the searchHit to set
	 */
	public final void setSearchHit(int searchHit) {
		this.searchHit = searchHit;
	}

	/**
	 * @return the maxPage
	 */
	public final int getMaxPage() {
		return maxPage;
	}

	/**
	 * @param maxPage
	 *            the maxPage to set
	 */
	public final void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	/**
	 * @return the currentPage
	 */
	public final int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage
	 *            the currentPage to set
	 */
	public final void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the list
	 */
	public final CompanyResult[] getList() {
		return list;
	}

	/**
	 * @param list
	 *            the list to set
	 */
	public final void setList(CompanyResult[] list) {
		this.list = list;
	}

	/**
	 * @return the success
	 */
	public final boolean isSuccess() {
		return success;
	}

	/**
	 * @param success
	 *            the success to set
	 */
	public final void setSuccess(boolean success) {
		this.success = success;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SearchResult [searchHit=" + searchHit + ", maxPage=" + maxPage
				+ ", currentPage=" + currentPage + ", list="
				+ Arrays.toString(list) + ", success=" + success + "]";
	}

}
