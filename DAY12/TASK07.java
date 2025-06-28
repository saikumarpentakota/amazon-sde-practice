package DAY12;

public class TASK07 {
    public static void main(String[] args){
        int[] arr ={10,20,50,30,60,80};
        System.out.println("original array: ");
        for (int i : arr){
            System.out.print(i + " ");
        }
        int n = arr.length;
        for (int i = 0 ; i < n/2; i++){
            int temp = arr[i];
            arr[i] = arr[n - 1 - i];
            arr [ n- 1 - 1] = temp;

        }
        System.out.println("reversed array:");
        for (int i : arr ) {
            System.out.print(i + " ");
        }

    }
}
