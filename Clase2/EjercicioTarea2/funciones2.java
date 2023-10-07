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
    /*
     * E6 Dado un número entero positivo, determinar el minimo comun multiplo de dos
     * números.
     */
    // Ejercicio6

    public static int calcularMCM(int a, int b) {
        int mcd = calcularMaxCD(a, b);
        int mcm = (a * b) / mcd;
        return mcm;
    }

    /*
     * E5 Dado un número entero positivo, determinar el máximo común divisor de dos
     * números.
     */
    // Ejercicio5
    public static int calcularMaxCD(int a, int b) {
        while (b != 0) {
            int aux = b;
            b = a % b;
            a = aux;
        }
        return a;
    }

    /*
     * E7 Dado un número entero positivo, determinar si es divisible por otro
     * número.
     */
    // Ejercicio7
    public static boolean divisible(int a, int b) {
        boolean div = primo(a, b);
        return div;
    }

    /* E8 Dado un número entero positivo, determinar si es par o impar. */
    // Ejercicio8
    public static boolean esPar(int n) {
        if (n % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * E9 Dado un número entero positivo, determinar si es mayor, menor o igual que
     * otro número.
     */
    // Ejercicio9
    public static int comparaNum(int n, int m) {
        int result;
        if (n < m) {
            result = -1;
        } else if (n > m) {
            result = 1;
        } else {
            result = 0;
        }
        return result;
    }

    /*
     * E10 Dado un número entero positivo, determinar si es múltiplo de 5, 10 o 20.
     */
    // Ejercicio10
    public static int multiploDe(int n) {
        int result;
        if (n % 20 == 0) {
            result = 20;
        } else if (n % 10 == 0) {
            result = 10;
        } else if (n % 5 == 0) {
            result = 5;
        } else {
            result = 0;
        }
        return result;

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
        descomponerPrimos(numero);
        System.out.println("");

        // Ejercicio5
        int num1 = 152;
        int num2 = 180;
        int mcd = calcularMaxCD(num1, num2);
        System.out.println("El máximo común divisor de " + num1 + " y " + num2 + " es: " + mcd);

        // Ejercicio6
        int mcm = calcularMCM(num1, num2);
        System.out.println("El mínimo común multiplo de " + num1 + " y " + num2 + " es: " + mcm);

        // Ejercicio7
        if (divisible(n, n - 1)) {
            System.out.println(n + ": no es divisible");
        } else {
            System.out.println(n + ": es divisible por otro numero");
        }

        // Ejercicio8
        if (esPar(n)) {
            System.out.println(n + ": es par");
        } else {
            System.out.println(n + ": no es par");
        }

        // Ejercicio9
        if (comparaNum(num1, num2) == 1) {
            System.out.println(num1 + " es mayor que " + num2);
        } else if (comparaNum(num1, num2) == -1) {
            System.out.println(num1 + " es menor que " + num2);
        } else {
            System.out.println(num1 + " es igual que " + num2);
        }

        // Ejercicio10
        int multi = multiploDe(n);
        if (multi == 20) {
            System.out.println(n + " es multiplo de " + multi);
        } else if (multi == 10) {
            System.out.println(n + " es multiplo de " + multi);
        } else if (multi == 5) {
            System.out.println(n + " es multiplo de " + multi);
        } else {
            System.out.println(n + " no es multiplo de 5,10 ó 20");
        }

    }

}
