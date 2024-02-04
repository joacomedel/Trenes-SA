package test;

import Estructuras.Dinamicas.Avl;

public class testAvl {
    public static void main(String[] args) {
        Avl avl = new Avl();
        int[] array = { 10, 7, 15, 6, 12, 8, 17, 4, 11, 7, 14, 9, 16, 13 };
        insertar(array, avl);
        System.out.println(avl.toString());
        avl.eliminar(11);
        System.out.println(avl.toString());
        System.out.println(avl.inOrden());
    }

    public static void insertar(int[] array, Avl avl) {
        for (int i : array) {
            avl.insertar(i);
        }
    }
}
