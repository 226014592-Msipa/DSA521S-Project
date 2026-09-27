public class PartB {

    static int comparisons;
    static int swaps;
    static int shifts;
    static int partitionStage;

    // array-sting
    static String arrayToString(int[] arr) {
        String s = "[";
        for (int i = 0; i < arr.length; i++) {
            s = s + arr[i];
            if (i < arr.length - 1) {
                s = s + ", ";
            }
        }
        return s + "]";
    }

    // part of array 
    static String part(int[] arr, int from, int to) {
        String s = "[";
        for (int i = from; i <= to; i++) {
            s = s + arr[i];
            if (i < to) {
                s = s + ", ";
            }
        }
        return s + "]";
    }

    // selection sort
    static void selectionSort(int[] arr) {
        comparisons = 0;
        swaps = 0;
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                swaps++;
            }
            if (i < 3) {
                System.out.println("After pass " + (i + 1) + ": " + arrayToString(arr));
            }
        }
    }

    // insertion sort
    static void insertionSort(int[] arr) {
        comparisons = 0;
        shifts = 0;
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0) {
                comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    shifts++;
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
            if (i <= 3) {
                System.out.println("After pass " + i + ": " + arrayToString(arr));
            }
        }
    }

    // Merge sort
    static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        System.out.println("Divide: " + part(arr, left, right)
                + " -> " + part(arr, left, mid) + " and " + part(arr, mid + 1, right));
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
        System.out.println("Merge: " + part(arr, left, right));
    }

    static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        for (int x = 0; x < temp.length; x++) {
            arr[left + x] = temp[x];
        }
    }

    //Quick sort
    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
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
        partitionStage++;
        if (partitionStage <= 2) {
            System.out.println("Stage " + partitionStage + ": pivot = " + pivot
                    + ", left = " + (i + 1 > low ? part(arr, low, i) : "[]")
                    + ", right = " + (i + 2 <= high ? part(arr, i + 2, high) : "[]")
                    + " -> array: " + arrayToString(arr));
        }
        return i + 1;
    }

    // Main program 
    public static void main(String[] args) {
        int[] original = {17, 5, 23, 8, 14, 3, 11, 20, 6, 9};
        int[] arr;

        System.out.println("Original list: " + arrayToString(original));

        System.out.println("\n Selection Sort ");
        arr = original.clone();
        selectionSort(arr);
        System.out.println("Sorted: " + arrayToString(arr));
        System.out.println("Comparisons: " + comparisons + ", Swaps: " + swaps);

        System.out.println("\n Insertion Sort ");
        arr = original.clone();
        insertionSort(arr);
        System.out.println("Sorted: " + arrayToString(arr));
        System.out.println("Comparisons: " + comparisons + ", Shifts: " + shifts);

        System.out.println("\n  Merge Sort");
        arr = original.clone();
        mergeSort(arr, 0, arr.length - 1);
        System.out.println("Sorted: " + arrayToString(arr));

        System.out.println("\n Quick Sort ");
        arr = original.clone();
        partitionStage = 0;
        quickSort(arr, 0, arr.length - 1);
        System.out.println("Sorted: " + arrayToString(arr));
    }
}