package test;

import Estructuras.Estaticas.*;

public class testHeap {

    public static void main(String[] args) {
        HeapMin hMin = new HeapMin(10);
        hMin.insertar(1);
        hMin.insertar(2);
        hMin.insertar(3);
        hMin.insertar(4);
        hMin.insertar(5);
        hMin.insertar(6);
        System.out.println(hMin.toString());
        hMin.eliminarCima();
        System.out.println(hMin.toString());

    }
}
