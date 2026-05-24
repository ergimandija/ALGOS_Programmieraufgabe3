package algos_programmieraufgabe3;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
public class Graph {
    private HashMap<String,ArrayList<Edge>> adjList;
   
    Graph(){
        adjList = new HashMap<>();
    }
   
    public void addEdge(Node n, Edge e){
        adjList.get(n.getStationName()).add(e);
    }
    
    public void addNode(Node n){
        adjList.putIfAbsent(n.getStationName(), new ArrayList<>());
    }
    
    public  ArrayList<Edge> getNeighbors(Node n){
        return adjList.get(n.getStationName());
    }
    
    public Set<String> getNodes() {
        return adjList.keySet();
    }
    
}

