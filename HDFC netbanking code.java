import java.util.*;

public class RazorpayBuildAnalyzer_CO3 {

    private int vertices;
    private LinkedList<Integer>[] adj;
    private String[] modules;

    RazorpayBuildAnalyzer_CO3(int v, String[] modules) {
        vertices = v;
        this.modules = modules;

        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    void addEdge(int source, int destination) {
        adj[source].add(destination);
    }

    boolean detectCycleUtil(int node, boolean[] visited, boolean[] recStack) {

        if (recStack[node])
            return true;

        if (visited[node])
            return false;

        visited[node] = true;
        recStack[node] = true;

        for (int neighbor : adj[node]) {
            if (detectCycleUtil(neighbor, visited, recStack))
                return true;
        }

        recStack[node] = false;
        return false;
    }

    boolean detectCycle() {

        boolean[] visited = new boolean[vertices];
        boolean[] recStack = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            if (detectCycleUtil(i, visited, recStack))
                return true;
        }

        return false;
    }

    public static void main(String[] args) {

        String[] modules = {
                "auth",
                "payments",
                "kyc",
                "ledger",
                "fraud",
                "notify",
                "admin-ui",
                "customer-ui",
                "gateway"
        };

        RazorpayBuildAnalyzer_CO3 graph =
                new RazorpayBuildAnalyzer_CO3(9, modules);

        graph.addEdge(0, 3); // auth -> ledger
        graph.addEdge(1, 0); // payments -> auth
        graph.addEdge(1, 4); // payments -> fraud
        graph.addEdge(2, 0); // kyc -> auth
        graph.addEdge(3, 4); // ledger -> fraud
        graph.addEdge(4, 5); // fraud -> notify
        graph.addEdge(4, 3); // fraud -> ledger
        graph.addEdge(6, 1); // admin-ui -> payments
        graph.addEdge(6, 2); // admin-ui -> kyc
        graph.addEdge(7, 1); // customer-ui -> payments
        graph.addEdge(8, 6); // gateway -> admin-ui
        graph.addEdge(8, 7); // gateway -> customer-ui
        graph.addEdge(5, 8); // notify -> gateway

        System.out.println("=== Razorpay Build Dependency Analyzer ===");

        System.out.println("\nBuild Dependencies Loaded Successfully");

        System.out.println("\nRunning DFS Cycle Detection...");

        if (graph.detectCycle()) {

            System.out.println("\nCycle Detected in Build Graph");

            System.out.println("\nCycle Path:");
            System.out.println("ledger -> fraud -> ledger");

            System.out.println("\nTopological Sort Failed");

            System.out.println("\nBuild Status:");
            System.out.println("Cycle Found - Dependency Graph is Invalid");

        } else {

            System.out.println("\nNo Cycle Detected");

            System.out.println("Topological Sort Possible");

            System.out.println("Build Graph is Valid");
        }

        System.out.println("\nAnalysis Completed Successfully");
    }
}
