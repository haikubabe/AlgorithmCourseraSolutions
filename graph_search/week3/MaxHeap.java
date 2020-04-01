package graph_search.week3;

/**
 * Max Heap
 */
public class MaxHeap
{
    private int[] maxHeap;
    public int size;

    public MaxHeap(int n) {
        maxHeap = new int[n];
        size = 0;
    }

    private int leftChild(int i) {
        return 2*i+1;
    }

    private int rightChild(int i) {
        return 2*i+2;
    }

    private int parent(int i) {
        return Math.floorDiv(i-1,2);
    }

    private void maxHeapify(int i) {
        // for non-leaf nodes
        while (i<= (size / 2) - 1) {
            int left = leftChild(i);
            int right = rightChild(i);
            int largest = i;
            if (left <= size - 1 && maxHeap[left] > maxHeap[i]) {
                largest = left;
            }
            if (right <= size - 1 && maxHeap[right] > maxHeap[largest]) {
                largest = right;
            }

            if (largest == i) {
                return;
            }
            int temp = maxHeap[i];
            maxHeap[i] = maxHeap[largest];
            maxHeap[largest] = temp;
            i = largest;
        }
    }

    public int getMax() {
        return maxHeap[0];
    }

    public int extractMax() {
        int max = maxHeap[0];
        maxHeap[0] = maxHeap[size - 1];
        size--;
        maxHeapify(0);
        return max;
    }

    private void increaseKey(int i, int key) {
        if (key < maxHeap[i]) {
            System.out.println("key cannot be less than A[i]");
            return;
        }
        maxHeap[i] = key;
        int parentIndex = parent(i);
        while (i>0 && maxHeap[i]>maxHeap[parentIndex]) {
            int temp = maxHeap[i];
            maxHeap[i] = maxHeap[parentIndex];
            maxHeap[parentIndex] = temp;
            i = parentIndex;
            parentIndex = parent(i);
        }
    }

    public void insert(int key) {
        int size = this.size - 1;
        this.size++;
        maxHeap[++size] = Integer.MIN_VALUE;
        increaseKey(size, key);
    }

    public static void main(String[] args)
    {
        MaxHeap heap = new MaxHeap(10);
        heap.insert(7);
        heap.insert(3);
        heap.insert(9);
        heap.insert(6);
        heap.insert(4);
        heap.insert(8);
        heap.insert(1);
        heap.insert(2);
        heap.insert(10);
        heap.insert(5);

        for (int i = 0;i<heap.size; i++) {
            System.out.print(heap.maxHeap[i] + " ");
        }

        System.out.println();
        System.out.println("Extract Max");
        System.out.println(heap.extractMax());

        for (int i = 0;i<heap.size; i++) {
            System.out.print(heap.maxHeap[i] + " ");
        }
    }
}
