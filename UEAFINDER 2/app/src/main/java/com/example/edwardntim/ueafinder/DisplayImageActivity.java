package com.example.edwardntim.ueafinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayImageActivity extends AppCompatActivity {

    private int currentPage;
    private ArrayList<String> nodes;
    private int totalPages;

    private  String building;
    private String route;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image);


        final Intent intent = getIntent();
        route = intent.getStringExtra(RouteHelper.ROUTE);
        building = intent.getStringExtra(RouteHelper.BUILDING);



        //Get the current pages and list of nodes passed in from the intent

        currentPage = intent.getIntExtra(RouteHelper.PAGE_NO, 0);
        nodes = getIntent().getStringArrayListExtra(RouteHelper.NODES);
        totalPages = nodes.size();

        //Get the node we want to display and extract its image id dynamically
        final String imageToDisplay = nodes.get(currentPage); //Fire exit
        final int imageId = ImageHelper.extractImageIdForNode(imageToDisplay,building);

        TextView textView = (TextView) findViewById(R.id.textView4);
        textView.setText(imageToDisplay);

        final ImageView imageView = (ImageView)findViewById(R.id.imageView);
        if (imageId == -1) //Then we weren't able to find an image for that node
        {
            imageView.setImageResource(R.drawable.logo); //Set a default image
        }
        else
        {
            imageView.setImageResource(imageId);
        }


        //Determine whether we want to show the next/prev page buttons
        final Button previousButton = (Button)findViewById(R.id.previous);
        if (ImageHelper.hasPrevPage(currentPage, totalPages)) {
            previousButton.setVisibility(View.VISIBLE);
        }

        final Button nextButton = (Button)findViewById(R.id.next);
        if (ImageHelper.hasNextPage(currentPage, totalPages)) {
            nextButton.setVisibility(View.VISIBLE);
        }
    }

    //Previous page button
    public void previousImage(View view) {
        final Intent intent = new Intent(this, DisplayImageActivity.class);
        intent.putExtra(RouteHelper.PAGE_NO, currentPage - 1);
        intent.putExtra(RouteHelper.NODES, nodes);
        intent.putExtra(RouteHelper.BUILDING, building);
        intent.putExtra(RouteHelper.ROUTE, route);
        startActivity(intent);
    }

    //Next image
    public void nextImage(View view) {
        final Intent intent = new Intent(this, DisplayImageActivity.class);
        intent.putExtra(RouteHelper.PAGE_NO, currentPage + 1);
        intent.putExtra(RouteHelper.NODES, nodes);
        intent.putExtra(RouteHelper.BUILDING, building);
        intent.putExtra(RouteHelper.ROUTE, route);
        startActivity(intent);
    }

    public void savebutton (View view)  {

        HistoryHelper.saveRoute(building, route,this);

    }

    public void returnToPreviousPage(View view) throws ClassNotFoundException {

        Intent intent = new Intent(this, Class.forName(building));
        startActivity(intent);
    }

}









