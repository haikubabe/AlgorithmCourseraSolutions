package graph_search.week3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Find the median of running streams of number
 */
public class Median
{
    static class MaxHeap {
        private int[] maxHeap;
        private int size;

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

        private void insert(int key) {
            int size = this.size - 1;
            this.size++;
            maxHeap[++size] = Integer.MIN_VALUE;
            increaseKey(size, key);
        }
    }

    static class MinHeap {
        private int[] minHeap;
        private int size;

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

        private int getMin() {
            return minHeap[0];
        }

        private int extractMin() {
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

        private void insert(int key) {
            int size = this.size - 1;
            this.size++;
            minHeap[++size] = Integer.MAX_VALUE;
            decreaseKey(size, key);
        }
    }

    public static void main(String[] args) throws IOException
    {
        MaxHeap maxHeap = new MaxHeap(10000);
        MinHeap minHeap = new MinHeap(10000);
        int median = 0, sumMedian = 0;
        BufferedReader br = new BufferedReader(new FileReader("graph_search/week3/input.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            int num = Integer.parseInt(line.split("\\s")[0]);
            if (num < median) {
                //insert into maxheap
                maxHeap.insert(num);
            } else {
                //insert into minheap
                minHeap.insert(num);
            }
            // check if the size difference between two heaps are more than 1
            int maxHeapSize = maxHeap.size;
            int minHeapSize = minHeap.size;
            int diff = (maxHeap.size > minHeap.size) ? maxHeap.size - minHeap.size : minHeap.size - maxHeap.size;
            while (diff > 1) {
                if (maxHeapSize > minHeapSize) {
                    int max = maxHeap.extractMax();
                    minHeap.insert(max);
                } else {
                    int min = minHeap.extractMin();
                    maxHeap.insert(min);
                }
                diff = (maxHeap.size > minHeap.size) ? maxHeap.size - minHeap.size : minHeap.size - maxHeap.size;
            }

            if (maxHeap.size == minHeap.size || maxHeap.size > minHeap.size) {
                median = maxHeap.getMax();
            } else {
                median = minHeap.getMin();
            }
            sumMedian += median;
        }
        System.out.println(sumMedian%10000);
    }
}
