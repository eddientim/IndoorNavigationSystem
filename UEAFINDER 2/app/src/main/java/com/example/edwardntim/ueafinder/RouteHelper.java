package com.example.edwardntim.ueafinder;

import android.content.Context;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by edwardntim on 29/04/2017.
 */
public class RouteHelper {

    public static final String ROUTE = "com.example.ueafinder.ROUTE";
    public static final String BUILDING = "com.example.ueafinder.BUILDING";

    public static final String PAGE_NO = "com.example.ueafinder.PAGE";
    public static final String NODES = "com.example.ueafinder.NODES";

    public static String reverseRoute(final String route) {

        final List<String> nodes = Arrays.asList(route.split(" -> "));
        Collections.reverse(nodes);

        return StringUtils.join(nodes, " -> ");
    }

    public static List<String> splitRoute(String splitRoute) {

        return Arrays.asList(splitRoute.split(" -> "));

    }

    public static ArrayList<String> getRouteNodes(final String route) {
        return new ArrayList<String>(Arrays.asList(route.split( " -> ")));
    }

    public static String getRoute(final Context context, final int nodeFile,
                                  final String start, final String end) {

        final List<Graph.Edge> graph = buildGraphFromFile(context, nodeFile);
        Graph g = new Graph(graph);
        g.dijkstra(start);
        return g.printPath(end);
    }

    private static List<Graph.Edge> buildGraphFromFile(final Context context, final int nodeFile)
    {
        final InputStream inputStream = context.getResources().openRawResource(nodeFile);
        final List<Graph.Edge> graph = new ArrayList<>();
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String nodes;

        try {
            while ((nodes = reader.readLine()) != null) {
                String [] values = nodes.split(",");
                graph.add(new Graph.Edge(values[0], values[1], Integer.valueOf(values[2])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return graph;
    }






}

