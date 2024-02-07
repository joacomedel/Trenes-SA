package Estructuras.Dinamicas;

import java.util.ArrayList;
import java.util.List;

import Estructuras.Estaticas.ColaPrioridad;

public class Grafo {
    private NodoVertice nodoInicial;

    public boolean insertarVertice(Object obj) {
        boolean noEncontro = ubicarVertice(obj) == null;
        if (noEncontro) {
            nodoInicial = new NodoVertice(nodoInicial, obj);
        }
        return noEncontro;
    }

    public boolean eliminarVertice(Object elem) {
        boolean elimino = false;
        NodoVertice aux = this.nodoInicial;
        NodoVertice anterior = null;
        while (aux != null && !elimino) {
            if (aux.getElem().equals(elem)) {
                NodoAdy auxAdy = aux.getPrimerAdy();
                while (auxAdy != null) {
                    eliminarNodoAdy(auxAdy.getNodo(), elem);
                    auxAdy = auxAdy.getNodoAdyacente();
                }
                aux.setPrimerAdy(null);
                if (anterior == null) {
                    this.nodoInicial = aux.getSigNodoVert();
                } else {
                    anterior.setSigNodoVert(aux.getSigNodoVert());
                }
                elimino = true;
            }
            anterior = aux;
            aux = aux.getSigNodoVert();
        }
        return elimino;
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
            aux = aux.getSigNodoVert();
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
                exito = true;
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

    public List<Object> listarEnProfundidad() {
        List<Object> visitados = new ArrayList<Object>();
        NodoVertice aux = this.nodoInicial;
        while (aux != null) {
            if (!visitados.contains(aux.getElem())) {
                listarEnProfundidadRec(aux, visitados);
            }
            aux = aux.getSigNodoVert();
        }
        return visitados;
    }

    private void listarEnProfundidadRec(NodoVertice nodo, List<Object> visitados) {
        if (nodo != null) {
            visitados.add(nodo.getElem());
            NodoAdy nodoAdy = nodo.getPrimerAdy();
            while (nodoAdy != null) {
                if (!visitados.contains(nodoAdy.getNodo().getElem())) {
                    listarEnProfundidadRec(nodoAdy.getNodo(), visitados);
                }
                nodoAdy = nodoAdy.getNodoAdyacente();
            }
        }
    }

    public List<Object> caminoMasCortoVertices(Object elemPartida, Object elemLlegada) {
        boolean encontroPartida = false;
        boolean encontroLlegada = false;
        NodoVertice aux = this.nodoInicial;
        while (!encontroPartida && !encontroLlegada && aux != null) {
            encontroPartida = aux.getElem().equals(elemPartida);
            encontroLlegada = aux.getElem().equals(elemLlegada);
            if (!encontroPartida && !encontroLlegada) {
                aux = aux.getSigNodoVert();
            }
        }
        List<Object>[] listas = new ArrayList[2];
        listas[0] = new ArrayList<>();
        listas[1] = new ArrayList<>();
        if (encontroLlegada) {
            caminoMasCortoVerticesAux(elemPartida, aux, listas);
        } else {
            if (encontroPartida) {
                caminoMasCortoVerticesAux(elemLlegada, aux, listas);
            }
        }
        return listas[1];
    }

    private void caminoMasCortoVerticesAux(Object elem, NodoVertice nodo, List<Object>[] listas) {
        // listas 0 lista actual
        // listas 1 lista mas corta
        if (nodo != null && !listas[0].contains(nodo.getElem())) {
            listas[0].add(nodo.getElem());
            if (!elem.equals(nodo.getElem())) {
                if (listas[1].size() == 0 || listas[0].size() < listas[1].size()) {
                    // entro si tod no encontre camino minimo , si encontre me fijo q el camino
                    // actual
                    // sea menor al camino minimo "cortocircuito"
                    NodoAdy auxAdy = nodo.getPrimerAdy();
                    while (auxAdy != null) {
                        caminoMasCortoVerticesAux(elem, auxAdy.getNodo(), listas);
                        auxAdy = auxAdy.getNodoAdyacente();
                    }
                }
            } else {
                if (listas[1].size() == 0 || listas[1].size() > listas[0].size()) {
                    // Si tod no encontramos una camino minimo , o si el camino actual es menor al
                    // camino minimo
                    listas[1] = new ArrayList<>(listas[0]);
                }
            }
            listas[0].remove(nodo.getElem());
        }
    }

    public List<Object> caminoMasCortoDistancia(Object elemPartida, Object elemLlegada) {
        boolean encontroPartida = false;
        boolean encontroLlegada = false;
        NodoVertice aux = this.nodoInicial;
        while (!encontroPartida && !encontroLlegada && aux != null) {
            encontroPartida = aux.getElem().equals(elemPartida);
            encontroLlegada = aux.getElem().equals(elemLlegada);
            if (!encontroPartida && !encontroLlegada) {
                aux = aux.getSigNodoVert();
            }
        }
        List<Object>[] listas = new ArrayList[2];
        listas[0] = new ArrayList<>();
        listas[1] = new ArrayList<>();
        int[] distancias = { 0, 0 };
        if (encontroLlegada) {
            caminoMasCortoDistanciaAux(elemPartida, aux, listas, distancias);
        } else {
            if (encontroPartida) {
                caminoMasCortoDistanciaAux(elemLlegada, aux, listas, distancias);
            }
        }
        return listas[1];
    }

    private void caminoMasCortoDistanciaAux(Object elem, NodoVertice nodo, List<Object>[] listas, int[] distancias) {
        // listas 0 lista actual
        // listas 1 lista mas corta
        // distancia 0 , distancia actual
        // distancia 1 , distancia mas corta
        int distTemp = distancias[0];
        if (nodo != null && !listas[0].contains(nodo.getElem())) {
            // entra si el nodo no es nulo , y si el nodo actual no es en la lista actual ,
            // anti ciclos
            listas[0].add(nodo.getElem());
            if (!elem.equals(nodo.getElem())) {
                // No es el elemento
                if (distancias[1] == 0 || distancias[0] < distancias[1]) {
                    // No registro ninguna distancia minima , o la distancia actual es menor a la
                    // minima
                    ColaPrioridad colaPrioridad = new ColaPrioridad(100);
                    NodoAdy auxAdy = nodo.getPrimerAdy();
                    while (auxAdy != null) {
                        colaPrioridad.insertar(auxAdy, (Comparable) auxAdy.getEtiqueta());
                        auxAdy = auxAdy.getNodoAdyacente();
                    }
                    // Almacena todos los adyacentes en una cola de prioridad para ir siempre por el
                    // mas chico
                    while (!colaPrioridad.esVacio()) {
                        distancias[0] = distTemp;
                        // Setea la distancia actual que puede estar modificada , por la verdadera
                        // distancia actual guardada el inicio del metodo
                        NodoVertice temp = ((NodoAdy) colaPrioridad.obtenerFrente()).getNodo();
                        distancias[0] += (int) ((NodoAdy) colaPrioridad.obtenerFrente()).getEtiqueta();
                        // Sumo la distancia hacia donde voy a ir
                        colaPrioridad.eliminarFrente();
                        // llama a la funcion con el nodo adyacente mas "cercano"
                        caminoMasCortoDistanciaAux(elem, temp, listas, distancias);
                    }
                }
            } else {
                // es el nodo q buscamos
                if (distancias[1] == 0 || distancias[1] > distancias[0]) {
                    // Si tod no encontramos una distancia minima , o si la distancia actual es
                    // menor a la minima
                    listas[1] = new ArrayList<>(listas[0]);
                    distancias[1] = distancias[0];
                }
            }
            listas[0].remove(nodo.getElem());
            distancias[0] = distTemp;
        }
    }

    public List caminosPosiblesSinPasar(Object a, Object b, Object c) {
        // Llegar desde a , b sin pasar por c
        List<List> listaDeListas = new ArrayList<List>();
        NodoVertice aux = this.nodoInicial;
        boolean encontroA = false;
        boolean encontroB = false;
        while (aux != null && !encontroA && !encontroB) {
            encontroA = aux.getElem().equals(a);
            encontroB = aux.getElem().equals(b);
            if (!encontroA && !encontroB) {
                aux = aux.getSigNodoVert();
            }
            if (encontroA) {
                caminosPosiblesSinPasarAux(aux, b, c, listaDeListas, new ArrayList<>());
            } else if (encontroB) {
                caminosPosiblesSinPasarAux(aux, a, c, listaDeListas, new ArrayList<>());
            }
        }
        return listaDeListas;
    }

    private void caminosPosiblesSinPasarAux(NodoVertice nodo, Object elem, Object noPasar, List listaAcum,
            List listaActual) {
        if (nodo != null && !listaActual.contains(nodo.getElem()) && !nodo.getElem().equals(noPasar)) {
            listaActual.add(nodo.getElem());
            if (!nodo.getElem().equals(elem)) {
                NodoAdy auxAdy = nodo.getPrimerAdy();
                while (auxAdy != null) {
                    caminosPosiblesSinPasarAux(auxAdy.getNodo(), elem, noPasar, listaAcum, listaActual);
                    auxAdy = auxAdy.getNodoAdyacente();
                }
            } else {
                listaAcum.add(new ArrayList<>(listaActual));
            }
            listaActual.remove(nodo.getElem());
        }
    }

    public boolean existeCaminoDistanciaMax(Object elemPartida, Object elemLlegada, int distanciaMax) {
        boolean encontroPartida = false;
        boolean encontroLlegada = false;
        boolean caminoExiste = false;
        NodoVertice aux = this.nodoInicial;
        while (!encontroPartida && !encontroLlegada && aux != null) {
            encontroPartida = aux.getElem().equals(elemPartida);
            encontroLlegada = aux.getElem().equals(elemLlegada);
            if (!encontroPartida && !encontroLlegada) {
                aux = aux.getSigNodoVert();
            }
        }
        List<Object> lista = new ArrayList<>();
        int[] distancias = { 0, distanciaMax };
        if (encontroLlegada) {
            caminoExiste = existeCaminoDistanciaMaxAux(elemPartida, aux, lista, distancias);
        } else {
            if (encontroPartida) {
                caminoExiste = existeCaminoDistanciaMaxAux(elemLlegada, aux, lista, distancias);
            }
        }
        return caminoExiste;
    }

    private boolean existeCaminoDistanciaMaxAux(Object elem, NodoVertice nodo, List<Object> visitados,
            int[] distancias) {
        boolean existeCamino = false;
        // distancia 0 , distancia actual
        // distancia 1 , distancia maxima
        int distTemp = distancias[0];
        if (nodo != null && !visitados.contains(nodo.getElem()) && (distancias[0] < distancias[1])) {
            visitados.add(nodo.getElem());
            if (!nodo.getElem().equals(elem)) {
                ColaPrioridad colaPrioridad = new ColaPrioridad(100);
                NodoAdy auxAdy = nodo.getPrimerAdy();
                while (auxAdy != null) {
                    colaPrioridad.insertar(auxAdy, (Comparable) auxAdy.getEtiqueta());
                    auxAdy = auxAdy.getNodoAdyacente();
                }
                // Almacena todos los adyacentes en una cola de prioridad para ir siempre por el
                // mas chico
                while (!colaPrioridad.esVacio() && !existeCamino) {
                    distancias[0] = distTemp;
                    // Setea la distancia actual que puede estar modificada , por la verdadera
                    // distancia actual guardada el inicio del metodo
                    NodoVertice temp = ((NodoAdy) colaPrioridad.obtenerFrente()).getNodo();
                    distancias[0] += (int) ((NodoAdy) colaPrioridad.obtenerFrente()).getEtiqueta();
                    // Sumo la distancia hacia donde voy a ir
                    colaPrioridad.eliminarFrente();
                    // llama a la funcion con el nodo adyacente mas "cercano"
                    existeCamino = existeCaminoDistanciaMaxAux(elem, temp, visitados, distancias);
                }
            } else {
                existeCamino = true;
            }
        }
        return existeCamino;

    }

    @Override
    public String toString() {
        NodoVertice aux = this.nodoInicial;
        String retorna = "";
        while (aux != null) {
            String strTemp = "[" + aux.getElem() + "]";
            NodoAdy auxAdy = aux.getPrimerAdy();
            while (auxAdy != null) {
                strTemp += "(" + auxAdy.getNodo().getElem() + "," + auxAdy.getEtiqueta() + ")";
                auxAdy = auxAdy.getNodoAdyacente();
            }
            retorna += strTemp + "\n";
            aux = aux.getSigNodoVert();
        }
        return retorna;
    }
}
