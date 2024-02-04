package Estructuras.Dinamicas;

public class NodoAdy {
    private NodoVertice nodo;
    private Object etiqueta;
    private NodoAdy nodoAdyacente;

    public NodoAdy(NodoVertice nodoVert, NodoAdy nodoAdyacente, Object etiqueta) {
        this.etiqueta = etiqueta;
        this.nodoAdyacente = nodoAdyacente;
        nodo = nodoVert;
    }

    public void setEtiqueta(Object etiqueta) {
        this.etiqueta = etiqueta;
    }

    public void setNodo(NodoVertice nodo) {
        this.nodo = nodo;
    }

    public void setNodoAdyacente(NodoAdy nodoAdyacente) {
        this.nodoAdyacente = nodoAdyacente;
    }

    public Object getEtiqueta() {
        return etiqueta;
    }

    public NodoVertice getNodo() {
        return nodo;
    }

    public NodoAdy getNodoAdyacente() {
        return nodoAdyacente;
    }
}
