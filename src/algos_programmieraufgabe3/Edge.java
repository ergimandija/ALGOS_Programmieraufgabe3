package algos_programmieraufgabe3;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
public class Edge {
    private Node destination;
    private String line;
    private int weight;

    public Edge(Node destination, String line, int weight) {
        this.destination = destination;
        this.line = line;
        this.weight = weight;
    }

    public Node getDestination() { return destination; }
    public String getLine() { return line; }
    public int getWeight() { return weight; }
    
}
