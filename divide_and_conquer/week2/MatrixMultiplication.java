package divide_and_conquer.week2;

public class MatrixMultiplication
{
    public static void main(String[] args) {
        int[][] a = new int[][]{
                {1, 2, 3},
                {4, 5, 6}
        };
        int[][] b = new int[][] {
                {7, 8, 9, 10},
                {11, 12, 13, 14},
                {16, 17, 18, 19}
        };

        int n = a.length, m = a[0].length, p = b[0].length;
        int[][] c = new int[n][p];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {
                for (int k = 0; k < m; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {
                System.out.print(c[i][j] + " ");
            }
            System.out.println();
        }
    }
}
