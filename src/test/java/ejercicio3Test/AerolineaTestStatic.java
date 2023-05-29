package ejercicio3Test;

import ejercicio2.Aerolinea;
import ejercicio2.AerolineaService;
import ejercicio3.AerolineaServiceStatic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class AerolineaTestStatic {
    MockedStatic<AerolineaServiceStatic> overrideAerolineaServiceStatic;

    @BeforeEach
    public void setup(){

        Mockito.when(overrideAerolineaServiceStatic.existenPasajes("La Paz", 2)).thenReturn(true);
        Mockito.when(overrideAerolineaServiceStatic.existenPasajes("Cochabamba", 2)).thenReturn(false);

        Mockito.when(overrideAerolineaServiceStatic.getDay(29, 5, 2023)).thenReturn("Lunes 29 Mayo 2023");
    }

    @ParameterizedTest
    @CsvSource({
            "La Paz, 2, 29, 5, 2023, el dia Lunes 29 Mayo 2023 existen 2 pasajes para La Paz"
    })
    public void verifyReservaVueloExists(String destinoTest, int nroPasajesTest, int diaTest, int mesTest, int anioTest, String expectedResult) {
        Aerolinea aerolinea = new Aerolinea(overrideAerolineaServiceStatic);

        String actualResult = aerolinea.reservaVuelo(destinoTest, nroPasajesTest, diaTest, mesTest, anioTest);
        Assertions.assertEquals(expectedResult, actualResult);

        Mockito.verify(overrideAerolineaServiceStatic).getDay(diaTest, mesTest, anioTest);
        Mockito.verify(overrideAerolineaServiceStatic).existenPasajes(destinoTest, nroPasajesTest);
    }

    @ParameterizedTest
    @CsvSource({
            "Cochabamba, 2, 30, 12, 2024, no existen suficientes pasajes para Cochabamba"
    })
    public void verifyReservaVueloNotExists(String destinoTest, int nroPasajesTest, int diaTest, int mesTest, int anioTest, String expectedResult) {
        Aerolinea aerolinea = new Aerolinea(overrideAerolineaServiceStatic);

        String actualResult = aerolinea.reservaVuelo(destinoTest, nroPasajesTest, diaTest, mesTest, anioTest);
        Assertions.assertEquals(expectedResult, actualResult);

        Mockito.verify(overrideAerolineaServiceStatic).existenPasajes(destinoTest, nroPasajesTest);
    }
}
