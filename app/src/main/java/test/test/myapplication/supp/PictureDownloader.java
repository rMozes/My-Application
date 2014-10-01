package test.test.myapplication.supp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by RICHI on 2014.10.01..
 */
public class PictureDownloader extends HandlerThread {

    private static final String TAG = "PictureDownloader";

    private Handler mHandler;
    private ArrayList<ImageView> mList;
    private String mUrl;
    private ImageView imageView;
    private Update updateInterface;
    private Handler mResponseHandler;

    public PictureDownloader(Update updateInterface, Handler handler ) {
        super(TAG);
        mList = new ArrayList<ImageView>();
        this.updateInterface = updateInterface;
        this.mResponseHandler = handler;
    }

    public interface Update {
        public void updateImageView(ImageView view, Bitmap bitmap);
    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        mHandler = new Handler();
    }

    public void queue(final ImageView imageView, final String url) {
        Log.i(TAG, "Got a new download" + url);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                byte[] rawPicture = downloadPicture(url);
                final Bitmap bitmap = BitmapFactory.decodeByteArray(rawPicture, 0, rawPicture.length);
                final BitmapDrawable drawable = new BitmapDrawable(bitmap);
                mResponseHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        updateInterface.updateImageView(imageView, bitmap);
                    }
                });
            }
        });
    }

    private byte[] downloadPicture(String urlPicture) {
        ByteArrayOutputStream out = null;
        InputStream in = null;

        try {
            URL url = new URL(urlPicture);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            out = new ByteArrayOutputStream();
            in = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }

            out.close();
            connection.disconnect();

        } catch (java.io.IOException e) {
            Log.e(TAG, "IO Exception: ", e);
        }
        return out.toByteArray();
    }
}
