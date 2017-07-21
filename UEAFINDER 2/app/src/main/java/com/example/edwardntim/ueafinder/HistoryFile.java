package com.example.edwardntim.ueafinder;

/**
 * Created by edwardntim on 06/05/2017.
 */



import java.util.List;
import java.util.Map;




public class HistoryFile
{
    final String filename;
    final Map<String, List<String>> buildingToRoutesMap;

    public HistoryFile(final String filename, final Map<String, List<String>> buildingToRoutesMap)
    {
        this.filename = filename;
        this.buildingToRoutesMap = buildingToRoutesMap;
    }

    public Map<String, List<String>> getHistoryMap()
    {
        return buildingToRoutesMap;
    }

    public String getFilename()
    {
        return filename;
    }
}




