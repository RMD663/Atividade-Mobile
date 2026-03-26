package com.ryan.james;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> items;
    TextView text;
    ListView list;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        items = new ArrayList<>();
        items.add("List");


        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView text = findViewById(R.id.text_header);
        ListView list = findViewById(R.id.list_view);

        adapter = new ArrayAdapter<>(this, R.layout.list_view, R.id.itemTextView, items);
        list.setAdapter(adapter);

        for(byte i = 0; i < 100; i++){
            addItem("list");
        }
    }

    public void addItem(String item){
        items.add(item);
    }

    public void removeItem(String item) {items.remove(item);}
}