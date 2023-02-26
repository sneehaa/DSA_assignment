package QuestionSolutions;

import java.util.ArrayList;
import java.util.List;

public class Question_1B {

    public static void main(String[] args) {
        int[][] edges = {{0,1}, {0,2}, {1,3}, {1,6}, {2,4}, {4,6}, {4,5}, {5,7}};
        int targetDevice = 4;
        List<Integer>[] adjList = createAdjacencyList(edges);
        List<Integer> impactedDevices = getImpactedDevices(targetDevice, adjList);
        System.out.println("Impacted Device List: " + impactedDevices);
    }

    // function to create an adjacency list from the given edges
    public static List<Integer>[] createAdjacencyList(int[][] edges) {
        int numDevices = edges.length + 1; // number of network devices
        List<Integer>[] adjList = new ArrayList[numDevices];
        for (int i = 0; i < numDevices; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int device1 = edge[0];
            int device2 = edge[1];
            adjList[device1].add(device2);
            adjList[device2].add(device1);
        }
        return adjList;
    }

    // function to get the impacted device list due to power outage on a certain device
    public static List<Integer> getImpactedDevices(int targetDevice, List<Integer>[] adjList) {
        int numDevices = adjList.length; // number of network devices
        boolean[] visited = new boolean[numDevices];
        List<Integer> impactedDevices = new ArrayList<>();
        dfs(targetDevice, -1, visited, adjList, impactedDevices);
        return impactedDevices;
    }

    // DFS function to mark all the impacted devices due to power outage
    public static void dfs(int currentDevice, int parentDevice, boolean[] visited, List<Integer>[] adjList, List<Integer> impactedDevices) {
        visited[currentDevice] = true;
        for (int connectedDevice : adjList[currentDevice]) {
            if (!visited[connectedDevice]) {
                dfs(connectedDevice, currentDevice, visited, adjList, impactedDevices);
                if (connectedDevice != parentDevice && connectedDevice != currentDevice) {
                    impactedDevices.add(connectedDevice);
                }
            }
        }
    }
}
