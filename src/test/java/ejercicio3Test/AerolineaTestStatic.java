package ejercicio3Test;

import ejercicio3.AerolineaServiceStatic;
import ejercicio3.AerolineaStatic;
import org.junit.jupiter.api.AfterEach;
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
        overrideAerolineaServiceStatic = Mockito.mockStatic(AerolineaServiceStatic.class);

        overrideAerolineaServiceStatic.when(() -> AerolineaServiceStatic.existenPasajes("La Paz", 2)).thenReturn(true);
        overrideAerolineaServiceStatic.when(() -> AerolineaServiceStatic.existenPasajes("Cochabamba", 2)).thenReturn(false);

        overrideAerolineaServiceStatic.when(() -> AerolineaServiceStatic.getDay(29, 5, 2023)).thenReturn("Lunes 29 Mayo 2023");
    }
    @AfterEach
    public void cleanup(){
        overrideAerolineaServiceStatic.close();
    }

    @ParameterizedTest
    @CsvSource({
            "La Paz, 2, 29, 5, 2023, el dia Lunes 29 Mayo 2023 existen 2 pasajes para La Paz"
    })
    public void verifyReservaVueloExists(String destinoTest, int nroPasajesTest, int diaTest, int mesTest, int anioTest, String expectedResult) {
        AerolineaStatic aerolineaStatic = new AerolineaStatic();

        String actualResult = aerolineaStatic.reservaVuelo(destinoTest, nroPasajesTest, diaTest, mesTest, anioTest);
        Assertions.assertEquals(expectedResult, actualResult);

        overrideAerolineaServiceStatic.verify(() -> AerolineaServiceStatic.getDay(diaTest, mesTest, anioTest));
        overrideAerolineaServiceStatic.verify(() -> AerolineaServiceStatic.existenPasajes(destinoTest, nroPasajesTest));
    }

    @ParameterizedTest
    @CsvSource({
            "Cochabamba, 2, 30, 12, 2024, no existen suficientes pasajes para Cochabamba"
    })
    public void verifyReservaVueloNotExists(String destinoTest, int nroPasajesTest, int diaTest, int mesTest, int anioTest, String expectedResult) {
        AerolineaStatic aerolineaStatic = new AerolineaStatic();

        String actualResult = aerolineaStatic.reservaVuelo(destinoTest, nroPasajesTest, diaTest, mesTest, anioTest);
        Assertions.assertEquals(expectedResult, actualResult);

        overrideAerolineaServiceStatic.verify(() -> AerolineaServiceStatic.existenPasajes(destinoTest, nroPasajesTest));
    }
}
