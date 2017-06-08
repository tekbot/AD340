package com.tekbot.jeremy.hw7_location;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecyclerActivity extends AppCompatActivity {
    private static final String TAG = "RecyclerActivity";
    Context context;
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recylerViewLayoutManager;
    String[][] strings = {
            {"Doctorow, Cory","Overclocked"},
            {"Doctorow, Cory","Down and Out in the Magic Kingdom"},
            {"Stross, Charles","Saturn's Children"},
            {"Stross, Charles", "The Atrocity Archives"},
            {"Stross, Charles", "Accelerando"},
            {"Stross, Charles", "Glasshouse"},
            {"Stross, Charles", "Iton Sunrise"},
            {"Herbert, Frank", "Dune"},
            {"Gaiman, Neil", "American Gods"},
            {"Gaiman, Neil", "Good Omens"},
            {"Gaiman, Neil", "Fragile Things"},
            {"Repino, Robert", "MORT(E)"},
            {"Weir, Andy", "The Martian"},
            {"Cline, Ernest", "Ready Player One"},
            {"Anderson, Poul", "Tau Zero"},
            {"Asimov, Isaac", "Foundation Series"},
            {"Gibson, William", "Neuromancer"},
            {"Gibson, William", "The Difference Engine"},
            {"Stephenson, Neal", "Anathem"},
            {"Stephenson, Neal", "In the Beginning Was the Command Line"},
            {"Stephenson, Neal", "Cobweb"},
            {"Stephenson, Neal", "Snow Crash"},
            {"Stephenson, Neal", "Quicksilver"},
            {"Stephenson, Neal", "The Confusion"},
            {"Stephenson, Neal", "The System of the World"},
            {"Stephenson, Neal", "Seveneves"}
    };
    String[][] responseData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = getApplicationContext();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);
        recylerViewLayoutManager = new LinearLayoutManager(context);

        // use a linear layout manager
        recylerViewLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recylerViewLayoutManager);

        //Network Stuff
        String url = "http://brisksoft.us/ad340/android_terms.json";
        RequestQueue queue = Volley.newRequestQueue(this);
        if (isOnline()) {
            JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    String res = response.toString();
                    Log.d(TAG, "Volley Success: " + res);
                    responseData = new String[response.length()][2];
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject blob = (JSONObject) response.get(i);
                            responseData[i][0] = blob.getString("title");
                            String desc = blob.getString("description");
                            String subtitle = blob.getString("subtitle");
                            if (desc.equals("")) {
                                if (subtitle.equals("")) {
                                    desc = "No Description";
                                } else {
                                    desc = subtitle;
                                }
                            }
                            responseData[i][1] = desc;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    strings = responseData;
                    recyclerViewAdapter = new CustomAdapter();
                    recyclerView.setAdapter(recyclerViewAdapter);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, "JSONArrayRequest Error: " + error.getMessage());
                    recyclerViewAdapter = new CustomAdapter();
                    recyclerView.setAdapter(recyclerViewAdapter);
                }
            });
            queue.add(jsonRequest);
            //End Network Stuff
        } else {
            recyclerViewAdapter = new CustomAdapter();
            recyclerView.setAdapter(recyclerViewAdapter);
            Toast.makeText(this, "No Internet Connection Found, Loading Cached Data", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
        public class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            TextView sub1;
            TextView sub2;
            public ViewHolder(View v) {
                super(v);
                sub1 = (TextView) v.findViewById(R.id.subject_1);
                sub2 = (TextView) v.findViewById(R.id.subject_2);
            }
        }

        @Override
        public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // Inflate the view for this view holder
            View item = getLayoutInflater().inflate(R.layout.list_item2, parent, false);

            // Call the view holder's constructor, and pass the view to it;
            // return that new view holder
            return new ViewHolder(item);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            String separator = "    -   ";
            String author = strings[position][0] + separator;
            String title = strings[position][1];
            holder.sub1.setText(author);
            holder.sub2.setText(title);
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return strings.length;
        }
    }
}