package tdb.search.util;


public class Page {

	private int size = 20;
	private int page = 0;

	public int getStart() {
		return size * page;
	}

	public int getMax() {
		return (page + 1) * size;
	}

	public int next() {
		return page++;
	}

}
