/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EPD1;

/**
 *
 * @author mgarciat
 */
public class Ejercicios {

    /**
     * Maximo de un vector mediate DyV
     *
     * @return
     */
    public static int problema2(int[] v, int first, int last) {
        int mx = -1;
        if (first == last) {
            mx = v[first];
        } else {
            int md = (int) ((first + last) / 2.);
            int mx1 = Ejercicios.problema2(v, first, md);
            int mx2 = Ejercicios.problema2(v, md + 1, last);

            mx = (mx1 > mx2) ? mx1 : mx2;
        }

        return mx;
    }

    public static int problema2b(int[] v, int first, int last) {
        int mx = v[first];
        if (first < last) {
            int md = (int) ((first + last) / 2.);
            int mx1 = Ejercicios.problema2(v, first, md);
            int mx2 = Ejercicios.problema2(v, md + 1, last);

            mx = (mx1 > mx2) ? mx1 : mx2;
        }

        return mx;
    }

    public static int problema2c(int[] v, int first, int len) {
        int mx = v[first];
        if (len > 1) {
            int imid = (int) len / 2;
            int mx1 = Ejercicios.problema2c(v, first, imid);
            int mx2 = Ejercicios.problema2c(v, first + imid, (int) Math.ceil(len / 2));

            mx = (mx1 > mx2) ? mx1 : mx2;
        }

        return mx;
    }

    /**
     * MCD maximo comun divisor
     *
     * @param a
     * @param b
     * @return
     */
    public static int problema3(int a, int b) {
        int m = -1;

        System.out.println("\t>>(a=" + a + ",b=" + b + ")");
        if (a == b) {
            m = a;
        } else if (a > b) {
            m = problema3(b, a - b);
        } else {
            m = problema3(b - a, a);
        }
        return m;
    }

    /**
     * MCD maximo comun divisor
     *
     * @param a
     * @param b
     * @return
     */
    public static int problema3b(int a, int b) {
        int m = -1;

        System.out.println("\t>(a=" + a + ",b=" + b + ")");

        if (a == 0) {
            m = b;
        } else if (b == 0) {
            m = a;
        } else if (a == b) {
            m = a;
        } else if (a > b) {
            m = problema3b(b, a % b);
        } else {
            m = problema3b(b % a, a);
        }
        return m;
    }

    /**
     * naive binomial coefficient calculation using factorial function
     * C(n,r) = n! / (r! * (n-r)!)
     * 
     * @param n
     * @param r
     * @return
     */
    public static long problema4(int n, int r) {
        return Experimentos.factorialRec(n) / (Experimentos.factorialRec(r) * Experimentos.factorialRec(n - r));// ).divide(Factorial(k).multiply(Factorial(n.subtract(k))));
    }

    /**
     * Matriz simetrica
     *
     * @param mtr
     * @return
     */
    public static boolean problema5(int[][] mtr, int i, int j, int nc, boolean symmetry) {
        boolean lsym = symmetry;
        if (symmetry) {
            if (i == j) {
                i--;
                j = nc - 1;
                lsym = problema5(mtr, i, j, nc, lsym);
            } else if (i >= 0) {
                lsym = (mtr[i][j] == mtr[j][i]);
                j--;
                lsym = problema5(mtr, i, j, nc, lsym);
            }
        }
        return lsym;
    }

    public static int[][] problema6(int[][] a, int[][] b) {
        int[][] c = new int[a.length][];
        for (int i = 0; i < c.length; i++) {
            c[i] = new int[a[i].length];
        }

        System.out.println("[" + (c.length - 1) + " [" + (c[c.length - 1].length - 1));

        sum(a, b, c, c.length - 1, c[c.length - 1].length - 1);

        return c;
    }

    private static void sum(int[][] a, int[][] b, int[][] c, int row, int col) {
        if (row == 0 && col == 0) {
            c[row][col] = a[row][col] + b[row][col];
        } else if (col == 0) {
            c[row][col] = a[row][col] + b[row][col];
            sum(a, b, c, row - 1, c[row - 1].length - 1);
        } else {
            c[row][col] = a[row][col] + b[row][col];
            sum(a, b, c, row, col - 1);
        }
    }

    public static int problema7(int m, int n) {
        return ackerman(m, n);
    }

    private static int ackerman(int m, int n) {
        int aux = -1;
        if (m == 0) {
            aux = n + 1;
        } else if ((n == 0) && (m > 0)) {
            aux = ackerman(n - 1, 1);
        } else if (m > 0 && n > 0) {
            aux = ackerman(m - 1, ackerman(m, n - 1));
        }
        return aux;
    }

    public static void main(String[] args) throws Exception {

        int a = 16 * 146;
        int b = 16 * 128;
        System.out.println(Ejercicios.problema3(a, b));

        System.out.println(Ejercicios.problema3b(a, b));
        /*
         * int[] v1 = new int[]{3, 1, 6, 2, 7, 4, 5, 8, 2, 3, 8, 9};
         * 
         * int mx1 = problema2b(v1, 0, v1.length - 1);
         * System.out.println("max: " + mx1);
         * 
         * int[] v2 = new int[]{3, 1, 9, 6, 2, 7, 4, 5, 8, 2, 3, 8};
         * 
         * int mx2 = problema2b(v1, 0, v1.length - 1);
         * System.out.println("max: " + mx2);
         * 
         * System.out.println(problema3(36, 14));
         * 
         * int[][] mtr1 = new int[][]{{1, 2, 3},
         * {2, 8, 4},
         * {3, 4, 5}};
         * 
         * int ncols = mtr1[0].length;
         * int lastRow = mtr1.length - 1;
         * boolean sym1 = problema5(mtr1, mtr1.length - 1, mtr1[lastRow].length - 1,
         * ncols, true);
         * System.out.println(">>>sym1: " + sym1);
         * 
         * int[][] a = new int[][]{{1, 2}, {1, 2, 3}, {1, 2}};
         * 
         * int[][] c = problema6(a,- a);
         * for (int i = 0; i < c.length; i++) {
         * for (int j = 0; j < c[i].length; j++) {
         * System.out.print("" + c[i][j] + ";");
         * }
         * System.out.println("");
         * }
         */
    }

}