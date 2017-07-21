package com.example.edwardntim.ueafinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;




public class MainActivity extends AppCompatActivity {

    Button BSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BSearch = (Button) findViewById(R.id.search);
    }

    public void goToContents(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }



        //Start a new activity of "SelectBuildingActivity
      //final Intent intent = new Intent(this, SelectBuildingActivity.class);



        //SelectBuildingActivity will then need to do the following code:
        //In order to make a spinner


    public void goToSavedHistory(View view) throws IOException {

        final HistoryHelper historyHelper = new HistoryHelper();
        openFileOutput ("history.csv" ,MODE_APPEND);
        final FileInputStream inputStream = openFileInput("history.csv");
        final HistoryFile historyFile = historyHelper.readRouteHistoryFromFile("history.csv", this);
        final ArrayList<String> savedBuildings = historyHelper.getSavedBuildings(historyFile);
        final Intent intent = new Intent(this, SelectBuildingActivity.class);
        intent.putExtra(HistoryHelper.SAVED_BUILDINGS, savedBuildings );
        startActivity(intent);
    }

    }





