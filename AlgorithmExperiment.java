import java.util.Arrays;
import java.util.Random;

public class AlgorithmExperiment {

    public static long comparisonCount = 0;

    public static void main(String[] args) {
        int[] inputSizes = {20, 50, 100, 500};

        System.out.println("=========================================================================================");
        System.out.printf("%-18s | %-12s | %-22s | %-20s\n", "Algorithm", "Input Size", "Number of Comparisons", "Execution Time (ns)");
        System.out.println("=========================================================================================");

        for (int size : inputSizes) {
            // 1. Generate single original random array
            int[] original = generateRandomArray(size);

            // 2. Selection Sort
            int[] arrSelection = Arrays.copyOf(original, original.length);
            comparisonCount = 0;
            long startTime = System.nanoTime();
            selectionSort(arrSelection);
            long endTime = System.nanoTime();
            long executionTime = endTime - startTime;
            printRow("Selection Sort", size, comparisonCount, executionTime);

            // 3. Insertion Sort
            int[] arrInsertion = Arrays.copyOf(original, original.length);
            comparisonCount = 0;
            startTime = System.nanoTime();
            insertionSort(arrInsertion);
            endTime = System.nanoTime();
            executionTime = endTime - startTime;
            printRow("Insertion Sort", size, comparisonCount, executionTime);

            // 4. Merge Sort
            int[] arrMerge = Arrays.copyOf(original, original.length);
            comparisonCount = 0;
            startTime = System.nanoTime();
            mergeSort(arrMerge, 0, arrMerge.length - 1);
            endTime = System.nanoTime();
            executionTime = endTime - startTime;
            printRow("Merge Sort", size, comparisonCount, executionTime);

            // 5. Quick Sort
            int[] arrQuick = Arrays.copyOf(original, original.length);
            comparisonCount = 0;
            startTime = System.nanoTime();
            quickSort(arrQuick, 0, arrQuick.length - 1);
            endTime = System.nanoTime();
            executionTime = endTime - startTime;
            printRow("Quick Sort", size, comparisonCount, executionTime);

            System.out.println("-----------------------------------------------------------------------------------------");
        }

        // ==========================================
        // ADDITIONAL TEST: Almost-Sorted (100 elements)
        // ==========================================
        System.out.println("\n=========================================================================================");
        System.out.println("ADDITIONAL TEST: Almost-sorted array (100 elements with 5 neighbor swaps)");
        System.out.println("=========================================================================================");
        System.out.printf("%-18s | %-12s | %-22s | %-20s\n", "Algorithm", "Input Size", "Number of Comparisons", "Execution Time (ns)");
        System.out.println("-----------------------------------------------------------------------------------------");

        int[] almostSorted = generateAlmostSortedArray(100, 5);

        // Selection Sort
        int[] asSel = Arrays.copyOf(almostSorted, almostSorted.length);
        comparisonCount = 0;
        long start = System.nanoTime();
        selectionSort(asSel);
        long execTime = System.nanoTime() - start;
        printRow("Selection Sort", 100, comparisonCount, execTime);

        // Insertion Sort
        int[] asIns = Arrays.copyOf(almostSorted, almostSorted.length);
        comparisonCount = 0;
        start = System.nanoTime();
        insertionSort(asIns);
        execTime = System.nanoTime() - start;
        printRow("Insertion Sort", 100, comparisonCount, execTime);

        // Merge Sort
        int[] asMrg = Arrays.copyOf(almostSorted, almostSorted.length);
        comparisonCount = 0;
        start = System.nanoTime();
        mergeSort(asMrg, 0, asMrg.length - 1);
        execTime = System.nanoTime() - start;
        printRow("Merge Sort", 100, comparisonCount, execTime);

        // Quick Sort
        int[] asQk = Arrays.copyOf(almostSorted, almostSorted.length);
        comparisonCount = 0;
        start = System.nanoTime();
        quickSort(asQk, 0, asQk.length - 1);
        execTime = System.nanoTime() - start;
        printRow("Quick Sort", 100, comparisonCount, execTime);

        System.out.println("=========================================================================================");
    }

    // Helper: Formatted console output
    private static void printRow(String algorithm, int size, long comparisons, long time) {
        System.out.printf("%-18s | %-12d | %-22d | %-20d\n", algorithm, size, comparisons, time);
    }

    // Array Generators
    private static int[] generateRandomArray(int size) {
        Random rand = new Random(42); 
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(1000);
        }
        return arr;
    }

    private static int[] generateAlmostSortedArray(int size, int numberOfSwaps) {
        int[] arr = generateRandomArray(size);
        mergeSort(arr, 0, arr.length - 1); // Use our own merge sort to sort ascending

        // Swap 5 pairs of neighboring values
        for (int i = 0; i < numberOfSwaps; i++) {
            int idx = i * 15; 
            int temp = arr[idx];
            arr[idx] = arr[idx + 1];
            arr[idx + 1] = temp;
        }
        return arr;
    }

    //  SORTING ALGORITHMS 

    // 1. Selection Sort
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                comparisonCount++; // Data comparison
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }

    // 2. Insertion Sort
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0) {
                comparisonCount++; // Data comparison
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j = j - 1;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }
    }

    // 3. Merge Sort
    public static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i) L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j) R[j] = arr[m + 1 + j];

        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            comparisonCount++; // Data comparison
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // 4. Quick Sort
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            comparisonCount++; // Data comparison
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
}