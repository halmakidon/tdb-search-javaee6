package tdb.search.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class SystemDate {

	public static Date getDate() {
		return new Date();
	}

	public static Date getDate(long time) {
		return new Date(time);
	}

	public static Calendar getCalendar() {
		return Calendar.getInstance();
	}

	public static Calendar getCalendar(Locale aLocale) {
		return Calendar.getInstance(aLocale);
	}

	public static Calendar getCalendar(TimeZone zone) {
		return Calendar.getInstance(zone);
	}

	public static Calendar getCalendar(TimeZone zone, Locale aLocale) {
		return Calendar.getInstance(zone, aLocale);
	}

}
