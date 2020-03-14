package graph_search.week1;

import java.io.*;
import java.util.*;

public class StronglyConnectedComponents
{
    private static Map<Integer, List<Integer>> graph = new TreeMap<>(Collections.reverseOrder());
    private static Map<Integer, List<Integer>> revGraph = new TreeMap<>(Collections.reverseOrder());
    private static Set<Integer> visitedNodes = new HashSet<>();
    private static Map<Integer, Integer> finishingTime = new TreeMap<>(Collections.reverseOrder());
    private static List<Integer> connectedComponentsSize = new ArrayList<>();
    private static StringBuilder str = new StringBuilder();

    private static void firstDfsLoop() {
        Set<Integer> vertices = revGraph.keySet();
        int finishTime = 0;
        for (int u : vertices) {
            if (visitedNodes.contains(u)) {
                continue;
            }
            Stack<Integer> stack = new Stack<>();
            stack.push(u);
            while (!stack.isEmpty()) {
                int v = stack.pop();
                if (!visitedNodes.contains(v)) {
                    visitedNodes.add(v);
                    stack.push(v);
                    List<Integer> neighbors = revGraph.get(v);
                    if (neighbors != null) {
                        for (int w : neighbors) {
                            if (!visitedNodes.contains(w)) {
                                stack.push(w);
                            }
                        }
                    }
                } else {
                    finishTime++;
                    finishingTime.put(finishTime, v);
                }
            }
        }
    }

    private static void secondDfsLoop() {
        visitedNodes.clear();
        Set<Integer> finishTimes = finishingTime.keySet();
        for (int f : finishTimes) {
            int u = finishingTime.get(f);
            if (visitedNodes.contains(u)) {
                continue;
            }
            int size = 0;
            Stack<Integer> stack = new Stack<>();
            stack.push(u);
            while (!stack.empty()) {
                int v = stack.pop();
                if (!visitedNodes.contains(v)) {
                    visitedNodes.add(v);
                    stack.push(v);
                    size++;
                    str.append(v).append(" ");
                    List<Integer> neighbors = graph.get(v);
                    if (neighbors != null) {
                        for (int w : neighbors) {
                            if (!visitedNodes.contains(w)) {
                                stack.push(w);
                            }
                        }
                    }
                }
            }
            connectedComponentsSize.add(size);
            str.append("\n");
        }
        connectedComponentsSize.sort(Collections.reverseOrder());
    }

    private static void findStronglyConnectedComponents() {
        firstDfsLoop();
        secondDfsLoop();
    }

    public static void main(String[] args)
            throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("graph_search/week1/input.txt"));
        String line;
        List<Integer> outEdges;
        List<Integer> inEdges;

        while ((line = bufferedReader.readLine()) != null)
        {
            String[] s = line.split("\\s+");
            int u = Integer.parseInt(s[0]);
            int v = Integer.parseInt(s[1]);
            if (graph.containsKey(u))
            {
                outEdges = graph.get(u);
            }
            else
            {
                outEdges = new ArrayList<>();
            }
            if (revGraph.containsKey(v))
            {
                inEdges = revGraph.get(v);
            }
            else
            {
                inEdges = new ArrayList<>();
            }

            outEdges.add(v);
            inEdges.add(u);
            graph.put(u, outEdges);
            revGraph.put(v, inEdges);
        }
        
        findStronglyConnectedComponents();
        for (int i=0;i<5;i++) {
            System.out.print(connectedComponentsSize.get(i) + " ");
        }
        System.out.println();

        FileOutputStream fs = new FileOutputStream(new File("graph_search/week1/output.txt"));
        fs.write(str.toString().getBytes());
    }
}
