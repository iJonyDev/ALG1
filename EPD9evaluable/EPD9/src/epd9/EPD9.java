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
    private static List<Integer> divideAndConquerTSP(double[][] distanceMatrix) {
        int n = distanceMatrix.length;
        List<Integer> tour = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tour.add(i);
        }
        return divideAndConquer(distanceMatrix, tour);
    }

    private static List<Integer> divideAndConquer(double[][] distanceMatrix, List<Integer> tour) {
        if (tour.size() <= 3) {
            return tour;
        }

        int mid = tour.size() / 2;
        List<Integer> leftTour = divideAndConquer(distanceMatrix, tour.subList(0, mid));
        List<Integer> rightTour = divideAndConquer(distanceMatrix, tour.subList(mid, tour.size()));

        return mergeTours(distanceMatrix, leftTour, rightTour);
    }

    private static List<Integer> mergeTours(double[][] distanceMatrix, List<Integer> leftTour, List<Integer> rightTour) {
        List<Integer> mergedTour = new ArrayList<>(leftTour);
        mergedTour.addAll(rightTour);
        return mergedTour;
    }

    public static void main(String[] args) {
        String filePath = "src/data/vm1748.tsp";
        List<double[]> cities = instanceLoader(filePath);

        // Calcular la matriz de distancias
        double[][] distanceMatrix = calculateDistanceMatrix(cities);


        // Resolver TSP usando Algoritmo Voraz
        List<Integer> greedyTour = greedyTSP(distanceMatrix);
        System.out.println("Greedy TSP Tour: " + greedyTour);

        // Resolver TSP usando Divide y Vencerás
        List<Integer> divideAndConquerTour = divideAndConquerTSP(distanceMatrix);
        System.out.println("Divide and Conquer TSP Tour: " + divideAndConquerTour);
    }
}
