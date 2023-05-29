package ejercicio1Test;

import ejercicio1.Calendario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.text.ParseException;

public class CalendarioTest {

    Calendario calendario = new Calendario();

    @ParameterizedTest
    @CsvSource({
            "31,12,2023,01/01/2024",
    })
    public void verifyNextDay(int dia, int mes, int anio, String expectedResult){
        String actualResult = calendario.nextDay(dia, mes, anio);
        System.out.println(actualResult);
        Assertions.assertEquals(expectedResult, actualResult);
    }
}
