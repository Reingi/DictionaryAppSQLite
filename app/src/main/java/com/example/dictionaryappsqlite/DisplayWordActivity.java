package com.example.dictionaryappsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import helper.DBHelper;
import models.Word;

public class DisplayWordActivity extends AppCompatActivity {

    private ListView lstWord;
    private EditText filterText;
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_word);

        lstWord = findViewById(R.id.lstWords);
        filterText = findViewById(R.id.etsearch);

        final DBHelper helper = new DBHelper(this);
        final SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();

        List<Word> wordList = new ArrayList<>();
        wordList = helper.GetAllWords(sqLiteDatabase);

        HashMap<Integer, String> hashMap = new HashMap<>();

        for (int i = 0; i < wordList.size(); i++) {
            hashMap.put(wordList.get(i).getWordId(), wordList.get(i).getWord());
        }

        listAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>(hashMap.values())
        );
        lstWord.setAdapter(listAdapter);

        //on itemclick event
        lstWord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Position " + position, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(DisplayWordActivity.this,Meaning.class);
                intent.putExtra("word_id",position);
                startActivity(intent);
            }
        });

        filterText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                DisplayWordActivity.this.listAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}
