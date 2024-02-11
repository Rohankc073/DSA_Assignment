package Question2;

public class MinMovesToEqualizeDresses {

    public static int minMovesToEqualize(int[] sewingMachines) {
        int totalDresses = 0;
        int machinesCount = sewingMachines.length;

        // Calculate the total number of dresses
        for (int dresses : sewingMachines) {
            totalDresses += dresses;
        }

        // If total dresses cannot be evenly distributed, return -1
        if (totalDresses % machinesCount != 0) {
            return -1;
        }

        // Calculate the target number of dresses for each machine
        int targetDresses = totalDresses / machinesCount;
        int moves = 0;

        // Iterate until all machines have the target dress count
        for (int dresses : sewingMachines) {
            // Calculate the difference between current dresses and the target
            int diff = dresses - targetDresses;
            // Accumulate moves needed to equalize dresses
            if (diff > 0) {
                moves += diff;
            }
        }

        return moves;
    }

    public static void main(String[] args) {
        int[] sewingMachines = {1, 0, 5};
        System.out.println(minMovesToEqualize(sewingMachines)); // Output: 2
    }
}
