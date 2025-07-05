package DAY15;

public class HOMETASK3 {
    public static void main(String[] args) {
        int[] arr = {5, 10, 15, 20, 25, 30, 35};
        int target = 25;

        int result = searchArray(arr, target, 0);

        if (result != -1) {
            System.out.println("Element " + target + " found at index: " + result);
        } else {
            System.out.println("Element " + target + " not found in array");
        }
    }

    public static int searchArray(int[] arr, int target, int index) {
        // Base cases
        if (index >= arr.length) {
            return -1;  // Element not found
        }
        if (arr[index] == target) {
            return index;  // Element found
        }


        return searchArray(arr, target, index + 1);
    }
}

