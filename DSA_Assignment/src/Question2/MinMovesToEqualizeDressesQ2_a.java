package Question2;

public class MinMovesToEqualizeDressesQ2_a {

    public static int minMovesToEqualize(int[] sewingMachines) {
        int totalDresses = 0;
        int numMachines = sewingMachines.length;

        for (int dresses : sewingMachines) {
            totalDresses += dresses;
        }

        if (totalDresses % numMachines != 0) {
            return -1;
        }

        int targetDresses = totalDresses / numMachines;
        int moves = 0;
        int accumulatedDifference = 0;

        for (int dresses : sewingMachines) {
            int difference = dresses - targetDresses;
            accumulatedDifference += difference;
            moves = Math.max(moves, Math.abs(accumulatedDifference));
        }

        return moves;
    }

    public static void main(String[] args) {
        int[] sewingMachines = {2, 1, 3, 0, 2};
        int result = minMovesToEqualize(sewingMachines);

        System.out.println("Minimum moves required: " + result);
    }
}
