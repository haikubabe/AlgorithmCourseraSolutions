package divide_and_conquer.week4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MinimumCut
{
    private static ArrayList<Integer> vertices = new ArrayList<>();
    private static Map<Integer, ArrayList<Integer>> adj = new HashMap<>();

    private static void buildGraph()
            throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("divide_and_conquer/week4/input.txt"));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] s = line.split("\\s+");
            int source = Integer.parseInt(s[0]);
            ArrayList<Integer> edges = new ArrayList<>();
            for (int i=1;i<s.length;i++) {
                int dest = Integer.parseInt(s[i]);
                edges.add(dest);
            }
            vertices.add(source);
            adj.put(source, edges);
        }
    }

    private static int minCut() {
        int label = adj.size()+1;
        while (vertices.size()>2) {
            //find the sum of degree of vertices
            int sumDegrees = findSumDegrees();
            //build the edge graph
            int[][] edges = buildEdge(sumDegrees);
            //select a random edge
            Random random = new Random();
            int e = random.nextInt(edges.length);
            int u = edges[e][0];
            int v = edges[e][1];
            //merge the vertices u and v
            mergeVertices(u,v,label);
            //remove self-loops
            removeSelfLoop(label);
            label++;
        }
        return adj.get(vertices.get(0)).size();
    }

    private static void mergeVertices(int u, int v, int label) {
        ArrayList<Integer> edgesU = adj.get(u);
        ArrayList<Integer> edgesV = adj.get(v);

        for (int w : edgesV)
        {
            edgesU.add(w);
        }
        //remove v from vertices
        vertices.remove(vertices.indexOf(v));
        //remove v from adj
        adj.remove(v);

        //replace all the vertices u and v by the new label
        for (Integer vertex : vertices)
        {
            ArrayList<Integer> edges = adj.get(vertex);
            for (int j = 0; j < edges.size(); j++)
            {
                if (edges.get(j) == u)
                {
                    edges.set(j, label);
                }
                if (edges.get(j) == v)
                {
                    edges.set(j, label);
                }
            }
        }

        //change the label of u to new label in adj
        ArrayList<Integer> edges = adj.get(u);
        adj.remove(u);
        adj.put(label, edges);

        //change the label of u in vertices
        vertices.set(vertices.indexOf(u), label);
    }

    private static void removeSelfLoop(int label) {
        int position = 0;
        ArrayList<Integer> edges = adj.get(label);
        int size = edges.size();
        while (position < size) {
            if (edges.get(position) == label) {
                edges.remove(position);
                size--;
            } else {
                position++;
            }
        }
    }

    private static int findSumDegrees() {
        int sumDegrees = 0;
        for (int vertex : vertices)
        {
            ArrayList<Integer> edges = adj.get(vertex);
            sumDegrees += edges.size();
        }
        return sumDegrees;
    }

    private static int[][] buildEdge(int sumDegrees) {
        int[][] edges = new int[sumDegrees][2];
        int k=0;
        for (int u : vertices)
        {
            ArrayList<Integer> e = adj.get(u);
            for (int v : e)
            {
                edges[k][0] = u;
                edges[k][1] = v;
                k++;
            }
        }
        return edges;
    }

    public static void main(String[] args)
            throws IOException
    {
        buildGraph();
        int absoluteMin = adj.size() * adj.size();

        for (int i=0;i<adj.size();i++) {
            int minCut = minCut();
            System.out.println("Min found on call " + i + " is: " + minCut);
            if (minCut < absoluteMin) {
                absoluteMin = minCut;
            }
            vertices.clear();
            adj.clear();
            buildGraph();
        }
        System.out.println("\n MINIMAL CUT FOUND IS \n" + absoluteMin);
    }
}
