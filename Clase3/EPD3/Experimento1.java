package Clase3.EPD3;

import java.util.Arrays;
import java.util.Random;

public class Experimento1 {

    /*
     * El algoritmo de ordenamiento por inserción recorre el vector y en cada
     * iteración, compara el valor actual con los valores anteriores e inserta el
     * valor en la posición correcta. Tiene una complejidad de tiempo promedio de
     * O(n^2), donde n es el tamaño del vector.
     */
    public static void ordenarPorInsercion(int[] vector) {
        int j, valorActual;
        for (int i = 1; i < vector.length; i++) {
            valorActual = vector[i];
            j = i - 1;
            while ((j >= 0) && (valorActual < vector[j])) {
                vector[j + 1] = vector[j];
                j--;
            }
            vector[j + 1] = valorActual;
        }
    }

    /*
     * El algoritmo de ordenamiento por burbuja iterativo compara pares de elementos
     * adyacentes y los intercambia si están en el orden incorrecto. Repite este
     * proceso hasta que no haya más intercambios por realizar. Tiene una
     * complejidad de tiempo promedio de O(n^2), donde n es el tamaño del vector.
     */
    public static void ordenarPorBurbujaIterativo(int[] vector) {
        int i, aux;
        boolean intercambio;
        do {
            intercambio = false;
            for (i = 0; i < vector.length - 1; i++) {
                if (vector[i + 1] < vector[i]) {
                    aux = vector[i + 1];
                    vector[i + 1] = vector[i];
                    vector[i] = aux;
                    intercambio = true;
                }
            }
        } while (intercambio);
    }

    /*
     * En términos generales, ambos algoritmos tienen una complejidad de tiempo
     * similar. Sin embargo, el rendimiento real puede variar según el tamaño y la
     * distribución de los datos en el vector.
     */

    public static int[] newArray(int nele) {
        return newArray(nele, 13579);
    }

    public static int[] newArray(int nele, long seed) {
        int[] array = new int[nele];

        Random rnd = new Random(seed);

        for (int i = 0; i < nele; i++) {
            array[i] = rnd.nextInt(10 * nele);
        }

        return array;
    }

    public static double[] runExperimentsBubble(int[] sizes) throws Exception {

        double[] etime = new double[sizes.length];

        for (int e = 0; e < sizes.length; e++) {
            System.out.println("Running bubble experiment " + (e + 1) + "/" + sizes.length);
            int[] array = newArray(sizes[e]);

            long start = System.nanoTime();
            ordenarPorBurbujaIterativo(array);
            long stop = System.nanoTime();

            etime[e] = ((double) (stop - start)) / 1_000_000_000;

        }

        return etime;
    }

    public static double[] runExperimentsInsertion(int[] sizes) throws Exception {

        double[] etime = new double[sizes.length];

        for (int e = 0; e < sizes.length; e++) {
            System.out.println("Running insertionq experiment " + (e + 1) + "/" + sizes.length);

            long start = System.nanoTime();
            for (int i = 0; i < 1_0; i++) {
                int[] array = newArray(sizes[e]);
                ordenarPorInsercion(array);
            }
            long stop = System.nanoTime();

            etime[e] = ((double) (stop - start)) / 1_000_000_000 / 1_0;

        }

        return etime;
    }

    public static void main(String[] args) throws Exception {

        int[] sizes = new int[] { 50000, 75000, 100000 };

        // double[] etime = runExperimentsBubble(sizes);

        // System.out.println("bubble: " + Arrays.toString(etime));

        double[] etime = runExperimentsInsertion(sizes);

        System.out.println("Insertion: " + Arrays.toString(etime));

    }

}
