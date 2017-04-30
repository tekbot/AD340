package com.example.jeremy.hw3_addinglayouts;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "Derpy Sherpa";
    private static final String TAG = MainActivity.class.getSimpleName();
    String[] demoArray = {"RecycleView", "Toast 1", "Toast 2", "Toast 3" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() Main created");

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        setContentView(R.layout.activity_main);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ButtonAdapter(this));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    /** Called when the user taps a grid button */
    public void showRecyclerView(int id) {
        if (id == 0) {
            Intent intentR = new Intent(this, RecyclerActivity.class);
            startActivity(intentR);
        } else {
            Toast.makeText(MainActivity.this, "" + id,  Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class ButtonAdapter extends BaseAdapter {
        private Context mContext;

        public ButtonAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return demoArray.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            Button button;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                button = new Button(mContext);
            } else {
                button = (Button) convertView;
            }

            button.setText(demoArray[position]);
            button.setId(position);
            button.setOnClickListener(new BtnOnClickListener(position));
            return button;
        }

        class BtnOnClickListener implements View.OnClickListener
        {
            private final int position;
            public BtnOnClickListener(int position)
            {
                this.position = position;
            }
            public void onClick(View v)
            {
                Log.d(TAG, "tapped button");
                showRecyclerView(v.getId());
            }
        }

    }
}

