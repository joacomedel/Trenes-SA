package Estructuras.Estaticas;

import javax.swing.text.html.HTMLDocument.RunElement;

public class ColaPrioridad {
    // cola por prioridad menor
    private HeapMin heap;

    public ColaPrioridad(int tamanio) {
        heap = new HeapMin(tamanio);
    }

    public boolean insertar(Object elem, Comparable valor) {
        boolean inserto = false;
        inserto = heap.insertar(new NodoColaP<Object>(elem, valor));
        return inserto;
    }

    public boolean eliminarFrente() {
        return heap.eliminarCima();
    }

    public Object obtenerFrente() {
        return ((NodoColaP<Object>) heap.recuperarCima()).getElem();
    }

    public boolean esVacio() {
        return heap.esVacio();
    }

    @Override
    public String toString() {
        String str = heap.toString();
        return str;
    }
}
