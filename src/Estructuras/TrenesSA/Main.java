package Estructuras.TrenesSA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import Estructuras.Dinamicas.Diccionario;
import Estructuras.Dinamicas.Grafo;

public class Main {
    static Diccionario trenes = new Diccionario();
    static Diccionario estaciones = new Diccionario();
    static HashMap<String, Object> lineas = new HashMap<String, Object>();
    static Grafo rieles = new Grafo();
    static final String RutaEscritura = "data/log.txt";
    static final String RutaLectura = "data/data.txt";

    public static void main(String[] args) throws IOException {

        FileReader file = new FileReader(RutaLectura);
        BufferedReader br = new BufferedReader(file);
        String lineaTxt = br.readLine();
        String accion = "";
        registrarLog("Carga inicial comienza");
        while (lineaTxt != null) {
            StringTokenizer tknAtr = new StringTokenizer(lineaTxt, ";");
            while (tknAtr.hasMoreTokens()) {
                String token = tknAtr.nextToken();
                String temp = getAccion(token);
                if (temp != "") {
                    accion = temp;
                    System.out.println("Seleccion accion");
                    // Solo asigno la accion cuando el token es de tipo accion osea T,R,L,E
                } else {
                    // Si el token actual no es de accion significa q tengo q guardar algo
                    switch (accion) {
                        case "E":
                            insertarEstacion(token, tknAtr);
                            break;
                        case "L":
                            insertarLinea(token, tknAtr);
                            break;
                        case "R":
                            insertarRiel(token, tknAtr);
                            break;
                        case "T":
                            insertarTren(token, tknAtr);
                            break;
                    }
                }

            }
            lineaTxt = br.readLine();
        }
        br.close();
        menuGeneral();
    }

    public static void menuGeneral() {
        boolean continua = true;
        String lectura;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("MENU GENERAL TRENES_SA");
            System.out.println("Seleccione 1 para ir al menu Estacion");
            System.out.println("Seleccione 2 para ir al menu Linea");
            System.out.println("Seleccione 3 para ir al menu Riel");
            System.out.println("Seleccione 4 para ir al menu Tren");
            System.out.println("Seleccione 0 para salir");
            lectura = sc.nextLine();
            switch (lectura) {
                case "1":
                    System.out.println("Selecciono Menu Estacion");
                    EstacionMenu.menuEstacion(sc, estaciones);
                    break;
                case "2":
                    System.out.println("Selecciono Menu Linea");
                    menuLinea(sc);
                    break;
                case "3":
                    System.out.println("Selecciono Menu Riel");
                    menuRiel(sc);
                    break;
                case "4":
                    System.out.println("Seleccion Menu Tren");
                    menuTren(sc);
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

    public static void menuLinea(Scanner sc) {
        boolean continua = true;
        String lectura;
        do {
            System.out.println("Seleccione 1 para dar de baja , alta o modificar Linea");
            System.out.println("Seleccione 0 para volver al menu general");
            lectura = sc.nextLine();
            switch (lectura) {
                case "1":
                    System.out.println("Selecciono dar de baja , alta o modificar estacion");
                    abmLinea(sc);

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

    public static void abmLinea(Scanner sc) {

    }

    public static void menuRiel(Scanner sc) {
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

    }

    public static void caminoMenosEstaciones(Scanner sc) {

    }

    public static void caminoMenosKm(Scanner sc) {

    }

    public static void caminosSinEstacion(Scanner sc) {

    }

    public static void posibleCaminoKmMax(Scanner sc) {

    }

    public static void menuTren(Scanner sc) {
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
                    abmTren(sc);
                    break;
                case "2":
                    System.out.println("Selecciono mostrar tren");
                    mostrarTren(sc);
                    break;
                case "3":
                    System.out.println(
                            "Selecciono verificar si está destinado a alguna línea y mostrar las ciudades que visitaría");
                    verfLineasDeTren(sc);
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

    public static void abmTren(Scanner sc) {

    }

    public static void mostrarTren(Scanner sc) {

    }

    public static void verfLineasDeTren(Scanner sc) {

    }

    public static String getAccion(String token) {
        String accion = "";
        switch (token) {
            case "E":
                accion = "E";
                break;
            case "L":
                accion = "L";
                break;
            case "R":
                accion = "R";
                break;
            case "T":
                accion = "T";
                break;
        }
        return accion;
    }

    public static void insertarEstacion(String token, StringTokenizer tknAtr) {
        String nombreEstacion = token;
        String calle = tknAtr.nextToken();
        String numeroCalle = tknAtr.nextToken();
        String ciudad = tknAtr.nextToken();
        String codigoPostal = tknAtr.nextToken();
        String cantidadVias = tknAtr.nextToken();
        String cantidadPlataformas = tknAtr.nextToken();
        if (estaciones.insertar(nombreEstacion, new Estacion(calle, numeroCalle,
                ciudad, codigoPostal, cantidadVias, cantidadPlataformas))) {
            // Registo solo si inserto
            registrarLog("Estacion cargada:" + nombreEstacion + " " + calle + " " + numeroCalle + " " +
                    ciudad + " " + codigoPostal + " " + cantidadVias + " " + cantidadPlataformas);
        }

    }

    public static void insertarRiel(String token, StringTokenizer tknAtr) {
        String estacion1 = token;
        String estacion2 = tknAtr.nextToken();
        String distancia = tknAtr.nextToken();
        if (rieles.insertarVertice(estacion1)) {
            registrarLog("Vertice estacion cargada con exito" + estacion1);
        }
        if (rieles.insertarVertice(estacion2)) {
            registrarLog("Vertice estacion cargada con exito" + estacion2);
        }
        if (rieles.insertarArco(estacion1, estacion2, distancia)) {
            registrarLog("Arco cargado con exito:" + estacion1 + " " + estacion2 + " " + distancia);
        }

    }

    public static void insertarLinea(String token, StringTokenizer tknAtr) {
        String key = token;
        List<String> lineasTemp = new ArrayList<String>();
        boolean quedanTokens = true;
        token = tknAtr.nextToken();
        String lineasStringTemp = "";
        while (getAccion(token) == "" && quedanTokens) {
            // Acumula las posibles estaciones por las q pasa una linea
            // 1..n
            lineasStringTemp += token + " ";
            lineasTemp.add(token);
            quedanTokens = tknAtr.hasMoreTokens();
            if (quedanTokens) {
                token = tknAtr.nextToken();
            }
        }
        lineas.put(key, new Linea(lineasTemp));
        // Verificar q puso?
        registrarLog("Linea cargada:" + key + lineasStringTemp);
    }

    public static void insertarTren(String token, StringTokenizer tknAtr) {
        String codigo = token;
        String tipoDePropulsion = tknAtr.nextToken();
        String vagonesPasajeros = tknAtr.nextToken();
        String vagonesCarga = tknAtr.nextToken();
        String linea = tknAtr.nextToken();
        if (trenes.insertar(codigo,
                new Tren(tipoDePropulsion, vagonesPasajeros,
                        vagonesCarga, linea))) {
            registrarLog(
                    "Tren insertado:" + codigo + " " + tipoDePropulsion + " " + vagonesPasajeros + " " + vagonesCarga
                            + " " + linea);
        }
    }

    private static void registrarLog(String mensaje) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RutaEscritura, true))) {
            writer.write(mensaje);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo de log: " + e.getMessage());
        }
    }
}
