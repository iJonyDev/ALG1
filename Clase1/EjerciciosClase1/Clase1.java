package EjerciciosClase1;
// Ejercicio 1: Algoritmo Iterativo vs Recursivo

public class Clase1 {

    
    public static void main(String args[]) {
        Ejercicio1 r = new Ejercicio1();
        Ejercicio2 f = new Ejercicio2();

        System.out.println("Factorial por Algoritmo recursivo");
        System.out.println(r.factorial_recurs(3));
        System.out.println("Factorial por Algoritmo iterativo");
        System.out.println(r.factorial_iter(3));

        System.out.println("Fibonacci por Algoritmo recursivo");
        System.out.println(f.a(3));
        System.out.println("Fibonacci por Algoritmo iterativo");
        System.out.println(f.b(3));

    }
}
