package com.example.dictionaryappsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import helper.DBHelper;
import models.Word;

public class Meaning extends AppCompatActivity {
    private TextView meaning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meaning);

        meaning = findViewById(R.id.meaning);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int wordId = bundle.getInt("word_id");
        int id = wordId + 1;

        DBHelper dbHelper = new DBHelper(Meaning.this);
        Word meanings = dbHelper.getMeaning(id);

        this.setTitle(meanings.getWord());
        meaning.setText(meanings.getMeaning());

        Toast.makeText(getApplicationContext(), "Word id is  " + id, Toast.LENGTH_LONG).show();
    }
}
