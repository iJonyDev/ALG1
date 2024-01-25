package Clase6.Algoritmos;

import java.util.Random;

public class HillClimbing {
    public static void main(String[] args) {
        double x = 10; // Valor inicial de x
        double stepSize = 0.1; // TamaÃ±o de paso
        int maxIterations = 100; // NÃºmero mÃ¡ximo de iteraciones

        for (int i = 0; i < maxIterations; i++) {
            double currentEvaluation = evaluateFunction(x);
            double newX = x + (Math.random() - 0.5) * stepSize; // Genera un nuevo punto cercano

            double newEvaluation = evaluateFunction(newX);

            if (newEvaluation > currentEvaluation) {
                x = newX; // Mueve hacia la nueva posiciÃ³n si es mejor
            }

            System.out.println("IteraciÃ³n " + (i + 1) + ": x = " + x + ", f(x) = " + evaluateFunction(x));
        }

        System.out.println("SoluciÃ³n aproximada: x = " + x + ", f(x) = " + evaluateFunction(x));
    }

    // FunciÃ³n objetivo (puede reemplazarse por cualquier otra funciÃ³n)
    public static double evaluateFunction(double x) {
        return -x * x;
    }
}