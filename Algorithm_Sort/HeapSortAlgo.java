package Algorithm_Sort;

import java.util.Arrays;

public class HeapSortAlgo {

    public static HeapSortAlgo heapSortAlgo = new HeapSortAlgo();

    public static void main(String[] args) {
        int[] array = new int[]{4, 10, 3, 5, 1, 2, 11};
        int heapSize = array.length;
        for (int i = heapSize / 2 - 1; i >= 0; i--) {
            heapSortAlgo.maxHeaping(array, heapSize, i);
        }
        heapSortAlgo.sortHeaping(array);
    }

    private void sortHeaping(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            System.out.println(Arrays.toString(array));
            int maxNum = array[0];
            array[0] = array[i];
            array[i] = maxNum;
            new HeapSortAlgo().maxHeaping(array, i, 0);
        }
    }

    private void maxHeaping(int[] array, int heapSize, int parentNode) {
        System.out.println(Arrays.toString(array) + "Before: " + parentNode);
        int largest = parentNode;
        int leftChildNode = 2 * parentNode + 1;
        int rightChildNode = 2 * parentNode + 2;

        if (leftChildNode < heapSize && array[leftChildNode] > array[largest]) {
            largest = leftChildNode;
        }

        if (rightChildNode < heapSize && array[rightChildNode] > array[largest]) {
            largest = rightChildNode;
        }

        if (largest != parentNode) {
            int swap = array[parentNode];
            array[parentNode] = array[largest];
            array[largest] = swap;
            maxHeaping(array, heapSize, largest);
        }
    }
}