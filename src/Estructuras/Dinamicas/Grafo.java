package Estructuras.Dinamicas;

public class Grafo {
    private NodoVertice nodoInicial;

    public boolean insertarVertice(Object obj) {
        boolean noEncontro = ubicarVertice(obj) == null;
        if (ubicarVertice(obj) == null) {
            nodoInicial = new NodoVertice(nodoInicial, obj);
        }
        return noEncontro;
    }

    public boolean insertarArco(Object elemVert1, Object elemVert2, Object etiqueta) {
        boolean exito;
        NodoVertice nodoOrigen = null;
        NodoVertice nodoFinal = null;
        NodoVertice aux = nodoInicial;
        while (aux != null && (nodoOrigen == null || nodoFinal == null)) {
            if (aux.getElem().equals(elemVert1)) {
                nodoOrigen = aux;
            }
            if (aux.getElem().equals(elemVert2)) {
                nodoFinal = aux;
            }
            aux.getSigNodoVert();
        }
        exito = nodoOrigen != null && nodoFinal != null;
        if (exito) {

            nodoOrigen.setPrimerAdy(new NodoAdy(nodoFinal, nodoOrigen.getPrimerAdy(), etiqueta));
            if (nodoOrigen != nodoFinal) {
                // Solo entra cuando los nodos son distintos , si fueran iguales seria un lazo y
                // con un solo ady seria suficiente
                nodoFinal.setPrimerAdy(new NodoAdy(nodoOrigen, nodoFinal.getPrimerAdy(), etiqueta));
            }

        }
        return exito;
    }

    public boolean eliminarArco(Object elemVert1, Object elemVert2) {
        boolean exito = false;
        NodoVertice aux = this.nodoInicial;
        NodoVertice nodoOrigen = null;
        NodoVertice nodoFinal = null;
        boolean elemVert1Comparado = false;
        while (aux != null && nodoOrigen == null) {
            if (aux.getElem().equals(elemVert1)) {
                nodoOrigen = aux;
                elemVert1Comparado = true;
            }
            if (aux.getElem().equals(elemVert2)) {
                nodoOrigen = aux;
            }
            aux = aux.getSigNodoVert();
        }
        if (nodoOrigen != null) {
            NodoVertice temp;
            if (elemVert1Comparado) {
                temp = eliminarNodoAdy(nodoOrigen, elemVert2);
            } else {
                temp = eliminarNodoAdy(nodoOrigen, elemVert1);
            }
            if (temp != null) {
                if (elemVert1Comparado) {
                    eliminarNodoAdy(temp, elemVert1);
                } else {
                    eliminarNodoAdy(temp, elemVert2);
                }
            }
        }
        return exito;
    }

    private NodoVertice eliminarNodoAdy(NodoVertice nodoVert, Object elem) {
        NodoVertice nodoRef = null;
        NodoAdy nodoAdy = nodoVert.getPrimerAdy();
        boolean encontro;
        if (nodoAdy != null) {
            encontro = nodoAdy.getNodo().getElem().equals(elem);
            if (encontro) {
                // El primer adyacente es el q quiero borrar
                nodoRef = nodoAdy.getNodo();
                nodoVert.setPrimerAdy(nodoAdy.getNodoAdyacente());
            } else {
                NodoAdy anterior;
                anterior = nodoAdy;
                nodoAdy = nodoAdy.getNodoAdyacente();
                while (nodoAdy != null && !encontro) {
                    encontro = nodoAdy.getNodo().getElem().equals(elem);
                    if (encontro) {
                        nodoRef = nodoAdy.getNodo();
                        anterior.setNodoAdyacente(nodoAdy.getNodoAdyacente());
                    }
                }
            }
        }
        return nodoRef;
    }

    private NodoVertice ubicarVertice(Object obj) {
        NodoVertice aux = this.nodoInicial;
        while (aux != null && !aux.getElem().equals(obj)) {
            aux = aux.getSigNodoVert();
        }
        return aux;
    }
}
