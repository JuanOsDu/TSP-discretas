/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package discretasjava;

/**
 *
 * @author Juan
 */
import java.util.ArrayList;
import java.util.Scanner;

public class DiscretasJava {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<String> ruta = new ArrayList<>();
    static int[][] matrixs;
    static int[][] matrixCopy;
    static int[] distancias;
    static int ciudades;
    static int distancia;
    static int obj, pos;
    static int recorrido = 0;
    static int distanciaVuelta;

    public static void main(String[] args) {
        System.out.println("Ingrese numero de ciudades:");
        ciudades = Integer.parseInt(sc.next());
        matrixs = new int[ciudades][ciudades];
        distancias = new int[ciudades];
        System.out.println("Ingrese Distancias: 0 no hay ruta (sugerencia 0<x<=9)");
        for (int i = 0; i < ciudades; i++) {
            for (int j = 0; j < ciudades; j++) {
                if (i != j) {
                    System.out.println("Ciudad " + i + " → ciudad " + j);
                    matrixs[i][j] = Integer.parseInt(sc.next());
                }

            }
        }
        matrixCopy = new int[ciudades][ciudades];
        for (int i = 0; i < ciudades; i++) {
            for (int j = 0; j < ciudades; j++) {
                matrixCopy[i][j] = matrixs[i][j];
            }
        }

        showMatrix(matrixs);

        System.out.println("Solucion mediante un algoritmo heuristico");
        System.out.println("partiendo de la ciudad 0");
        ruta.add(String.valueOf(0));
        pos = 0;
        obj = ciudades - 1;

        camino(matrixs);
        //evaluando ruta de vuelta

        if (matrixCopy[pos][0] > 0){//&& distanciaVuelta > matrixCopy[pos][0]) { implementacion en prograse, hay que calcular si hay un camino mas corto desde el final al origen que uno directo.
            ruta.add(String.valueOf(0));
            distancia += matrixCopy[pos][0];
            pos = 0;
        }

        System.out.println("Ruta sugerida: " + ruta);
        System.out.println("Distancia del trayecto: " + distancia);

    }

    static void camino(int[][] matrix) {
        int max;
        int menor;
        recorrido = 0;
        while (recorrido != obj) {
            menor = ciudades;
            max = matrix[pos][0];
            for (int i = 0; i < ciudades; i++) {
                matrix[i][pos] = 0;
            }
            for (int i = 0; i < ciudades; i++) {

                distancias[i] = matrix[pos][i];

            }
            for (int i = 0; i < ciudades; i++) {
                if (distancias[i] > max) {
                    max = distancias[i];
                }
            }

            for (int i = 0; i < ciudades; i++) {
                if (distancias[i] != 0) {
                    if (distancias[i] < max && i != 0) {
                        menor = distancias[i];
                        pos = i;
                        //si sucede algo con posiciones repetidas en la ruta, cambiar < por <= y eliminar el else if
                    } else if (distancias[i] == max && i == (ciudades - 1)) {
                        menor = max;
                    }

                }
                max = menor;

            }

            
            distancia += menor;
            ruta.add(String.valueOf(pos));
            recorrido++;

        }

        showMatrix(matrixs);
        System.out.println(ruta);
        System.out.println(distancia);

    }
//no esta habilitado el camino de vuelta 
    static void caminoVuelta(int[][] matrix) {
        int max;
        int menor;
        recorrido = 0;
        while (recorrido != obj) {
            menor = ciudades;
            max = matrix[pos][0];
            for (int i = 0; i < ciudades; i++) {
                matrix[i][pos] = 0;
            }
            for (int i = 0; i < ciudades; i++) {

                distancias[i] = matrix[pos][i];

            }
            for (int i = 0; i < ciudades; i++) {
                if (distancias[i] > max) {
                    max = distancias[i];
                }
            }
            for (int i = 0; i < ciudades; i++) {
                if (distancias[i] != 0) {
                    if (distancias[i] <= max && i != pos) {
                        menor = distancias[i];
                        pos = i;
                    }
                }
            }
            max = menor;
            distanciaVuelta += menor;
            ruta.add(String.valueOf(pos));
            recorrido++;
        }
    }

    static void showMatrix(int[][] matrix) {
        System.out.println("Ciudad");
        System.out.print("       ");
        for (int i = 0; i < ciudades; i++) {
            System.out.print("   " + i + "  ");
        }
        System.out.println("");
        for (int i = 0; i < ciudades; i++) {
            System.out.print("  " + i + "   ");
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != 0) {
                    System.out.print("   |" + 1 + "|");
                } else {
                    System.out.print("   |*|");
                }
            }
            System.out.println("");

        }
    }
}
