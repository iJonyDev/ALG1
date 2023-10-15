package RepasoParcial1;

public class Ejemplo1 {
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {2, 1, 4},
            {3, 4, 1}
        };
        System.out.println(isSymmetric(matrix));
    }

    public static boolean isSymmetric(int[][] matrix) {
        int N = matrix.length;
        int i = 0;
        while ( i < N ) {
            int j = i + 1;
            while ( j < N) {
                if (matrix[i][j] != matrix[j][i]) {
                    return false;
                }
                 j++;
            }
            i++;
        }
        return true;
    }
}