package test.test.myapplication;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.kevinsawicki.http.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import test.test.myapplication.supp.Constants;
import test.test.myapplication.supp.GalleryItem;
import test.test.myapplication.supp.PictureDownloader;

/**
 * Created by RICHI on 2014.10.01..
 */
public class DetailFragment extends Fragment implements PictureDownloader.Update {

    private static final String TAG = "DetailFragment";

    private String mEndPoint;
    private GalleryItem mItem;
    private TextView mName;
    private TextView mText;
    private ImageView mImageView;
    private PictureDownloader downloader;
    private Handler mHandler;

    public static DetailFragment newInstance(String name) {
        Bundle args = new Bundle();
        args.putString(Constants.EXTRA_NAME, name);

        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(args);

        return detailFragment;
    }

    @Override
    public void updateImageView(ImageView view, Bitmap bitmap) {
        view.setImageBitmap(bitmap);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mItem = new GalleryItem();
        mItem.setName(getArguments().getString(Constants.EXTRA_NAME));

        mEndPoint = "http://dev.tapptic.com/test/json.php?name=";
        mHandler = new Handler();
        downloader = new PictureDownloader(this, mHandler);
        downloader.getLooper();
        downloader.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment, container, false);

        mName = (TextView) view.findViewById(R.id.name_detail_fragment);
        mText = (TextView) view.findViewById(R.id.text_detail_fragment);
        mImageView = (ImageView) view.findViewById(R.id.picture_detail_fragment);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        MyTask myTask = new MyTask();
        myTask.execute(mItem);
    }

    @Override
    public void onPause() {
        super.onPause();
        downloader.quit();
    }

    private class MyTask extends AsyncTask<GalleryItem, Void, GalleryItem> {

        @Override
        protected GalleryItem doInBackground(GalleryItem... params) {

            GalleryItem item = params[0];
            String name = item.getName();
            HttpRequest request = HttpRequest.get(mEndPoint+name);
            try {
                if (request.ok()) {

                    String body = request.body();
                    JSONObject jsonObject = new JSONObject(body);
                    String text = jsonObject.getString("text");
                    String url =  jsonObject.getString("image");
                    item.setText(text);
                    item.setBigUrl(url);
                }
            } catch (JSONException e) {
                Log.e(TAG, "JSON Exception: " + e);
            }


            return item;
        }

        @Override
        protected void onPostExecute(GalleryItem galleryItem) {
            super.onPostExecute(galleryItem);
            mItem = galleryItem;
            mName.setText(galleryItem.getName());
            mText.setText(galleryItem.getText());
            downloader.queue(mImageView, galleryItem.geBigUrl());
        }
    }
}

