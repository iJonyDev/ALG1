package EPD2;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author mgarciat
 */
public class Experimento01SumaMatrices {

    public static double[][] newMatrix(int nr, int nc, Random rnd) throws Exception {
        double[][] mtr = new double[nr][nc];

        for (int i = 0; i < nr; i++) {
            for (int j = 0; j < nc; j++) {
                mtr[i][j] = rnd.nextDouble();
            }
        }
        return mtr;
    }

    public static double[][] suma(double[][] m1, double[][] m2) {
        double[][] sum = new double[m1.length][];

        for (int i = 0; i < m1.length; i++) {
            sum[i] = new double[m1[i].length];
            for (int j = 0; j < m1[i].length; j++) {
                sum[i][j] = m1[i][j] + m2[i][j];
            }
        }
        return sum;
    }
    
    public static void runExperimentsIt(int[] n) throws Exception {
        Random rnd = new Random(123);

        double[] tIt = new double[n.length];

        for (int i = 0; i < n.length; i++) {
            int d = n[i];

            double[][] m1 = Experimento01SumaMatrices.newMatrix(d, d, rnd);
            double[][] m2 = Experimento01SumaMatrices.newMatrix(d, d, rnd);

            long start = System.nanoTime();
            //long start = System.currentTimeMillis();
            double[][] m12 = Experimento01SumaMatrices.suma(m1, m2);
            //long stop = System.currentTimeMillis();
            long stop = System.nanoTime();

            tIt[i] = ((double) (stop - start)) / 1000_000_000;
        }

        System.out.println("t(s) (iterativo): " + Arrays.toString(tIt));
    }
    
    public static void main(String[] args) throws Exception {
        int[] n = new int[] {500,1000,1100,800};
        
        Experimento01SumaMatrices.runExperimentsIt(n);
        
    }
}