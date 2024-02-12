package TrenesSA;

import java.util.Set;

public class Estacion {
    String nombreEstacion;
    String calle;
    int numeroCalle;
    String ciudad;
    String codigoPostal;
    int cantidadVias;
    int cantidadPlataformas;

    public Estacion(String nombreEstacion, String calle, int numeroCalle, String ciudad, String codigoPostal,
            int cantidadVias,
            int cantidadPlataformas) {
        this.nombreEstacion = nombreEstacion;
        this.calle = calle;
        this.numeroCalle = numeroCalle;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.cantidadVias = cantidadVias;
        this.cantidadPlataformas = cantidadPlataformas;

    }

    public Estacion(String nombreEstacion, String calle, String numeroCalle, String ciudad, String codigoPostal,
            String cantidadVias,
            String cantidadPlataformas) {
        this.nombreEstacion = nombreEstacion;
        this.calle = calle;
        this.numeroCalle = Integer.parseInt(numeroCalle);
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.cantidadVias = Integer.parseInt(cantidadVias);
        this.cantidadPlataformas = Integer.parseInt(cantidadPlataformas);
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setCantidadPlataformas(int cantidadPlataformas) {
        this.cantidadPlataformas = cantidadPlataformas;
    }

    public void setCantidadVias(int cantidadVias) {
        this.cantidadVias = cantidadVias;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setNumeroCalle(int numeroCalle) {
        this.numeroCalle = numeroCalle;
    }

    public String getCalle() {
        return calle;
    }

    public int getCantidadPlataformas() {
        return cantidadPlataformas;
    }

    public int getCantidadVias() {
        return cantidadVias;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public int getNumeroCalle() {
        return numeroCalle;
    }

    @Override
    public String toString() {
        return nombreEstacion + ";" + calle + ";" + numeroCalle + ";" + ciudad + ";" + codigoPostal + ";" + cantidadVias
                + ";"
                + cantidadPlataformas;
    }

}
