package epd9;

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
public class EPD9 {

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

    private static void printCities(List<double[]> cities) {
        for (double[] city : cities) {
            System.out.printf("City: x=%.2f, y=%.2f%n", city[0], city[1]);
        }
    }

    // Algoritmo Voraz para TSP
    //Empieza en una ciudad y selecciona la ciudad más cercana no visitada.
    //Repite hasta que todas las ciudades hayan sido visitadas.
    private static List<Integer> greedyTSP(double[][] distanceMatrix) {
        int n = distanceMatrix.length;
        boolean[] visited = new boolean[n];
        List<Integer> tour = new ArrayList<>();
        int currentCity = 0;
        tour.add(currentCity);
        visited[currentCity] = true;

        for (int i = 1; i < n; i++) {
            int nextCity = -1;
            double minDistance = Double.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && distanceMatrix[currentCity][j] < minDistance) {
                    minDistance = distanceMatrix[currentCity][j];
                    nextCity = j;
                }
            }
            tour.add(nextCity);
            visited[nextCity] = true;
            currentCity = nextCity;
        }
        return tour;
    }

    // Algoritmo Divide y Vencerás para TSP
    // Divide la lista de ciudades en dos sublistas.
    // Resuelve el TSP para cada sublista recursivamente.
    // Combina las soluciones de las sublistas.
    public static List<Integer> divideAndConquerTSP(double[][] distanceMatrix) {
        int n = distanceMatrix.length;
        if (n <= 3) {
            // Si el número de ciudades es pequeño, resolver directamente usando el algoritmo voraz
            return greedyTSP(distanceMatrix);
        }

        // Dividir el conjunto de ciudades en dos subgrupos
        int mid = n / 2;
        double[][] subMatrix1 = new double[mid][mid];
        double[][] subMatrix2 = new double[n - mid][n - mid];

        for (int i = 0; i < mid; i++) {
            for (int j = 0; j < mid; j++) {
                subMatrix1[i][j] = distanceMatrix[i][j];
            }
        }

        for (int i = mid; i < n; i++) {
            for (int j = mid; j < n; j++) {
                subMatrix2[i - mid][j - mid] = distanceMatrix[i][j];
            }
        }

        // Resolver el TSP para cada subgrupo
        List<Integer> tour1 = divideAndConquerTSP(subMatrix1);
        List<Integer> tour2 = divideAndConquerTSP(subMatrix2);

        // Ajustar los índices de tour2
        for (int i = 0; i < tour2.size(); i++) {
            tour2.set(i, tour2.get(i) + mid);
        }

        // Combinar las soluciones de los subgrupos
        return combineTours(tour1, tour2, distanceMatrix);
    }

    public static List<Integer> combineTours(List<Integer> tour1, List<Integer> tour2, double[][] distanceMatrix) {
        List<Integer> combinedTour = new ArrayList<>(tour1);
        combinedTour.addAll(tour2);

        // Encontrar el mejor punto de conexión entre los dos tours
        double bestCost = Double.MAX_VALUE;
        int bestI = -1;
        int bestJ = -1;

        for (int i = 0; i < tour1.size(); i++) {
            for (int j = 0; j < tour2.size(); j++) {
                double cost = distanceMatrix[tour1.get(i)][tour2.get(j)] +
                              distanceMatrix[tour2.get(j)][tour1.get((i + 1) % tour1.size())] -
                              distanceMatrix[tour1.get(i)][tour1.get((i + 1) % tour1.size())];
                if (cost < bestCost) {
                    bestCost = cost;
                    bestI = i;
                    bestJ = j;
                }
            }
        }

        // Conectar los dos tours en el mejor punto de conexión
        List<Integer> mergedTour = new ArrayList<>();
        for (int i = 0; i <= bestI; i++) {
            mergedTour.add(tour1.get(i));
        }
        for (int j = bestJ; j < tour2.size(); j++) {
            mergedTour.add(tour2.get(j));
        }
        for (int j = 0; j <= bestJ; j++) {
            mergedTour.add(tour2.get(j));
        }
        for (int i = bestI + 1; i < tour1.size(); i++) {
            mergedTour.add(tour1.get(i));
        }

        return mergedTour;
    }

    private static List<Object[]> runGreedyTSP(String filePath) {
        List<double[]> cities = instanceLoader(filePath);
        double[][] distanceMatrix = calculateDistanceMatrix(cities);
        List<Object[]> results = new ArrayList<>();
        
            long start = System.nanoTime();
            List<Integer> greedyTour = greedyTSP(distanceMatrix);
            long stop = System.nanoTime();
            double etime = ((double) (stop - start)) / 1_000_000_000;
            results.add(new Object[]{etime, greedyTour});
        return results;
    }

    private static List<Object[]> runDivideAndConquerTSP(String filePath) {
        List<double[]> cities = instanceLoader(filePath);
        double[][] distanceMatrix = calculateDistanceMatrix(cities);
        List<Object[]> results = new ArrayList<>();
        
            long start = System.nanoTime();
            List<Integer> divideAndConquerTour = divideAndConquerTSP(distanceMatrix);
            long stop = System.nanoTime();
            double etime = ((double) (stop - start)) / 1_000_000_000;
            results.add(new Object[]{etime, divideAndConquerTour});
        return results;
    }

    private static void printResults(List<Object[]> results) {
        for (int i = 0; i < results.size(); i++) {
            double etime = (double) results.get(i)[0];
            List<Integer> tour = (List<Integer>) results.get(i)[1];
            System.out.println("Tiempo de ejecución: " + etime + " segundos");
            System.out.println("Mejor solución encontrada: " + tour);
        }
    }
    public static void main(String[] args) {
        String filePath = "/workspaces/ALG1/EPD9evaluable/EPD9/src/data/berlin52.tsp";

        // Ejecutar y medir el tiempo del algoritmo voraz
        List<Object[]> resultsList1 = runGreedyTSP(filePath);
        System.out.println("-----Algoritmo Voraz-----");
        printResults(resultsList1);

        // Ejecutar y medir el tiempo del algoritmo de divide y vencerás
        List<Object[]> resultsList2 = runDivideAndConquerTSP(filePath);
        System.out.println("-----Algoritmo Divide y Vencerás-----");
        printResults(resultsList2);
    }
}
