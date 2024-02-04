package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import Estructuras.Dinamicas.Diccionario;
import Estructuras.TrenesSA.Estacion;
import Estructuras.TrenesSA.Linea;
import Estructuras.TrenesSA.Tren;

public class testInsercciones {
    public static void main(String[] args) throws IOException {
        Diccionario trenes = new Diccionario();
        Diccionario estaciones = new Diccionario();
        HashMap<String, Object> lineas = new HashMap<String, Object>();
        FileReader file = new FileReader("data/data.txt");
        BufferedReader br = new BufferedReader(file);
        String lineaTxt = br.readLine();
        String accion = "";
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
                            estaciones.insertar(token, new Estacion(tknAtr.nextToken(), tknAtr.nextToken(),
                                    tknAtr.nextToken(), tknAtr.nextToken(), tknAtr.nextToken(), tknAtr.nextToken()));
                            break;
                        case "L":
                            String key = token;
                            List<String> lineasTemp = new ArrayList<String>();
                            boolean quedanTokens = true;
                            token = tknAtr.nextToken();
                            while (getAccion(token) == "" && quedanTokens) {
                                // Acumula las posibles estaciones por las q pasa una linea
                                // 1..n
                                lineasTemp.add(token);
                                quedanTokens = tknAtr.hasMoreTokens();
                                if (quedanTokens) {
                                    token = tknAtr.nextToken();
                                }
                            }
                            lineas.put(key, new Linea(lineasTemp));
                            break;
                        case "R":

                            break;
                        case "T":
                            trenes.insertar(token,
                                    new Tren(tknAtr.nextToken(), tknAtr.nextToken(),
                                            tknAtr.nextToken(), tknAtr.nextToken()));
                    }
                }

            }
            lineaTxt = br.readLine();
        }
        br.close();
        System.out.println(trenes.toString());
        System.out.println(estaciones.toString());
        System.out.println(lineas.toString());
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
}
