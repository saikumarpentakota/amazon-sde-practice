public class TASK0003 {
    public static void main(String[] args) {
        try {
            int[] mynum = {1, 2, 3};
            System.out.println(mynum[2]);
            System.out.println(mynum[1]);
        } catch (Exception e) {
            System.out.println("somthing went wrong");
        }
        finally {
            System.out.println("i'm from finally block");
        }
    }
}
