package ejercicio3;

public class AerolineaStatic {
    AerolineaServiceStatic aerolineaService;

    public AerolineaStatic(){
        this.aerolineaService = new AerolineaServiceStatic();
    }

    public AerolineaStatic(AerolineaServiceStatic aerolineaService){
        this.aerolineaService = aerolineaService;
    }
    public String reservaVuelo(String destino, int nro_pasajes, int dia, int mes, int gestion){
        boolean hayPasajesDisponibles = aerolineaService.existenPasajes(destino, nro_pasajes);
        if(hayPasajesDisponibles){
            String day = aerolineaService.getDay(dia, mes, gestion);
            return "el dia " + day + " existen " + nro_pasajes + " pasajes para " + destino;
        }else{
            return "no existen suficientes pasajes para " + destino;
        }
    }
}
