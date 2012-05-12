package tdb.search;

public class ScrapingException extends Exception {

	private static final long serialVersionUID = 4891744419004152098L;

	public ScrapingException() {
		super();
	}

	public ScrapingException(String message) {
		super(message);
	}

	public ScrapingException(String message, Throwable exception) {
		super(message, exception);
	}

	public ScrapingException(Throwable exception) {
		super(exception);
	}
}
