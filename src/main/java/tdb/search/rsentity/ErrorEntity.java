package tdb.search.rsentity;


public class ErrorEntity extends AbstractResult {

	private static final long serialVersionUID = -575764825356963134L;

	public static final String MESSAGE = "検索キーワード(word)、ページ番号(page)パラメータが必要です";


	public ErrorEntity(String message) {
		super.errorMessage = message;
	}
}
