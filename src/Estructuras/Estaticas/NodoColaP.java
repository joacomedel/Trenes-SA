package Estructuras.Estaticas;

public class NodoColaP<T> implements Comparable<NodoColaP<T>> {
    private T elem;
    Comparable valor;

    public NodoColaP(T elem, Comparable valor) {
        this.elem = elem;
        this.valor = valor;
    }

    public T getElem() {
        return elem;
    }

    public Comparable getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "{" + elem + "," + valor + "}";
    }

    @Override
    public int compareTo(NodoColaP<T> otroNodo) {
        return valor.compareTo(otroNodo.getValor());
    }
}
