import java.util.*;

public class MazeGame {

    // Class to represent a cell in the grid
    static class Cell {
        int row, col, keys;

        public Cell(int row, int col, int keys) {
            this.row = row;
            this.col = col;
            this.keys = keys;
        }
    }

    public static int shortestPathAllKeys(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int totalKeys = 0;
        int startRow = 0, startCol = 0;

        // Find the starting position and count the total number of keys
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'S') {
                    startRow = i;
                    startCol = j;
                } else if (Character.isLowerCase(grid[i][j])) {
                    totalKeys++;
                }
            }
        }

        Queue<Cell> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(new Cell(startRow, startCol, 0));
        visited.add(startRow + ":" + startCol + ":0");
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Cell current = queue.poll();
                int row = current.row;
                int col = current.col;
                int keys = current.keys;

                if (keys == (1 << totalKeys) - 1) {
                    return steps;
                }

                for (int[] dir : directions) {
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];
                    int newKeys = keys;

                    // Check if the new position is within the grid and not a wall
                    if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && grid[newRow][newCol] != 'W') {
                        char cell = grid[newRow][newCol];

                        // If it's a key, collect it
                        if (Character.isLowerCase(cell)) {
                            newKeys |= 1 << (cell - 'a');
                        }

                        // If it's a locked door and we don't have the corresponding key, skip
                        if (Character.isUpperCase(cell) && ((newKeys >> (cell - 'A')) & 1) == 0) {
                            continue;
                        }

                        // If the cell hasn't been visited with the same set of keys, explore it
                        if (!visited.contains(newRow + ":" + newCol + ":" + newKeys)) {
                            queue.offer(new Cell(newRow, newCol, newKeys));
                            visited.add(newRow + ":" + newCol + ":" + newKeys);
                        }
                    }
                }
            }
            steps++;
        }

        return -1; // If all keys cannot be collected
    }

    public static void main(String[] args) {
        char[][] grid = {
                {'S', 'P', 'q', 'P', 'P'},
                {'W', 'W', 'W', 'P', 'W'},
                {'r', 'P', 'Q', 'P', 'R'}
        };
        System.out.println(shortestPathAllKeys(grid)); // Output: 8
    }
}
