package Estructuras.Estaticas;

public class HeapMin {
    private int tamanio;
    private Comparable[] array;
    private int ultimo;

    public HeapMin(int tamanio) {
        this.tamanio = tamanio;
        this.array = new Comparable[tamanio];
        this.ultimo = 0;
    }

    public boolean esVacio() {
        return ultimo == 0;
    }

    public boolean insertar(Comparable elem) {
        boolean puedeInsertar = ultimo + 1 != tamanio;
        if (puedeInsertar) {
            array[ultimo + 1] = elem;
            ultimo++;
            subirElem(ultimo);
        }
        return puedeInsertar;
    }

    private void subirElem(int pos) {

        if (pos > 1) {
            int posPadre = pos / 2;
            if (array[pos].compareTo(array[posPadre]) < 0) {
                Comparable temp;
                temp = array[pos];
                array[pos] = array[posPadre];
                array[posPadre] = temp;
                subirElem(posPadre);
            }
        }
    }

    public void eliminarCima() {
        if (!this.esVacio()) {
            array[1] = array[ultimo];
            array[ultimo] = null;
            ultimo--;
            bajarElem(1);
        }
    }

    private void bajarElem(int pos) {
        int posMen = 0;// Posicion del menor de los hijos
        if (pos * 2 <= ultimo) {
            posMen = pos * 2;
        }
        if (pos * 2 + 1 <= ultimo) {
            if (array[posMen].compareTo(array[pos * 2 + 1]) > 0) {
                posMen = pos * 2 + 1;
            }
        }
        if (posMen != 0 && array[pos].compareTo(array[posMen]) > 0) {
            Comparable temp = array[pos];
            array[pos] = array[posMen];
            array[posMen] = temp;
            bajarElem(posMen);
        }
    }

    public Comparable recuperarCima() {
        return array[1];
    }

    @Override
    public String toString() {
        String str = "[";
        for (int index = 1; index <= ultimo; index++) {
            str += array[index] + ",";
        }
        str += "]";
        return str;
    }

}
