package Estructuras.Dinamicas;

public class NodoDiccionario {
    private Comparable clave;
    private Object dato;
    private NodoDiccionario hijIzq;
    private NodoDiccionario hijDer;
    private int altura;

    public NodoDiccionario(Comparable clave, Object dato) {
        this.dato = dato;
        this.clave = clave;
        altura = 0;
    }

    public void setClave(Comparable clave) {
        this.clave = clave;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public Comparable getClave() {
        return clave;
    }

    public Object getDato() {
        return dato;
    }

    public void setHijDer(NodoDiccionario hijDer) {
        this.hijDer = hijDer;
    }

    public void setHijIzq(NodoDiccionario hijIzq) {
        this.hijIzq = hijIzq;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getAltura() {
        return altura;
    }

    public NodoDiccionario getHijDer() {
        return hijDer;
    }

    public NodoDiccionario getHijIzq() {
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
        String str = "[" + clave + "]";
        String izq = "()";
        if (hijIzq != null) {
            izq = "(" + hijIzq.getClave() + ")";
        }
        String der = "()";
        if (hijDer != null) {
            der = "(" + hijDer.getClave() + ")";
        }
        return "{" + str + izq + der + "}" + "Altura: " + altura;
    }

}
