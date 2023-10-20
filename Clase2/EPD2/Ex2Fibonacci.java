package EPD2;

import java.util.Arrays;

/**
 *
 * @author mgarciat
 */
public class Ex2Fibonacci {

    public static long fibonacciIt(int n) {
        long f1 = 0, f2 = 1, aux, naux = n;

        while (naux > 1) {
            aux = f2;
            f2 += f1;
            f1 = aux;
            naux--;
        }
        return f2;
    }

    public static void runExperimentIt(int[] n) {

        double[] t = new double[n.length];
        for (int i = 0; i < n.length; i++) {
            long start = System.nanoTime();
            long f = fibonacciIt(n[i]);
            long stop = System.nanoTime();

            t[i] = (stop - start) / 1000_000_000;
        }
        System.out.println("t[it]=" + Arrays.toString(t));
    }

    public static void runExperimentIt(int[] n, int it) {

        double[] t = new double[n.length];
        for (int i = 0; i < n.length; i++) {
            long start = System.nanoTime();
            for (int j = 0; j < it; j++) {
                long f = fibonacciIt(n[i]);
            }
            long stop = System.nanoTime();

            t[i] = (stop - start) / 1000_000_000 / ((double) it);
        }
        System.out.println("t[it]=" + Arrays.toString(t));
    }

    public static long fibonacci(int n) {
        return (n <= 1) ? n : fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void runExperimentRe(int[] n) {

        double[] t = new double[n.length];
        for (int i = 0; i < n.length; i++) {
            long start = System.nanoTime();
            long f = fibonacci(n[i]);
            long stop = System.nanoTime();

            t[i] = (stop - start) / 1000_000_000;
        }
        System.out.println("t[it]=" + Arrays.toString(t));
    }

    public static void main(String[] args) throws Exception {

        int[] n = new int[]{100, 200, 300, 400, 500};
        int it = 1000_000_0;
        runExperimentIt(n, it);
        
        int[] n2 = new int[]{40,45,46,47};
        runExperimentRe(n2);

    }

}
