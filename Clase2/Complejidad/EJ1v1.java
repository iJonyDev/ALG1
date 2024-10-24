package Complejidad;

public class EJ1v1{
    // Codigo original
    public int algorithm(int[] A) { // O(n)
        int i = 1;  // O(1)
        int j = 1;  // O(1)
        int c = 0;  // O(1)
        int m = 0;  // O(1)
    
        while (j < A.length) {  // O(n)
            if (A[i] == A[j]) {  // O(1)
                c++;  // O(1)
                j++;  // O(1)
            } else {  // O(1)
                j++;  // O(1)
                if (c > m) {  // O(1)
                    m = c;  // O(1)
                }
                c = 0;  // O(1)
            }
        }
        return m;  // O(1)
    }

    public static int improvedAlgorithm(int[] A) {
        int n = A.length;    // O(1)
        int maxLen = 1;  // Longitud máxima inicializada en 1  // O(1)
        int currLen = 1;  // Longitud actual de la subsecuencia  // O(1)

        // Iteramos sobre el arreglo
        for (int i = 1; i < n; i++) {  // O(n)
            // Si el elemento actual es igual al anterior, incrementamos la longitud actual
            if (A[i] == A[i-1]) {  // O(1)
                currLen++;  // O(1)
            } else {
                // Si son diferentes, actualizamos la longitud máxima si es necesario
                maxLen = Math.max(maxLen, currLen);  // O(1)
                // Reiniciamos la longitud actual para la nueva subsecuencia
                currLen = 1;  // O(1)
            }
        }

        // Al final, comparamos la última subsecuencia con la máxima encontrada
        return Math.max(maxLen, currLen);   // O(1)
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 1, 1, 1};
        int longest = improvedAlgorithm(arr);
        System.out.println("La longitud de la subsecuencia más larga es: " + longest);
    }
}
