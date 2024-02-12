package TrenesSA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class LineaMenu {
    private static HashMap<String, Object> lineasGlobal;
    private static Scanner scGlobal;

    public static void menuLinea(HashMap<String, Object> lineas, Scanner sc) {
        lineasGlobal = lineas;
        scGlobal = sc;
        String lectura;
        boolean continua = true;
        do {
            System.out.println("Seleccione 1 para eliminar una linea");
            System.out.println("Seleccione 2 para modificar una linea");
            System.out.println("Seleccione 3 para agregar una linea");
            System.out.println("Seleccione 0 para volver al menu general");
            lectura = scGlobal.nextLine();
            switch (lectura) {
                case "1":
                    System.out.println("Selecciono eliminar una linea");
                    bajaLinea();
                    break;
                case "2":
                    System.out.println("Selecciono modificar una linea");
                    modificarLinea();
                    break;
                case "3":
                    System.out.println("Selecciono agregar una linea");
                    altaLinea();
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

    public static void bajaLinea() {
        boolean continua = true;
        String lectura;
        do {
            System.out.println("Seleccione 1 para eliminar linea");
            System.out.println("Seleccione 0 para salir");
            lectura = scGlobal.nextLine();
            switch (lectura) {
                case "1":
                    System.out.println("Ingrese el nombre de la linea a eliminar");
                    lectura = scGlobal.nextLine();
                    Main.registrarLog("Se intenta eliminar la linea:" + lectura);
                    if (null != lineasGlobal.remove(lectura)) {
                        System.out.println("Se elimino la linea:" + lectura);
                        Main.registrarLog("Se elimino la linea:" + lectura);
                    } else {
                        Main.registrarLog("No se encontro la linea:" + lectura);
                        System.out.println("No se encontro la linea:" + lectura);
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

    public static void modificarLinea() {
        boolean continua = true;
        String lectura;
        do {
            System.out.println("Seleccione 1 para modificar una linea");
            System.out.println("Seleccione 0 para salir");
            lectura = scGlobal.nextLine();
            switch (lectura) {
                case "1":
                    System.out.println("Ingrese el nombre de la linea q quiere modificar");
                    lectura = scGlobal.nextLine();
                    Main.registrarLog("Intenta obtener la linea:" + lectura);
                    Linea linea = (Linea) lineasGlobal.get(lectura);
                    if (linea != null) {
                        Main.registrarLog("Se encontro la linea:" + lectura);
                        System.out.println("Selecciono la linea:" + linea.toString());
                        String nombreLinea = lectura;
                        boolean continua2 = true;
                        do {
                            System.out.println("Seleccione 1 para eliminar una estacion de la linea");
                            System.out.println("Seleccione 2 para agregar una estacion a la linea");
                            System.out.println("Seleccione 0 para salir");
                            lectura = scGlobal.nextLine();
                            switch (lectura) {
                                case "1":
                                    System.out.println("Ingrese el nombre de la estacion para eliminar");
                                    lectura = scGlobal.nextLine();
                                    linea.eliminarEstacion(lectura);
                                    break;
                                case "2":
                                    System.out.println("Ingrese el nombre de la estacion para agregar");
                                    lectura = scGlobal.nextLine();
                                    linea.agregarEstacion(lectura);
                                    break;
                                case "0":
                                    System.out.println("Selecciono volver al menu anterior");
                                    Main.registrarLog("Se modifico la linea:" + linea.toString());
                                    System.out.println("Se modifico la linea:" + linea.toString());
                                    continua2 = false;
                                    break;
                                default:
                                    System.out.println(
                                            "Selecciono una opcion no valida porfavor ingrese una funcion valida");
                                    break;
                            }
                        } while (continua2);
                    } else {
                        Main.registrarLog("No se encontro la linea:" + lectura);
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

    public static void altaLinea() {
        boolean continua = true;
        String lectura;
        do {
            System.out.println("Seleccione 1 para eliminar la linea");
            System.out.println("Seleccione 0 para salir");
            lectura = scGlobal.nextLine();
            switch (lectura) {
                case "1":
                    System.out.println("Ingrese el nombre de la linea");
                    String nombreLinea = scGlobal.nextLine();
                    if (!lineasGlobal.containsKey(nombreLinea)) {
                        List<String> estacionesTemp = new ArrayList<String>();
                        String estacionesTempStr = "";
                        boolean continua2 = true;
                        do {
                            System.out.println("Ingrese 1 para ingresar una estacion");
                            System.out.println("Ingrese 0 para dejar de ingresar estacion");
                            lectura = scGlobal.nextLine();
                            switch (lectura) {
                                case "1":
                                    System.out.println("Ingrese el nombre de la estacion");
                                    lectura = scGlobal.nextLine();
                                    estacionesTemp.add(lectura);
                                    estacionesTempStr += lectura + ";";
                                    break;
                                case "0":
                                    System.out.println("Selecciono volver al menu anterior");
                                    continua = false;
                                    break;
                                default:
                                    System.out
                                            .println(
                                                    "Selecciono una opcion no valida porfavor ingrese una funcion valida");
                                    break;
                            }
                        } while (continua2);
                        // Se asume q las lineas se cargan en funcionamiento
                        lineasGlobal.put(nombreLinea, new Linea(nombreLinea, estacionesTemp, true));
                        Main.registrarLog("Se ingreso la linea:" + nombreLinea + ";" + estacionesTempStr);
                        System.out.println("Se ingreso la linea:" + nombreLinea + ";" + estacionesTempStr);
                    } else {
                        System.out.println("Intento ingresar una linea q ya estaba registrada");
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
