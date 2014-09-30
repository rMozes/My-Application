package test.test.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import test.test.myapplication.supp.StudentAdapter;

/**
 * Created by BruSD on 9/23/2014.
 */
public class ThirdLessonActivity extends Activity {
    private ListView listView;
    private ArrayList<HashMap<String, String>> student = new ArrayList<HashMap<String, String>>();
    private StudentAdapter studentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_lesson_layout);

        listView = (ListView) findViewById(R.id.custom_items_list_view);
        generateStudentList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        studentAdapter =  new StudentAdapter(this, student, R.layout.item_list_view_layout, null, null );
        listView.setAdapter(studentAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private void generateStudentList() {
        HashMap<String, String> item = new HashMap<String, String>();

        item.put("name", "Юлія");
        item.put("sname", "Горкій");

        student.add(item);

        item =  new HashMap<String, String>();
        item.put("name", "Михайло");
        item.put("sname", "Тромбола ");
        student.add(item);
        item.put("name", "Олександр");
        item.put("sname", "Мікуланінець ");
         student.add(item);
        item.put("name", "Іван");
        item.put("sname", "Фельцан ");
        student.add(item);
        item.put("name", "Михайло");
        item.put("sname", "Рогач ");
        student.add(item);
        item.put("name", "Александр ");
        item.put("sname", "Миченко ");
        student.add(item);
        item.put("name", "Oleh ");
        item.put("sname", "Mahobey");
        student.add(item);
        item.put("name", "Диана");
        item.put("sname", "Ручкайте ");
        student.add(item);
        item.put("name", "Саша");
        item.put("sname", "Курта  ");
        student.add(item);
        item.put("name", "Сергей");
        item.put("sname", "Грищук  ");
        student.add(item);

    }
}
