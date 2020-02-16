package divide_and_conquer.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Matrix multiplication of two n X n matrices by divide and conquer approach
public class MatrixMultiplicationRecursive {
    private static int[][] addMatrix(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    private static int[][] squareMatrixMultiplyRecursive(int[][] X, int[][] Y, int n) {
        int[][] Z = new int[n][n];
        if (n == 1)
            Z[0][0] = X[0][0] * Y[0][0];
        else {
            int[][] A = new int[n / 2][n / 2];
            int[][] B = new int[n / 2][n / 2];
            int[][] C = new int[n / 2][n / 2];
            int[][] D = new int[n / 2][n / 2];
            int[][] E = new int[n / 2][n / 2];
            int[][] F = new int[n / 2][n / 2];
            int[][] G = new int[n / 2][n / 2];
            int[][] H = new int[n / 2][n / 2];

            //partition the array X & Y into 4 submatrices each of size n/2
            for (int i = 0; i < n / 2; i++) {
                for (int j = 0; j < n / 2; j++) {
                    A[i][j] = X[i][j];
                    B[i][j] = X[i][j + n / 2];
                    C[i][j] = X[i + n / 2][j];
                    D[i][j] = X[i + n / 2][j + n / 2];

                    E[i][j] = Y[i][j];
                    F[i][j] = Y[i][j + n / 2];
                    G[i][j] = Y[i + n / 2][j];
                    H[i][j] = Y[i + n / 2][j + n / 2];
                }
            }
            // 8 recursive calls
            int[][] P1 = squareMatrixMultiplyRecursive(A, E, n / 2);
            int[][] P2 = squareMatrixMultiplyRecursive(B, G, n / 2);
            int[][] P3 = squareMatrixMultiplyRecursive(A, F, n / 2);
            int[][] P4 = squareMatrixMultiplyRecursive(B, H, n / 2);
            int[][] P5 = squareMatrixMultiplyRecursive(C, E, n / 2);
            int[][] P6 = squareMatrixMultiplyRecursive(D, G, n / 2);
            int[][] P7 = squareMatrixMultiplyRecursive(C, F, n / 2);
            int[][] P8 = squareMatrixMultiplyRecursive(D, H, n / 2);

            //calculate Z1, Z2, Z3 and Z4
            int[][] Z1 = addMatrix(P1, P2);
            int[][] Z2 = addMatrix(P3, P4);
            int[][] Z3 = addMatrix(P5, P6);
            int[][] Z4 = addMatrix(P7, P8);

            // 4 additions of (n^2)/4 times
            for (int i = 0; i < n / 2; i++) {
                for (int j = 0; j < n / 2; j++) {
                    Z[i][j] = Z1[i][j];
                    Z[i][j + n / 2] = Z2[i][j];
                    Z[i + n / 2][j] = Z3[i][j];
                    Z[i + n / 2][j + n / 2] = Z4[i][j];
                }
            }
        }
        return Z;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] line1 = bufferedReader.readLine().split("\\s+");
        int n = Integer.parseInt(line1[0]);
        int m = Integer.parseInt(line1[1]);
        int[][] X = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] line = bufferedReader.readLine().split("\\s+");
            for (int j = 0; j < m; j++) {
                X[i][j] = Integer.parseInt(line[j]);
            }
        }
        String[] line2 = bufferedReader.readLine().split("\\s+");
        int q = Integer.parseInt(line2[0]);
        int p = Integer.parseInt(line2[1]);
        int[][] Y = new int[q][p];
        for (int i = 0; i < q; i++) {
            String[] line = bufferedReader.readLine().split("\\s+");
            for (int j = 0; j < p; j++) {
                Y[i][j] = Integer.parseInt(line[j]);
            }
        }
        int[][] Z = squareMatrixMultiplyRecursive(X, Y, X.length);
        for (int i = 0; i < Z.length; i++) {
            for (int j = 0; j < Z[0].length; j++) {
                System.out.print(Z[i][j] + " ");
            }
            System.out.println();
        }
    }
}
