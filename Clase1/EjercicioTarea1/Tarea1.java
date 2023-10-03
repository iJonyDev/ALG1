package EjercicioTarea1;

public class Tarea1 {
    public static void main(String args[]) {
        // Ejercicio 1
        funciones1 s = new funciones1();
        int sum = s.sumaRecursiva(4);
        System.out.println(sum);

        // Ejercicio 2
        int[] array = {4,1,5,-1,10,3,-2};
        funciones1 m = new funciones1();
        int menor = m.valMenor(array, 0);
        System.out.println(menor);

        // Ejercicio 3
        funciones1 sr = new funciones1();
        int sumArr = sr.sumArr(array, array.length);
        System.out.println(sumArr);

        // Ejercicio 4
        String palabra = "reconocer";
        funciones1 p = new funciones1();
        boolean esPalindroma = p.Palindroma(palabra);
        if (esPalindroma) {
            System.out.println("'" + palabra + "'" + " es un palíndromo");
        } else {
            System.out.println("'" + palabra + "'" + " no es un palíndromo");
        }

        // Ejercicio 5
        funciones1 c = new funciones1();
        int[] a = {1, 3, 2, 3, 1, 1, 0,1};
        int x = 1;
        int cantidad = c.contarX(a, x, 0, 0);
        System.out.println("La cantidad de elementos que coinciden con " + x + " es: " + cantidad);

        // Ejercicio Extra 1
        funciones1 b = new funciones1();
        int decimal=0;
        System.out.println("Número decimal: " + decimal);
        System.out.print("Equivalente binario: ");
        b.numBinario(decimal);

        // Ejercicio Extra 2

        funciones1 h = new funciones1();
        h.hanoi(3, "Torre1", "Torre2", "Torre3");

        // Ejercicio Extra 3
        funciones mul = new funciones();
        int multiplica =mul.multiplica(4, 4);
        System.out.println(multiplica);
    }
}
