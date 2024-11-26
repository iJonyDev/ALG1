package Experimento1;
public class LaberintoBackTrakingTest_A {

    public static void main(String[] args) {
        LaberintoBackTraking laberintoSolver = new LaberintoBackTraking();

        // Laberinto 1: Camino directo sin obstáculos
        int[][] laberinto1 = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        LaberintoBackTraking.Posicion entrada1 = new LaberintoBackTraking.Posicion(0, 0);
        LaberintoBackTraking.Posicion salida1 = new LaberintoBackTraking.Posicion(4, 4);
        System.out.println("Laberinto 1:");
        laberintoSolver.imprimirLaberinto(laberinto1);
        boolean exito1 = laberintoSolver.laberinto_bt(laberinto1, entrada1, salida1);
        System.out.println("Resultado: " + (exito1 ? "Camino encontrado" : "Camino no encontrado"));
        laberintoSolver.imprimirLaberinto(laberinto1);

        // Laberinto 2: Con obstáculos
        int[][] laberinto2 = {
            {0, 0, 0, 1, 0},
            {0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0}
        };
        LaberintoBackTraking.Posicion entrada2 = new LaberintoBackTraking.Posicion(0, 0);
        LaberintoBackTraking.Posicion salida2 = new LaberintoBackTraking.Posicion(4, 4);
        System.out.println("Laberinto 2:");
        laberintoSolver.imprimirLaberinto(laberinto2);
        boolean exito2 = laberintoSolver.laberinto_bt(laberinto2, entrada2, salida2);
        System.out.println("Resultado: " + (exito2 ? "Camino encontrado" : "Camino no encontrado"));
        laberintoSolver.imprimirLaberinto(laberinto2);

        // Laberinto 3: Sin salida posible
        int[][] laberinto3 = {
            {0, 0, 0, 1, 0},
            {0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 1}
        };
        LaberintoBackTraking.Posicion entrada3 = new LaberintoBackTraking.Posicion(0, 0);
        LaberintoBackTraking.Posicion salida3 = new LaberintoBackTraking.Posicion(4, 4);
        System.out.println("Laberinto 3:");
        laberintoSolver.imprimirLaberinto(laberinto3);
        boolean exito3 = laberintoSolver.laberinto_bt(laberinto3, entrada3, salida3);
        System.out.println("Resultado: " + (exito3 ? "Camino encontrado" : "Camino no encontrado"));
        laberintoSolver.imprimirLaberinto(laberinto3);
    }
}