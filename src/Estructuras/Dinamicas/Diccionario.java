package Estructuras.Dinamicas;

public class Diccionario {
    NodoDiccionario raiz;

    public boolean esVacio() {
        return raiz == null;
    }

    public boolean insertar(Comparable clave, Object dato) {
        int[] arr = { 0 };
        if (esVacio()) {
            raiz = new NodoDiccionario(clave, dato);
        } else {
            insertarRec(raiz, null, clave, dato, arr);
        }
        boolean inserto = true;
        if (arr[0] == 0)
            inserto = false;
        return inserto;
    }

    private void insertarRec(NodoDiccionario nodo, NodoDiccionario padre, Comparable clave, Object dato, int[] bool) {
        if (nodo.getClave().compareTo(clave) > 0) {
            if (nodo.getHijIzq() != null) {
                insertarRec(nodo.getHijIzq(), nodo, clave, dato, bool);
            } else {
                nodo.setHijIzq(new NodoDiccionario(clave, dato));
                bool[0] = 1;
            }
        } else {
            if (nodo.getClave().compareTo(clave) < 0) {
                if (nodo.getHijDer() != null) {
                    insertarRec(nodo.getHijDer(), nodo, clave, dato, bool);
                } else {
                    nodo.setHijDer(new NodoDiccionario(clave, dato));
                    bool[0] = 1;
                }
            } else {
                // igual
                if (nodo.getHijDer() != null) {
                    insertarRec(nodo.getHijDer(), nodo, clave, dato, bool);
                } else {
                    nodo.setHijDer(new NodoDiccionario(clave, dato));
                    bool[0] = 1;
                }
            }
        }
        nodo.calcularAltura();
        if (bool[0] != 2) {
            if (balancear(nodo, padre))
                bool[0] = 2;
        }
    }

    public boolean eliminar(Comparable elem) {
        if (raiz != null) {
            eliminar(raiz, null, elem);
        }
        return true;
    }

    private void eliminar(NodoDiccionario nodo, NodoDiccionario padre, Comparable elem) {
        int comparacion = elem.compareTo(nodo.getClave());
        if (comparacion == 0) {
            // Es el nodo q buscamos
            if (nodo.getHijIzq() != null && nodo.getHijDer() != null) {
                // Tiene los dos hijos
                boolean[] bool = { false };
                NodoDiccionario nodoGrande = eliminarDosHijos(nodo.getHijIzq(), bool);
                if (bool[0]) {
                    // Si esta en true , entonces el hijo izq no tiene hij derecho
                    nodo.setHijIzq(nodoGrande.getHijIzq());
                }
                if (padre == null) {
                    // es la raiz
                    raiz = nodoGrande;
                    raiz.setHijDer(nodo.getHijDer());
                    raiz.setHijIzq(nodo.getHijIzq());
                } else {
                    if (padre.getHijIzq() == nodo) {
                        padre.setHijIzq(nodoGrande);
                    } else {
                        padre.setHijDer(nodoGrande);
                    }
                    nodoGrande.setHijDer(nodo.getHijDer());
                    nodoGrande.setHijIzq(nodo.getHijIzq());
                }
                nodoGrande.calcularAltura();
            } else {
                if (nodo.getHijIzq() == null && nodo.getHijDer() != null) {
                    // Tiene hijo Der
                    if (padre == null) {
                        // es la raiz
                        raiz = nodo.getHijDer();
                    } else {
                        if (padre.getHijIzq() == nodo) {
                            padre.setHijIzq(nodo.getHijDer());
                        } else {
                            padre.setHijDer(nodo.getHijDer());
                        }
                    }

                } else {
                    if (nodo.getHijIzq() != null && nodo.getHijDer() == null) {
                        // Tiene solo hijo Izq
                        if (padre == null) {
                            // es la raiz
                            raiz = nodo.getHijIzq();
                        } else {
                            if (padre.getHijIzq() == nodo) {
                                padre.setHijIzq(nodo.getHijIzq());
                            } else {
                                padre.setHijDer(nodo.getHijIzq());
                            }
                        }

                    } else {
                        // no tiene hijos
                        if (padre == null) {
                            // es la raiz y no tiene hijos
                            raiz = null;
                        } else {
                            if (padre.getHijIzq() == nodo) {
                                padre.setHijIzq(null);
                            } else {
                                padre.setHijDer(null);
                            }
                        }
                    }
                }
            }
        } else {
            // No el nodo q buscamos
            if (comparacion < 0) {
                if (nodo.getHijIzq() != null) {
                    eliminar(nodo.getHijIzq(), nodo, elem);
                }
            } else {
                if (nodo.getHijDer() != null) {
                    eliminar(nodo.getHijDer(), nodo, elem);
                }
            }
        }
        nodo.calcularAltura();
        balancear(nodo, padre);
    }

    private NodoDiccionario eliminarDosHijos(NodoDiccionario nodo, boolean[] asignarIzq) {
        NodoDiccionario nodoRetornar;
        if (nodo.getHijDer() != null) {
            nodoRetornar = eliminarDosHijos(nodo.getHijDer(), asignarIzq);
            if (asignarIzq[0]) {
                nodo.setHijDer(nodoRetornar.getHijIzq());
                asignarIzq[0] = false;
            }
        } else {
            nodoRetornar = nodo;
            asignarIzq[0] = true;
        }
        nodo.calcularAltura();
        return nodoRetornar;
    }

    private boolean balancear(NodoDiccionario nodo, NodoDiccionario padre) {
        boolean balanceo = false;
        int balance = getBalance(nodo);
        if (balance >= 2) {
            System.out.println("NODO DESBALANCEADO hacia izq");
            if (getBalance(nodo.getHijIzq()) >= 0) {
                rotSimpDer(nodo, padre);
            } else {
                rotSimpIzq(nodo.getHijIzq(), nodo);
                rotSimpDer(nodo, padre);
            }
            balanceo = true;
        } else if (balance <= -2) {
            System.out.println("NODO DESBALANCEADO hacia der");
            if (getBalance(nodo.getHijDer()) <= 0) {
                rotSimpIzq(nodo, padre);
            } else {
                rotSimpDer(nodo.getHijDer(), nodo);
                rotSimpIzq(nodo, padre);
            }
            balanceo = true;
        }
        return balanceo;
    }

    private void rotSimpIzq(NodoDiccionario nodo, NodoDiccionario padre) {
        NodoDiccionario hijDer = nodo.getHijDer();
        if (padre == null) {
            raiz = hijDer;

        } else {
            if (padre.getHijIzq() == nodo) {
                padre.setHijIzq(hijDer);
            } else {
                padre.setHijDer(hijDer);
            }
        }
        nodo.setHijDer(hijDer.getHijIzq());
        hijDer.setHijIzq(nodo);
        hijDer.getHijIzq().calcularAltura();
        hijDer.calcularAltura();
    }

    private void rotSimpDer(NodoDiccionario nodo, NodoDiccionario padre) {
        NodoDiccionario hijIzq = nodo.getHijIzq();
        if (padre == null) {
            raiz = hijIzq;
        } else {
            if (padre.getHijIzq() == nodo) {
                padre.setHijIzq(hijIzq);
            } else {
                padre.setHijDer(hijIzq);
            }
        }
        nodo.setHijIzq(hijIzq.getHijDer());
        hijIzq.setHijDer(nodo);
        hijIzq.getHijDer().calcularAltura();
        hijIzq.calcularAltura();
    }

    private int getBalance(NodoDiccionario nodo) {
        int altIzq = -1;
        int altDer = -1;
        if (nodo.getHijIzq() != null) {
            altIzq = nodo.getHijIzq().getAltura();
        }
        if (nodo.getHijDer() != null) {
            altDer = nodo.getHijDer().getAltura();
        }
        return altIzq - altDer;
    }

    private void toStringRec(NodoDiccionario nodo, String[] str) {
        if (nodo != null) {
            str[0] = str[0] + nodo.toString() + "\n";
            toStringRec(nodo.getHijIzq(), str);
            toStringRec(nodo.getHijDer(), str);
        }
    }

    @Override
    public String toString() {
        String[] str = new String[1];
        str[0] = "";
        toStringRec(raiz, str);
        return str[0];
    }

    public String inOrden() {
        String str[] = new String[1];
        str[0] = "";
        inOrdenRec(raiz, str);
        return str[0];
    }

    private void inOrdenRec(NodoDiccionario nodo, String[] str) {
        if (nodo != null) {
            inOrdenRec(nodo.getHijIzq(), str);
            str[0] += nodo.getClave() + ",";
            inOrdenRec(nodo.getHijDer(), str);
        }
    }

    public Object obtener(Comparable clave) {
        return obtenerRecursivo(clave, raiz);
    }

    private Object obtenerRecursivo(Comparable clave, NodoDiccionario nodo) {
        Object dato = null;
        if (nodo != null) {
            int comparacion = clave.compareTo(nodo.getClave());
            if (comparacion == 0)
                dato = nodo.getDato();
            else {
                if (comparacion < 0) {
                    dato = obtenerRecursivo(clave, nodo.getHijIzq());
                } else {
                    dato = obtenerRecursivo(clave, nodo.getHijDer());
                }
            }

        }
        return dato;
    }

}