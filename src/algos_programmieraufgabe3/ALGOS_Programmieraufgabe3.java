/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package algos_programmieraufgabe3;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author User
 */
public class ALGOS_Programmieraufgabe3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
        Path p =  Paths.get("verkehrsnetz.txt");
        List<String> lines = new ArrayList<String>();
        Files.lines(p).forEach(line-> lines.add(line));
        Graph graph = new Graph();
        for(String line : lines){
            String[] linePart = line.split(":");
            String lineName = linePart[0];
            String stops = linePart[1].trim().substring(1);
            //System.out.println(stops);
            List<String> stopsList =  Arrays.asList(stops.split("\""));
            stopsList = stopsList.stream().map(String::trim).collect(Collectors.toList());
            for(int i=0;i< stopsList.size();i++){
                
                Node a = new Node(stopsList.getFirst());
                stopsList.removeFirst();
                int weight = Integer.parseInt(stopsList.getFirst());
                stopsList.removeFirst();
                Node b = new Node(stopsList.getFirst());
                graph.addNode(a);
                graph.addNode(b);
                System.out.println("adde node "+ a.getStationName() +","+b.getStationName()+" that are connected with weight " + weight);
                graph.addEdge(a, new Edge(b,lineName,weight));
                graph.addEdge(b, new Edge(a,lineName,weight));
            }
            
        }
        } catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    
}
