package com.udacity.nanodegree;

import android.os.AsyncTask;

import com.udacity.nanodegree.advait.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import java.io.IOException;

/**
 * Created by Advait on 8/10/2017.
 */

public class JokeAsyncTask  extends AsyncTask<Void, Void, String> {
    private static MyApi myApiService = null;
    private IAsyncTaskListener iAsyncTaskListener;
    private String result;
    public JokeAsyncTask(IAsyncTaskListener iAsyncTaskListener) {
        this.iAsyncTaskListener = iAsyncTaskListener;
    }


    @Override
    protected String doInBackground(Void... params) {
        if(myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("http://192.168.1.28:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }

        try {
            result = myApiService.getJokes().execute().getData();
            return result;
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        this.result = result;
        iAsyncTaskListener.onResult(result);
    }
}
