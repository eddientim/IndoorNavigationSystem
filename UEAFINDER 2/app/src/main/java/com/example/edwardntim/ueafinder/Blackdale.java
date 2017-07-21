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
import java.io.InputStream;
import java.util.ArrayList;

public class Blackdale extends AppCompatActivity {


    Button mRouteButton;
    Spinner mBlackdaleStartSpinner, mBlackdaleEndSpinner;
    TextView mRouteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blackdale);

        mRouteButton = (Button) findViewById(R.id.routeButton);
        mBlackdaleStartSpinner = (Spinner) findViewById(R.id.blackStart);
        mBlackdaleEndSpinner = (Spinner) findViewById(R.id.blackEnd);
        mRouteText = (TextView) findViewById(R.id.outputText);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.bds_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown);
        mBlackdaleStartSpinner.setAdapter(adapter);
        mBlackdaleEndSpinner.setAdapter(adapter);

    }

    public void doRoute(View view) throws IOException {

        final String start = mBlackdaleStartSpinner.getSelectedItem().toString();
        final String end = mBlackdaleEndSpinner.getSelectedItem().toString();

        final String route = RouteHelper.getRoute(getApplicationContext(), R.raw.bdsnodes, start, end);
        final ArrayList<String> nodes = RouteHelper.getRouteNodes(route);

        //   final Intent intent = new Intent(this, DisplayRouteActivity.class);
        final Intent intent = new Intent(this, DisplayImageActivity.class);
        intent.putExtra(RouteHelper.ROUTE, route);
        intent.putExtra(RouteHelper.BUILDING, this.getClass().getCanonicalName());

        intent.putExtra(RouteHelper.NODES, nodes);
        intent.putExtra(RouteHelper.PAGE_NO, 0);

        startActivity(intent);
    }
    //Previous page button
    public void returnToHomeFromBlackdale(View view) {

        Intent intent = new Intent(this, MainActivity2.class);

        startActivity(intent);
    }

}