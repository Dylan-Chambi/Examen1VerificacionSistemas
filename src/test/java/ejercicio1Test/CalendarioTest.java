package ejercicio1Test;

import ejercicio1.Calendario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CalendarioTest {

    Calendario calendario = new Calendario();

    @ParameterizedTest
    @CsvSource({
            "14,4,0,15/04/0000",
            "31,12,0,01/01/0001",
            "2,1,1,03/01/0001",
            "14,4,2,15/04/0002",
            "31,12,2023,01/01/2024",
            "28,2,2023,01/03/2023",
            "30,4,3000,01/05/3000",
            "30,4,4000,01/05/4000",

    })
    public void verifyNextDayPositive(int dia, int mes, int anio, String expectedResult){
        String actualResult = calendario.nextDay(dia, mes, anio);
        System.out.println(actualResult);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest
    @CsvSource({
            "31,15,-1",
            "40,2,2020",
            "0,0,0",
            "12,20,2000"
    })
    public void verifyNextDayNegative(int dia, int mes, int anio){
        Assertions.assertThrows(Exception.class, () -> calendario.nextDay(dia, mes, anio));
    }
}
