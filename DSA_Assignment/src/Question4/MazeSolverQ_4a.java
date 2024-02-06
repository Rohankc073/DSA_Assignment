package Question4;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class MazeSolverQ_4a {

    public int minMoves(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int keysCount = 0;
        Set<Character> allKeys = new HashSet<>();
        int startRow = -1, startCol = -1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'S') {
                    startRow = i;
                    startCol = j;
                } else if (grid[i][j] >= 'a' && grid[i][j] <= 'f') {
                    keysCount++;
                    allKeys.add(grid[i][j]);
                }
            }
        }

        Queue<State> queue = new ArrayDeque<>();
        Set<State> visited = new HashSet<>();
        State initial = new State(startRow, startCol, new HashSet<>());
        queue.offer(initial);
        visited.add(initial);

        while (!queue.isEmpty()) {
            State current = queue.poll();

            if (current.keys.size() == keysCount && grid[current.row][current.col] == 'E') {
                return current.moves;
            }

            for (int[] direction : new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}}) {
                int newRow = current.row + direction[0];
                int newCol = current.col + direction[1];

                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && grid[newRow][newCol] != 'W') {
                    char cell = grid[newRow][newCol];

                    Set<Character> newKeys = new HashSet<>(current.keys);
                    if (cell >= 'a' && cell <= 'f') {
                        newKeys.add(cell);
                    }

                    if (cell >= 'A' && cell <= 'F' && !current.keys.contains((char) (cell + 32))) {
                        continue;
                    }

                    State nextState = new State(newRow, newCol, newKeys, current.moves + 1);

                    if (!visited.contains(nextState)) {
                        queue.offer(nextState);
                        visited.add(nextState);
                    }
                }
            }
        }

        return -1;
    }

    static class State {
        int row;
        int col;
        Set<Character> keys;
        int moves;

        public State(int row, int col, Set<Character> keys) {
            this.row = row;
            this.col = col;
            this.keys = keys;
            this.moves = 0;
        }

        public State(int row, int col, Set<Character> keys, int moves) {
            this.row = row;
            this.col = col;
            this.keys = keys;
            this.moves = moves;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            State state = (State) o;

            if (row != state.row) return false;
            if (col != state.col) return false;
            return keys.equals(state.keys);
        }

        @Override
        public int hashCode() {
            int result = row;
            result = 31 * result + col;
            result = 31 * result + keys.hashCode();
            return result;
        }
    }
}
