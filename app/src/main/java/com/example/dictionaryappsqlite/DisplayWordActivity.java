package com.example.dictionaryappsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import helper.DBHelper;
import models.Word;

public class DisplayWordActivity extends AppCompatActivity {

    private ListView lstWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_word);

        lstWord = findViewById(R.id.lstWords);
        LoadWord();
    }

    private void LoadWord(){
        final DBHelper helper = new DBHelper(this);
        final SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();

        List<Word> wordList = new ArrayList<>();
        wordList = helper.GetAllWords(sqLiteDatabase);

        HashMap<String, String> hashMap = new HashMap<>();

        for(int i=0;i<wordList.size();i++){
            hashMap.put(wordList.get(i).getWord(),wordList.get(i).getMeaning());
        }

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>(hashMap.keySet())
                );
        lstWord.setAdapter(stringArrayAdapter);
    }
}
