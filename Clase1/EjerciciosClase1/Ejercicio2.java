package EjerciciosClase1;

// Algoritmos para la secuencia:
// a0=1, a1=2
// an=a(n-1)*a(n-2)
public class Ejercicio2 {

    // Fibonacci Recursivo
    int a(int n) {
        int r = 0;
        if (n >= 0) {
            r = (n <= 1) ? n + 1 : a(n - 1) * a(n - 2);
        }
        return r;
    }
    // Fibonacci Iterativo
    int b(int n) {
        int r = 0;
        if (n >= 0) {
            if (n <= 1)
                r = n + 1;
            else {
                int an = 2, an1 = 2, an2;
                for (int i = 3; i <= n; i++) {
                    an2 = an1;
                    an1 = an;
                    an = an1 * an2;
                }
                r = an;
            }
        }
        return r;
    }

}
