package DAY18;

import java.util.Scanner;

    public class TASK02 {
        public static void heapify(int arr[], int n, int i) {
            int largest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;


            if (left < n && arr[left] > arr[largest])
                largest = left;


            if (right < n && arr[right] > arr[largest])
                largest = right;


            if (largest != i) {
                int swap = arr[i];
                arr[i] = arr[largest];
                arr[largest] = swap;


                heapify(arr, n, largest);
            }
        }

        public static void heapSort(int arr[]) {
            int n = arr.length;

            // Build heap (rearrange array)
            for (int i = n / 2 - 1; i >= 0; i--)
                heapify(arr, n, i);

            // One by one extract an element from heap
            for (int i = n - 1; i > 0; i--) {
                // Move current root to end
                int temp = arr[0];
                arr[0] = arr[i];
                arr[i] = temp;

                // Call max heapify on the reduced heap
                heapify(arr, i, 0);
            }
        }

        public static void printArray(int arr[]) {
            for (int i = 0; i < arr.length; i++)
                System.out.print(arr[i] + " ");
            System.out.println();
        }

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            // Input size of array
            System.out.print("Enter the number of elements: ");
            int n = scanner.nextInt();

            int[] arr = new int[n];

            // Input array elements
            System.out.println("Enter " + n + " elements:");
            for(int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }

            System.out.println("Original array:");
            printArray(arr);

            heapSort(arr);

            System.out.println("Sorted array:");
            printArray(arr);

            scanner.close();
        }
    }


