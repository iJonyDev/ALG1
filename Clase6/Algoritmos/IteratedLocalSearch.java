package Clase6.Algoritmos;

import java.util.Random;

public class IteratedLocalSearch {
    public static void main(String[] args) {
        double x = 10; // Valor inicial de x
        double stepSize = 0.1; // TamaÃ±o de paso
        int maxIterations = 100; // NÃºmero mÃ¡ximo de iteraciones
        int perturbationIterations = 10; // Iteraciones de perturbaciÃ³n

        for (int i = 0; i < maxIterations; i++) {
            x = localSearch(x, stepSize);

            // PerturbaciÃ³n: cambiar aleatoriamente la posiciÃ³n
            Random rand = new Random();
            x = x + (rand.nextDouble() - 0.5) * stepSize;

            System.out.println("IteraciÃ³n " + (i + 1) + ": x = " + x + ", f(x) = " + evaluateFunction(x));
        }

        System.out.println("SoluciÃ³n aproximada: x = " + x + ", f(x) = " + evaluateFunction(x));
    }

    // FunciÃ³n objetivo (puede reemplazarse por cualquier otra funciÃ³n)
    public static double evaluateFunction(double x) {
        return -x * x;
    }

    // BÃºsqueda local (Hill Climbing)
    public static double localSearch(double x, double stepSize) {
        double currentEvaluation = evaluateFunction(x);
        double newX = x + (Math.random() - 0.5) * stepSize; // Genera un nuevo punto cercano
        double newEvaluation = evaluateFunction(newX);

        if (newEvaluation > currentEvaluation) {
            return newX; // Mueve hacia la nueva posiciÃ³n si es mejor
        }

        return x; // Mantener la posiciÃ³n actual
    }
}