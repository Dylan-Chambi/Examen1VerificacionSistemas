package ejercicio2;

public class Aerolinea {
    AerolineaService aerolineaService;

    public  Aerolinea(){
        this.aerolineaService = new AerolineaService();
    }

    public Aerolinea(AerolineaService aerolineaService){
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
