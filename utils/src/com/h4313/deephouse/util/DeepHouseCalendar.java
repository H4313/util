package com.h4313.deephouse.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DeepHouseCalendar
{
	private static volatile DeepHouseCalendar instance = null;

	private long startTime = 0;
	
    /**
     * Constructeur de l'objet.
     */
    private DeepHouseCalendar() {
        super();
//        this.init();
    }

    /**
     * M�thode permettant de renvoyer une instance de la classe Singleton
     * @return Retourne l'instance du singleton.
     */
    public final static DeepHouseCalendar getInstance() {
        if (DeepHouseCalendar.instance == null) {
           synchronized(DeepHouseCalendar.class) {
             if (DeepHouseCalendar.instance == null) {
            	 DeepHouseCalendar.instance = new DeepHouseCalendar();
             }
           }
        }
        return DeepHouseCalendar.instance;
    }
    
    public void init()
    {
    	if(startTime == 0)
    		startTime = System.currentTimeMillis();
    }
    
    public Calendar getCalendar()
    {
    	GregorianCalendar calendar = new GregorianCalendar(Constant.DEFAULT_YEAR, Constant.DEFAULT_MONTH, 
    			Constant.DEFAULT_DAY, Constant.DEFAULT_HOUR, Constant.DEFAULT_MINUTE, Constant.DEFAULT_SECOND);
    	
    	int passedTime = ((int)(System.currentTimeMillis() - startTime)) * Constant.TIME_FACTOR; 
    	calendar.set(Calendar.MILLISECOND, passedTime);
    	
    	// Affichage de la date
//    	SimpleDateFormat formatter = new SimpleDateFormat();
//    	String currentDate = formatter.format(calendar.getTime());
//    	System.out.println(currentDate);
    	
    	return calendar;
    }
}
