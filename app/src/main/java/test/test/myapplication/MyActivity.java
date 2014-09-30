package test.test.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MyActivity extends Activity {
    private Button button, button2;
    private int i;
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.btn_1:
                    intent = new Intent(MyActivity.this, Activity2.class);
                    intent.putExtra("ttt", "fff");
                    startActivity(intent);
                    break;
                case R.id.btn_2:
                    intent = new Intent(MyActivity.this, AsyncActivity.class);
                    startActivity(intent);
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Log.v("Test", "onCreate");
        button = (Button) findViewById(R.id.btn_1);
        button2 = (Button) findViewById(R.id.btn_2);
        button.setOnClickListener(onClickListener);
        button2.setOnClickListener(onClickListener);
        i = 1;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v("Test", "onStart");
        i++;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("Test", "onResume");
        i++;

    }

    @Override
    protected void onPause() {
        Log.v("Test", "onPause");
        super.onPause();

    }

    @Override
    protected void onStop() {
        Log.v("Test", "onStop");
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        Log.v("Test", "onDestroy");
        super.onDestroy();

    }
}
