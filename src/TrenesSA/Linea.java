package TrenesSA;

import java.util.List;

import javax.sound.sampled.ReverbType;

public class Linea {
    List<String> estaciones;
    String nombreLinea;
    boolean funcionando;

    public Linea(String nombreLinea, List<String> estaciones, boolean funcionando) {
        this.estaciones = estaciones;
        this.nombreLinea = nombreLinea;
        this.funcionando = funcionando;
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
        String func;
        if (funcionando) {
            func = "en funcionamiento";
        } else {
            func = "fuera de funcionamiento";
        }
        return nombreLinea + str + ";" + func;
    }
}
