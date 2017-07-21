package com.example.edwardntim.ueafinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity2 extends android.app.ListActivity {


    //ListView mList;
    static final String[] LOCATIONS = new String[]{"Blackdale(BDS)",
                                     "Julian Study Centre (JSC)",
            "Lecture Theatre Building (LT)", "Norwich Medical School(MED)", "Multifaith Centre(CHAP)", "Music Building(MUS)","Return to homescreen"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_main_2, LOCATIONS));

        ListView listView = getListView();
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                if (position == 0) {
                    Intent myintent = new Intent(view.getContext(), Blackdale.class);
                    startActivityForResult(myintent, 0);
                }

                if (position == 1) {
                    Intent myintent = new Intent(view.getContext(), Julian.class);
                    startActivityForResult(myintent, 1);
                }

                if (position == 2) {
                    Intent myintent = new Intent(view.getContext(), Lecture.class);
                    startActivityForResult(myintent, 2);
                }
                if (position == 3) {
                    Intent myintent = new Intent(view.getContext(), Medical.class);
                    startActivityForResult(myintent, 3);
                }
                if (position == 4) {
                    Intent myintent = new Intent(view.getContext(), Multifaith.class);
                    startActivityForResult(myintent, 4);
                }
                if (position == 5) {
                    Intent myintent = new Intent(view.getContext(), Music.class);
                    startActivityForResult(myintent, 5);
                }
                if (position == 6) {
                    Intent myintent = new Intent(view.getContext(), MainActivity.class);
                    startActivityForResult(myintent, 6);
                }


            }
        });
    }

    public void returnToHomeScreen(View view) {

        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }


}





