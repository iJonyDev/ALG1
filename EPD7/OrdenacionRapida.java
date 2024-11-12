package EPD7;

public class OrdenacionRapida {
    public static void main(String[] args) {
        int[] array = {9,-3,5,2,6,8,-6,1,3};
        quickSort(array, 0, array.length - 1);
        for (int i : array) {
            System.out.print(i + " ");
        }
    }

    public static void quickSort(int[] array, int inicio, int fin) {
        if (inicio < fin) {
            int pivote = particion(array, inicio, fin);
            quickSort(array, inicio, pivote - 1); // Ordenamos la lista de los menores
            quickSort(array, pivote + 1, fin); // Ordenamos la lista de los mayores
        }
    }

    public static int particion(int[] array, int inicio, int fin) {
        int pivote = array[fin];
        int i = inicio - 1;
        for (int j = inicio; j < fin; j++) {
            if (array[j] < pivote) {
                i++;
                int aux = array[i];
                array[i] = array[j];
                array[j] = aux;
            }
        }
        int aux = array[i + 1];
        array[i + 1] = array[fin];
        array[fin] = aux;
        return i + 1;
    }
}