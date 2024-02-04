package Estructuras.TrenesSA;

public class Tren {
    String tipoDePropulsion;
    int vagonesPasajeros;
    int vagonesCarga;
    String linea;

    public Tren(String tipoDePropulsion, int vagonesPasajeros, int vagonesCarga, String linea) {
        this.tipoDePropulsion = tipoDePropulsion;
        this.vagonesCarga = vagonesCarga;
        this.vagonesPasajeros = vagonesPasajeros;
        this.linea = linea;
    }

    public Tren(String tipoDePropulsion, String vagonesPasajeros, String vagonesCarga, String linea) {
        this.tipoDePropulsion = tipoDePropulsion;
        this.vagonesCarga = Integer.parseInt(vagonesCarga);
        this.vagonesPasajeros = Integer.parseInt(vagonesPasajeros);
        this.linea = linea;
    }

    @Override
    public String toString() {

        return "[" + tipoDePropulsion + "," + vagonesPasajeros + "," + vagonesCarga + "," + linea + "]";
    }

}
