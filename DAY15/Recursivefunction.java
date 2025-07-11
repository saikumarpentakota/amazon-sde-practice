package DAY15;

public class Recursivefunction {
    public static int binarySearch(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;

        if (arr[mid] == target) {
            return mid;
        }


        if (arr[mid] > target) {
            return binarySearch(arr, target, left, mid - 1);
        }


        return binarySearch(arr, target, mid + 1, right);
    }


    public static int linearSearch(int[] arr, int target, int index) {

        if (index >= arr.length) {
            return -1;
        }

        if (arr[index] == target) {
            return index;
        }

        return linearSearch(arr, target, index + 1);
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15};
        int target = 7;


        int binaryResult = binarySearch(arr, target, 0, arr.length - 1);
        System.out.println("Binary Search: Element " + target +
                " found at index: " + binaryResult);

        int linearResult = linearSearch(arr, target, 0);
        System.out.println("Linear Search: Element " + target +
                " found at index: " + linearResult);
    }
}

