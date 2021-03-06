package com.udacity.nanodegree.advait.builditbiggerv4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.nanodegree.advait.builditbiggerv4.R;
import com.udacity.nanodegree.IAsyncTaskListener;
import com.udacity.nanodegree.advait.jokeandroidlib.JokeActivity;
import com.udacity.nanodegree.JokeAsyncTask;
import com.udacity.nanodegree.advait.jokeandroidlib.JokeFragment;

/**
 * Created by Advait on 8/11/2017.
 */

public class MainActivity extends FragmentActivity implements IAsyncTaskListener {
    public static final String TAG = MainActivity.class.getCanonicalName();
    private Button button;
    private String joke;
    private InterstitialAd interstitialAd;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.jokeButton);
        progressBar = findViewById(R.id.progressBar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {
                    Log.d(TAG, "The interstitial wasn't loaded yet.");
                }
                JokeAsyncTask jokeAsyncTask = new JokeAsyncTask(MainActivity.this);
                jokeAsyncTask.execute();

            }
        });
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(this.getString(R.string.banner_ad_unit_id));
        interstitialAd.loadAd(new AdRequest.Builder().build());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(button.getVisibility() == View.GONE) {
            button.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResult(Object o) {
        progressBar.setVisibility(View.GONE);
        joke = (String) o;
        if (!TextUtils.isEmpty(joke)) {
            Intent jokeIntent = new Intent(MainActivity.this, JokeActivity.class);
            jokeIntent.putExtra(JokeFragment.JOKE_KEY, joke);
            startActivity(jokeIntent);
        } else {
            Toast.makeText(this, R.string.error_text, Toast.LENGTH_SHORT).show();
            button.setVisibility(View.VISIBLE);
        }
    }
}
