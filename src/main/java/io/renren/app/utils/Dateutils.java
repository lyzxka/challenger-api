package io.renren.app.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dateutils {
    public static String dateMin(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date= sdf.parse(dateString);
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
    public static String dateMax(String dateString) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date= sdf.parse(dateString);
        date.setHours(23);
        date.setMinutes(59);
        date.setSeconds(59);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
    public static String dateMin(Date date)   {
        date.setHours(00);
        date.setMinutes(00);
        date.setSeconds(00);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
    public static String dateMax(Date date)  {
        date.setHours(23);
        date.setMinutes(59);
        date.setSeconds(59);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
    //预约时间
    public static Long longAppointmentTime(Date date)  {
        date.setHours(15);
        date.setMinutes(00);
        date.setSeconds(00);
      //  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return date.getTime();
    }
    //预约时间
    public static String stringAppointmentTime(Date date)  {
        date.setHours(15);
        date.setMinutes(00);
        date.setSeconds(00);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return  format.format(date);
    }
}
