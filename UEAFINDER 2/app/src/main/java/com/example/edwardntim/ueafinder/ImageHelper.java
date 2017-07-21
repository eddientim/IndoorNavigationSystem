package com.example.edwardntim.ueafinder;

import java.lang.reflect.Field;


/**
 * Created by edwardntim on 16/05/2017.
 */

public class ImageHelper {

    private static final String underScore = "_";

    public static int extractImageIdForNode(final String nodeName, final String building)
    {
        final String cleanNodeName = cleanNodeName(nodeName); //Fire exit -> fireexit
        String clean = cleanBuilding(building);
        try {
            final Class drawableFolder = R.drawable.class;

           final String image = clean + underScore + cleanNodeName;
            Field field = drawableFolder.getField(image);

            return field.getInt(null);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    private static String cleanNodeName(final String nodeName)
    {
        //nodeName.toLowerCase: Fire exit -> fire exit
        //replaceAll: fire exit -> fireexit
        return nodeName.toLowerCase().replaceAll("\\s|\\.|\\/", "");
    }
    //Index pages from 0;
    public static boolean hasPrevPage(final int currentPage, final int numberOfPages)
    {
        return currentPage != 0 && numberOfPages != 1;
    }

    //Index pages from 0
    public static boolean hasNextPage(final int currentPage, final int numberOfPages)
    {
        //2 pages, but numbered 0 and 1
        return currentPage != numberOfPages-1 && numberOfPages != 1;
    }

    private static String cleanBuilding(String building){

        final String[] splitPackageName = building.split("\\.");
        final String buildingName = splitPackageName[splitPackageName.length - 1].toLowerCase();

        return buildingName;
    }

}

