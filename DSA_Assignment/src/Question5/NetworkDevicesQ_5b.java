package Question5;

import java.util.ArrayList;
import java.util.List;

public class NetworkDevicesQ_5b {

    public static List<Integer> findImpactedDevices(int[][] network, int deviceWithOutage) {
        int numDevices = network.length;
        boolean[] visited = new boolean[numDevices];
        List<Integer> impactedDevices = new ArrayList<>();
        dfs(network, deviceWithOutage, visited, impactedDevices);
        return impactedDevices;
    }

    private static void dfs(int[][] network, int currentDevice, boolean[] visited, List<Integer> impactedDevices) {
        visited[currentDevice] = true;
        impactedDevices.add(currentDevice);

        for (int i = 0; i < network.length; i++) {
            if (network[currentDevice][i] == 1 && !visited[i]) {
                dfs(network, i, visited, impactedDevices);
            }
        }
    }

    public static void main(String[] args) {
        int[][] network = {
                {0, 1, 1, 0, 0},
                {1, 0, 0, 1, 0},
                {1, 0, 0, 1, 1},
                {0, 1, 1, 0, 1},
                {0, 0, 1, 1, 0}
        };
        int deviceWithOutage = 2;

        List<Integer> impactedDevices = findImpactedDevices(network, deviceWithOutage);
        System.out.println("Impacted devices: " + impactedDevices);
    }
}
