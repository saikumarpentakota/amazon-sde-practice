package DAY18;

import java.util.*;

class Node1{
    int key;
    Node1 left, right;

    public Node1(int key)
    {
        this.key = key;
        left = right = null;
    }
}

class TASK11 {
    Node1 root;

    void printCorner(Node1 root) {
        Queue<Node1> q = new LinkedList<Node1>();
        q.add(root);

        while (!q.isEmpty()) {
            int n = q.size();
            for(int i = 0 ; i < n ; i++){
                Node1 temp = q.peek();
                q.poll();// retrieve and remove the node

                if(i==0 || i==n-1)
                    System.out.print(temp.key + "  ");

                if (temp.left != null)
                    q.add(temp.left);
                if (temp.right != null)
                    q.add(temp.right);
            }
        }
    }

    public static void main(String[] args){
        TASK11 tree = new TASK11();
        tree.root = new Node1(1);
        tree.root.left = new Node1(2);
        tree.root.right = new Node1(3);
        tree.root.left.left = new Node1(4);
        tree.root.left.right = new Node1(5);
        tree.root.right.left = new Node1(6);
        tree.root.right.right = new Node1(7);

        tree.printCorner(tree.root);
    }
}

