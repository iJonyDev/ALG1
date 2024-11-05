package EPD6;

public class mochilaProblem {

    // Función para preparar la solución inicial
    public static void prepararSolucion(float[] S) {
        for (int i = 0; i < S.length; i++) {
            S[i] = 0;
        }
    }

    // Función para verificar si el conjunto de entrada está vacío
    public static boolean esVacio(int[] B, int index) {
        return index >= B.length;
    }

    // Función para seleccionar el siguiente candidato
    public static int seleccionarCandidato(int index) {
        return index;
    }

    // Función para verificar si el candidato es prometedor
    public static boolean esPrometedor(float pesoAc, float pesoCandidato, float m) {
        return (pesoAc + pesoCandidato) <= m;
    }

    // Función para incluir el candidato en la solución
    public static void incluir(int index, float[] S) {
        S[index] = 1.0f;
    }

    // Función para verificar si la solución es completa
    public static boolean esSolucion(float pesoAc, float m) {
        return pesoAc == m;
    }

    // Implementación del algoritmo voraz
    public static int algoritmoVoraz(int[] B, float[] P, float m, float[] S) {
        int i = 0;
        float pesoAc = 0.0f;
        int benef = 0;
        int enc = 0;

        prepararSolucion(S);    

        while (!esVacio(B, i) && (enc == 0)) {            
            int candidato = seleccionarCandidato(i);        
            if (esPrometedor(pesoAc, P[candidato], m)) {        
                incluir(candidato, S); 
                pesoAc += P[candidato];
                benef += B[candidato];
                if (esSolucion(pesoAc, m)) {
                    enc = 1;
                }
            }
            i++;
        }
        return benef;
    }

    public static void main(String[] args) {
        int[] B = {15, 20, 25};
        float[] P = {10, 18, 15};
        float m = 45;
        float[] S = new float[B.length];

        int maxBeneficio = algoritmoVoraz(B, P, m, S);
        System.out.println("Máximo beneficio: " + maxBeneficio);
        muestraVector(S, "Solución");
    }

    public static void muestraVector(float[] v, String s) {
        System.out.println("\nDatos vector " + s);
        for (float valor : v) {
            System.out.print(valor + " ");
        }
        System.out.println("");
    }
}
