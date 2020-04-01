package graph_search.week3;

/**
 * Min Heap
 */
public class MinHeap
{
    private int[] minHeap;
    public int size;

    public MinHeap(int n) {
        minHeap = new int[n];
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

    private void minHeapify(int i) {
        // for non-leaf nodes
        while (i<= (size / 2) - 1) {
            int left = leftChild(i);
            int right = rightChild(i);
            int smallest = i;
            if (left <= size - 1 && minHeap[left] < minHeap[i]) {
                smallest = left;
            }
            if (right <= size - 1 && minHeap[right] < minHeap[smallest]) {
                smallest = right;
            }

            if (smallest == i) {
                return;
            }
            int temp = minHeap[i];
            minHeap[i] = minHeap[smallest];
            minHeap[smallest] = temp;
            i = smallest;
        }
    }

    public int getMin() {
        return minHeap[0];
    }

    public int extractMin() {
        int min = minHeap[0];
        minHeap[0] = minHeap[size - 1];
        size--;
        minHeapify(0);
        return min;
    }

    private void decreaseKey(int i, int key) {
        if (key > minHeap[i]) {
            System.out.println("key cannot be greater than A[i]");
            return;
        }
        minHeap[i] = key;
        int parentIndex = parent(i);
        while (i>0 && minHeap[i]<minHeap[parentIndex]) {
            int temp = minHeap[i];
            minHeap[i] = minHeap[parentIndex];
            minHeap[parentIndex] = temp;
            i = parentIndex;
            parentIndex = parent(i);
        }
    }

    public void insert(int key) {
        int size = this.size - 1;
        this.size++;
        minHeap[++size] = Integer.MAX_VALUE;
        decreaseKey(size, key);
    }

    public static void main(String[] args)
    {
        MinHeap heap = new MinHeap(10);
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
            System.out.print(heap.minHeap[i] + " ");
        }

        System.out.println();
        System.out.println("Extract Min");
        System.out.println(heap.extractMin());

        for (int i = 0;i<heap.size; i++) {
            System.out.print(heap.minHeap[i] + " ");
        }
    }
}
