package DAY18;

class Node {
    int data;
    Node left, right;

    public Node(int item) {
        data = item;
        left = right = null;
    }
}

public class TASK10 {
    Node root;


    public void printCornerNodes(Node root) {
        if (root == null)
            return;


        java.util.Queue<Node> queue = new java.util.LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {

            int n = queue.size();


            for (int i = 0; i < n; i++) {
                Node temp = queue.poll();


                if (i == 0 || i == n-1)
                    System.out.print(temp.data + " ");


                if (temp.left != null)
                    queue.add(temp.left);


                if (temp.right != null)
                    queue.add(temp.right);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TASK10 tree = new TASK10();


        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.left = new Node(6);
        tree.root.right.right = new Node(7);
        tree.root.right.right.right = new Node(15);
        tree.root.left.right.left = new Node(8);
        tree.root.left.right.right = new Node(11);
        tree.root.right.left.left = new Node(12);
        tree.root.right.left.right = new Node(13);
        tree.root.right.right.left = new Node(14);

        System.out.println("Corner nodes of each level:");
        tree.printCornerNodes(tree.root);
    }
}

