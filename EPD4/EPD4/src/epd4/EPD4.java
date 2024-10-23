package epd4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jonathanquishpe
 */
public class EPD4 {

    public static List<double[]> instanceLoader(String filePath) {
        List<double[]> cities = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Ignorar las primeras 7 líneas (cabecera)
            for (int i = 0; i < 7; i++) {
                br.readLine();
            }

            // Leer todas las líneas restantes en una lista
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Ignorar líneas vacías
                String[] parts = line.trim().split("\\s+");
                if (parts.length >= 3) { // Verificar que hay al menos 3 partes
                    double x = Double.parseDouble(parts[1]);
                    double y = Double.parseDouble(parts[2]);
                    cities.add(new double[]{x, y});
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return cities;
    }

    private static double[][] calculateDistanceMatrix(List<double[]> cities) {
        double[][] distanceMatrix = new double[cities.size()][cities.size()];
        for (int i = 0; i < cities.size(); i++) {
            for (int j = 0; j < cities.size(); j++) {
                distanceMatrix[i][j] = calculateDistance(cities.get(i), cities.get(j));
            }
        }
        return distanceMatrix;
    }

    private static double calculateDistance(double[] city1, double[] city2) {
        double dx = city1[0] - city2[0];
        double dy = city1[1] - city2[1];
        return Math.sqrt(dx * dx + dy * dy);
    }

    private static void printDistanceMatrix(double[][] distanceMatrix) {
        for (int i = 0; i < distanceMatrix.length; i++) {
            for (int j = 0; j < distanceMatrix[i].length; j++) {
                System.out.printf("%.2f ", distanceMatrix[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String filePath = "/Users/jonathanquishpe/JoniDev/ALG1/EPD4/EPD4/src/data/berlin52.tsp";
        List<double[]> cities = instanceLoader(filePath);

        // Calcular la matriz de distancias
        double[][] distanceMatrix = calculateDistanceMatrix(cities);

        // Imprimir la matriz de distancias
        printDistanceMatrix(distanceMatrix);
    }
}
