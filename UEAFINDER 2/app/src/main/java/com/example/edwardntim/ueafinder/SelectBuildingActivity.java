package com.example.edwardntim.ueafinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by edwardntim on 09/05/2017.
 */


public class SelectBuildingActivity extends android.app.ListActivity {

    String selectedBuilding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final List<String> savedBuildings = getIntent().getStringArrayListExtra(HistoryHelper.SAVED_BUILDINGS);

        setListAdapter(new ArrayAdapter<String>(this, R.layout.select_building, savedBuildings));

        final ListView listView = getListView();
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
                selectedBuilding = (String) (listView.getItemAtPosition(myItemInt));
                showRoutesForSelectedBuilding(selectedBuilding);

            }
        });
    }

    private void showRoutesForSelectedBuilding(String  selectedBuilding) {

        final HistoryHelper historyHelper = new HistoryHelper();

        try {

            openFileOutput("history.csv", MODE_APPEND);
            final FileInputStream inputStream = openFileInput("history.csv");
            final HistoryFile historyFile = historyHelper.readRouteHistoryFromFile("history.csv", this);
            String route = historyHelper.printSavedRoutesForBuilding(historyFile, selectedBuilding);
            final Intent intent = new Intent(this, ShowRouteHistory.class);
            intent.putExtra(HistoryHelper.SAVED_ROUTES, route);
            startActivity(intent);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


        //Previous page button

    public void returnToHomeFromMedical(View view) {

        Intent intent = new Intent(this, MainActivity2.class);

        startActivity(intent);
    }


}
