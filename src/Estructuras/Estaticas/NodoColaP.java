package Estructuras.Estaticas;

public class NodoColaP<T> implements Comparable<NodoColaP<T>> {
    private T elem;
    private int valor;

    public NodoColaP(T elem, int valor) {
        this.elem = elem;
        this.valor = valor;
    }

    public T getElem() {
        return elem;
    }

    public int getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "{" + elem + "," + valor + "}";
    }

    @Override
    public int compareTo(NodoColaP<T> otroNodo) {
        return Integer.compare(this.valor, otroNodo.getValor());
    }
}
