package epd4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
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
            for (int i = 0; i < 6; i++) {
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

    public static int[] randomSearch(double[][] distances, int n) {
        int[] bestSolution = new int[n];
        double bestCost = Double.MAX_VALUE;
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            int[] currentSolution = generateRandomSolution(n, random);
            double currentCost = calculateCost(currentSolution, distances);

            if (currentCost < bestCost) {
                bestCost = currentCost;
                bestSolution = currentSolution.clone();
            }
        }

        return bestSolution;
    }

    public static int[] generateRandomSolution(int n, Random random) {
        int[] solution = new int[n];
        for (int i = 0; i < n; i++) {
            solution[i] = i;
        }
        for (int i = 0; i < n; i++) {
            int j = random.nextInt(n);
            int temp = solution[i];
            solution[i] = solution[j];
            solution[j] = temp;
        }
        return solution;
    }

    public static double calculateCost(int[] solution, double[][] distances) {
        double cost = 0;
        for (int i = 0; i < solution.length - 1; i++) {
            cost += distances[solution[i]][solution[i + 1]];
        }
        cost += distances[solution[solution.length - 1]][solution[0]]; // Volver al inicio
        return cost;
    }

    private static void printDistanceMatrix(double[][] distanceMatrix) {
        for (int i = 0; i < distanceMatrix.length; i++) {
            for (int j = 0; j < distanceMatrix[i].length; j++) {
                System.out.printf("%.3f ", distanceMatrix[i][j]);
            }
            System.out.println();
        }
    }

    private static void printCities(List<double[]> cities) {
        System.out.printf("%-5s %-10s %-10s\n", "City", "X", "Y");
        int i = 1;
        for (double[] city : cities) {
            System.out.printf("%-5d %-10.2f %-10.2f\n", i, city[0], city[1]);
            i++;
        }
    }

    private static double runBA1() {
        String filePath = "/workspaces/ALG1/EPD4/EPD4/src/data/berlin52.tsp";
        List<double[]> cities = instanceLoader(filePath);
        double[][] distanceMatrix = calculateDistanceMatrix(cities);

        int n = distanceMatrix.length; // Número de ciudades
        double etime = 0;
        long start = System.nanoTime();
        int[] bestSolution = randomSearch(distanceMatrix, n);
        long stop = System.nanoTime();
        etime = ((double) (stop - start))/1_000_000_000;
        return etime;
    }

    

    public static void main(String[] args) {
        double etime = runBA1();
        System.out.println("Time: " + etime + " seconds");
        //String filePath = "/workspaces/ALG1/EPD4/EPD4/src/data/berlin52.tsp";
        //List<double[]> cities = instanceLoader(filePath);
        //double[][] distanceMatrix = calculateDistanceMatrix(cities);

       // int n = distanceMatrix.length; // Número de ciudades
       // int[] bestSolution = randomSearch(distanceMatrix, n);
        
        //System.out.println("Mejor solución encontrada:");
        //for (int city : bestSolution) {
        //    System.out.print(city + " ");
        //}
    }
}
