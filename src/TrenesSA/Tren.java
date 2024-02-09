package TrenesSA;

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

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public void setTipoDePropulsion(String tipoDePropulsion) {
        this.tipoDePropulsion = tipoDePropulsion;
    }

    public void setVagonesCarga(int vagonesCarga) {
        this.vagonesCarga = vagonesCarga;
    }

    public void setVagonesPasajeros(int vagonesPasajeros) {
        this.vagonesPasajeros = vagonesPasajeros;
    }

    public String getLinea() {
        return linea;
    }

    public String getTipoDePropulsion() {
        return tipoDePropulsion;
    }

    public int getVagonesCarga() {
        return vagonesCarga;
    }

    public int getVagonesPasajeros() {
        return vagonesPasajeros;
    }

    @Override
    public String toString() {
        return tipoDePropulsion + ";" + vagonesPasajeros + ";" + vagonesCarga + ";" + linea;
    }

}
