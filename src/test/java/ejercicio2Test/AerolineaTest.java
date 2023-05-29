package ejercicio2Test;

import ejercicio2.Aerolinea;
import ejercicio2.AerolineaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

public class AerolineaTest {

    AerolineaService aerolineaServiceMock = Mockito.mock(AerolineaService.class);

    @BeforeEach
    public void setup(){
        Mockito.when(aerolineaServiceMock.existenPasajes("La Paz", 2)).thenReturn(true);
        Mockito.when(aerolineaServiceMock.existenPasajes("Buenos Aires", 5)).thenReturn(true);
        Mockito.when(aerolineaServiceMock.existenPasajes("Santa Cruz", 1)).thenReturn(true);
        Mockito.when(aerolineaServiceMock.existenPasajes("Beni", 3)).thenReturn(true);
        Mockito.when(aerolineaServiceMock.existenPasajes("La Paz", 10)).thenReturn(true);

        Mockito.when(aerolineaServiceMock.existenPasajes("Cochabamba", 2)).thenReturn(false);
        Mockito.when(aerolineaServiceMock.existenPasajes("Santa Cruz", 3)).thenReturn(false);
        Mockito.when(aerolineaServiceMock.existenPasajes("New York", 5)).thenReturn(false);
        Mockito.when(aerolineaServiceMock.existenPasajes("Beni", 10)).thenReturn(false);
        Mockito.when(aerolineaServiceMock.existenPasajes("La Paz", 11)).thenReturn(false);

        Mockito.when(aerolineaServiceMock.getDay(29, 5, 2023)).thenReturn("Lunes 29 Mayo 2023");
        Mockito.when(aerolineaServiceMock.getDay(30, 4, 2024)).thenReturn("Domingo 30 Abril 2024");
        Mockito.when(aerolineaServiceMock.getDay(15, 5, 2023)).thenReturn("Lunes 15 Mayo 2023");
        Mockito.when(aerolineaServiceMock.getDay(20, 8, 2023)).thenReturn("Domingo 20 Agosto 2023");
        Mockito.when(aerolineaServiceMock.getDay(1, 6, 2023)).thenReturn("Jueves 1 Junio 2023");
    }

    @ParameterizedTest
    @CsvSource({
            "La Paz, 2, 29, 5, 2023, el dia Lunes 29 Mayo 2023 existen 2 pasajes para La Paz",
            "Buenos Aires, 5, 30, 4, 2024, el dia Domingo 30 Abril 2024 existen 5 pasajes para Buenos Aires",
            "Santa Cruz, 1, 15, 5, 2023, el dia Lunes 15 Mayo 2023 existen 1 pasajes para Santa Cruz",
            "Beni, 3, 20, 8, 2023, el dia Domingo 20 Agosto 2023 existen 3 pasajes para Beni",
            "La Paz, 10, 1, 6, 2023, el dia Jueves 1 Junio 2023 existen 10 pasajes para La Paz"
    })
    public void verifyReservaVueloExists(String destinoTest, int nroPasajesTest, int diaTest, int mesTest, int anioTest, String expectedResult) {
        Aerolinea aerolinea = new Aerolinea(aerolineaServiceMock);

        String actualResult = aerolinea.reservaVuelo(destinoTest, nroPasajesTest, diaTest, mesTest, anioTest);
        Assertions.assertEquals(expectedResult, actualResult);

        Mockito.verify(aerolineaServiceMock).getDay(diaTest, mesTest, anioTest);
        Mockito.verify(aerolineaServiceMock).existenPasajes(destinoTest, nroPasajesTest);
    }

    @ParameterizedTest
    @CsvSource({
            "Cochabamba, 2, 30, 12, 2024, no existen suficientes pasajes para Cochabamba",
            "Santa Cruz, 3, 15, 5, 2023, no existen suficientes pasajes para Santa Cruz",
            "New York, 5, 20, 8, 2023, no existen suficientes pasajes para New York",
            "Beni, 10, 1, 6, 2023, no existen suficientes pasajes para Beni",
            "La Paz, 11, 1, 6, 2023, no existen suficientes pasajes para La Paz"
    })
    public void verifyReservaVueloNotExists(String destinoTest, int nroPasajesTest, int diaTest, int mesTest, int anioTest, String expectedResult) {
        Aerolinea aerolinea = new Aerolinea(aerolineaServiceMock);

        String actualResult = aerolinea.reservaVuelo(destinoTest, nroPasajesTest, diaTest, mesTest, anioTest);
        Assertions.assertEquals(expectedResult, actualResult);

        Mockito.verify(aerolineaServiceMock).existenPasajes(destinoTest, nroPasajesTest);
    }
}
