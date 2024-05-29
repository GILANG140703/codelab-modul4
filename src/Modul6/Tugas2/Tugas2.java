package Modul6.Tugas2;

import java.util.*;

class Graph {
    private int numVertices;
    private LinkedList<Integer>[] adjLists;
    private boolean[] visited;

    // Constructor
    public Graph(int vertices) {
        numVertices = vertices;
        adjLists = new LinkedList[vertices];
        visited = new boolean[vertices];

        for (int i = 0; i < vertices; i++)
            adjLists[i] = new LinkedList<>();
    }

    // Add edges to the graph
    public void addEdge(int src, int dest) {
        adjLists[src].add(dest);
    }

    // DFS algorithm
    public void DFS(int vertex) {
        visited[vertex] = true;
        System.out.print(vertex + " ");

        Iterator<Integer> ite = adjLists[vertex].listIterator();
        while (ite.hasNext()) {
            int adj = ite.next();
            if (!visited[adj])
                DFS(adj);
        }
    }

    // BFS algorithm
    public void BFS(int startVertex) {
        boolean[] visited = new boolean[numVertices];
        LinkedList<Integer> queue = new LinkedList<>();

        visited[startVertex] = true;
        queue.add(startVertex);

        while (queue.size() != 0) {
            int vertex = queue.poll();
            System.out.print(vertex + " ");

            Iterator<Integer> ite = adjLists[vertex].listIterator();
            while (ite.hasNext()) {
                int adj = ite.next();
                if (!visited[adj]) {
                    visited[adj] = true;
                    queue.add(adj);
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(12); // Graf memiliki 11 node (indeks 0 tidak digunakan)

        // Menambahkan semua edge sesuai gambar
        g.addEdge(1, 6);
        g.addEdge(1, 11);
        g.addEdge(1, 3);
        g.addEdge(2, 5);
        g.addEdge(3, 4);
        g.addEdge(3, 7);
        g.addEdge(4, 10);
        g.addEdge(4, 8);
        g.addEdge(5, 9);
        g.addEdge(5, 3);
        g.addEdge(5, 6);
        g.addEdge(6, 1);
        g.addEdge(7, 8);
        g.addEdge(8, 7);
        g.addEdge(10, 4);

        System.out.println("Depth First Traversal starting from vertex 1:");
        g.DFS(1);

        System.out.println("\nBreadth First Traversal starting from vertex 1:");
        g.BFS(1);
    }
}
