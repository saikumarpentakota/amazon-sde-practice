package DAY16;

public class Insertionsorting {
    public static void insertionSort(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;



            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;


            System.out.print("Pass " + i + ": ");
            printArray(arr);
        }
    }

    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22};

        System.out.println("Original array:");
        printArray(arr);

        System.out.println("\nSorting process:");
        insertionSort(arr);

        System.out.println("\nSorted array:");
        printArray(arr);
    }
}

