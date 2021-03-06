package com.udacity.nanodegree.advait.builditbiggerv4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.udacity.nanodegree.IAsyncTaskListener;
import com.udacity.nanodegree.JokeAsyncTask;
import com.udacity.nanodegree.advait.builditbiggerv4.R;
import com.udacity.nanodegree.advait.jokeandroidlib.JokeActivity;
import com.udacity.nanodegree.advait.jokeandroidlib.JokeFragment;

public class MainActivity extends AppCompatActivity implements IAsyncTaskListener {

    private Button button;
    private String joke;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.jokeButton);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                JokeAsyncTask jokeAsyncTask = new JokeAsyncTask(MainActivity.this);
                jokeAsyncTask.execute();
            }
        });
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
            Toast.makeText(this, getString(R.string.error_text), Toast.LENGTH_SHORT).show();
            button.setVisibility(View.VISIBLE);
        }
    }
}
