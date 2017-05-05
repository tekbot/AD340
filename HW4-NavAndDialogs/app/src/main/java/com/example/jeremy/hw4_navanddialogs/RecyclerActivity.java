package com.example.jeremy.hw4_navanddialogs;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecyclerActivity extends AppCompatActivity {
    private static final String TAG = "RecyclerActivity";
    Context context;
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recylerViewLayoutManager;
    String[][] books = {
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

        recyclerViewAdapter = new CustomAdapter();
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
        public class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            TextView mAuthor;
            TextView mTitle;
            public ViewHolder(View v) {
                super(v);
                mAuthor = (TextView) v.findViewById(R.id.subject_1);
                mTitle = (TextView) v.findViewById(R.id.subject_2);
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
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            String separator = "    -   ";
            String author = books[position][0] + separator;
            String title = books[position][1];
            holder.mAuthor.setText(author);
            holder.mTitle.setText(title);
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return books.length;
        }
    }
}