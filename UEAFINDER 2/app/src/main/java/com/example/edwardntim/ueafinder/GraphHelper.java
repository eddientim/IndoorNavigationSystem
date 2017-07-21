package com.example.edwardntim.ueafinder;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class GraphHelper {




    public static List<Graph.Edge> buildGraphFromFile(final InputStream input) throws IOException
    {

        List<Graph.Edge> graph = new ArrayList<>();

        //calls the txt file to read the file in.
        final BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        String nodes;

        //reads the line of text.
        while ((nodes = reader.readLine()) != null) {

            //reads the next value after the comma.
            String [] values = nodes.split(",");

            //Splits the values into String, String, Integer
            graph.add(new Graph.Edge(values[0], values[1], Integer.valueOf(values[2])));
        }
        //returns the arraylist
        return graph;
    }




    public static String route(String start, String end, InputStream inputStream) throws IOException {

        List<Graph.Edge> myGRAPH = GraphHelper.buildGraphFromFile(inputStream);

        Graph g = new Graph(myGRAPH);
        g.dijkstra(start);
        String route = g.printPath(end);

        return route;
    }


}
