package DAY16;

public class Sortingarray2 {
    public static void bubbleSort(int[] arr) {
        int n = arr.length;

        System.out.println("Original Array:");
        printArray(arr);


        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

            System.out.print("After Pass " + (i + 1) + ": ");
            printArray(arr);
        }
    }


    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }


    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        int[] arr = {64, 34, 25, 12, 22, 11, 90};


        bubbleSort(arr);


        System.out.println("\nArray is sorted: " + isSorted(arr));
    }
}

