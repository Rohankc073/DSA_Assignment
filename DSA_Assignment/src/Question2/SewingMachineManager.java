public class SewingMachineManager {

    public static int minMovesToEqualize(int[] machines) {
        int totalDresses = 0;
        int n = machines.length;

        // Calculate total number of dresses
        for (int machine : machines) {
            totalDresses += machine;
        }

        // If total dresses cannot be evenly distributed, return -1
        if (totalDresses % n != 0) {
            return -1;
        }

        // Calculate target number of dresses per machine
        int targetDresses = totalDresses / n;

        int maxMoves = 0;
        int totalMoves = 0;
        int leftDresses = 0;

        // Iterate through all possible split positions
        for (int i = 0; i < n - 1; i++) {
            // Calculate dresses to pass from left to right
            leftDresses += machines[i] - targetDresses;
            int rightDresses = machines[n - 1] - targetDresses - leftDresses;

            // Calculate moves needed for current split position
            int moves = Math.max(leftDresses, rightDresses);

            // Update maximum moves required
            maxMoves = Math.max(maxMoves, moves);

            // Accumulate total moves
            totalMoves += moves;
        }

        return maxMoves;
    }

    public static void main(String[] args) {
        int[] machines = {2, 1, 3, 0, 2};

        int minMoves = minMovesToEqualize(machines);
        System.out.println("Minimum moves needed to equalize dresses: " + minMoves);
    }
}
