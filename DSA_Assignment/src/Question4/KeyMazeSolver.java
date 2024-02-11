package Question4;

import java.util.LinkedList;
import java.util.Queue;

public class KeyMazeSolver {

    // Method to find the minimum number of steps to collect all keys
    public static int minStepsToCollectAllKeys(String[] maze) {
        int rows = maze.length;
        int cols = maze[0].length();

        int targetKeys = 0;
        int startX = 0, startY = 0;

        // Extract information from the maze
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char cell = maze[i].charAt(j);
                if (cell == 'S') {
                    startX = i;
                    startY = j;
                } else if (cell == 'E') {
                    targetKeys |= (1 << ('f' - 'a')); // Set the bit for the exit door
                } else if (cell >= 'a' && cell <= 'f') {
                    targetKeys |= (1 << (cell - 'a')); // Set the bit for the key
                }
            }
        }

        // Perform BFS traversal
        Queue<int[]> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[rows][cols][1 << 6]; // 1 << 6 represents the keys bitmask
        queue.offer(new int[]{startX, startY, 0, 0}); // {x, y, keys, steps}

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int keys = current[2];
            int steps = current[3];

            if (keys == targetKeys) {
                return steps; // All keys collected, return the steps
            }

            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && maze[newX].charAt(newY) != 'W') {
                    char cell = maze[newX].charAt(newY);

                    if (cell == 'E' || cell == 'P' || (cell >= 'a' && cell <= 'f') || (cell >= 'A' && cell <= 'F' && (keys & (1 << (cell - 'A'))) != 0)) {
                        int newKeys = keys;
                        if (cell >= 'a') {
                            newKeys |= (1 << (cell - 'a')); // Collect the key
                        }

                        if (!visited[newX][newY][newKeys]) {
                            visited[newX][newY][newKeys] = true;
                            queue.offer(new int[]{newX, newY, newKeys, steps + 1});
                        }
                    }
                }
            }
        }

        return -1; // All possible moves explored and keys not collected, return -1
    }

    public static void main(String[] args) {
        String[] maze = {"SPaPP", "WWWPW", "bPAPB"};
        int result = minStepsToCollectAllKeys(maze);
        System.out.println("Minimum number of moves: " + result); // Output: 8
    }
}
