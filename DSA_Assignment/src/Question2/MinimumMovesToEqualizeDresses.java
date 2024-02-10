package Question2;

public class MinimumMovesToEqualizeDresses {

    public static int minMovesToEqualize(int[] sewingMachines) {
        int totalDresses = 0;
        int numMachines = sewingMachines.length;

        for (int dresses : sewingMachines) {
            totalDresses += dresses;
        }

        if (totalDresses % numMachines != 0) {
            return -1;  // If total dresses cannot be equally distributed, return -1
        }

        int targetDresses = totalDresses / numMachines;
        int moves = 0;

        for (int i = 0; i < numMachines - 1; i++) {
            int diff = sewingMachines[i] - targetDresses;
            sewingMachines[i] -= diff;
            sewingMachines[i + 1] += diff;
            moves += Math.abs(diff);
        }

        return moves;
    }

    public static void main(String[] args) {
        int[] sewingMachines = {2, 1, 3, 0, 2};
        int moves = minMovesToEqualize(sewingMachines);
        System.out.println("Output: " + moves); // Output: 5
    }
}
