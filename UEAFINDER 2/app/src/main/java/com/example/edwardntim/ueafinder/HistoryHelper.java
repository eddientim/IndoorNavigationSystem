package com.example.edwardntim.ueafinder;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class HistoryHelper

{

    public static final String SAVED_BUILDINGS = "com.example.ueafinder.SAVED_BUILDINGS";
    public static final String SAVED_ROUTES = "com.example.ueafinder.SAVED_ROUTES";

    public boolean saveRouteHistory(final HistoryFile file, final String building, final String route,
                                    final Context context) {
        try {

            final FileOutputStream fileOutputStream = context.openFileOutput("history.csv", context.MODE_APPEND);
            final Map<String, List<String>> history = file.getHistoryMap();

            if (history.containsKey(building)) {
                final List<String> savedRoutes = history.get(building);
                savedRoutes.add(route);
            } else {
                final List<String> routes = new ArrayList<>();
                routes.add(route);
                history.put(building, routes);
            }

            writeRouteHistoryToFile(history, fileOutputStream);
            return true;


        } catch (FileNotFoundException e) {
            e.printStackTrace();

            return false;
        }

    }

    public HistoryFile readRouteHistoryFromFile(final String filename, final Context context) {
        final Map<String, List<String>> buildingToRoutesMap = new HashMap<>();

        try {

            context.openFileOutput(filename, context.MODE_APPEND);
            InputStream inputStream = context.openFileInput(filename);
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                final String[] buildingToRoutes = line.split(",");

                final String building = buildingToRoutes[0];
                final String[] routes = ArrayUtils.subarray(buildingToRoutes, 1, buildingToRoutes.length);
                final List<String> routeList = new ArrayList<>(Arrays.asList(routes));
                buildingToRoutesMap.put(building, routeList);
            }

            bufferedReader.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return new HistoryFile(filename, buildingToRoutesMap);
    }

    public String printSavedRoutesForBuilding(final HistoryFile historyFile, final String building) {
        final StringBuilder printRoutes = new StringBuilder("");

        final List<String> routesForBuilding = getRoutesForBuilding(historyFile, building);
        for (final String route : routesForBuilding) {
            printRoutes.append(route).append("\n\n\n");
        }

        return printRoutes.toString();
    }

    public ArrayList<String> getSavedBuildings(final HistoryFile file) {
        return new ArrayList<>(file.getHistoryMap().keySet());
    }

    private List<String> getRoutesForBuilding(final HistoryFile file, final String building) {
        final Map<String, List<String>> history = file.getHistoryMap();

        if (history.containsKey(building)) {
            return history.get(building);
        }

        return Collections.emptyList();
    }

    private void writeRouteHistoryToFile(final Map<String, List<String>> historyMap, final FileOutputStream outputStream) {
        try {

            final BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

            String historyForBuilding;

            for (final String building : historyMap.keySet()) {
                historyForBuilding = building + "," + StringUtils.join(historyMap.get(building), ","); // "Medical,a,b,c"
                bufferedWriter.write(historyForBuilding);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveRoute(String building, String route, Context context)
    {



            final String[] splitPackageName = building.split("\\.");
            final String buildingToSave = splitPackageName[splitPackageName.length - 1];//Split on the literal dot rather than match any characters
            final String routeToSave = route;

            final HistoryHelper historyHelper = new HistoryHelper();

            final HistoryFile fileToUpdate = historyHelper.readRouteHistoryFromFile("history.csv", context);

            final boolean savedDisplay = historyHelper.saveRouteHistory(fileToUpdate, buildingToSave, routeToSave, context);

            String notification = savedDisplay ? "You have saved this route!" : "Oops something went wrong!";
            new AlertDialog.Builder(context)
                    .setMessage(notification)
                    .show();






    }
}
