package Experimento1;
public class LaberintoBackTraking {

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
        laberinto[p.getFila()][p.getColumna()] = 9;
    }

    void desmarcar(Posicion p, int[][] laberinto) {
        laberinto[p.getFila()][p.getColumna()] = 0;
    }

    boolean llegamos(Posicion ent, Posicion sal) {
        return (ent.getFila() == sal.getFila() && ent.getColumna() == sal.getColumna());
    }

    // Método recursivo que implementa el backtracking
    boolean laberinto_bt(int[][] laberinto, Posicion entrada, Posicion salida) {
        boolean exito = false;
        int m = 0;
        if (llegamos(entrada, salida)) // Caso base
            exito = true;
        else {  // Caso recursivo
            Posicion pos_actual = new Posicion(0, 0);   // Posición actual
            while (m < NMOV && !exito) {    // Recorrer los movimientos posibles
                marcar(entrada, laberinto); // Marcar la posición actual    
                pos_actual.setFila(entrada.getFila() + MOVIMIENTOS[m].getFila());   // Moverse
                pos_actual.setColumna(entrada.getColumna()      // a la nueva posición
                        + MOVIMIENTOS[m].getColumna());     // según el movimiento
                if (valida(pos_actual)  // Si la posición es válida
                        && !usada(pos_actual, laberinto)    // y no ha sido visitada
                        && !obstaculo(pos_actual, laberinto)) { // y no hay obstáculo
                    exito = laberinto_bt(laberinto, pos_actual, salida);    // Llamada recursiva
                    desmarcar(pos_actual, laberinto);   // Desmarcar la posición
                }
                m++;    // Siguiente movimiento
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
    }
}
