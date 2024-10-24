package Clase3.EPD3;

import java.util.Arrays;

public class ComparacionAlgoritmos {

    public static void main(String[] args) {
        int[] vector;
        int[] vectorCopia;
        int[] tamanios = {5000, 10000, 20000}; // Tamaños de los vectores a probar

        for (int n : tamanios) {
            vector = generarVectorAleatorio(n); // Generar un nuevo vector aleatorio de tamaño n
            vectorCopia = Arrays.copyOf(vector, vector.length); // Hacer una copia del vector para usar en ambos algoritmos

            // Medir el tiempo de ejecución del algoritmo de ordenamiento por inserción
            long tiempoInicioInsercion = System.nanoTime();
            Experimento1 e1= new Experimento1();
            e1.ordenarPorInsercion(vector);
            long tiempoFinInsercion = System.nanoTime();
            long tiempoEjecucionInsercion = tiempoFinInsercion - tiempoInicioInsercion;

            // Medir el tiempo de ejecución del algoritmo de ordenamiento por burbuja iterativo
            long tiempoInicioBurbuja = System.nanoTime();
            Experimento1 e2= new Experimento1();
            e2.ordenarPorBurbujaIterativo(vectorCopia);
            long tiempoFinBurbuja = System.nanoTime();
            long tiempoEjecucionBurbuja = tiempoFinBurbuja - tiempoInicioBurbuja;

            // Imprimir los tiempos de ejecución obtenidos
            System.out.println("Tamaño del vector: " + n);
            System.out.println("Tiempo de ejecución (Inserción): " + tiempoEjecucionInsercion/10000000 + " microsegundos");
            System.out.println("Tiempo de ejecución (Burbuja): " + tiempoEjecucionBurbuja/1000000 + " microsegundos");
            System.out.println("----------------------------------------");
        }
    }

    public static int[] generarVectorAleatorio(int n) {
        int[] vector = new int[n];
        for (int i = 0; i < n; i++) {
            vector[i] = (int) (Math.random() * 1000); // Generar números aleatorios entre 0 y 999
        }
        return vector;
    }
}
