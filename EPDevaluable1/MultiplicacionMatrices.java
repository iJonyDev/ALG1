package EPDevaluable1;

public class MultiplicacionMatrices {
    public static void main(String[] args) {
        int[][] matriz1 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        int[][] matriz2 = {
            {9, 8, 7},
            {6, 5, 4},
            {3, 2, 1}
        };

        // Multiplicación iterativa
        long startTime = System.nanoTime();
        int[][] resultadoIterativo = multiplicarIterativo(matriz1, matriz2);
        long endTime = System.nanoTime();
        long tiempoIterativo = endTime - startTime;

        // Multiplicación recursiva
        startTime = System.nanoTime();
        int[][] resultadoRecursivo = multiplicarRecursivo(matriz1, matriz2);
        endTime = System.nanoTime();
        long tiempoRecursivo = endTime - startTime;

        // Comparar resultados
        boolean sonIguales = sonMatricesIguales(resultadoIterativo, resultadoRecursivo);

        // Imprimir resultados y tiempos de ejecución
        System.out.println("Resultado iterativo:");
        imprimirMatriz(resultadoIterativo);
        System.out.println("Tiempo de ejecución iterativo: " + tiempoIterativo + " ns");

        System.out.println("\nResultado recursivo:");
        imprimirMatriz(resultadoRecursivo);
        System.out.println("Tiempo de ejecución recursivo: " + tiempoRecursivo + " ns");

        System.out.println("\n¿Los resultados son iguales? " + sonIguales);
    }

    public static int[][] multiplicarIterativo(int[][] matriz1, int[][] matriz2) {
        int[][] resultado = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    resultado[i][j] += matriz1[i][k] * matriz2[k][j];
                }
            }
        }
        return resultado;
    }

    public static int[][] multiplicarRecursivo(int[][] matriz1, int[][] matriz2) {
        int[][] resultado = new int[3][3];
        multiplicarRecursivoAux(matriz1, matriz2, resultado, 0, 0, 0);
        return resultado;
    }

    public static void multiplicarRecursivoAux(int[][] matriz1, int[][] matriz2, int[][] resultado, int i, int j, int k) {
        if (i >= 3) {
            return;
        }
        if (j < 3) {
            if (k < 3) {
                resultado[i][j] += matriz1[i][k] * matriz2[k][j];
                multiplicarRecursivoAux(matriz1, matriz2, resultado, i, j, k + 1);
            }
            multiplicarRecursivoAux(matriz1, matriz2, resultado, i, j + 1, 0);
        }
        if (j >= 3) {
            multiplicarRecursivoAux(matriz1, matriz2, resultado, i + 1, 0, 0);
        }
    }

    public static boolean sonMatricesIguales(int[][] matriz1, int[][] matriz2) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matriz1[i][j] != matriz2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void imprimirMatriz(int[][] matriz) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
}