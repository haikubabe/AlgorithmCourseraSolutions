package divide_and_conquer.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StrassenMultiplication {
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

    private static int[][] subtractMatrix(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }

    private static int[][] strassen(int[][] X, int[][] Y, int n) {
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

            //calculate 10 matrices from the four submatrices formed above
            int[][] S1 = subtractMatrix(F, H);
            int[][] S2 = addMatrix(A, B);
            int[][] S3 = addMatrix(C, D);
            int[][] S4 = subtractMatrix(G, E);
            int[][] S5 = addMatrix(A, D);
            int[][] S6 = addMatrix(E, H);
            int[][] S7 = subtractMatrix(B, D);
            int[][] S8 = addMatrix(G, H);
            int[][] S9 = subtractMatrix(A, C);
            int[][] S10 = addMatrix(E, F);

            // calculate 7 recursive calls
            int[][] P1 = strassen(A, S1, n / 2);
            int[][] P2 = strassen(S2, H, n / 2);
            int[][] P3 = strassen(S3, E, n / 2);
            int[][] P4 = strassen(D, S4, n / 2);
            int[][] P5 = strassen(S5, S6, n / 2);
            int[][] P6 = strassen(S7, S8, n / 2);
            int[][] P7 = strassen(S9, S10, n / 2);

            //calculate Z1, Z2, Z3 and Z4
            int[][] Z1 = addMatrix(subtractMatrix(addMatrix(P5, P4), P2), P6);
            int[][] Z2 = addMatrix(P1, P2);
            int[][] Z3 = addMatrix(P3, P4);
            int[][] Z4 = subtractMatrix(subtractMatrix(addMatrix(P1, P5), P3), P7);

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
        int[][] Z = strassen(X, Y, X.length);
        for (int i = 0; i < Z.length; i++) {
            for (int j = 0; j < Z[0].length; j++) {
                System.out.print(Z[i][j] + " ");
            }
            System.out.println();
        }
    }
}
