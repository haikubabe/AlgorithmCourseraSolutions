package graph_search.week3;

/**
 * Implementation of Min Priority Queue using Min Heap
 */
public class MinPriorityQueue
{
    private int[] minHeap;
    private int size;
    private int index;

    public MinPriorityQueue(int n) {
        this.minHeap = new int[n];
        this.size = 0;
        this.index = -1;
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

    private void decreaseKey(int key) {
        if (index == size) {
            System.out.println("Cannot insert any more elements. Size is full");
            return;
        }
        minHeap[++index] = key;
        size++;
        int parentIndex = parent(index);
        int i = index;
        while (i>0 && minHeap[i]<minHeap[parentIndex]) {
            int temp = minHeap[i];
            minHeap[i] = minHeap[parentIndex];
            minHeap[parentIndex] = temp;
            i = parentIndex;
            parentIndex = parent(i);
        }
    }

    public void insert(int key) {
        decreaseKey(key);
    }

    public static void main(String[] args)
    {
        MinPriorityQueue queue = new MinPriorityQueue(10);
        queue.insert(7);
        queue.insert(3);
        queue.insert(9);
        queue.insert(6);
        queue.insert(4);
        queue.insert(8);
        queue.insert(1);
        queue.insert(2);
        queue.insert(10);
        queue.insert(5);

        for (int i = 0;i<queue.size; i++) {
            System.out.print(queue.minHeap[i] + " ");
        }

        System.out.println();
        System.out.println("Extract Min");
        System.out.println(queue.extractMin());

        for (int i = 0;i<queue.size; i++) {
            System.out.print(queue.minHeap[i] + " ");
        }
    }
}
