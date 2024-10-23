package EjerciciosClase1;

// Cálculo del factorial
public class Ejercicio1 {
    // Factorial recursivo
    int factorial_recurs(int n) {
        int r = 0;
        if (n == 0)
            r = 1;
        else if (n > 0)
            r = n * factorial_recurs(n - 1);
        return r;
    }
    // Factorial iterativo
    int factorial_iter(int n) {
        int i, r = 1;
        for (i = 0; i < n; i++) {
            r = r * (n - i);
        }

        return r;
    }
}
