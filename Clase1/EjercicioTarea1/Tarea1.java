package EjercicioTarea1;

public class Tarea1 {
    public static void main(String args[]) {
        // Ejercicio 1
        funciones s = new funciones();
        int sum = s.sumaRecursiva(4);
        System.out.println(sum);

        // Ejercicio 2
        int[] array = {4,1,5,-1,10,3,-2};
        funciones m = new funciones();
        int menor = m.valMenor(array, 0);
        System.out.println(menor);

        // Ejercicio 3
        funciones sr = new funciones();
        int sumArr = sr.sumArr(array, array.length);
        System.out.println(sumArr);

        // Ejercicio 4
        String palabra = "reconocer";
        funciones p = new funciones();
        boolean esPalindroma = p.Palindroma(palabra);
        if (esPalindroma) {
            System.out.println("'" + palabra + "'" + " es un palíndromo");
        } else {
            System.out.println("'" + palabra + "'" + " no es un palíndromo");
        }

        // Ejercicio 5
        funciones c = new funciones();
        int[] a = {1, 3, 2, 3, 1, 1, 0,1};
        int x = 1;
        int cantidad = c.contarX(a, x, 0, 0);
        System.out.println("La cantidad de elementos que coinciden con " + x + " es: " + cantidad);

        // Ejercicio Extra 1
        funciones b = new funciones();
        int decimal=0;
        System.out.println("Número decimal: " + decimal);
        System.out.print("Equivalente binario: ");
        b.numBinario(decimal);

        // Ejercicio Extra 2

        funciones h = new funciones();
        h.hanoi(3, "Torre1", "Torre2", "Torre3");
    }
}
