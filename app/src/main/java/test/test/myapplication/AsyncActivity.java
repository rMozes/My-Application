package test.test.myapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.github.kevinsawicki.http.HttpRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by BruSD on 9/30/2014.
 */
public class AsyncActivity extends Activity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_of_async_layout);
        imageView = (ImageView) findViewById(R.id.est_image_view);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadWithPicasos();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        AsyncTestTask asyncTestTask =  new AsyncTestTask();
        asyncTestTask.execute();
    }

    private void loadWithPicasos(){

        Picasso.with(this)
                .load("http://inu.tapptic.com/test/image.php?text=%E5%9B%9B&size=60")
                .into(imageView);
    }

    private class AsyncTestTask extends AsyncTask<Object, Long, JSONArray>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONArray doInBackground(Object[] params) {
            JSONArray jsonArray = new JSONArray();
            HttpRequest request = HttpRequest.get("http://dev.tapptic.com/test/json.php");
            if (request.code() == 200) {

                    String response = request.body();

                try {
                    jsonArray = new JSONArray(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }


            return jsonArray;
        }

        @Override
        protected void onPostExecute(JSONArray result) {
            super.onPostExecute(result);
            Log.v("BruSD",  result.toString());
        }
    }



}
