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

    public static int[] randomSearch(double[][] distances) {    // Algoritmo de búsqueda aleatoria (BA1)
        int[] bestSolution = new int[distances.length]; // Vector que contendrá la mejor solución cuyo tamanio es igual al número de ciudades 
        double bestCost = Double.MAX_VALUE; // Inicializar con un valor muy grande para asegurar que cualquier solución sea mejor
        Random random = new Random();

        for (int i = 0; i < distances.length; i++) { // Número de soluciones aleatorias en funcion de la cantidad de ciudades
            int[] currentSolution = generateRandomSolution(distances.length, random);  // Generar una solución aleatoria
            double currentCost = calculateCost(currentSolution, distances); // Calcular el costo de la solución

            if (currentCost < bestCost) {   // Si la solución actual es mejor que la mejor solución encontrada
                bestCost = currentCost;    // Actualizar el mejor costo
                bestSolution = currentSolution.clone(); // Actualizar la mejor solución
            }
        }

        return bestSolution;    // Retornar la mejor solución encontrada
    }


    public static int[] randomSearch(double[][] distances, int n) {
        List<int[]> solutions = new ArrayList<>(); // Lista que contendrá las soluciones
        double bestCost = Double.MAX_VALUE; // Inicializar con un valor muy grande para asegurar que cualquier solución sea mejor
        Random random = new Random();

        for (int i = 0; i < n; i++) { // Número soluciones aleatorias fijadas por el usuario
            int[] currentSolution = generateRandomSolution(distances.length, random);  // Generar una solución aleatoria
            solutions.add(currentSolution); // Agregar la solución a la lista
        }

        int[] bestSolution = solutions.get(0);  // Inicializar la mejor solución con la primera solución generada
        for (int[] solution : solutions) {  // Recorrer todas las soluciones generadas
            double currentCost = calculateCost(solution, distances); // Calcular el costo de la solución
            if (currentCost < bestCost) {   // Si la solución actual es mejor que la mejor solución encontrada
                bestCost = currentCost;    // Actualizar el mejor costo
                bestSolution = solution.clone(); // Actualizar la mejor solución
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
    /* 
    int[] solution : array que representa una posible solución al problema TSP
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

    public static void printResults(List<Object[]> results, int[] n) { // Imprimir los resultados
        int i = 0;
        for (Object[] result : results) {
            double etime = (double) result[0];
            int[] bestSolution = (int[]) result[1];
            System.out.println("Tiempo de ejecución para "+ n[i] +" instacias : " + etime + " segundos");
            // System.out.println("Mejor solución encontrada:");
            // for (int city: bestSolution) {
            //     System.out.print(city + " ");
            // }
            System.out.println();
            i++;
        }

    }

    // Metodo auxiliar para imprimir la matriz de distancias(No usado)
    private static void printDistanceMatrix(double[][] distanceMatrix) { // Imprimir la matriz de distancias
        for (int i = 0; i < distanceMatrix.length; i++) {
            for (int j = 0; j < distanceMatrix[i].length; j++) {
                System.out.printf("%.3f ", distanceMatrix[i][j]);
            }
            System.out.println();
        }
    }

    // Metodo auxiliar para imprimir coordenadas extraidas de la instancia .tcp (No usado)
    private static void printCities(List<double[]> cities) {    // Imprimir las ciudades
        System.out.printf("%-5s %-10s %-10s\n", "City", "X", "Y");
        int i = 1;
        for (double[] city : cities) {
            System.out.printf("%-5d %-10.2f %-10.2f\n", i, city[0], city[1]);
            i++;
        }
    }

    private static List<Object[]> runBA1(int[] nInstance, String filePath) { // Método que ejecuta el algoritmo BA1
        List<double[]> cities = instanceLoader(filePath); // Cargar las ciudades
        double[][] distanceMatrix = calculateDistanceMatrix(cities);    // Calcular la matriz de distancias
        List<Object[]> results = new ArrayList<>(); // Lista que contendrá los resultados

        double etime = 0;   // Inicializar el tiempo de ejecución

        for (int i=0; i < nInstance.length; i++) {
            long start = System.nanoTime(); // Iniciar el cronómetro
            int[] bestSolution = randomSearch(distanceMatrix);  // Ejecutar el algoritmo BA1
            long stop = System.nanoTime();  // Detener el cronómetro
            etime = ((double) (stop - start))/1_000_000_000;    // Calcular el tiempo de ejecución en segundos
            results.add(new Object[]{etime, bestSolution});    // Agregar el tiempo de ejecución y la mejor solución encontrada a la lista
            etime = 0;  // Reiniciar el tiempo de ejecución
        }

        return results;   // Retornar el tiempo de ejecución y la mejor solución encontrada
    }

    private static List<Object[]> runBA2(int[] nInstance, String filePath) { // Método que ejecuta el algoritmo BA1
        List<double[]> cities = instanceLoader(filePath); // Cargar las ciudades
        double[][] distanceMatrix = calculateDistanceMatrix(cities);    // Calcular la matriz de distancias
        List<Object[]> results = new ArrayList<>(); // Lista que contendrá los resultados

        double etime = 0;   // Inicializar el tiempo de ejecución
        for (int i=0; i < nInstance.length; i++) {
            long start = System.nanoTime(); // Iniciar el cronómetro
            int n = 100;   // Número de soluciones aleatorias a generar
            int[] bestSolution = randomSearch(distanceMatrix, n);  // Ejecutar el algoritmo BA2
            long stop = System.nanoTime();  // Detener el cronómetro
            etime = ((double) (stop - start))/1_000_000_000;    // Calcular el tiempo de ejecución en segundos
            results.add(new Object[]{etime, bestSolution});    // Agregar el tiempo de ejecución y la mejor solución encontrada a la lista
            etime = 0;  // Reiniciar el tiempo de ejecución
        }
        return results;   // Retornar el tiempo de ejecución y la mejor solución encontrada
    }

    

    public static void main(String[] args) {
        String filePath = "src/data/vm1748.tsp"; // Ruta del archivo de la instancia
        int[] n = {100, 500, 1000, 5000};
        List<Object[]> resultsList1 = runBA1(n, filePath); // Ejecutar el algoritmo BA1
        System.out.println("-----Algoritmo BA1-----");
        printResults(resultsList1, n); // Imprimir los resultados

        System.out.println(" ");
        
        int[] p = {50, 100, 250, 500};
        List<Object[]> resultsList2 = runBA2(p, filePath); // Ejecutar el algoritmo BA1
        System.out.println("-----Algoritmo BA2-----");
        printResults(resultsList2, p); // Imprimir los resultados
 
    }
}
