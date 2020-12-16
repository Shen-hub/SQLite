package com.example.playlistsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView TotalTask;
    Button AddTask;
    ListView Tasks;
    EditText TaskMessage, Data;
    SQLiteDatabase DB;

    List<Item> Items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TotalTask = findViewById(R.id.TotalCount);
        AddTask = findViewById(R.id.Button);
        Tasks = findViewById(R.id.Tasks);
        TaskMessage = findViewById(R.id.TaskMessage);
        Data = findViewById(R.id.Data);

        DBHelper helper = new DBHelper(this);
        DB = helper.getWritableDatabase();

        getItems();

        final Adapter simpleAdapter = new Adapter(Items);
        Tasks.setAdapter(simpleAdapter);
        AddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TaskMessage.getText().toString().isEmpty() || Data.getText().toString().isEmpty())
                    return;
                ContentValues contentValues = new ContentValues();
                contentValues.put(DBHelper.COLUMN_NAME, TaskMessage.getText().toString());
                contentValues.put(DBHelper.DATA, Data.getText().toString());
                DB.insert(DBHelper.TABLE_NAME, null, contentValues);
                getItems();
                simpleAdapter.notifyDataSetChanged();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void getItems() {
        Items.clear();
        Cursor cursor = DB.query(
                DBHelper.TABLE_NAME,
                new String[]{DBHelper.COLUMN_NAME, DBHelper.DATA},
                null,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()) {
            String currentName = cursor.getString(0);
            String currentData = cursor.getString(1);
            Items.add(new Item(currentName, currentData));
        }
        cursor.close();

        TotalTask.setText("Всего заметок: " + String.valueOf(Items.size()));
    }
}
