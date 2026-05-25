package algos_programmieraufgabe3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ALGOS_Programmieraufgabe3 {

    public static void main(String[] args) {
        Graph graph = new Graph();
        String filepath = "verkehrsnetz.txt";

        Pattern stationPattern = Pattern.compile("\"([^\"]+)\"\\s*(\\d+)?");

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(":", 2);
                String lineName = parts[0].trim();
                String remainder = parts[1].trim();

                Matcher matcher = stationPattern.matcher(remainder);

                String currentStation = null;
                int currentWeight = -1;

                while (matcher.find()) {
                    String stationName = matcher.group(1);

                    if (currentStation != null && currentWeight != -1) {
                        Node vonKnoten = new Node(currentStation);
                        Node zuKnoten = new Node(stationName);

                        Edge kante = new Edge(zuKnoten, lineName, currentWeight);

                        graph.addNode(vonKnoten);
                        graph.addNode(zuKnoten);

                        graph.addEdge(vonKnoten, kante);

                        Edge rueckKante = new Edge(vonKnoten, lineName, currentWeight);
                        graph.addEdge(zuKnoten, rueckKante);
                    }

                    currentStation = stationName;
                    String weightStr = matcher.group(2);
                    currentWeight = (weightStr != null) ? Integer.parseInt(weightStr) : -1;
                }
            }

            System.out.println("Verkehrsnetz erfolgreich eingelesen.");

            Scanner scanner = new Scanner(System.in);

            System.out.print("Start-Haltestelle eingeben: ");
            String start = scanner.nextLine();

            System.out.print("Ziel-Haltestelle eingeben: ");
            String ziel = scanner.nextLine();

            DijkstraPathFinder.findAndPrintShortestPath(graph, start, ziel);

            scanner.close();

        } catch (Exception e) {
            System.out.println("Fehler beim Einlesen oder Verarbeiten der Datei: " + e.getMessage());
            e.printStackTrace();
        }
    }
}