package tdb.search.util;

import java.math.BigDecimal;

public class Page {

	protected int size = 20;
	protected int page = 1;

	public Page() {
	}

	public Page(int page) {
		this.page = page;
	}

	public int getFirstResult() {
		return size * (page - 1);
	}

	public int getMaxResults() {
		return size;
	}

	public int getPage() {
		return page;
	}

	public Page next() {
		page++;
		return this;
	}

	public int calcurateMaxPage(int maxCount) {
		BigDecimal max = new BigDecimal(maxCount);
		return max.divide(new BigDecimal(size), 0, BigDecimal.ROUND_UP).intValue();
	}

	public String toString() {
		return String.valueOf(page);
	}

}
