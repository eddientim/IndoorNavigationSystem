package com.example.edwardntim.ueafinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.edwardntim.ueafinder.R;

/**
 * Created by edwardntim on 07/05/2017.
 */
public class ShowRouteHistory extends AppCompatActivity {

    Button mRouteButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_history);

//        mRouteButton = (Button) findViewById(R.id.routeButton);

        final String savedRoutes = getIntent().getStringExtra(HistoryHelper.SAVED_ROUTES);

        TextView textView = (TextView) findViewById(R.id.route);
        textView.setText(savedRoutes);
        textView.setMovementMethod(new ScrollingMovementMethod());

    }
    public void returnToMainActivity(View view) {

        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }



}
