package com.bankrate.utils;

import java.util.Calendar;

/** 
  * @Description: cac ham xu ly ve datetime
  * @author:truonglt2
  * @since:Feb 7, 2014 5:25:05 PM
  * @version: 1.0
  * @since: 1.0
  * 
  */
public class DateTimeUtil {

	/**
	 * This method will return the current date time follow format: dd/mm/yyyy hh/mm/ss
	 * @return Return date time follow this format dd/mm/yyyy hh/mm/ss
	 * @author Truonglt2
	*  @throws: 
	 */
	public static String getCurrentDateTime() {
		Calendar cal = Calendar.getInstance();

		// get current day in month
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		// get current month
		int month = cal.get(Calendar.MONTH) + 1;
		// get current year
		int year = cal.get(Calendar.YEAR);

		// get current hour 24h for a day
		int hour24 = cal.get(Calendar.HOUR);
		// get current minute
		int minute = cal.get(Calendar.MINUTE);
		// get current second
		int second = cal.get(Calendar.SECOND);

		String AM_PM;
		if (Calendar.AM == cal.get(Calendar.AM_PM)) {
			AM_PM = " AM";
		} else {
			AM_PM = " PM";
		}

		StringBuffer dateTimeString = new StringBuffer();
		dateTimeString.append(dayOfMonth);
		dateTimeString.append("/");
		dateTimeString.append(month);
		dateTimeString.append("/");
		dateTimeString.append(year);

		dateTimeString.append(" ");

		dateTimeString.append(hour24);
		dateTimeString.append(":");
		dateTimeString.append(minute);
		dateTimeString.append(":");
		dateTimeString.append(second);

		dateTimeString.append(AM_PM);

		return dateTimeString.toString();
    }
	/**
	*  kiem tra thoi gian co hop le hay ko
	*  @author: truonglt2
	*  @return: void
	*  @throws: 
	*/
	public static boolean isTimeValidate()
	{
		Calendar c = Calendar.getInstance();
		int minu = c.get(Calendar.MINUTE);
		int hour = c.get(Calendar.HOUR);
		
		if((hour>=8 && hour <= 12) || (hour >= 13 && hour < 18))
		{
			if(hour >= 13 && hour < 14 )
			{
				if(minu > 30)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			if( hour >= 17 && hour < 18 )
			{
				if(minu < 30)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			return true;
		}
		return false;
	}
}
