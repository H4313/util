package com.h4313.deephouse.util;

import java.util.Calendar;

public abstract class Date
{
	public static String getMonthFrFromCalendar(Calendar cal)
	{
		String monthFr = null;
		switch(DeepHouseCalendar.getInstance().getCalendar().get(Calendar.MONTH))
		{
		case 0:
			monthFr = "JANVIER";
			break;
		case 1:
			monthFr = "FEVRIER";
			break;
		case 2:
			monthFr = "MARS";
			break;
		case 3:
			monthFr = "AVRIL";
			break;
		case 4:
			monthFr = "MAI";
			break;
		case 5:
			monthFr = "JUIN";
			break;
		case 6:
			monthFr = "JUILLET";
			break;
		case 7:
			monthFr = "AOUT";
			break;
		case 8:
			monthFr = "SEPTEMBRE";
			break;
		case 9:
			monthFr = "OCTOBRE";
			break;
		case 10:
			monthFr = "NOVEMBRE";
			break;
		case 11:
			monthFr = "DECEMBRE";
			break;
		}
		
		return monthFr;
	}
}
