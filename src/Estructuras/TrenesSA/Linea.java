package Estructuras.TrenesSA;

import java.util.List;

import javax.sound.sampled.ReverbType;

public class Linea {
    List<String> estaciones;

    public Linea(List<String> estaciones) {
        this.estaciones = estaciones;
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
