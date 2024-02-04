import java.util.*;

public class SecretSharingQ2_b {
    public static List<Integer> getIndividuals(int n, int[][] intervals, int firstPerson) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> knownSecrets = new HashSet<>();
        knownSecrets.add(firstPerson);

        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];

            Set<Integer> newIndividuals = new HashSet<>();

            for (int i = start; i <= end; i++) {
                if (knownSecrets.contains(i)) {
                    // Add individuals who receive the secret during this interval
                    for (int j = 0; j < n; j++) {
                        if (!knownSecrets.contains(j)) {
                            newIndividuals.add(j);
                        }
                    }
                }
            }

            knownSecrets.addAll(newIndividuals);
        }

        result.addAll(knownSecrets);
        return result;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] intervals = {{0, 2}, {1, 3}, {2, 4}};
        int firstPerson = 0;

        List<Integer> result = getIndividuals(n, intervals, firstPerson);

        System.out.println("Individuals who will eventually know the secret: " + result);
    }
}
