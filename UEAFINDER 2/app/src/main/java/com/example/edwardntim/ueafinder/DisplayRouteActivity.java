package com.example.edwardntim.ueafinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.content.Context;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.example.edwardntim.ueafinder.R;
import com.example.edwardntim.ueafinder.HistoryHelper;
import com.example.edwardntim.ueafinder.RouteHelper;



public class DisplayRouteActivity extends AppCompatActivity {

    String route;
    String building;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Generic layout name
        setContentView(R.layout.display_route);

        Intent intent = getIntent();
        route = intent.getStringExtra(RouteHelper.ROUTE);
        building = intent.getStringExtra(RouteHelper.BUILDING);
        // Capture the layout's TextView and set the string as its text
        TextView textView = (TextView) findViewById(R.id.Path);
        textView.setText(route);

    }

    //Previous page button
    public void returnToPreviousPage(View view) throws ClassNotFoundException {

        Intent intent = new Intent(this, Class.forName(building));
        startActivity(intent);
    }

    //Reverse Route Button
    public void displayReverseRoute(View view) {

        final String reverseRoute = RouteHelper.reverseRoute(route);

        final Intent intent = new Intent(this, DisplayRouteActivity.class);
        intent.putExtra(RouteHelper.ROUTE, reverseRoute);
        intent.putExtra(RouteHelper.BUILDING, building);


        startActivity(intent);
    }


    public void saveRoute (View view) throws IOException {
        //Split on the literal dot rather than match any characters
        final String[] splitPackageName = building.split("\\.");
        final String buildingToSave = splitPackageName[splitPackageName.length-1];
        //Split on the literal dot rather than match any characters
        final String routeToSave = route;

        final HistoryHelper historyHelper = new HistoryHelper();

        final FileInputStream inputStream = openFileInput("history.csv");

        final HistoryFile fileToUpdate = historyHelper.readRouteHistoryFromFile("history.csv", this);

        final boolean savedDisplay = historyHelper.saveRouteHistory(fileToUpdate,buildingToSave,routeToSave, getApplicationContext());

        String notification = savedDisplay ? "You have saved this route!" : "Oops something went wrong!";
        new AlertDialog.Builder(this)
                .setMessage(notification)
                .show();
    }
}

