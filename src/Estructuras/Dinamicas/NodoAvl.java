package Estructuras.Dinamicas;

public class NodoAvl {
    private Comparable elem;
    private NodoAvl hijIzq;
    private NodoAvl hijDer;
    private int altura;

    public NodoAvl(Comparable elem) {
        this.elem = elem;
        altura = 0;
    }

    public void setElem(Comparable elem) {
        this.elem = elem;
    }

    public void setHijDer(NodoAvl hijDer) {
        this.hijDer = hijDer;
    }

    public void setHijIzq(NodoAvl hijIzq) {
        this.hijIzq = hijIzq;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getAltura() {
        return altura;
    }

    public Comparable getElem() {
        return elem;
    }

    public NodoAvl getHijDer() {
        return hijDer;
    }

    public NodoAvl getHijIzq() {
        return hijIzq;
    }

    public void calcularAltura() {
        int altIzq = -1;
        int altDer = -1;
        if (hijIzq != null)
            altIzq = hijIzq.getAltura();
        if (hijDer != null)
            altDer = hijDer.getAltura();
        altura = Math.max(altIzq, altDer) + 1;
    }

    @Override
    public String toString() {
        String str = "[" + elem + "]";
        String izq = "()";
        if (hijIzq != null) {
            izq = "(" + hijIzq.getElem() + ")";
        }
        String der = "()";
        if (hijDer != null) {
            der = "(" + hijDer.getElem() + ")";
        }
        return "{" + str + izq + der + "}" + "Altura: " + altura;
    }

}
