package Question1;

import java.util.Arrays;
import java.util.PriorityQueue;

public class SpaceshipMissionQ1_b {

    // Function to calculate the minimum time needed to build all engines
    public static int minBuildTime(int[] engines, int splitCost) {
        Arrays.sort(engines); // Sort engines in ascending order

        PriorityQueue<Integer> engineers = new PriorityQueue<>(); // Min heap to keep track of available engineers
        int totalTime = 0;

        for (int engine : engines) {
            if (engineers.isEmpty()) {
                // If no engineers are available, split one
                engineers.offer(splitCost);
            } else {
                // Assign the current engine to an available engineer
                int splitTime = engineers.poll();
                totalTime += splitTime;
                engineers.offer(splitTime + splitCost); // Split the engineer again and update the queue
            }

            engineers.offer(engine); // Assign the current engine to an available engineer
        }

        // Collect the remaining time from engineers
        while (!engineers.isEmpty()) {
            totalTime += engineers.poll();
        }

        return totalTime;
    }

    public static void main(String[] args) {
        // Example usage
        int[] engines = {3, 4, 5, 2};
        int splitCost = 2;

        // Calculate and print the minimum time needed to build all engines
        int result = minBuildTime(engines, splitCost);
        System.out.println("Minimum time needed to build all engines: " + result);
    }
}
