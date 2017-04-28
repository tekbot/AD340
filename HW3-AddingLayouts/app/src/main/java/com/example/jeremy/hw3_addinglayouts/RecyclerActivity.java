package com.example.jeremy.hw3_addinglayouts;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Window;
import android.widget.RelativeLayout;

public class RecyclerActivity extends AppCompatActivity {
    private static final String TAG = "RecyclerActivity";
    Context context;
    RecyclerView recyclerView;
    RelativeLayout relativeLayout;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    String[][] subjects = {
        {"ANDROID","1"},
        {"PHP","2"},
        {"BLOGGER","3"},
        {"WORDPRESS", "4"},
        {"JOOMLA", "5"},
        {"ASP.NET", "6"},
        {"JAVA", "7"},
        {"C++", "8"},
        {"JAVASCRIPT", "9"},
        {"NODEJS", "10"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);

        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        relativeLayout = (RelativeLayout) findViewById(R.id.relativelayout1);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);
        recyclerViewLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(context, subjects);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
