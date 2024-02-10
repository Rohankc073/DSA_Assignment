package Question3;

import java.util.*;

// Class to represent a graph edge
class Edge implements Comparable<Edge> {
    int src, dest, weight;

    // Constructor
    Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    // Compare two edges based on their weight
    public int compareTo(Edge compareEdge) {
        return this.weight - compareEdge.weight;
    }
}

// Class to represent a subset for union-find
class Subset {
    int parent, rank;

    Subset(int parent, int rank) {
        this.parent = parent;
        this.rank = rank;
    }
}

class KruskalAlgorithm {
    int V, E; // Number of vertices and edges in the graph
    Edge[] edges; // Array of edges

    // Constructor
    KruskalAlgorithm(int v, int e) {
        V = v;
        E = e;
        edges = new Edge[E];
        for (int i = 0; i < e; ++i)
            edges[i] = new Edge(0, 0, 0);
    }

    // Function to find the set of an element 'i'
    int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }

    // Function that does union of two sets of x and y
    void union(Subset[] subsets, int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        // Attach smaller rank tree under root of high rank tree
        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;

            // If ranks are the same, then make one as root and increment its rank by one
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    // Function to construct MST using Kruskal's algorithm
    void KruskalMST() {
        Edge[] result = new Edge[V]; // This will store the resultant MST
        int e = 0; // An index variable, used for result[]
        int i = 0; // An index variable, used for sorted edges
        for (i = 0; i < V; ++i)
            result[i] = new Edge(0, 0, 0);

        // Step 1: Sort all the edges in non-decreasing order of their weight
        Arrays.sort(edges);

        // Allocate memory for creating V subsets
        Subset[] subsets = new Subset[V];
        for (i = 0; i < V; ++i)
            subsets[i] = new Subset(i, 0);

        i = 0; // Index used to pick next edge

        // Number of edges to be taken is equal to V-1
        while (e < V - 1) {
            // Step 2: Pick the smallest edge. Increment the index for the next iteration
            Edge next_edge = edges[i++];

            int x = find(subsets, next_edge.src);
            int y = find(subsets, next_edge.dest);

            // If including this edge does't cause cycle, include it in the result and increment the index
            // of result for the next edge
            if (x != y) {
                result[e++] = next_edge;
                union(subsets, x, y);
            }
            // Else discard the next_edge
        }

        // Print the edges of MST
        System.out.println("Following are the edges in the constructed MST:");
        int minimumCost = 0;
        for (i = 0; i < e; ++i) {
            System.out.println(result[i].src + " -- " + result[i].dest + " == " + result[i].weight);
            minimumCost += result[i].weight;
        }
        System.out.println("Minimum Cost Spanning Tree: " + minimumCost);
    }
}

// Main class
public class MinHeapQ3_b {
    public static void main(String[] args) {
        int V = 4; // Number of vertices in graph
        int E = 5; // Number of edges in graph
        KruskalAlgorithm graph = new KruskalAlgorithm(V, E);

        // Add edge 0-1
        graph.edges[0] = new Edge(0, 1, 10);
        // Add edge 0-2
        graph.edges[1] = new Edge(0, 2, 6);
        // Add edge 0-3
        graph.edges[2] = new Edge(0, 3, 5);
        // Add edge 1-3
        graph.edges[3] = new Edge(1, 3, 15);
        // Add edge 2-3
        graph.edges[4] = new Edge(2, 3, 4);

        // Function call to find the minimum spanning tree
        graph.KruskalMST();
    }
}
