public class TASK0000010 {
    public static void main(String[] args){
        String s1 = "hi there";
        String rev = "";
        for(int i = s1.length() -1 ; i >=0;i--){
            rev += s1.charAt(i);
        }
        System.out.println("original   "  +   s1);
        System.out.println("reversed   "  +  rev);

    }
}
