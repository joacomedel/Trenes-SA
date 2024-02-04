package test;

import Estructuras.Dinamicas.Diccionario;
import Estructuras.TrenesSA.Tren;

public class testDiccionario {

    public static void main(String[] args) {
        Tren tren = new Tren("Tren 1", 10, 10, "B");
        Tren tren2 = new Tren("Tren 2", 10, 10, "B");
        Diccionario diccionario = new Diccionario();
        diccionario.insertar(1, tren);
        diccionario.insertar(2, tren2);
        System.out.println(diccionario.toString());
        System.out.println(diccionario.obtener(2));
    }
}
