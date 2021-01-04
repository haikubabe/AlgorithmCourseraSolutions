package graph_search.week3;

/**
 * Implementation of Max Priority Queue using Max Heap
 */
public class MaxPriorityQueue
{
    private int[] maxHeap;
    private int size;
    private int index;

    public MaxPriorityQueue(int n) {
        this.maxHeap = new int[n];
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

    private int getMax() {
        return maxHeap[0];
    }

    private int extractMax() {
        int max = maxHeap[0];
        maxHeap[0] = maxHeap[size - 1];
        size--;
        maxHeapify(0);
        return max;
    }

    private void increaseKey(int key) {
        if (index == size) {
            System.out.println("Cannot insert any more elements. Size is full");
            return;
        }
        maxHeap[++index] = key;
        size++;
        int parentIndex = parent(index);
        int i = index;
        while (i>0 && maxHeap[i]>maxHeap[parentIndex]) {
            int temp = maxHeap[i];
            maxHeap[i] = maxHeap[parentIndex];
            maxHeap[parentIndex] = temp;
            i = parentIndex;
            parentIndex = parent(i);
        }
    }

    private void insert(int key) {
        increaseKey(key);
    }

    private void delete(int index) {
        if (index >= 0 && index < size) {
            int temp = maxHeap[index];
            maxHeap[index] = maxHeap[size-1];
            maxHeap[size-1] = temp;
            maxHeapify(index);
            size--;
        }
    }

    public static void main(String[] args)
    {
        MaxPriorityQueue queue = new MaxPriorityQueue(10);
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
            System.out.print(queue.maxHeap[i] + " ");
        }

        System.out.println();
        System.out.println("Extract Max");
        System.out.println(queue.extractMax());

        for (int i = 0;i<queue.size; i++) {
            System.out.print(queue.maxHeap[i] + " ");
        }
        System.out.println();
        queue.delete(1);
        for (int i = 0;i<queue.size; i++) {
            System.out.print(queue.maxHeap[i] + " ");
        }
    }
}
