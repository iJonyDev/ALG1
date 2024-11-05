
package EPD6;

/**
 *
 * @author Miguel Garcia Torres (mgarciat[at]upo[dot]es)
 */
public class KnapsackProblem {

    
    public static void experimentos() throws Exception {
        int[] B   = new int[]  {8, 5, 3, 2, 1};
        float[] P = new float[]{5, 1, 2, 1, 2};
        float[] S = new float[B.length];
        float capacidad = 7.0f;
        
        System.out.println("Ordenado en funcion del beneficio:\n\tBeneficio obtenido " + mochilaEntera(B, P, capacidad, S) + "");
	muestraVector(S, "\tproporciones");
        
        
        B   = new int[]  {8, 1, 3, 5, 2};
        P = new float[]{5, 2, 2, 1, 1};
        S = new float[B.length];
        
        
        System.out.println("Ordenado en funcion del peso: \n\tBeneficio obtenido " + mochilaEntera(B, P, capacidad, S) + "");
	muestraVector(S, "\tproporciones");
        
        
        
    }
    
    
    
    public static void main(String[] args) throws Exception {

        experimentos();

        /*int[] B = new int[]{11, 6, 6};
        float[] P = new float[]{5, 3, 2};
        float[] S = new float[B.length];
        float capacidad = 7.0f;
        
        System.out.println("Beneficio obtenido " + mochilaEntera(B, P, capacidad, S) + "");
	muestraVector(S, "proporciones");
        */
    }

    public static int mochila(int[] B, float[] P, float m, float[] S) { // Beneficio con objetos fraccionados
        int i;
        float pesoAc = 0.0f;
        int benef = 0;

        for (i = 0; i < B.length; i++) {    // Inicializamos el vector solucion
            S[i] = 0;
        }

        i = 0;
        while ((pesoAc < m) && (i < B.length)) {    // Mientras no se haya alcanzado la capacidad y no se hayan procesado todos los elementos
            if (P[i] + pesoAc <= m) {   // Si cabe el objeto i
                S[i] = 1.0f;    // Se mete entero
            } else {    // Si no cabe
                S[i] = (m - pesoAc) / P[i]; // Se mete una fraccion
            }
            pesoAc = pesoAc + (S[i] * P[i]);    // Se actualiza el peso acumulado
            benef += (S[i] * B[i]); // Se actualiza el beneficio
            i = i + 1;  // Se pasa al siguiente objeto
        }
        return (benef); // Se devuelve el beneficio obtenido
    }
    
    public static int mochilaEntera(int[] B, float[] P, float m, float[] S) {   // Beneficio con objetos enteros
        int i;
        float pesoAc = 0.0f;
        int benef = 0;

        for (i = 0; i < B.length; i++) {    // Inicializamos el vector solucion
            S[i] = 0;   // Se inicializa a 0
        }

        i = 0;
        while ((pesoAc < m) && (i < B.length)) {    // Mientras no se haya alcanzado la capacidad y no se hayan procesado todos los elementos
            if (P[i] + pesoAc <= m) {   // Si cabe el objeto i
                S[i] = 1.0f;            // Se mete entero
            }
            pesoAc = pesoAc + (S[i] * P[i]);    // Se actualiza el peso acumulado
            benef += (S[i] * B[i]);         // Se actualiza el beneficio
            i = i + 1;  // Se pasa al siguiente objeto
        }
        return (benef);     // Se devuelve el beneficio obtenido
    }

    public static void muestraVector(float[] v, String s) {
        int i;
        System.out.println("\nDatos vector " + s);
        for (i = 0; i < v.length; i++) {
            System.out.print(v[i] + " ");
        }
        System.out.println("");
    }

}
