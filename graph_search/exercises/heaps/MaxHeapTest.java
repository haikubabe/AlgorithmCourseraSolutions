package graph_search.exercises.heaps;

public class MaxHeapTest
{
    private static int leftChild(int i) {
        return 2*i+1;
    }

    private static int rightChild(int i) {
        return 2*i+2;
    }

    private static int parent(int i) {
        return Math.floorDiv(i-1,2);
    }

    /**
     * max heapify to maintain the max heap property
     * @param A
     * @param i
     */
    private static void maxHeapify(int[] A, int heapSize, int i) {
        // for leaf nodes
        if (i > heapSize/2) {
            return;
        }
        int left = leftChild(i);
        int right = rightChild(i);
        int largest = i;
        if (left <= heapSize && A[left] > A[i]) {
            largest = left;
        }
        if (right <= heapSize && A[right] > A[largest]) {
            largest = right;
        }

        if (largest != i) {
            int temp = A[i];
            A[i] = A[largest];
            A[largest] = temp;
            maxHeapify(A, heapSize, largest);
        }
    }

    /**
     * build max heap
     * @param A
     */
    private static void buildMaxHeap(int[] A, int heapSize) {
        for (int i=heapSize/2;i>=0;i--) {
            maxHeapify(A, heapSize, i);
        }
    }

    /**
     * heapsort algorithm
     * @param A
     * @param heapSize
     */
    private static void heapSort(int[] A, int heapSize) {
        buildMaxHeap(A, heapSize);
        for (int i=heapSize;i>0;i--) {
            int temp = A[0];
            A[0] = A[i];
            A[i] = temp;
            heapSize--;
            maxHeapify(A, heapSize, 0);
        }
    }

    private static int getMax(int[] A) {
        return A[0];
    }

    private static int extractMax(int[] A, int heapSize) {
        int max = A[0];
        A[0] = A[heapSize];
        heapSize--;
        maxHeapify(A, heapSize, 0);
        return max;
    }

    private static void increaseKey(int[] A, int i, int key) {
        if (key < A[i]) {
            System.out.println("key cannot be less than A[i]");
            return;
        }
        A[i] = key;
        int parentIndex = parent(i);
        while (i>0 && A[i]>A[parentIndex]) {
            int temp = A[i];
            A[i] = A[parentIndex];
            A[parentIndex] = temp;
            i = parentIndex;
            parentIndex = parent(i);
        }
    }

    private static void insert(int[] A, int heapSize, int key) {
        heapSize++;
        A[heapSize] = Integer.MIN_VALUE;
        increaseKey(A, heapSize, key);
    }

    public static void main(String[] args)
    {
        int[] A = {16,4,10,14,7,9,3,2,8,1};
        maxHeapify(A, A.length-1, 1);
        System.out.println("After Max Heapify");
        for (int i=0;i<A.length;i++) {
            System.out.print(A[i] + " ");
        }

        System.out.println();
        int[] arr = {5,3,17,10,84,19,6,22,9};
        System.out.println("Build Max Heap");
        buildMaxHeap(arr, arr.length-1);
        for (int i=0;i<arr.length;i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println();
        int[] B = {5,13,2,25,7,17,20,8,4};
        System.out.println("Heap sort");
        heapSort(B, B.length-1);
        for (int i=0;i<B.length;i++) {
            System.out.print(B[i] + " ");
        }

        System.out.println();
        System.out.println("Get max");
        System.out.println(getMax(arr));
        System.out.println("Extract max");
        System.out.println(extractMax(arr, arr.length-1));
        System.out.println("After extracting max");
        for (int i=0;i<arr.length;i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println();
        System.out.println("Increase key");
        int[] C = {16,14,10,8,7,9,3,2,4,1};
        increaseKey(C, 8, 15);
        for (int i=0;i<C.length;i++) {
            System.out.print(C[i] + " ");
        }

        System.out.println();
        System.out.println("Insert a key");
        int[] D = {15,13,9,5,12,8,7,4,0,6,2,1};
        /*insert(D,D.length-1,10);
        for (int i=0;i<D.length;i++) {
            System.out.print(D[i] + " ");
        }*/
    }
}
