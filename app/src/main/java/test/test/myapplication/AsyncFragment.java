package test.test.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import test.test.myapplication.supp.Constants;
import test.test.myapplication.supp.GalleryItem;

/**
 * Created by RICHI on 2014.10.01..
 */
public class AsyncFragment extends ListFragment {
    private static final String TAG = "AsyncFragment";

    private ArrayList<HashMap<String, GalleryItem>> mItems;
    private MyListAdapter mListAdapter;
    private String endpPoint;
    private String mKey;

    public static AsyncFragment newInstance() {
        Bundle args = new Bundle();

        AsyncFragment fragment = new AsyncFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mItems = new ArrayList<HashMap<String, GalleryItem>>();
        endpPoint = "http://dev.tapptic.com/test/json.php";
        mKey = Constants.HASHMAP_KEY;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setupAdapter();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        BackroundTask backroundTask = new BackroundTask();
        backroundTask.execute();
    }

    private void setupAdapter() {
        if (getActivity() == null || mItems.size() == 0) {
            return;
        }
        mListAdapter = new MyListAdapter(getActivity(), mItems, R.layout.list_item_async_layout,
                null, null);
        setListAdapter(mListAdapter);
    }

    private void loadPicture(String url, final ImageView imageView) {
        Picasso.with(getActivity())
                .load(url)
                .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                .into(imageView);
    }

    private class BackroundTask extends AsyncTask<Void, Void, ArrayList<HashMap<String, GalleryItem>>> {

        @Override
        protected ArrayList<HashMap<String, GalleryItem>> doInBackground(Void... params) {
            ArrayList<HashMap<String, GalleryItem>> items =
                    new ArrayList<HashMap<String, GalleryItem>>();
            HttpRequest requestToArray = HttpRequest.get(endpPoint);
            if (requestToArray.ok()) {
                String body = requestToArray.body();
                Gson gson = new Gson();
                GalleryItem[] galleryItems = gson.fromJson(body, GalleryItem[].class);
                for (int i = 0; i < galleryItems.length; i++) {
                    HashMap<String, GalleryItem> itemHashMap = new HashMap<String, GalleryItem>();
                    itemHashMap.put(mKey, galleryItems[i]);
                    items.add(itemHashMap);
                }
            }
            return items;
        }

        @Override
        protected void onPostExecute(ArrayList<HashMap<String, GalleryItem>> items) {
            super.onPostExecute(items);
            mItems = items;
            setupAdapter();
        }
    }

    private class MyListAdapter extends SimpleAdapter {
        private Context context;
        private ArrayList<HashMap<String, GalleryItem>> data;
        private int resource;
        private ViewHolder viewHolder;

        public MyListAdapter(Context context, ArrayList<HashMap<String, GalleryItem>> data,
                             int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);

            this.context = context;
            this.data = data;
            this.resource = resource;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater)
                        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(resource, parent, false);

                viewHolder = new ViewHolder();
                viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image_item);
                viewHolder.textView = (TextView) convertView.findViewById(R.id.name_of_picture);
                viewHolder.btn = (Button) convertView.findViewById(R.id.button_in_item);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            HashMap<String, GalleryItem> map = data.get(position);
            final GalleryItem item = map.get(mKey);

            loadPicture(item.getUrl(), viewHolder.imageView);

            viewHolder.textView.setText(item.getName());

            viewHolder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), DetailActivity.class);
                    intent.putExtra(Constants.EXTRA_NAME, item.getName());
                    startActivity(intent);
                }
            });

            return convertView;
        }
    }

    private static class ViewHolder {
        ImageView imageView;
        TextView textView;
        Button btn;
    }
}
