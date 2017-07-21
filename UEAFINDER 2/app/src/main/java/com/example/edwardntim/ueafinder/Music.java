package com.example.edwardntim.ueafinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;

public class Music extends AppCompatActivity {

    Button mRouteButton;
    Spinner mMusicStartSpinner, mMusicEndSpinner;
    TextView mRouteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_building);

        mRouteButton = (Button) findViewById(R.id.routeButton);
        mMusicStartSpinner = (Spinner) findViewById(R.id.musicEnd);
        mMusicEndSpinner = (Spinner) findViewById(R.id.musicStart);
        mRouteText = (TextView) findViewById(R.id.outputText);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.music_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown);
        mMusicStartSpinner.setAdapter(adapter);
        mMusicEndSpinner.setAdapter(adapter);

    }
    public void doRoute(View view) throws IOException {

        final String selectedSLocation = mMusicStartSpinner.getSelectedItem().toString();
        final String selectedELocation = mMusicEndSpinner.getSelectedItem().toString();


        final String route = GraphHelper.route(selectedSLocation, selectedELocation, getResources().openRawResource(R.raw.musicnodes));
        final Intent intent = new Intent(this, DisplayRouteActivity.class);

        intent.putExtra(RouteHelper.ROUTE, route);
        intent.putExtra(RouteHelper.BUILDING, this.getClass().getCanonicalName());



        startActivity(intent);
    }
    //Previous page button
    public void returnToHomeFromMusic(View view) {

        Intent intent = new Intent(this, MainActivity2.class);

        startActivity(intent);
    }

}