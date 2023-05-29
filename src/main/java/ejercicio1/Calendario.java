package ejercicio1;

import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Calendario {
    public String nextDay(int dia, int mes, int anio){
        if (dia <= 0 || mes <= 0 || anio < 0 || dia > 31 || mes > 12){
            throw new IllegalArgumentException();
        }
        Date currentDate = new GregorianCalendar(anio, mes-1, dia).getTime();
        DateTime dateTime = new DateTime(currentDate);
        DateTime newDateTime = dateTime.plusDays(1);
        Date newDate = newDateTime.toDate();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        if(anio == 0 && mes != 12 && anio != 31){
            String[] date = dateFormat.format(newDate).split("/");
            return date[0] + "/" + date[1] + "/0000";
        }

        return dateFormat.format(newDate);
    }
}
