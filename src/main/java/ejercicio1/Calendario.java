package ejercicio1;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Calendario {
    public String nextDay(int dia, int mes, int anio){
        Date currentDate = new GregorianCalendar(anio, mes-1, dia).getTime();
        DateTime dateTime = new DateTime(currentDate);
        DateTime newDateTime = dateTime.plusDays(1);
        Date newDate = newDateTime.toDate();
        return newDate.toLocaleString();
    }
}