package test;

import Estructuras.Estaticas.ColaPrioridad;

public class testColaPrioridad {
    public static void main(String[] args) {
        ColaPrioridad colaPrio = new ColaPrioridad(100);
        colaPrio.insertar("elem 5", 5);
        colaPrio.insertar("elem 100", 100);
        colaPrio.insertar("elem 4", 4);
        colaPrio.insertar("elem 1", 1);
        colaPrio.insertar("elem -50", -50);
        System.out.println(colaPrio.toString());
    }
}
