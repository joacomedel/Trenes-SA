package Estructuras.TrenesSA;

import java.rmi.ConnectIOException;
import java.util.Scanner;

import Estructuras.Dinamicas.Diccionario;

public class EstacionMenu {
    private static Diccionario estacionesGlobal;
    private static Scanner scGlobal;

    public static void menuEstacion(Scanner sc, Diccionario estaciones) {
        estacionesGlobal = estaciones;
        scGlobal = sc;
        boolean continua = true;
        String lectura;
        do {
            System.out.println("Seleccione 1 para dar de baja , alta o modificar estacion");
            System.out.println("Seleccione 2 para mostrar estacion");
            System.out.println("Seleccione 3 para buscar estacion con subcadena");
            System.out.println("Seleccione 0 para volver al menu general");
            lectura = sc.nextLine();
            switch (lectura) {
                case "1":
                    System.out.println("Selecciono dar de baja , alta o modificar estacion");
                    abmEstacion();
                    break;
                case "2":
                    System.out.println("Selecciono mostrar estacion");
                    mostrarEstacion();
                    break;
                case "3":
                    System.out.println("Selecciono buscar estacion con subcadena");
                    buscarEstacionSubcadena();
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

    public static void abmEstacion() {
        boolean continua = true;
        String lectura;
        do {
            System.out.println("Seleccione 1 si quiere eliminar una estacion");
            System.out.println("Seleccione 2 si quiere modificar una estacion");
            System.out.println("Seleccione 3 si quiere agregar una estacion");
            System.out.println("Seleccione 0 para salir");
            lectura = scGlobal.nextLine();
            switch (lectura) {
                case "1":
                    System.out.println("Selecciono eliminar estacion");
                    bajaEstacion();
                    break;
                case "2":
                    System.out.println("Selecciono modificar estacion");
                    modificarEstacion();
                    break;
                case "3":
                    System.out.println("Selecciono agregar estacion");
                    altaEstacion();
                    break;
                case "0":
                    System.out.println("Selecciono volver al menu anterior");
                    continua = false;
                    break;
                default:
                    break;
            }
        } while (continua);
    }

    public static void bajaEstacion() {
        String lectura;
        boolean continua = true;
        do {
            System.out.println("Seleccione 1 para eliminar");
            System.out.println("Seleccione 0 para salir");
            lectura = scGlobal.nextLine();
            switch (lectura) {
                case "1":
                System.out.println("Ingrese el nombre de estacion a eliminar");
                lectura = scGlobal.nextLine();
                if (estacionesGlobal.eliminar(lectura);) {
                    
                }
                break;
                case "0":
                    System.out.println("Selecciono salir del programa");
                    continua = false;
                    break;
                default:
                    System.out.println("Selecciono una opcion no valida porfavor ingrese una funcion valida");
                    break;
            }
        } while (continua);
    }

    public static void modificarEstacion() {

    }

    public static void altaEstacion() {

    }

    public static void mostrarEstacion() {

    }

    public static void buscarEstacionSubcadena() {

    }
}
