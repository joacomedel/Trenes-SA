package TrenesSA;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner14;

import Estructuras.Dinamicas.Diccionario;
import TrenesSA.Estacion;

public class TrenMenu {
    private static Diccionario trenesGlobal;
    private static Scanner scGlobal;
    private static HashMap<String, Object> lineasGlobal;
    private static Diccionario estacionesGlobal;

    public static void menuTren(Scanner sc, Diccionario trenes, HashMap<String, Object> lineas,
            Diccionario estaciones) {
        trenesGlobal = trenes;
        scGlobal = sc;
        lineasGlobal = lineas;
        estacionesGlobal = estaciones;
        boolean continua = true;
        String lectura;
        do {
            System.out.println("Seleccione 1 para dar de baja , alta o modificar tren");
            System.out.println("Seleccione 2 para mostrar tren");
            System.out.println(
                    "Seleccione 3 para verificar si está destinado a alguna línea y mostrar las ciudades que visitaría");
            System.out.println("Seleccione 0 para volver al menu general");
            lectura = sc.nextLine();
            switch (lectura) {
                case "1":
                    System.out.println("Selecciono dar de baja , alta o modificar tren");
                    abmTren();
                    break;
                case "2":
                    System.out.println("Selecciono mostrar tren");
                    mostrarTren();
                    break;
                case "3":
                    System.out.println(
                            "Selecciono verificar si está destinado a alguna línea y mostrar las ciudades que visitaría");
                    verfLineasDeTren();
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

    public static void abmTren() {
        boolean continua = true;
        String lectura;
        do {
            System.out.println("Seleccione 1 si quiere eliminar un tren");
            System.out.println("Seleccione 2 si quiere modificar un tren");
            System.out.println("Seleccione 3 si quiere agregar un tren");
            System.out.println("Seleccione 0 para volver al menu general");
            lectura = scGlobal.nextLine();
            switch (lectura) {
                case "1":
                    System.out.println("Selecciono eliminar un tren");
                    eliminarTren();
                    break;
                case "2":
                    System.out.println("Selecciono modificar un tren");
                    modificarTren();
                    break;
                case "3":
                    System.out.println("Selecciono agregar un tren");
                    agregarTren();
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

    public static void eliminarTren() {
        boolean continua = true;
        String lectura;
        do {
            System.out.println("Seleccione 1 para eliminar un tren");
            System.out.println("Seleccione 0 para volver al menu anterior");
            lectura = scGlobal.nextLine();
            switch (lectura) {
                case "1":
                    System.out.println("Ingrese el codigo del tren a eliminar");
                    lectura = scGlobal.nextLine();
                    Main.registrarLog("Se intenta eliminar el tren:" + lectura);
                    if (trenesGlobal.eliminar(lectura)) {
                        System.out.println("Se logro eliminar el tren:" + lectura);
                        Main.registrarLog("Se logro eliminar el tren:" + lectura);
                    } else {
                        System.out.println("No se logro eliminar el tren:" + lectura);
                        Main.registrarLog("No se logro eliminar el tren:" + lectura);
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

    public static void modificarTren() {
        boolean continua = true;
        String lectura;
        do {
            System.out.println("Seleccione 1 para modificar un tren");
            System.out.println("Seleccione 0 para volver al menu anterior");
            lectura = scGlobal.nextLine();
            switch (lectura) {
                case "1":
                    System.out.println("Ingrese el codigo del tren q quiere modificar");
                    lectura = scGlobal.nextLine();
                    Main.registrarLog("Se intenta buscar el tren:" + lectura);
                    Tren tren = (Tren) trenesGlobal.obtener(lectura);
                    if (tren != null) {
                        String codigoTren = lectura;
                        Main.registrarLog("Se encuentra el tren:" + lectura);
                        boolean continua2 = true;
                        do {
                            System.out.println("Seleccione 1 para modificar el tipo de propulsion");
                            System.out.println("Seleccione 2 para modificar la cantidad de vagones para pasajeros");
                            System.out.println("Seleccione 3 para modificar la cantidad de vagones para carga");
                            System.out.println("Seleccione 4 para modificar la linea del tren");
                            System.out.println("Seleccione 0 para volver al menu anterior");
                            lectura = scGlobal.nextLine();
                            switch (lectura) {
                                case "1":
                                    System.out.println("Selecciono modificar el tipo de propulsion");
                                    System.out.println("Ingrese el tipo de propulsion");
                                    lectura = scGlobal.nextLine();
                                    tren.setTipoDePropulsion(lectura);
                                    System.out.println(
                                            "Se modifico el tipo de propulsion de el tren con codigo:" + codigoTren);
                                    Main.registrarLog(
                                            "Se modifico el tipo de propulsion de el tren con codigo:" + codigoTren);
                                    break;
                                case "2":
                                    System.out.println("Selecciono modificar la cantidad de vagones para pasajeros");
                                    System.out.println("Ingrese la cantidad de vagones para pasajeros");
                                    int vagPasj = Integer.parseInt(scGlobal.nextLine());
                                    tren.setVagonesPasajeros(vagPasj);
                                    System.out.println(
                                            "Se modifico la cantidad de vagones para pasajeros de el tren con codigo:"
                                                    + codigoTren);
                                    Main.registrarLog(
                                            "Se modifico la cantidad de vagones para pasajeros de el tren con codigo:"
                                                    + codigoTren);
                                    break;
                                case "3":
                                    System.out.println("Selecciono modificar la cantidad de vagones para carga");
                                    System.out.println("Ingrese la cantidad de vagones para carga");
                                    int vagCarg = Integer.parseInt(scGlobal.nextLine());
                                    tren.setVagonesCarga(vagCarg);
                                    System.out.println(
                                            "Se modifico la cantidad de vagones para carga de el tren con codigo:"
                                                    + codigoTren);
                                    Main.registrarLog(
                                            "Se modifico la cantidad de vagones para carga de el tren con codigo:"
                                                    + codigoTren);
                                    break;
                                case "4":
                                    System.out.println("Selecciono modificar la linea del tren");
                                    System.out.println("Ingrese la linea");
                                    lectura = scGlobal.nextLine();
                                    tren.setLinea(lectura);
                                    System.out.println(
                                            "Se modifico la linea de el tren con codigo:"
                                                    + codigoTren);
                                    Main.registrarLog(
                                            "Se modifico la linea de el tren con codigo:"
                                                    + codigoTren);
                                    break;
                                case "0":
                                    System.out.println("El tren fue modificado , su estado actual es:" + codigoTren
                                            + ";" + tren.toString());
                                    Main.registrarLog("El tren fue modificado , su estado actual es:" + codigoTren
                                            + ";" + tren.toString());
                                    System.out.println("Selecciono volver al menu anterior");

                                    continua = false;
                                    break;
                                default:
                                    System.out.println(
                                            "Selecciono una opcion no valida porfavor ingrese una funcion valida");
                                    break;
                            }
                        } while (continua2);
                    } else {
                        Main.registrarLog("No se encuentra el tren:" + lectura);
                        System.out.println("No se encuentra el tren:" + lectura);
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

    public static void agregarTren() {
        boolean continua = true;
        String lectura;
        do {
            System.out.println("Seleccione 1 para agregar un tren");
            System.out.println("Seleccione 0 para volver al menu anterior");
            lectura = scGlobal.nextLine();
            switch (lectura) {
                case "1":
                    System.out.println("Ingrese el codigo del tren");
                    String codigo = scGlobal.nextLine();
                    System.out.println("Ingrese el tipo de propulsion");
                    String tipoDePropulsion = scGlobal.nextLine();
                    System.out.println("Ingrese la cant de vagones para pasajeros");
                    String vagonesPasajeros = scGlobal.nextLine();
                    System.out.println("Ingrese la cantidad de vagones para carga");
                    String vagonesCarga = scGlobal.nextLine();
                    System.out.println("Ingrese la linea del tren");
                    String linea = scGlobal.nextLine();
                    Main.registrarLog(linea);
                    Main.registrarLog("Se intenta agregar el tren:" + codigo + ";" + tipoDePropulsion + ";"
                            + vagonesPasajeros + ";" + vagonesCarga + ";" + linea);
                    if (trenesGlobal.insertar(codigo,
                            new Tren(tipoDePropulsion, vagonesPasajeros, vagonesCarga, linea))) {
                        System.out.println("Se agrego el tren:" + codigo + ";" + tipoDePropulsion + ";"
                                + vagonesPasajeros + ";" + vagonesCarga + ";" + linea);
                        Main.registrarLog("Se agrego el tren:" + codigo + ";" + tipoDePropulsion + ";"
                                + vagonesPasajeros + ";" + vagonesCarga + ";" + linea);
                    } else {
                        System.out.println("No se pudo agregar el tren:" + codigo + ";" + tipoDePropulsion + ";"
                                + vagonesPasajeros + ";" + vagonesCarga + ";" + linea);
                        Main.registrarLog("No se pudo agregar el tren:" + codigo + ";" + tipoDePropulsion + ";"
                                + vagonesPasajeros + ";" + vagonesCarga + ";" + linea);
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

    public static void mostrarTren() {
        boolean continua = true;
        String lectura;
        do {
            System.out.println("Seleccione 1 para mostrar un tren");
            System.out.println("Seleccione 0 para volver al menu general");
            lectura = scGlobal.nextLine();
            switch (lectura) {
                case "1":
                    System.out.println("Ingrese el codigo del tren a mostrar");
                    lectura = scGlobal.nextLine();
                    Main.registrarLog("Se intenta obtener el tren con codigo:" + lectura);
                    Tren tren = (Tren) trenesGlobal.obtener(lectura);
                    if (tren != null) {
                        System.out.println("El tren es:" + lectura + ";" + tren.toString());
                    } else {
                        System.out.println("No es encontro tren con el codigo:" + lectura);
                        Main.registrarLog("No es encontro tren con el codigo:" + lectura);
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

    public static void verfLineasDeTren() {
        boolean continua = true;
        String lectura;
        do {
            System.out.println("Ingrese 1 para verificar la linea y las ciudades q visitaria");
            System.out.println("Seleccione 0 para volver al menu general");
            lectura = scGlobal.nextLine();
            switch (lectura) {
                case "1":
                    System.out.println(
                            "Ingrese el codigo de tren para verificar su linea y las ciudades por las q pasaria");
                    lectura = scGlobal.nextLine();
                    Main.registrarLog("Se intenta obtener el tren con codigo:" + lectura);
                    Tren tren = (Tren) trenesGlobal.obtener(lectura);
                    if (tren != null) {
                        if (!tren.getLinea().equals("no-asignado")) {
                            Main.registrarLog("Se intenta obtener la linea con nombre:" + tren.getLinea());
                            Linea linea = (Linea) lineasGlobal.get(tren.getLinea());
                            if (linea != null) {
                                List<String> estacionesNombre = linea.getEstaciones();
                                if (estacionesNombre != null) {
                                    String ciudadesAcum = "";
                                    for (String nombreEstacion : estacionesNombre) {
                                        Main.registrarLog("Se intenta buscar la estacion con nombre " + nombreEstacion);
                                        Estacion estacion = (Estacion) estacionesGlobal.obtener(nombreEstacion);
                                        if (estacion != null) {
                                            ciudadesAcum += estacion.getCiudad() + ";";
                                        } else {
                                            Main.registrarLog(
                                                    "No se encuentra la estacion con nombre " + nombreEstacion);
                                        }
                                    }
                                    System.out.println(
                                            "Las ciudades por las q pasa el tren:" + lectura + " con linea "
                                                    + tren.getLinea() + " son:" + ciudadesAcum);
                                } else {
                                    System.out.println("La linea " + tren.getLinea() + " no tiene estaciones ");
                                    Main.registrarLog("La linea " + tren.getLinea() + " no tiene estaciones ");
                                }
                            } else {
                                System.out.println("No es encontro linea con ese nombre:" + tren.getLinea());
                                Main.registrarLog("No es encontro linea con ese nombre:" + tren.getLinea());
                            }
                        } else {
                            System.out.println("El tren no tiene linea");
                            Main.registrarLog("El tren encontrado no tiene linea ");
                        }
                    } else {
                        System.out.println("No es encontro tren con el codigo:" + lectura);
                        Main.registrarLog("No es encontro tren con el codigo:" + lectura);
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
