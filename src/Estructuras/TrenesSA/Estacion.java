package Estructuras.TrenesSA;

public class Estacion {
    String calle;
    int numeroCalle;
    String ciudad;
    String codigoPostal;
    int cantidadVias;
    int cantidadPlataformas;

    public Estacion(String calle, int numeroCalle, String ciudad, String codigoPostal, int cantidadVias,
            int cantidadPlataformas) {
        this.calle = calle;
        this.numeroCalle = numeroCalle;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.cantidadVias = cantidadVias;
        this.cantidadPlataformas = cantidadPlataformas;

    }

    public Estacion(String calle, String numeroCalle, String ciudad, String codigoPostal, String cantidadVias,
            String cantidadPlataformas) {
        this.calle = calle;
        this.numeroCalle = Integer.parseInt(numeroCalle);
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.cantidadVias = Integer.parseInt(cantidadVias);
        this.cantidadPlataformas = Integer.parseInt(cantidadPlataformas);
    }
}
