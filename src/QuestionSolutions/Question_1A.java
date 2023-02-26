package QuestionSolutions;

import java.util.*;

public class Question_1A {

    // Edge class to represent a route
    static class Edge {
        int source;
        int destination;
        int cost;
        Edge(int source, int destination, int cost) {
            this.source = source;
            this.destination = destination;
            this.cost = cost;
        }
    }

    public static int findCheapestRoute(int[][] edgeList, int[] nodeCosts, int source, int destination, int maxTime) {
        // Construct graph using adjacency list
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int[] edge : edgeList) {
            int sourceNode = edge[0];
            int destNode = edge[1];
            int edgeCost = edge[2];
            graph.computeIfAbsent(sourceNode, k -> new ArrayList<>()).add(new Edge(sourceNode, destNode, edgeCost));
        }

        // Initialize priority queue and visited set
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]); // [node, time]
        Set<Integer> visited = new HashSet<>();

        // Add source node to priority queue
        pq.offer(new int[]{source, 0, nodeCosts[source]}); // [node, time, cost]

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currNode = curr[0];
            int currTime = curr[1];
            int currCost = curr[2];

            // If destination reached, return cost
            if (currNode == destination) {
                return currCost;
            }

            // Mark node as visited
            visited.add(currNode);

            // Explore neighboring nodes
            if (graph.containsKey(currNode)) {
                for (Edge edge : graph.get(currNode)) {
                    int nextNode = edge.destination;
                    int nextTime = currTime + edge.cost;
                    int nextCost = currCost + nodeCosts[nextNode];

                    // Check if next node can be visited within time constraint and has not been visited before
                    if (nextTime <= maxTime && !visited.contains(nextNode)) {
                        pq.offer(new int[]{nextNode, nextTime, nextCost});
                    }
                }
            }
        }

        // If destination not reachable, return -1
        return -1;
    }

    public static void main(String[] args) {
        int[][] edgeList = {{0,1,5}, {0,3,2}, {1,2,5}, {3,4,5}, {4,5,6}, {2,5,5}};
        int[] nodeCosts = {10,2,3,25,25,4};
        int source = 0;
        int destination = 5;
        int maxTime = 14;
        int cheapestCost = findCheapestRoute(edgeList, nodeCosts, source, destination, maxTime);
        System.out.println("Cheapest route cost: " + cheapestCost);
    }
}
