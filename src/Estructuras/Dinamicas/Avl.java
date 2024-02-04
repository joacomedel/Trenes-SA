package Estructuras.Dinamicas;

public class Avl {
    NodoAvl raiz;

    public boolean esVacio() {
        return raiz == null;
    }

    public boolean insertar(Comparable elem) {
        int[] arr = { 0 };
        if (esVacio()) {
            raiz = new NodoAvl(elem);
        } else {
            insertarRec(raiz, null, elem, arr);
        }
        boolean inserto = true;
        if (arr[0] == 0)
            inserto = false;
        return inserto;
    }

    private void insertarRec(NodoAvl nodo, NodoAvl padre, Comparable elem, int[] bool) {
        if (nodo.getElem().compareTo(elem) > 0) {
            if (nodo.getHijIzq() != null) {
                insertarRec(nodo.getHijIzq(), nodo, elem, bool);

            } else {
                nodo.setHijIzq(new NodoAvl(elem));
                bool[0] = 1;
            }
        } else {
            if (nodo.getElem().compareTo(elem) < 0) {
                if (nodo.getHijDer() != null) {
                    insertarRec(nodo.getHijDer(), nodo, elem, bool);
                } else {
                    nodo.setHijDer(new NodoAvl(elem));
                    bool[0] = 1;
                }
            } else {
                // igual
                if (nodo.getHijDer() != null) {
                    insertarRec(nodo.getHijDer(), nodo, elem, bool);
                } else {
                    nodo.setHijDer(new NodoAvl(elem));
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

    private void eliminar(NodoAvl nodo, NodoAvl padre, Comparable elem) {
        int comparacion = elem.compareTo(nodo.getElem());
        if (comparacion == 0) {
            // Es el nodo q buscamos
            if (nodo.getHijIzq() != null && nodo.getHijDer() != null) {
                // Tiene los dos hijos
                boolean[] bool = { false };
                NodoAvl nodoGrande = eliminarDosHijos(nodo.getHijIzq(), bool);
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

    private NodoAvl eliminarDosHijos(NodoAvl nodo, boolean[] asignarIzq) {
        NodoAvl nodoRetornar;
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

    private boolean balancear(NodoAvl nodo, NodoAvl padre) {
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

    private void rotSimpIzq(NodoAvl nodo, NodoAvl padre) {
        NodoAvl hijDer = nodo.getHijDer();
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

    private void rotSimpDer(NodoAvl nodo, NodoAvl padre) {
        NodoAvl hijIzq = nodo.getHijIzq();
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

    private int getBalance(NodoAvl nodo) {
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

    private void toStringRec(NodoAvl nodo, String[] str) {
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

    private void inOrdenRec(NodoAvl nodo, String[] str) {
        if (nodo != null) {
            inOrdenRec(nodo.getHijIzq(), str);
            str[0] += nodo.getElem() + ",";
            inOrdenRec(nodo.getHijDer(), str);
        }
    }

}
