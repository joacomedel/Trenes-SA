package TrenesSA;

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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Estructuras.Dinamicas.Diccionario;
import Estructuras.Dinamicas.Grafo;

public class Main {
    private static Diccionario trenes = new Diccionario();
    private static Diccionario estaciones = new Diccionario();
    private static HashMap<String, Object> lineas = new HashMap<String, Object>();
    private static Grafo rieles = new Grafo();
    static LocalDateTime now = LocalDateTime.now();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH-mm-ss");
    static String fechaHoraFormateada = now.format(formatter);
    private static final String RutaEscritura = "logs/" + fechaHoraFormateada + ".txt";
    private static final String RutaLectura = "data/data.txt";
    private static final int CantParamEstacion = 7;
    private static final int CantParamMinLinea = 2;
    private static final int CantParamRiel = 3;
    private static final int CantParamTren = 5;

    public static void main(String[] args) throws IOException {

        FileReader file = new FileReader(RutaLectura);
        BufferedReader br = new BufferedReader(file);
        String lineaTxt = br.readLine();
        String accion = "";
        boolean leerTokenizer = true;
        registrarLog("Carga inicial comienza");
        while (lineaTxt != null) {
            StringTokenizer tknAtr = new StringTokenizer(lineaTxt, ";");
            leerTokenizer = true;
            while (tknAtr.hasMoreTokens() && leerTokenizer) {
                String token = tknAtr.nextToken();
                String temp = getAccion(token);
                if (temp != "") {
                    accion = temp;
                    // Solo asigno la accion cuando el token es de tipo accion osea T,R,L,E
                } else {
                    // Si el token actual no es de accion significa q tengo q guardar algo
                    switch (accion) {
                        case "E":
                            insertarEstacion(token, tknAtr);
                            leerTokenizer = tknAtr.countTokens() == CantParamEstacion - 1;
                            break;
                        case "L":
                            insertarLinea(token, tknAtr);
                            leerTokenizer = tknAtr.countTokens() > CantParamMinLinea - 1;
                            break;
                        case "R":
                            insertarRiel(token, tknAtr);
                            leerTokenizer = tknAtr.countTokens() == CantParamRiel - 1;
                            break;
                        case "T":
                            insertarTren(token, tknAtr);
                            leerTokenizer = tknAtr.countTokens() == CantParamTren - 1;
                            break;
                    }
                }

            }
            lineaTxt = br.readLine();
        }
        br.close();
        registrarLog(sistemaToString());
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
            System.out.println("Seleccione 5 para ver el sistema");
            System.out.println("Seleccione 0 para salir");
            lectura = sc.nextLine();
            switch (lectura) {
                case "1":
                    System.out.println("Selecciono Menu Estacion");
                    EstacionMenu.menuEstacion(sc, estaciones);
                    break;
                case "2":
                    System.out.println("Selecciono Menu Linea");
                    LineaMenu.menuLinea(lineas, sc);
                    break;
                case "3":
                    System.out.println("Selecciono Menu Riel");
                    RielMenu.menuRiel(rieles, sc);
                    break;
                case "4":
                    System.out.println("Seleccion Menu Tren");
                    TrenMenu.menuTren(sc, trenes, lineas, estaciones);
                    break;
                case "5":
                    System.out.println("Seleccion ver el sistema");
                    System.out.println(sistemaToString());
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

    public static String sistemaToString() {
        return "\n Estaciones: \n" + estaciones.toString() + "\n Rieles: \n" + rieles.toString() + "\n Lineas: \n"
                + lineas.toString() + "\n \n Trenes \n" + trenes.toString();
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
        registrarLog("Comienza insercion de estacion:" + nombreEstacion);
        if (tknAtr.countTokens() == CantParamEstacion - 1) {
            String calle = tknAtr.nextToken();
            try {
                int numeroCalle = Integer.parseInt(tknAtr.nextToken());
                String ciudad = tknAtr.nextToken();
                String codigoPostal = tknAtr.nextToken();
                int cantidadVias = Integer.parseInt(tknAtr.nextToken());
                int cantidadPlataformas = Integer.parseInt(tknAtr.nextToken());
                if (estaciones.insertar(nombreEstacion, new Estacion(calle, numeroCalle,
                        ciudad, codigoPostal, cantidadVias, cantidadPlataformas))) {
                    // Registo solo si inserto
                    registrarLog("Estacion cargada:" + nombreEstacion + ";" + calle + ";" + numeroCalle + ";" +
                            ciudad + ";" + codigoPostal + ";" + cantidadVias + ";" + cantidadPlataformas);
                }
            } catch (NumberFormatException e) {
                // TODO: handle exception
                registrarLog("Algun token tenia q ser int y no fue ingresado como int expecion :" + e);
            }

        } else {
            Main.registrarLog("Faltan tokens");
        }
        registrarLog("\n");
    }

    public static void insertarRiel(String token, StringTokenizer tknAtr) {
        String estacion1 = token;
        registrarLog("Comienzo insercion de riel con primera estacion:" + estacion1);
        if (tknAtr.countTokens() == CantParamRiel - 1) {
            try {
                String estacion2 = tknAtr.nextToken();
                int distancia = Integer.parseInt(tknAtr.nextToken());
                boolean existeEstacion1 = null != estaciones.obtener(estacion1);
                boolean existeEstacion2 = null != estaciones.obtener(estacion2);
                if (existeEstacion1 && existeEstacion2) {
                    if (rieles.insertarVertice(estacion1)) {
                        registrarLog("Vertice estacion cargada con exito" + estacion1);
                    }
                    if (rieles.insertarVertice(estacion2)) {
                        registrarLog("Vertice estacion cargada con exito" + estacion2);
                    }
                    if (rieles.insertarArco(estacion1, estacion2, distancia)) {
                        registrarLog("Arco cargado con exito:" + estacion1 + ";" + estacion2 + ";" + distancia);
                    }
                } else {
                    if (!existeEstacion1) {
                        registrarLog("No existe la estacion:" + estacion1);
                    }
                    if (!existeEstacion2) {
                        registrarLog("No existe la estacion:" + estacion2);
                    }
                }
            } catch (NumberFormatException e) {
                registrarLog("Algun token tenia q ser int y no fue ingresado como int expecion :" + e);
                System.out.println();
            }
        } else {
            registrarLog("Faltan tokens");
        }
        registrarLog("\n");
    }

    public static void insertarLinea(String token, StringTokenizer tknAtr) {
        String key = token;
        registrarLog("Comienzo insercion de linea:" + key);
        List<String> estacionesTemp = new ArrayList<String>();
        boolean quedanTokens = tknAtr.countTokens() > CantParamMinLinea - 1;
        if (quedanTokens) {
            token = tknAtr.nextToken();
            String lineasStringTemp = "";
            while (getAccion(token) == "" && quedanTokens) {
                // Acumula las posibles estaciones por las q pasa una linea
                // 1..n
                if (estaciones.obtener(token) != null) {
                    lineasStringTemp += token + ";";
                    estacionesTemp.add(token);
                } else {
                    Main.registrarLog("Se intento registrar en la linea " + key + " la estacion " + token
                            + " y no fue encontrada como estacion");
                }
                quedanTokens = tknAtr.hasMoreTokens();
                if (quedanTokens) {
                    token = tknAtr.nextToken();
                }
            }
            lineas.put(key, new Linea(estacionesTemp));
            // Verificar q puso?
            registrarLog("Linea cargada:" + key + ";" + lineasStringTemp);
        } else {
            registrarLog("Faltan tokens no se ingresarion estaciones");
        }
        registrarLog("\n");
    }

    public static void insertarTren(String token, StringTokenizer tknAtr) {
        registrarLog("Comienzo insercion de tren");
        try {
            int codigo = Integer.parseInt(token);
            registrarLog("Comienzo insercion de tren con codigo:" + codigo);
            if (tknAtr.countTokens() == CantParamTren - 1) {
                String tipoDePropulsion = tknAtr.nextToken();
                int vagonesPasajeros = Integer.parseInt(tknAtr.nextToken());
                int vagonesCarga = Integer.parseInt(tknAtr.nextToken());
                String linea = tknAtr.nextToken();
                if (linea.equals("no-asignado") || lineas.get(linea) != null) {
                    if (trenes.insertar(codigo,
                            new Tren(tipoDePropulsion, vagonesPasajeros,
                                    vagonesCarga, linea))) {
                        registrarLog(
                                "Tren insertado:" + codigo + ";" + tipoDePropulsion + ";"
                                        + vagonesPasajeros + ";"
                                        + vagonesCarga
                                        + ";" + linea);
                    } else {
                        registrarLog("Se intento insertar y no se pudo ser insertado:" + codigo + ";"
                                + tipoDePropulsion + ";"
                                + vagonesPasajeros + ";"
                                + vagonesCarga
                                + ";" + linea);
                    }
                } else {
                    registrarLog("Se intento insertar un tren con una linea no existente:" + linea);
                }
            } else {
                registrarLog("Faltan tokens");
            }
        } catch (NumberFormatException e) {
            registrarLog("Algun token tenia q ser int y no fue ingresado como int expecion :" + e);
        }
        registrarLog("\n");
    }

    public static void registrarLog(String mensaje) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RutaEscritura, true))) {
            writer.write(mensaje);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo de log: " + e.getMessage());
        }
    }
}
