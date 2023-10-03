package EjercicioTarea2;


public class funciones2 {
/*E1 Dado un número entero positivo, determinar si es primo*/
    // Ejercicio1
    public static boolean primo(int n,int c){
        // Caso base: si n es la unidad
        if (c <= 1){
            return true;
        }
        // Comprueba si n es multiplo de "c = n-1"
        else if (n % c == 0){
            return false;
        }
        else{
            return primo(n,c-1);
        }
    }
/*E2 Dado un número entero positivo, determinar su factorial*/
    // Ejercicio2
    public static int factorial(int n){
        if (n == 0){
            return 1;
        }
        else{
            return n * factorial(n-1);
        }
    }

/*E3 Dado un número entero positivo, determinar su raíz cuadrada*/    
    // Ejercicio3
    public static double raiz(int n){
        if (n==1){
            double result = (int) n;
            return result;
        }else{
            double result = Math.sqrt((double) n);
            return result;
        }
    }

    public static void main(String args[]){
        // Ejercicio1
        int n = 5;
        if (primo(n, n-1)){
            System.out.println(n + ": es primo");
        }else{
            System.out.println(n + ": no es primo");
        }

        // Ejercicio2
        System.out.println("Factorial de " + n + ": "+ factorial(n));

        // Ejercicio3
        System.out.println("Raiz de " + n + ": "+ raiz(n));

    }

}
