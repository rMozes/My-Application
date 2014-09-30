package test.test.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class Activity2 extends Activity implements
        GestureDetector.OnGestureListener {


    public static String DEBUG_TAG = "mDetector";
    private GestureDetector mDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(new SingleTouchEventView(this, null));
        setContentView(R.layout.activity_my_activity2);
        mDetector = new GestureDetector(this, this);

        Intent intent = getIntent();
        String str = intent.getStringExtra("ttt");
        TextView textView = (TextView) findViewById(R.id.text_view_1);
        textView.setText(str);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String[] list = {"qwe", "qwe"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(Activity2.this, android.R.layout.simple_list_item_1, android.R.id.text1, list);
        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent event) {
        Log.d(DEBUG_TAG, "onDown: " + event.toString());
        return true;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        Log.d(DEBUG_TAG, "onFling: " + event1.toString() + event2.toString());
        return true;
    }

    @Override
    public void onLongPress(MotionEvent event) {
        Log.d(DEBUG_TAG, "onLongPress: " + event.toString());
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        Log.d(DEBUG_TAG, "onScroll: " + e1.toString() + e2.toString());
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {
        Log.d(DEBUG_TAG, "onShowPress: " + event.toString());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        Log.d(DEBUG_TAG, "onSingleTapUp: " + event.toString());
        return true;
    }
}