package TrenesSA;

import java.util.List;
import java.util.Scanner;

import Estructuras.Dinamicas.Grafo;

public class RielMenu {
    private static Grafo rielesGlobal;
    private static Scanner scGlobal;

    public static void menuRiel(Grafo rieles, Scanner sc) {
        rielesGlobal = rieles;
        scGlobal = sc;
        boolean continua = true;
        String lectura;
        do {
            System.out.println("Seleccione 1 para dar de baja , alta o modificar riel");
            System.out.println("Seleccione 2 para mostrar camino con menos estaciones entre dos estaciones");
            System.out.println("Seleccione 3 para buscar el camino con menos km entre dos estaciones");
            System.out.println(
                    "Seleccione 4 para buscar todos los caminos entre dos estaciones sin pasar por una estacion");
            System.out.println(
                    "Seleccione 5 para buscar si se puede llegar de una estacion a otra sin pasar ciertos km maximos");
            System.out.println("Seleccione 0 para volver al menu general");
            lectura = sc.nextLine();
            switch (lectura) {
                case "1":
                    System.out.println("Selecciono dar de baja , alta o modificar riel");
                    abmRiel(sc);
                    break;
                case "2":
                    System.out.println("Selecciono mostrar camino con menos estaciones entre dos estaciones");
                    caminoMenosEstaciones(sc);
                    break;
                case "3":
                    System.out.println("Selecciono buscar el camino con menos km entre dos estaciones");
                    caminoMenosKm(sc);
                    break;
                case "4":
                    System.out.println(
                            "Selecciono buscar todos los caminos entre dos estaciones sin pasar por una estacion");
                    caminosSinEstacion(sc);
                    break;
                case "5":
                    System.out.println(
                            "Selecciono buscar si se puede llegar de una estacion a otra sin pasar ciertos km maximos");
                    posibleCaminoKmMax(sc);
                    break;
                case "0":
                    System.out.println("Selecciono volver al menu general");
                    continua = false;
                    break;

                default:
                    System.out.println("Selecciono una opcion no valida porfavor ingrese una funcion valida");
                    break;
            }

        } while (continua);
    }

    public static void abmRiel(Scanner sc) {
        boolean continua = true;
        String lectura;
        do {
            System.out.println("Seleccione 1 para dar de baja un riel");
            System.out.println("Seleccione 2 para modificar un riel");
            System.out.println("Seleccione 3 para dar de agregar un riel");
            System.out.println("Seleccione 0 para salir");
            lectura = scGlobal.nextLine();
            switch (lectura) {
                case "1":
                    System.out.println("Selecciono dar de baja un riel");
                    bajaRiel();
                    break;
                case "2":
                    System.out.println("Selecciono dar modificar un riel");
                    modificarRiel();
                    break;
                case "3":
                    System.out.println("Selecciono dar de alta un riel");
                    altaRiel();
                    break;
                case "0":
                    System.out.println("Selecciono volver al menu anterior");
                    continua = false;
                    break;
                default:
                    System.out.println("Selecciono una opcion no valida porfavor ingrese una funcion valida");
                    break;
            }
        } while (continua);
    }

    public static void bajaRiel() {
        boolean continua = true;
        String lectura;
        do {
            System.out.println("Seleccione 1 para dar de baja un riel");
            System.out.println("Seleccione 0 para salir");
            lectura = scGlobal.nextLine();
            switch (lectura) {
                case "1":
                    System.out.println("Ingrese la primera estacion ");
                    String estacion1 = scGlobal.nextLine();
                    System.out.println("Ingrese la segunda estacion ");
                    String estacion2 = scGlobal.nextLine();
                    Main.registrarLog("Intento eliminar el riel entre" + estacion1 + " Y la estacion:" + estacion2);
                    if (rielesGlobal.eliminarArco(estacion1, estacion2)) {
                        System.out.println(
                                "Se elimino el riel entre la estacion:" + estacion1 + "Y la estacion:" + estacion2);
                        Main.registrarLog(
                                "Se elimino el riel entre la estacion:" + estacion1 + "Y la estacion:" + estacion2);

                    } else {
                        System.out.println("No se pudo eliminar el riel");
                        Main.registrarLog("No se pudo eliminar el riel");
                    }
                    break;
                case "0":
                    System.out.println("Selecciono volver al menu anterior");
                    continua = false;
                    break;
                default:
                    System.out.println("Selecciono una opcion no valida porfavor ingrese una funcion valida");
                    break;
            }
        } while (continua);
    }

    public static void modificarRiel() {
        boolean continua = true;
        String lectura;
        do {
            System.out.println("Seleccione 1 para modificar un riel");
            System.out.println("Seleccione 0 para salir");
            lectura = scGlobal.nextLine();
            switch (lectura) {
                case "1":
                    System.out.println("Ingrese el nombre de la primera estacion");
                    String estacion1 = scGlobal.nextLine();
                    System.out.println("Ingrese el nombre de la segunda estacion");
                    String estacion2 = scGlobal.nextLine();
                    System.out.println("Ingrese la distancia q quiere modificar");
                    int distancia = Integer.parseInt(scGlobal.nextLine());
                    Main.registrarLog("Intento modificar el riel entre" + estacion1 + "Y la estacion:" + estacion2);
                    if (rielesGlobal.modificarArco(estacion1, estacion2, distancia)) {
                        System.out.println("Se modifico el riel entre" + estacion1 + "Y la estacion:" + estacion2);
                        Main.registrarLog("Se modifico el riel entre" + estacion1 + "Y la estacion:" + estacion2);
                    } else {
                        System.out.println("No se modifico el riel entre" + estacion1 + "Y la estacion:" + estacion2);
                        Main.registrarLog("No se modifico el riel entre" + estacion1 + "Y la estacion:" + estacion2);
                    }
                    break;
                case "0":
                    System.out.println("Selecciono volver al menu anterior");
                    continua = false;
                    break;
                default:
                    System.out.println("Selecciono una opcion no valida porfavor ingrese una funcion valida");
                    break;
            }
        } while (continua);
    }

    public static void altaRiel() {
        boolean continua = true;
        String lectura;
        do {
            System.out.println("Seleccione 1 si quiere dar de alta un riel");
            System.out.println("Seleccione 0 para salir");
            lectura = scGlobal.nextLine();
            switch (lectura) {
                case "1":
                    System.out.println("Ingrese el nombre de la primera estacion");
                    String estacion1 = scGlobal.nextLine();
                    System.out.println("Ingrese el nombre de la segunda estacion");
                    String estacion2 = scGlobal.nextLine();
                    System.out.println("Ingrese la distancia q quiere modificar");
                    int distancia = Integer.parseInt(scGlobal.nextLine());
                    Main.registrarLog("Se intenta cargar el riel entre:" + estacion1 + "Y" + estacion2);
                    if (rielesGlobal.insertarVertice(estacion1)) {
                        Main.registrarLog("Vertice estacion cargada con exito" + estacion1);
                    }
                    if (rielesGlobal.insertarVertice(estacion2)) {
                        Main.registrarLog("Vertice estacion cargada con exito" + estacion2);
                    }
                    if (rielesGlobal.insertarArco(estacion1, estacion2, distancia)) {
                        Main.registrarLog("Arco cargado con exito:" + estacion1 + ";" + estacion2 + ";" + distancia);
                        System.out.println("Riel cargado con exito:" + estacion1 + ";" + estacion2 + ";" + distancia);
                    } else {
                        Main.registrarLog("No se pudo cargar el arco:" + estacion1 + ";" + estacion2 + ";" + distancia);
                    }
                    break;
                case "0":
                    System.out.println("Selecciono volver al menu anterior");
                    continua = false;
                    break;
                default:
                    System.out.println("Selecciono una opcion no valida porfavor ingrese una funcion valida");
                    break;
            }
        } while (continua);
    }

    public static void caminoMenosEstaciones(Scanner sc) {
        boolean continua = true;
        String lectura;
        do {
            System.out.println("Seleccione 1 para buscar el camino con menos estaciones");
            System.out.println("Seleccione 0 para salir");
            lectura = scGlobal.nextLine();
            switch (lectura) {
                case "1":
                    System.out.println("Ingrese el nombre de la primera estacion");
                    String estacion1 = scGlobal.nextLine();
                    System.out.println("Ingrese el nombre de la segunda estacion");
                    String estacion2 = scGlobal.nextLine();
                    Main.registrarLog(
                            "Se intenta buscar el camino con menos estaciones entre:" + estacion1 + "Y" + estacion2);
                    List<Object> camino = rielesGlobal.caminoMasCortoVertices(estacion1, estacion2);
                    if (camino.size() != 0) {
                        System.out.println("El camino es :" + camino.toString());
                        Main.registrarLog("El camino es :" + camino.toString());
                    } else {
                        System.out
                                .println("No se encontro camino entre las estaciones" + estacion1 + " Y " + estacion2);
                        Main.registrarLog("No se encontro camino entre las estaciones" + estacion1 + " Y " + estacion2);
                    }
                    break;
                case "0":
                    System.out.println("Selecciono volver al menu anterior");
                    continua = false;
                    break;
                default:
                    System.out.println("Selecciono una opcion no valida porfavor ingrese una funcion valida");
                    break;
            }
        } while (continua);
    }

    public static void caminoMenosKm(Scanner sc) {
        boolean continua = true;
        String lectura;
        do {
            System.out.println("Seleccione 1 para buscar el camino con menos km");
            System.out.println("Seleccione 0 para salir");
            lectura = scGlobal.nextLine();
            switch (lectura) {
                case "1":
                    System.out.println("Ingrese el nombre de la primera estacion");
                    String estacion1 = scGlobal.nextLine();
                    System.out.println("Ingrese el nombre de la segunda estacion");
                    String estacion2 = scGlobal.nextLine();
                    Main.registrarLog(
                            "Se intenta buscar el camino con menos km entre:" + estacion1 + "Y" + estacion2);
                    List<Object> camino = rielesGlobal.caminoMasCortoDistancia(estacion1, estacion2);
                    if (camino.size() != 0) {
                        System.out.println("El camino es :" + camino.toString());
                        Main.registrarLog("El camino es :" + camino.toString());
                    } else {
                        System.out
                                .println("No se encontro camino entre las estaciones" + estacion1 + " Y " + estacion2);
                        Main.registrarLog("No se encontro camino entre las estaciones" + estacion1 + " Y " + estacion2);
                    }
                    break;
                case "0":
                    System.out.println("Selecciono volver al menu anterior");
                    continua = false;
                    break;
                default:
                    System.out.println("Selecciono una opcion no valida porfavor ingrese una funcion valida");
                    break;
            }
        } while (continua);
    }

    public static void caminosSinEstacion(Scanner sc) {
        boolean continua = true;
        String lectura;
        do {
            System.out.println("Seleccione 1 para buscar el camino con menos estaciones");
            System.out.println("Seleccione 0 para salir");
            lectura = scGlobal.nextLine();
            switch (lectura) {
                case "1":
                    System.out.println("Ingrese el nombre de la primera estacion");
                    String estacion1 = scGlobal.nextLine();
                    System.out.println("Ingrese el nombre de la segunda estacion");
                    String estacion2 = scGlobal.nextLine();
                    System.out.println("Ingrese el nombre de la estacion por la q no quiere pasar");
                    String estacion3 = scGlobal.nextLine();
                    Main.registrarLog(
                            "Se intenta buscar el camino con menos estaciones entre:" + estacion1 + "Y" + estacion2);
                    List<Object> camino = rielesGlobal.caminosPosiblesSinPasar(estacion1, estacion2, estacion3);
                    if (camino.size() != 0) {
                        for (Object object : camino) {
                            String str = object.toString();
                            System.out.println("Se encontro este camino:" + str);
                            Main.registrarLog("Se encontro este camino:" + str);
                        }
                    } else {
                        System.out
                                .println("No se encontro camino entre las estaciones" + estacion1 + " Y " + estacion2);
                        Main.registrarLog("No se encontro camino entre las estaciones" + estacion1 + " Y " + estacion2);
                    }
                    break;
                case "0":
                    System.out.println("Selecciono volver al menu anterior");
                    continua = false;
                    break;
                default:
                    System.out.println("Selecciono una opcion no valida porfavor ingrese una funcion valida");
                    break;
            }
        } while (continua);
    }

    public static void posibleCaminoKmMax(Scanner sc) {
        boolean continua = true;
        String lectura;
        do {
            System.out.println("Seleccione 0 para salir");
            lectura = scGlobal.nextLine();
            switch (lectura) {
                case "1":
                    System.out.println("Ingrese el nombre de la primera estacion");
                    String estacion1 = scGlobal.nextLine();
                    System.out.println("Ingrese el nombre de la segunda estacion");
                    String estacion2 = scGlobal.nextLine();
                    System.out.println("Ingrese los km maximos");
                    int km = Integer.parseInt(sc.nextLine());
                    if (rielesGlobal.existeCaminoDistanciaMax(estacion1, estacion2, km)) {
                        System.out.println("Existe un camino entre:" + estacion1 + " Y " + estacion2 + " con menos de "
                                + km + " km");
                        Main.registrarLog("Existe un camino entre:" + estacion1 + " Y " + estacion2 + " con menos de "
                                + km + " km");
                    } else {
                        System.out
                                .println("no existe un camino entre:" + estacion1 + " Y " + estacion2 + " con menos de "
                                        + km + " km");
                        Main.registrarLog(
                                "No existe un camino entre:" + estacion1 + " Y " + estacion2 + " con menos de "
                                        + km + " km");
                    }
                    break;
                case "0":
                    System.out.println("Selecciono volver al menu anterior");
                    continua = false;
                    break;
                default:
                    System.out.println("Selecciono una opcion no valida porfavor ingrese una funcion valida");
                    break;
            }
        } while (continua);
    }
}
