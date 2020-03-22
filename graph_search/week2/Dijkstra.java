package graph_search.week2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import javafx.util.Pair;

public class Dijkstra
{
    private static Set<Integer> vertices = new HashSet<>();
    private static Map<Integer, ArrayList<Pair<Integer, Integer>>> graph = new HashMap<>();
    private static Map<Integer, Integer> dist = new HashMap<>();
    private static Set<Integer> visitedNodes = new HashSet<>();

    private static void initializeSingleSource(int s) {
        int max = Integer.MAX_VALUE;
        for (int v : vertices) {
            dist.put(v,max);
        }
        dist.put(s,0);
    }

    private static int pickMinVertex() {
        int minDist = Integer.MAX_VALUE;
        int minVertex = 0;
        for (Map.Entry<Integer, Integer> pair : dist.entrySet()) {
            int u = pair.getKey();
            int d = pair.getValue();
            if (d < minDist && !visitedNodes.contains(u)) {
                minDist = d;
                minVertex = u;
            }
        }
        return minVertex;
    }

    private static void relax(int u, int v, int wt) {
        int d = dist.get(u) + wt;
        if (dist.get(v) > d) {
            dist.put(v,d);
        }
    }

    private static void printDistMap() {
        for (Map.Entry<Integer, Integer> pair : dist.entrySet()) {
            int u = pair.getKey();
            int d = pair.getValue();
            System.out.println("Distance of " + u + ": " + d);
        }
    }

    private static String dijkstra(int s) {
        initializeSingleSource(s);
        StringBuilder str = new StringBuilder();
        while (!vertices.isEmpty()) {
            int u = pickMinVertex();
            vertices.remove(u);
            str.append(u);
            visitedNodes.add(u);
            ArrayList<Pair<Integer, Integer>> adj = graph.get(u);
            for (Pair<Integer, Integer> p : adj) {
                int v = p.getKey();
                int wt = p.getValue();
                relax(u,v,wt);
            }
        }
        return str.toString();
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("graph_search/week2/input.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            String[] row = line.split("\\s+");
            int u = Integer.parseInt(row[0]);
            vertices.add(u);
            ArrayList<Pair<Integer, Integer>> adj = new ArrayList<>();
            for (int i=1;i<row.length;i++) {
                String[] s = row[i].split(",");
                int v = Integer.parseInt(s[0]);
                int wt = Integer.parseInt(s[1]);
                adj.add(new Pair<>(v,wt));
            }
            graph.put(u, adj);
        }
        System.out.println(dijkstra(1));
        printDistMap();
        System.out.println();
    }
}
