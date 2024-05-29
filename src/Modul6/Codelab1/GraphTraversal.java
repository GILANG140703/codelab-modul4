package Modul6.Codelab1;

import java.util.*;

public class GraphTraversal {
    private int node;
    private LinkedList<Integer> adj[];
    private Queue<Integer> que;
    private boolean visited[];

    GraphTraversal(int v){
        node = v;
        adj = new LinkedList[node];
        for (int i = 0; i < v ; i++) {
            adj[i] = new LinkedList<>();
        }
        que = new LinkedList<Integer>();
        visited = new boolean[node];
    }

    void insertEdge(int v, int w){
        adj[v].add(w);
    }

    void BFS(int n) {
        boolean nodes[] = new boolean[node];
        int a = 0;
        nodes[n] = true;
        que.add(n);
        while (que.size() != 0) {
            n = que.poll();
            System.out.print(n + " ");
            for (int i = 0; i < adj[n].size(); i++) {
                a = adj[n].get(i);
                if (!nodes[a]) {
                    nodes[a] = true;
                    que.add(a);
                }
            }
        }
        System.out.println();
    }

    void DFS(int vertex) {
        visited[vertex] = true;
        System.out.print(vertex + " ");

        Iterator<Integer> it = adj[vertex].listIterator();
        while (it.hasNext()) {
            int n = it.next();
            if (!visited[n]) {
                DFS(n);
            }
        }
    }

    void resetVisited() {
        Arrays.fill(visited, false);
    }

    public static void main(String[] args) {
        GraphTraversal graph = new GraphTraversal(6);
        graph.insertEdge(0, 1);
        graph.insertEdge(0, 5);
        graph.insertEdge(0, 4);
        graph.insertEdge(1, 0);
        graph.insertEdge(1, 5);
        graph.insertEdge(1, 2);
        graph.insertEdge(5, 1);
        graph.insertEdge(5, 0);
        graph.insertEdge(5, 4);
        graph.insertEdge(4, 0);
        graph.insertEdge(4, 5);
        graph.insertEdge(4, 2);
        graph.insertEdge(2, 1);
        graph.insertEdge(2, 4);
        graph.insertEdge(2, 3);
        graph.insertEdge(3, 2);

        System.out.println("BFS untuk graph (dari node 3):");
        graph.BFS(3);

        graph.resetVisited();
        System.out.println("DFS untuk graph (dari node 3):");
        graph.DFS(3);
    }
}
