package divide_and_conquer.week3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Quicksort with pivot node as the median of three elements
 */
public class QuickSortPivotMedian
{
    private static int totalComparisons = 0;

    private static int median(int a, int b, int c) {
        if (a>b) {
            if (b>c) {
                return b;
            } else {
                return Math.min(a, c);
            }
        } else {
            if (b<c) {
                return b;
            } else {
                return Math.max(a, c);
            }
        }
    }

    private static int findPivot(int[] A, int l, int r) {
        int first = A[l];
        int last = A[r];
        int midIdx = l + (r-l)/2;
        int middle = A[midIdx];
        return median(first, last, middle);
    }

    private static int findPivotIndex(int[] A, int l, int r, int pivot) {
        int pivotIdx = -1;
        for (int i=l; i<=r; i++) {
            if (A[i] == pivot) {
               pivotIdx = i;
               break;
            }
        }
        return pivotIdx;
    }

    /**
     * returns the index of the pivot after partition
     * @param A
     * @param l
     * @param r
     * @return
     */
    private static int partition(int[] A, int l, int r) {
        int pivot = findPivot(A, l, r);
        int pivotIdx = findPivotIndex(A, l, r, pivot);
        //swap A[l] and A[pivotIdx]
        if (l != pivotIdx) {
            int temp = A[l];
            A[l] = A[pivotIdx];
            A[pivotIdx] = temp;
        }
        int i = l+1;
        for (int j=l+1;j<=r;j++) {
            if (A[j] < pivot) {   //if A[j] > pivot, do nothing because anyway j will increment
                //swap A[i] and A[j]
                if (i != j) {
                    int t = A[i];
                    A[i] = A[j];
                    A[j] = t;
                }
                i++;
            }
        }
        //swap A[i-1] and pivot
        int t = A[i-1];
        A[i-1] = pivot;
        A[l] = t;
        return i-1;
    }

    private static void quickSort(int[] A, int l, int r) {
        if (l<r) {
            int i = partition(A, l, r);
            totalComparisons += r-l;
            quickSort(A, l, i-1);
            quickSort(A, i+1, r);
        }
    }

    public static void main(String[] args)
            throws IOException
    {
        /*int[] A = {3,8,2,5,1,4,7,6};
        quickSort(A, 0, A.length-1);
        for (int i=0;i<A.length;i++) {
            System.out.print(A[i] + " ");
        }
        System.out.println();
        System.out.println("Total no. of comparisons : " + totalComparisons);*/

        int[] A = new int[10000];
        BufferedReader bufferedReader = new BufferedReader(new FileReader("divide_and_conquer/week3/input.txt"));
        String line;
        int i=0;
        while ((line = bufferedReader.readLine()) != null) {
            int n = Integer.parseInt(line);
            A[i++] = n;
        }
        quickSort(A, 0, A.length-1);
        System.out.println("Total no. of comparisons : " + totalComparisons);
    }
}
