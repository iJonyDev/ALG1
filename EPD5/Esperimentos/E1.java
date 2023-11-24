package EPD5.Esperimentos;

public class E1 {

    public static int[][] inicializaMatrizDistanciasABCDE() {
        int[][] matrizDistancias = {
                { 0, 1, 1, 4, 2 },
                { 1, 0, 2, 3, 4 },
                { 1, 2, 0, 2, 1 },
                { 4, 3, 2, 0, 3 },
                { 2, 4, 1, 3, 0 }
        };
        return matrizDistancias;
    }

    public static int getDistanciaTotal(int[][] distancia, int[] camino) {
        int distanciaTotal = 0;
        for (int i = 0; i < camino.length - 1; i++) {
            int origen = camino[i];
            int destino = camino[i + 1];
            distanciaTotal += distancia[origen][destino];
        }
        return distanciaTotal;
    }

    public static void main(String[] args) {
        int[][] matrizDistancias = inicializaMatrizDistanciasABCDE();
        int[] camino = { 3, 0};
        int distanciaTotal = getDistanciaTotal(matrizDistancias, camino);
        System.out.println("Distancia total: " + distanciaTotal);
    }
}