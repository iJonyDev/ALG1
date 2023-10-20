package Clase3.EPD3;

public class Experimento1 {

    /*El algoritmo de ordenamiento por inserción recorre el vector y en cada iteración, compara el valor actual con los valores anteriores e inserta el valor en la posición correcta. Tiene una complejidad de tiempo promedio de O(n^2), donde n es el tamaño del vector.  */
    public void ordenarPorInsercion(int[] vector) {

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
    /*El algoritmo de ordenamiento por burbuja iterativo compara pares de elementos adyacentes y los intercambia si están en el orden incorrecto. Repite este proceso hasta que no haya más intercambios por realizar. Tiene una complejidad de tiempo promedio de O(n^2), donde n es el tamaño del vector.  */
        public void ordenarPorBurbujaIterativo(int[] vector) {
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
}
/*En términos generales, ambos algoritmos tienen una complejidad de tiempo similar. Sin embargo, el rendimiento real puede variar según el tamaño y la distribución de los datos en el vector.  */
