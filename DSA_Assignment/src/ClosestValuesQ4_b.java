class TreeNode {
    int val;
    TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class ClosestValuesQ4_b {

    private static int[] result;
    private static double target;
    private static int count;

    public static int[] closestValues(TreeNode root, double k, int x) {
        result = new int[x];
        target = k;
        count = 0;

        inorderTraversal(root, x);

        return result;
    }

    private static void inorderTraversal(TreeNode node, int x) {
        if (node == null || count == x) {
            return;
        }

        inorderTraversal(node.left, x);

        if (count < x) {
            double currentDiff = Math.abs(node.val - target);
            double closestDiff = Math.abs(result[0] - target);

            if (count == 0 || currentDiff < closestDiff) {
                result[count++] = node.val;
            } else {
                return;  // No need to explore further on the right side
            }
        }

        inorderTraversal(node.right, x);
    }

    public static void main(String[] args) {
        // Example usage:
        // Construct a balanced binary search tree
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        double k = 3.8;
        int x = 2;

        int[] output = closestValues(root, k, x);

        System.out.print("Output: ");
        for (int value : output) {
            System.out.print(value + " ");
        }
    }
}
