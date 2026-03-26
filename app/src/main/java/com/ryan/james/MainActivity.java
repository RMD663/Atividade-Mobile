package com.ryan.james;

import static android.widget.Toast.*;

import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> items;
    TextView text;
    ListView list;
    SearchView searchText;
    Button searchButton;
    Button addButton;
    Button clearButton;
    EditText editText;
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

        text = findViewById(R.id.text_header);
        list = findViewById(R.id.list_view);
        editText = findViewById(R.id.add_text);
        addButton = findViewById(R.id.add_button);
        adapter = new ArrayAdapter<>(this, R.layout.list_view, R.id.itemTextView, items);
        list.setAdapter(adapter);
        searchButton = findViewById(R.id.search_button);
        searchText = findViewById(R.id.search_bar);
        clearButton = findViewById(R.id.clear_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchItem = searchText.getQuery().toString();
                if(!searchItem.isEmpty()) {
                    getItem(searchItem);
                }
            }
        });

        addButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String textToAdd = editText.getText().toString();
                if (!textToAdd.isEmpty()){
                    addItem(textToAdd);
                } else {
                    makeText(getApplicationContext(), "Invalid item!", Toast.LENGTH_LONG).show();
                }

            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView selected = findViewById(R.id.itemTextView);
                removeItem(selected.getText().toString());
            }
        });

    }

    public void addItem(String item){
        if(!items.contains(item)) {
            items.add(item);
            getItem(item);
            list.setAdapter(adapter);
        } else {
            makeText(this, "Element already exists!", LENGTH_LONG).show();
        }
    }

    public void removeItem(String item) {
        if(items.contains(item)) {
            items.remove(item);
            list.setAdapter(adapter);
        } else {
            makeText(this, "Element doesn't exists!", LENGTH_LONG).show();
        }
    }

    public void getItem(String item){
        ArrayList<String> gottenItem = new ArrayList<>();
        ArrayAdapter<String> adapterSearch = new ArrayAdapter<>(this, R.layout.list_view, R.id.itemTextView, gottenItem);

        for(int i = 0; i < items.size(); i++){
            String str = items.get(i);
            if(str.compareTo(item) == 0){
                    gottenItem.add(items.get(i));
                    list.setAdapter(adapterSearch);
            }
        }

        if(gottenItem.isEmpty()){
            makeText(getApplicationContext(), "Text not found!", LENGTH_LONG).show();
        }
    }

    public void clear(){
        list.setAdapter(adapter);
    }
}