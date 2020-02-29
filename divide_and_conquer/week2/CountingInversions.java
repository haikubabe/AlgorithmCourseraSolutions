package divide_and_conquer.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CountingInversions {

    private static long merge_and_countSplitInv(int[] A, int p, int q, int r) {
        int nL = q - p + 1;
        int nR = r - q;
        int[] L = new int[nL];
        int[] R = new int[nR];

        for (int i=0;i<nL;i++) {
            L[i] = A[i+p];
        }
        for (int j=0;j<nR;j++) {
            R[j] = A[j+q+1];
        }

        int i=0,j=0,k=p;
        long count_inversions = 0;

        while (i<nL && j<nR) {
            if (L[i]<=R[j]) {
                A[k] = L[i];
                i++;
            } else {
                A[k] = R[j];
                j++;
                count_inversions += (nL - i);   //count the no. of inversions
            }
            k++;
        }
        while (i < nL) {
            A[k] = L[i];
            i++;
            k++;
        }
        while (j < nR) {
            A[k] = R[j];
            j++;
            k++;
        }
        return count_inversions;
    }

    private static long sort_and_count(int[] A, int p, int r) {
        if (p<r) {
            int q = (p+r)/2;
            long x = sort_and_count(A, p, q);    //left inversion
            long y = sort_and_count(A,q+1,r);    //right inversion
            long z = merge_and_countSplitInv(A,p,q,r);   //split inversion
            return x+y+z;
        }
        return 0;
    }

    public static void main(String[] args)
            throws IOException
    {
        /*int[] A = {1,3,5,2,4,6};
        int n = A.length;
        System.out.println(sort_and_count(A,0,n-1));*/
        int[] A = new int[100000];
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        int i=0;
        while ((line = bufferedReader.readLine()) != null) {
            int n = Integer.parseInt(line.split("\\s+")[0]);
            A[i] = n;
            i++;
        }
        System.out.println(sort_and_count(A, 0, A.length-1));
    }
}
