package Experimento1;

public class LaberintoBackTraking_B {

    // Define the Posicion class
    static class Posicion {
        private int fila;
        private int columna;

        public Posicion(int fila, int columna) {
            this.fila = fila;
            this.columna = columna;
        }

        public int getFila() {
            return fila;
        }

        public void setFila(int fila) {
            this.fila = fila;
        }

        public int getColumna() {
            return columna;
        }

        public void setColumna(int columna) {
            this.columna = columna;
        }
    }

    private static final int TAM = 5;
    private static final int NMOV = 4;
    private static final Posicion[] MOVIMIENTOS = { new Posicion(-1, 0), new Posicion(1, 0), new Posicion(0, -1),
            new Posicion(0, 1) };

    // Métodos auxiliares
    boolean valida(Posicion p) {
        return (0 <= p.getFila()
                && p.getFila() < TAM
                && 0 <= p.getColumna()
                && p.getColumna() < TAM);
    }

    boolean usada(Posicion p, int[][] laberinto) {
        return (laberinto[p.getFila()][p.getColumna()] == 9);
    }

    boolean obstaculo(Posicion p, int[][] laberinto) {
        return (laberinto[p.getFila()][p.getColumna()] == 1);
    }

    void marcar(Posicion p, int[][] laberinto) {
        laberinto[p.getFila()][p.getColumna()] = 2; // Marcar el camino correcto con 2
    }

    void desmarcar(Posicion p, int[][] laberinto) {
        if (laberinto[p.getFila()][p.getColumna()] != 2) {
            laberinto[p.getFila()][p.getColumna()] = 0;
        }
    }

    boolean llegamos(Posicion ent, Posicion sal) {
        return (ent.getFila() == sal.getFila() && ent.getColumna() == sal.getColumna());
    }

    // Método recursivo que implementa el backtracking
    boolean laberinto_bt(int[][] laberinto, Posicion entrada, Posicion salida) {
        boolean exito = false;
        int m = 0;
        if (llegamos(entrada, salida)) { // Caso base: hemos llegado a la salida
            exito = true;
            marcar(entrada, laberinto); // Marcar la posición final
        } else {                      // Caso general: seguimos buscando
            Posicion pos_actual = new Posicion(0, 0);
            while (m < NMOV && !exito) {    // Recorrer los movimientos posibles
                marcar(entrada, laberinto); // Marcar la posición actual
                imprimirLaberinto(laberinto); // Imprimir el laberinto en cada paso
                pos_actual.setFila(entrada.getFila() + MOVIMIENTOS[m].getFila());
                pos_actual.setColumna(entrada.getColumna() + MOVIMIENTOS[m].getColumna());
                if (valida(pos_actual) && !usada(pos_actual, laberinto) && !obstaculo(pos_actual, laberinto)) {
                    exito = laberinto_bt(laberinto, pos_actual, salida);
                    if (!exito) {
                        desmarcar(pos_actual, laberinto);
                    }
                }
                m++;
            }
        }
        return exito;
    }

    // Método para imprimir el laberinto
    void imprimirLaberinto(int[][] laberinto) {
        for (int i = 0; i < TAM; i++) {
            for (int j = 0; j < TAM; j++) {
                System.out.print(laberinto[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
/*  Modificar el método marcar() para que marque el camino correcto con un valor diferente (por ejemplo, 2).
    Modificar el método desmarcar() para que no desmarque las posiciones que forman parte del camino correcto.
    Añadir una impresión del laberinto en cada paso para mostrar el camino recorrido.
*/
        LaberintoBackTraking_B laberintoSolver = new LaberintoBackTraking_B();

        // Laberinto de prueba
        int[][] laberinto = {
            {0, 0, 0, 1, 0},
            {0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0}
        };
        Posicion entrada = new Posicion(0, 0);
        Posicion salida = new Posicion(4, 4);
        System.out.println("Laberinto inicial:");
        laberintoSolver.imprimirLaberinto(laberinto);
        boolean exito = laberintoSolver.laberinto_bt(laberinto, entrada, salida);
        System.out.println("Resultado: " + (exito ? "Camino encontrado" : "Camino no encontrado"));
        System.out.println("Laberinto final:");
        laberintoSolver.imprimirLaberinto(laberinto);
    }
}
