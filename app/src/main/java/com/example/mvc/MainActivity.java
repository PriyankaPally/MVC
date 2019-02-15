package com.example.mvc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    EditText editText;
    Button button;
    MVCController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new MVCController(this);
        listView = (ListView)findViewById(R.id.lvTask);
        editText = (EditText)findViewById(R.id.etNewTask);
        button = (Button)findViewById(R.id.btNewTask);

        button.setOnClickListener(this.handleNewtaskEvents);

        populateTasks();
    }

    private void populateTasks() {

        List<String> task = controller.getTask();

        listView.setAdapter(new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item,
                task.toArray(new String[]{})));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView textView = (TextView)view;
                controller.deleteTask(id);
                populateTasks();
            }
        });
    }

    private View.OnClickListener handleNewtaskEvents = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            controller.addTask(editText.getText().toString());
            editText.setText("");
            populateTasks();
        }
    };
}
