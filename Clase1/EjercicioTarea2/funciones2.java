package EjercicioTarea2;

public class funciones2 {
    /* E1 Dado un número entero positivo, determinar si es primo */
    // Ejercicio1
    public static boolean primo(int n, int c) {
        // Caso base: si n es la unidad no es primo
        if (n == 1)
            return false;
        if (c == 1) {
            return true;
        }
        // Comprueba si n es multiplo de "c = n-1"
        else if (n % c == 0) {
            return false;
        } else {
            return primo(n, c - 1);
        }
    }

    /* E2 Dado un número entero positivo, determinar su factorial */
    // Ejercicio2
    public static int factorial(int n) {
        // Caso base: si n es cero su factorial es 1
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    /* E3 Dado un número entero positivo, determinar su raíz cuadrada */
    // Ejercicio3
    public static double raiz(int n) {
        // Caso base: si n es 1 su raiz es 1
        if (n == 1) {
            double result = (int) n;
            return result;
        } else {
            double result = Math.sqrt((double) n);
            return result;
        }
    }

    /* E4 Dado un número entero positivo, determinar su descomposición en primos. */
    // Ejercicio4
    public static void descomponerPrimos(int n) {
        int divisor = 2;
        while (n > 1) {
            if (n % divisor == 0) {
                System.out.print(divisor + " ");
                n = n / divisor;
            } else {
                divisor++;
            }
        }
    }

    /* E5 Dado un número entero positivo, determinar el máximo común divisor de dos números. */
    // Ejercicio5
    public static int calcularMaxCD(int a, int b) {
        while (b != 0) {
            int aux = b;
            b = a % b;
            a = aux;
        }
        return a;
    }

    public static void main(String args[]) {
        // Ejercicio1
        int n = 10;
        if (primo(n, n - 1)) {
            System.out.println(n + ": es primo");
        } else {
            System.out.println(n + ": no es primo");
        }

        // Ejercicio2
        System.out.println("Factorial de " + n + ": " + factorial(n));

        // Ejercicio3
        System.out.println("Raiz de " + n + ": " + raiz(n));

        // Ejercicio4
        int numero = 50;
        System.out.print("Descomposición en factores primos de " + numero + ": ");
        System.out.println("");
        descomponerPrimos(numero);

        // Ejercicio5
        int num1 = 25;
        int num2 = 96;
        int mcd = calcularMaxCD(num1, num2);
        System.out.println("El máximo común divisor de " + num1 + " y " + num2 + " es: " + mcd);

    }

}
