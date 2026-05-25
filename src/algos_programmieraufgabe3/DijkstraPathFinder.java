package algos_programmieraufgabe3;

import java.util.*;

public class DijkstraPathFinder {

    private static class NodeWrapper implements Comparable<NodeWrapper> {
        String name;
        int totalDist;

        NodeWrapper(String name, int totalDist) {
            this.name = name;
            this.totalDist = totalDist;
        }

        @Override
        public int compareTo(NodeWrapper o) {
            return Integer.compare(this.totalDist, o.totalDist);
        }
    }

    private static class PathStep {
        String fromStation;
        String line;
        int edgeWeight;

        PathStep(String fromStation, String line, int edgeWeight) {
            this.fromStation = fromStation;
            this.line = line;
            this.edgeWeight = edgeWeight;
        }
    }

    public static void findAndPrintShortestPath(Graph graph, String start, String ziel) {
        if (!graph.getNodes().contains(start) || !graph.getNodes().contains(ziel)) {
            System.out.println("Start- oder Zielstation existiert nicht im Verkehrsnetz.");
            return;
        }

        Map<String, Integer> distances = new HashMap<>();
        Map<String, PathStep> predecessors = new HashMap<>();
        PriorityQueue<NodeWrapper> pq = new PriorityQueue<>();

        // Initialisierung
        for (String node : graph.getNodes()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(start, 0);
        pq.add(new NodeWrapper(start, 0));

        while (!pq.isEmpty()) {
            NodeWrapper current = pq.poll();
            String u = current.name;


            if (u.equals(ziel)) break;

            if (current.totalDist > distances.get(u)) continue;

            for (Edge edge : graph.getNeighbors(new Node(u))) {
                String v = edge.getDestination().getStationName();
                int newDist = distances.get(u) + edge.getWeight();

                if (newDist < distances.get(v)) {
                    distances.put(v, newDist);
                    predecessors.put(v, new PathStep(u, edge.getLine(), edge.getWeight()));
                    pq.add(new NodeWrapper(v, newDist));
                }
            }
        }

        // Pfad ausgeben
        printResult(start, ziel, distances.get(ziel), predecessors);
    }

    private static void printResult(String start, String ziel, int totalCost, Map<String, PathStep> predecessors) {
        if (totalCost == Integer.MAX_VALUE) {
            System.out.println("Kein Weg von " + start + " nach " + ziel + " gefunden.");
            return;
        }

        System.out.println("==================================================");
        System.out.println("KÜRZESTER WEG VON: " + start + " NACH: " + ziel);
        System.out.println("Gesamtfahrzeit: " + totalCost + " Minuten");
        System.out.println("==================================================");

        // Weg rückwärts rekonstruieren
        LinkedList<String> pathDetails = new LinkedList<>();
        String current = ziel;
        String lastLine = null;
        int umstiege = 0;

        while (predecessors.containsKey(current)) {
            PathStep step = predecessors.get(current);
            String lineInfo = step.line;

            String stepOutput = String.format("  -> Fahre von '%s' zu '%s' mit der %s (%d min)",
                    step.fromStation, current, lineInfo, step.edgeWeight);
            pathDetails.addFirst(stepOutput);

            // Prüfen, ob hier umgestiegen wurde (rückwärts gedacht)
            if (lastLine != null && !lastLine.equals(lineInfo)) {
                pathDetails.addFirst("  [UMSTIEG] Umsteigen in Station: " + current);
                umstiege++;
            }

            lastLine = lineInfo;
            current = step.fromStation;
        }

        // Ausgabe der Fahrtenschritte
        for (String detail : pathDetails) {
            System.out.println(detail);
        }

    }
}