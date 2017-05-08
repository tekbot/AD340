package com.example.jeremy.networkoperationstutorial;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static android.support.v4.net.ConnectivityManagerCompat.RESTRICT_BACKGROUND_STATUS_DISABLED;
import static android.support.v4.net.ConnectivityManagerCompat.RESTRICT_BACKGROUND_STATUS_ENABLED;
import static android.support.v4.net.ConnectivityManagerCompat.RESTRICT_BACKGROUND_STATUS_WHITELISTED;

public class MainActivity extends FragmentActivity implements DownloadCallback {

    // Keep a reference to the NetworkFragment, which owns the AsyncTask object
    // that is used to execute network ops.
    private NetworkFragment mNetworkFragment;

    // Boolean telling us whether a download is in progress, so we don't trigger overlapping
    // downloads with consecutive button clicks.
    private boolean mDownloading = false;


    private void startDownload() {
        if (!mDownloading && mNetworkFragment != null) {
            // Execute the async download.
            mNetworkFragment.startDownload();
            mDownloading = true;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (isOnline()) {
            mNetworkFragment = NetworkFragment.getInstance(getSupportFragmentManager(), "https://www.google.com");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (connMgr.isActiveNetworkMetered()) {
            // Checks user’s Data Saver settings.
            switch (connMgr.getRestrictBackgroundStatus()) {
                case RESTRICT_BACKGROUND_STATUS_ENABLED:
                case RESTRICT_BACKGROUND_STATUS_WHITELISTED:
                case RESTRICT_BACKGROUND_STATUS_DISABLED:
                    return false;
            }
        }
        return (networkInfo != null && networkInfo.isConnected());
    }

    @Override
    public void updateFromDownload(Object result) {

    }

    @Override
    public NetworkInfo getActiveNetworkInfo() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo;
    }

    @Override
    public void onProgressUpdate(int progressCode, int percentComplete) {
        switch(progressCode) {
            // You can add UI behavior for progress updates here.
            case Progress.ERROR:
//            ...
                break;
            case Progress.CONNECT_SUCCESS:
//            ...
                break;
            case Progress.GET_INPUT_STREAM_SUCCESS:
//            ...
                break;
            case Progress.PROCESS_INPUT_STREAM_IN_PROGRESS:
//            ...
                break;
            case Progress.PROCESS_INPUT_STREAM_SUCCESS:
//            ...
                break;
        }
    }

    @Override
    public void finishDownloading() {
        mDownloading = false;
        if (mNetworkFragment != null) {
            mNetworkFragment.cancelDownload();
        }
    }

}
