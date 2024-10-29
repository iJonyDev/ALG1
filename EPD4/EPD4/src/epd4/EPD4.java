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
            while ((line = br.readLine()) != null) {    // Mientras haya líneas por leer
                if (line.trim().isEmpty()) continue; // Ignorar líneas vacías
                String[] parts = line.trim().split("\\s+"); // Separar por espacios
                if (parts.length >= 3) { // Verificar que hay al menos 3 partes
                    double x = Double.parseDouble(parts[1]);    // Convertir a double
                    double y = Double.parseDouble(parts[2]);    
                    cities.add(new double[]{x, y}); // Agregar a la lista de ciudades
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return cities;
    }
    // Calcular la matriz de distancias
    private static double[][] calculateDistanceMatrix(List<double[]> cities) {
        double[][] distanceMatrix = new double[cities.size()][cities.size()];   // Inicializar la matriz de distancias
        for (int i = 0; i < cities.size(); i++) {  
            for (int j = 0; j < cities.size(); j++) {  
                distanceMatrix[i][j] = calculateDistance(cities.get(i), cities.get(j)); // Calcular la distancia entre las ciudades i y j
            }
        }
        return distanceMatrix;  // Retornar la matriz de distancias
    }

    // Calcular la distancia entre dos ciudades
    private static double calculateDistance(double[] city1, double[] city2) {
        double dx = city1[0] - city2[0];
        double dy = city1[1] - city2[1];
        return Math.sqrt(dx * dx + dy * dy);    // Distancia euclidiana
    }

    public static int[] randomSearch(double[][] distances) {
        int[] bestSolution = new int[distances.length]; // Inicializar un vector que contendrá la mejor solución
        double bestCost = Double.MAX_VALUE; // Inicializar con un valor muy grande para asegurar que cualquier solución sea mejor
        Random random = new Random();

        for (int i = 0; i < distances.length; i++) { // Número de iteraciones para (soluciones aleatorias)
            int[] currentSolution = generateRandomSolution(distances.length, random);  // Generar una solución aleatoria
            double currentCost = calculateCost(currentSolution, distances); // Calcular el costo de la solución

            if (currentCost < bestCost) {   // Si la solución actual es mejor que la mejor solución encontrada
                bestCost = currentCost;    // Actualizar el mejor costo
                bestSolution = currentSolution.clone(); // Actualizar la mejor solución
            }
        }

        return bestSolution;    // Retornar la mejor solución encontrada
    }

    // Generar una solución aleatoria
    public static int[] generateRandomSolution(int n, Random random) {
        int[] solution = new int[n];    // Inicializar un arreglo de tamaño n (numero de ciudades)
        for (int i = 0; i < n; i++) {   // Llenar el arreglo con los índices de las ciudades
            solution[i] = i;
        }
        for (int i = 0; i < n; i++) {   // Mezclar las ciudades
            int j = random.nextInt(n);
            int temp = solution[i];
            solution[i] = solution[j];
            solution[j] = temp;
        }
        return solution;    // Retornar la solución generada
    }

    // Calcular el costo de una solución
    /* int[] solution : array que representa una posible solución al problema TSP
    Cada elemento del arreglo es un índice que representa una ciudad, y el orden
    de los elementos representa el orden en que se visitan las ciudades.
    */  
    public static double calculateCost(int[] solution, double[][] distances) {
        double cost = 0;
        for (int i = 0; i < solution.length - 1; i++) {     // Calcular el costo de la solución
            cost += distances[solution[i]][solution[i + 1]];    // Sumar la distancia entre la ciudad i y la ciudad i+1
        }
        cost += distances[solution[solution.length - 1]][solution[0]];  // Sumar la distancia entre la última ciudad y la primera
        return cost;
    }

    private static void printDistanceMatrix(double[][] distanceMatrix) { // Imprimir la matriz de distancias
        for (int i = 0; i < distanceMatrix.length; i++) {
            for (int j = 0; j < distanceMatrix[i].length; j++) {
                System.out.printf("%.3f ", distanceMatrix[i][j]);
            }
            System.out.println();
        }
    }

    private static void printCities(List<double[]> cities) {    // Imprimir las ciudades
        System.out.printf("%-5s %-10s %-10s\n", "City", "X", "Y");
        int i = 1;
        for (double[] city : cities) {
            System.out.printf("%-5d %-10.2f %-10.2f\n", i, city[0], city[1]);
            i++;
        }
    }

    private static Object[] runBA1() { // Método que ejecuta el algoritmo BA1
        String filePath = "/workspaces/ALG1/EPD4/EPD4/src/data/berlin52.tsp"; // Ruta del archivo de la instancia
        List<double[]> cities = instanceLoader(filePath); // Cargar las ciudades
        double[][] distanceMatrix = calculateDistanceMatrix(cities);    // Calcular la matriz de distancias

        double etime = 0;   // Inicializar el tiempo de ejecución
        long start = System.nanoTime(); // Iniciar el cronómetro
        int[] bestSolution = randomSearch(distanceMatrix);  // Ejecutar el algoritmo BA1
        long stop = System.nanoTime();  // Detener el cronómetro
        etime = ((double) (stop - start))/1_000_000_000;    // Calcular el tiempo de ejecución en segundos
        return new Object[]{etime, bestSolution};   // Retornar el tiempo de ejecución y la mejor solución encontrada
    }

    

    public static void main(String[] args) {
        Object[] result = runBA1();
        double etime = (double) result[0];
        int[] bestSolution = (int[]) result[1];
        System.out.println("Tiempo de ejecución: " + etime + " segundos");
        System.out.println("Mejor solución encontrada:");
        for (int city : bestSolution) {
            System.out.print(city + " ");
        }
    }
}
