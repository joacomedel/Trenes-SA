package test;

import Estructuras.Dinamicas.Grafo;

public class testGrafos {

    public static void main(String[] args) {
        Grafo grafo = new Grafo();
        grafo.insertarVertice("Plottier");
        grafo.insertarVertice("Neuquen");
        grafo.insertarVertice("Cipolleti");
        grafo.insertarVertice("Centenario");
        grafo.insertarVertice("Cinco saltos");
        grafo.insertarVertice("Ciudad basura");
        grafo.insertarVertice("Ciudad 1");

        grafo.insertarArco("Ciudad 1", "Cinco saltos", 16);
        grafo.insertarArco("Cinco saltos", "Cipolleti", 16);
        grafo.insertarArco("Cinco saltos", "Centenario", 5);
        grafo.insertarArco("Centenario", "Neuquen", 10);
        grafo.insertarArco("Cipolleti", "Neuquen", 4);
        grafo.insertarArco("Plottier", "Neuquen", 10);
        grafo.insertarArco("Plottier", "Centenario", 17);
        grafo.insertarArco("Centenario", "Ciudad basura", 17);
        System.out.println(grafo.toString());
        System.out.println(grafo.caminoMasCortoVertices("Cinco saltos", "Plottier"));
    }
}
