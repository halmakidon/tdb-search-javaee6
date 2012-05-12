package tdb.search.util;

import java.util.logging.Logger;

public class Log {

	public static Logger getLogger(Class<?> clazz) {

		return Logger.getLogger(clazz.getName());
	}

}
