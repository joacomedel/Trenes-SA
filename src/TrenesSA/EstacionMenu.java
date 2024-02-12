package TrenesSA;

import java.util.List;
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
            System.out.println("Seleccione 1 para ingresar el nombre de la estacion a eliminar");
            System.out.println("Seleccione 0 para salir");
            lectura = scGlobal.nextLine();
            switch (lectura) {
                case "1":
                    System.out.println("Ingrese el nombre de estacion a eliminar");
                    lectura = scGlobal.nextLine();
                    Main.registrarLog("Se intenta eliminar la estacion:" + lectura);
                    if (estacionesGlobal.eliminar(lectura)) {
                        System.out.println("Fue eliminada con exito");
                        Main.registrarLog("Fue eliminada la estacion:" + lectura);
                    } else {
                        System.out.println("No se encontro para eliminar");
                        // Escribir en el log
                    }
                    break;
                case "0":
                    System.out.println("Selecciono salir del menu");
                    continua = false;
                    break;
                default:
                    System.out.println("Selecciono una opcion no valida porfavor ingrese una funcion valida");
                    break;
            }
        } while (continua);
    }

    public static void modificarEstacion() {
        String lectura;
        boolean continua = true;
        do {
            System.out.println("Seleccione 1 para ingresar el nombre de la estacion a modificar");
            System.out.println("Seleccione 0 para salir");
            lectura = scGlobal.nextLine();
            switch (lectura) {
                case "1":
                    System.out.println("Ingrese el nombre de la estacion");
                    lectura = scGlobal.nextLine();
                    Estacion estacion = (Estacion) estacionesGlobal.obtener(lectura);
                    Main.registrarLog("Intenta buscar la estacion:" + lectura);
                    if (estacion != null) {
                        Main.registrarLog("Encuentra la estacion:" + lectura);
                        System.out.println("Selecciono la estacion :" + estacion.toString());
                        boolean continua2 = true;
                        do {
                            System.out.println("Ingrese 1 para modificar la calle");
                            System.out.println("Ingrese 2 para modificar el número de calle");
                            System.out.println("Ingrese 3 para modificar la ciudad");
                            System.out.println("Ingrese 4 para modificar el código postal ");
                            System.out.println("Ingrese 5 para modificar la cantidad de vías");
                            System.out.println("Ingrese 6 para modificar la cantidad de plataformas");
                            System.out.println("Ingrese 0 para salir");
                            lectura = scGlobal.nextLine();
                            switch (lectura) {
                                case "1":
                                    System.out.println("Selecciono modificar la calle");
                                    System.out.println("Ingrese el dato para modificar");
                                    continua = true;
                                    lectura = scGlobal.nextLine();
                                    estacion.setCalle(lectura);
                                    Main.registrarLog("Se altero la calle de la estacion :" + estacion.toString());
                                    System.out.println("Se altero la calle de la estacion :" + estacion.toString());
                                    break;
                                case "2":
                                    System.out.println("Selecciono modificar el número de calle");
                                    System.out.println("Ingrese el dato para modificar , solo numeros");
                                    continua = true;
                                    lectura = scGlobal.nextLine();
                                    try {
                                        estacion.setNumeroCalle(Integer.parseInt(lectura));
                                        System.out.println(
                                                "Se modifico exitosamente el numero de calle : " + estacion.toString());
                                        Main.registrarLog(
                                                "Se modifico exitosamente el numero de calle : " + estacion.toString());

                                    } catch (NumberFormatException e) {
                                        System.out.println(
                                                "El dato ingresado no es valido , se requiere un numero sin caracteres");
                                        Main.registrarLog("Se intento ingresar una string cuando se requeria un int");
                                    }
                                    break;
                                case "3":
                                    System.out.println("Selecciono modificar la ciudad");
                                    System.out.println("Ingrese el dato para modificar");
                                    continua = true;
                                    lectura = scGlobal.nextLine();
                                    estacion.setCiudad(lectura);
                                    Main.registrarLog("Se altero la ciudad de la estacion :" + estacion.toString());
                                    System.out.println("Se altero la ciudad de la estacion :" + estacion.toString());
                                    break;
                                case "4":
                                    System.out.println("Selecciono modificar el código postal");
                                    System.out.println("Ingrese el dato para modificar");
                                    continua = true;
                                    lectura = scGlobal.nextLine();
                                    estacion.setCodigoPostal(lectura);
                                    Main.registrarLog(
                                            "Se altero el codigo postal de la estacion :" + estacion.toString());
                                    System.out.println(
                                            "Se altero el codigo postal de la estacion :" + estacion.toString());
                                    break;
                                case "5":
                                    System.out.println("Selecciono modificar la cantidad de vías");
                                    System.out.println("Ingrese el dato para modificar");
                                    continua = true;
                                    lectura = scGlobal.nextLine();
                                    try {
                                        estacion.setCantidadVias(Integer.parseInt(lectura));
                                        System.out.println(
                                                "Se modifico exitosamente la cantidad de vias : "
                                                        + estacion.toString());
                                        Main.registrarLog(
                                                "Se modifico exitosamente la cantidad de vias : "
                                                        + estacion.toString());

                                    } catch (NumberFormatException e) {
                                        System.out.println(
                                                "El dato ingresado no es valido , se requiere un numero sin caracteres");
                                        Main.registrarLog("Se intento ingresar una string cuando se requeria un int");
                                    }
                                    break;
                                case "6":
                                    System.out.println("Selecciono modificar la cantidad de plataformas");
                                    System.out.println("Ingrese el dato para modificar");
                                    continua = true;
                                    lectura = scGlobal.nextLine();
                                    try {
                                        estacion.setCantidadPlataformas(Integer.parseInt(lectura));
                                        System.out.println(
                                                "Se modifico exitosamente la cantidad de plataformas : "
                                                        + estacion.toString());
                                        Main.registrarLog(
                                                "Se modifico exitosamente la cantidad de plataformas : "
                                                        + estacion.toString());

                                    } catch (NumberFormatException e) {
                                        System.out.println(
                                                "El dato ingresado no es valido , se requiere un numero sin caracteres");
                                        Main.registrarLog("Se intento ingresar una string cuando se requeria un int");
                                    }
                                    break;
                                case "0":
                                    System.out.println("Selecciono salir del menu");
                                    continua2 = false;
                                    break;

                                default:
                                    break;
                            }
                        } while (continua2);
                    } else {
                        Main.registrarLog("No encuentra la estacion " + lectura);
                        System.out.println("No encuentra la estacion " + lectura);
                    }
                    break;
                case "0":
                    System.out.println("Selecciono salir del menu");
                    continua = false;
                    break;
                default:
                    System.out.println("Selecciono una opcion no valida porfavor ingrese una funcion valida");
                    break;
            }
        } while (continua);
    }

    public static void altaEstacion() {
        boolean continua = true;
        String lectura = "";
        do {
            System.out.println("Ingrese 1 para comenzar a rellenar los datos para una nueva estacion");
            System.out.println("Ingrese 0 para salir");
            lectura = scGlobal.nextLine();
            switch (lectura) {
                case "1":
                    System.out.println("Ingrese el nombre de la estacion");
                    String nombreEstacion = scGlobal.nextLine();
                    // Falta verificar q la estacion no este ingresada
                    if (estacionesGlobal.obtener(nombreEstacion) == null) {
                        System.out.println("Ingrese la calle de la estacion");
                        String calleEstacion = scGlobal.nextLine();
                        System.out.println("Ingreso el numero de calle es la estacion");
                        String numeroCalleEstacion = scGlobal.nextLine();
                        System.out.println("Ingrese la ciudad de la estacion");
                        String ciudadDeLaEstacion = scGlobal.nextLine();
                        System.out.println("Ingrese el codigo postal de la estacion");
                        String codigoPostalEstacion = scGlobal.nextLine();
                        System.out.println("Ingrese la cantidad de vias de la estacion");
                        String cantidadViasEstacion = scGlobal.nextLine();
                        System.out.println("Ingrese la cantidad de plataformas de la estacion");
                        String cantidadPlataformasEstacion = scGlobal.nextLine();
                        if (estacionesGlobal.insertar(nombreEstacion,
                                new Estacion(nombreEstacion, calleEstacion, numeroCalleEstacion, ciudadDeLaEstacion,
                                        codigoPostalEstacion,
                                        cantidadViasEstacion, cantidadPlataformasEstacion))) {
                            System.out.println("Se inserto la estacion exitosamente:" + nombreEstacion + ";"
                                    + calleEstacion
                                    + ";" + numeroCalleEstacion + ";" + ciudadDeLaEstacion + ";" + codigoPostalEstacion
                                    + ";" +
                                    cantidadViasEstacion + ";" + cantidadPlataformasEstacion);
                            Main.registrarLog("Se inserto la estacion exitosamente:" + nombreEstacion + ";"
                                    + calleEstacion
                                    + ";" + numeroCalleEstacion + ";" + ciudadDeLaEstacion + ";" + codigoPostalEstacion
                                    + ";" +
                                    cantidadViasEstacion + ";" + cantidadPlataformasEstacion);
                        } else {
                            System.out.println("No se pudo ingresar la estacion");
                        }
                    } else {
                        System.out.println("Intento ingresar una estacion con un nombre q ya esta registrado");
                    }
                    break;
                case "0":
                    continua = false;
                    System.out.println("Selecciono salir del menu");
                    break;
                default:
                    System.out.println("Selecciono una opcion no valida porfavor ingrese una funcion valida");
                    break;
            }
        } while (continua);
    }

    public static void mostrarEstacion() {
        String lectura;
        boolean continua = true;
        do {
            System.out.println("Selecciono 1 para mostrar una estacion");
            System.out.println("Seleccione 0 para salir");
            lectura = scGlobal.nextLine();
            switch (lectura) {
                case "1":
                    System.out.println("Selecciono mostrar una estacion");
                    System.out.println("Ingrese el nombre de la estacion");
                    lectura = scGlobal.nextLine();
                    Estacion estacion = (Estacion) estacionesGlobal.obtener(lectura);
                    Main.registrarLog("Intenta buscar la estacion:" + lectura);
                    if (estacion != null) {
                        System.out.println("La estacion q esta buscando es ," + estacion.toString());
                        Main.registrarLog("Encontro la estacion:" + lectura);
                    } else {
                        System.out.println("No encontro la estacion:" + lectura);
                        Main.registrarLog("No encontro la estacion:" + lectura);
                    }
                    break;
                case "0":
                    System.out.println("Selecciono salir del menu");
                    break;

                default:
                    break;
            }
        } while (continua);
    }

    public static void buscarEstacionSubcadena() {
        boolean continua = true;
        String lectura;
        do {
            System.out.println("Seleccione 1 para ingresar una subcadena");
            System.out.println("Seleccione 0 si quiere volver al menu anterior");
            lectura = scGlobal.nextLine();
            System.out.println(lectura);
            switch (lectura) {
                case "1":
                    System.out.println("Ingrese la subcadena q quiere buscar");
                    lectura = scGlobal.nextLine();
                    Main.registrarLog("Intento buscar estacion con la substring:" + lectura);
                    // cambiarlo a listar por rango
                    List<Object> lista = estacionesGlobal.listarRango(lectura, lectura + "zzz");
                    if (lista.size() != 0) {
                        System.out.println("Las estaciones encontradas son:");
                        for (Object object : lista) {
                            System.out.println(object.toString());
                        }
                    } else {
                        Main.registrarLog("No se encontro estaciones con la substring:" + lectura);
                    }
                    break;
                case "0":
                    System.out.println("Selecciono volver al menu anterior");
                    continua = false;
                    break;

                default:
                    System.out.println("Ingreso algo no valido");
                    break;
            }
        } while (continua);

    }
}
