package divide_and_conquer.exercises;

public class MergeKSortedArray
{

    private static int[] merge(int[] A, int[] B) {
        int nA = A.length, nB = B.length;
        int[] C = new int[nA + nB];
        int i=0,j=0,k=0;
        while (i<nA && j<nB) {
            if (A[i] <= B[j]) {
                C[k] = A[i];
                i++;
            } else {
                C[k] = B[j];
                j++;
            }
            k++;
        }
        while (i<nA) {
            C[k] = A[i];
            i++;
            k++;
        }
        while (j<nB) {
            C[k] = B[j];
            j++;
            k++;
        }
        return C;
    }

    private static int[] mergeKSortedArray(int[][] k, int n) {
        int[] C = k[0];
        for (int i=1;i<n;i++) {
            int[] B = k[i];
            C = merge(C,B);
        }
        return C;
    }

    public static void main(String[] args)
    {
        int[][] k = new int[][]{{1,2,3,4}, {7,8,9,10}, {5,6,11,12}};
        int[] C = mergeKSortedArray(k, k.length);
        for (int i=0;i<C.length;i++) {
            System.out.print(C[i] + " ");
        }
        System.out.println();
    }
}
