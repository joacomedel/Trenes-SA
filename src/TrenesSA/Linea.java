package TrenesSA;

import java.util.List;

import javax.sound.sampled.ReverbType;

public class Linea {
    List<String> estaciones;

    public Linea(List<String> estaciones) {
        this.estaciones = estaciones;
    }

    public boolean eliminarEstacion(String estacion) {
        return estaciones.remove(estacion);
    }

    public boolean agregarEstacion(String estacion) {
        return estaciones.add(estacion);
    }

    public List<String> getEstaciones() {
        return estaciones;
    }

    @Override
    public String toString() {
        String str = "";
        for (String string : estaciones) {
            str += string + "|";
        }
        return str;
    }
}
