package Question4;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class ClosestValuesBSTQ4_b {
    public static List<Integer> closestValues(TreeNode root, double k, int x) {
        List<Integer> closest = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();

            if (closest.size() < x) {
                closest.add(current.val);
            } else if (Math.abs(current.val - k) < Math.abs(closest.get(0) - k)) {
                closest.remove(0);
                closest.add(current.val);
            } else {
                break;
            }

            current = current.right;
        }

        return closest;
    }

    public static void main(String[] args) {
        // Test
        // Creating the binary search tree
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        double k = 3.8;
        int x = 2;
        List<Integer> result = closestValues(root, k, x);
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
 }


