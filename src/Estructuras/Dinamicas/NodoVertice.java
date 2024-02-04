package Estructuras.Dinamicas;

public class NodoVertice {
    private Object elem;
    private NodoVertice sigNodoVert;
    private NodoAdy primerAdy;

    public NodoVertice(NodoVertice nodo, Object obj) {
        sigNodoVert = nodo;
        elem = obj;
    }

    public void setElem(Object elem) {
        this.elem = elem;
    }

    public void setPrimerAdy(NodoAdy primerAdy) {
        this.primerAdy = primerAdy;
    }

    public void setSigNodoVert(NodoVertice sigNodoVert) {
        this.sigNodoVert = sigNodoVert;
    }

    public Object getElem() {
        return elem;
    }

    public NodoAdy getPrimerAdy() {
        return primerAdy;
    }

    public NodoVertice getSigNodoVert() {
        return sigNodoVert;
    }
}
