package com.example.edwardntim.ueafinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.ApplicationTestCase;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;

public class Julian extends AppCompatActivity {

        Button mRouteButton;
        Spinner mJulianStartSpinner, mJulianEndSpinner;
        TextView mRouteText;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.julian_study);

                mRouteButton = (Button) findViewById(R.id.routeButton);
                mJulianStartSpinner = (Spinner) findViewById(R.id.julianStart);
                mJulianEndSpinner = (Spinner) findViewById(R.id.julianEnd);
                mRouteText = (TextView) findViewById(R.id.outputText);

                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.julian_array, R.layout.spinner_item);
                adapter.setDropDownViewResource(R.layout.spinner_dropdown);
                mJulianStartSpinner.setAdapter(adapter);
                mJulianEndSpinner.setAdapter(adapter);

        }
        public void doRoute(View view) throws IOException {

                final String selectedSLocation = mJulianStartSpinner.getSelectedItem().toString();
                final String selectedELocation = mJulianEndSpinner.getSelectedItem().toString();

                final String route = GraphHelper.route(selectedSLocation, selectedELocation, getResources().openRawResource(R.raw.jscnodes));
                getResources().openRawResource(R.raw.jscnodes);

                final Intent intent = new Intent(this, DisplayRouteActivity.class);


                intent.putExtra(RouteHelper.ROUTE, route);
                intent.putExtra(RouteHelper.BUILDING, this.getClass().getCanonicalName());

                startActivity(intent);
        }
        //Previous page button
        public void returnToHomeFromJulian(View view) {

                Intent intent = new Intent(this, MainActivity2.class);

                startActivity(intent);
        }

}