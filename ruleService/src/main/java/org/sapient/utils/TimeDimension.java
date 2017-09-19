package org.sapient.utils;

import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeDimension {

	public static final  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public enum TimeType {
		 SECONDS(0), MINUTES(1), HOURS(2), DAYS(3);
		private int type;

		TimeType(int type) {
			this.type =type;
		}

		public int getType() {
			return type;
		}

	}

	public static long getDifferenceInTime(Date d1, Date d2, int timeType) {
		// TODO to be moved to some utility class.
		if (d1 == null || d2 == null) {
			throw new InvalidParameterException("either od the dates can not be null !");
		}
		long diff = 0;
		try {
			diff = d2.getTime() - d1.getTime();
			long diffInTimeType = 0;

			long diffInSecType = diff / 1000 % 60;
			long diffInMinType = diff / (60 * 1000) % 60;
			long diffInHrType = diff / (60 * 60 * 1000) % 24;
			long diffInDayType = diff / (24 * 60 * 60 * 1000);
			if (timeType == 0) {// 0=SEC
				diffInTimeType = diffInSecType;
			} else if (timeType == 1) {// 1=MIN
				diffInTimeType = diffInMinType + diffInSecType / 60;
			} else if (timeType == 2) {// 2=HOURS
				diffInTimeType = diffInHrType + diffInMinType / 60 + diffInSecType / 3600;
			} else if (timeType == 2) {// 3=DAYS
				diffInTimeType = diffInDayType + diffInHrType / 24 + diffInMinType / 60 + diffInSecType / 3600;
			}

			diff = diffInTimeType;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return diff;
	}	
}
