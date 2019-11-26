package com.example.dictionaryappsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import helper.DBHelper;

public class MainActivity extends AppCompatActivity {
    EditText etWord, etMeaning;
    Button btnAddWord,btnViewlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etWord = findViewById(R.id.etWord);
        etMeaning = findViewById(R.id.etMeaning);
        btnAddWord = findViewById(R.id.btnAddWord);
        btnViewlist = findViewById(R.id.btnViewlist);

        final DBHelper helper = new DBHelper(this);
        final SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();

        btnViewlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DisplayWordActivity.class);
                startActivity(intent);
            }
        });

            btnAddWord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(etWord.getText().toString().isEmpty() && etMeaning.getText().toString().isEmpty()){
                        etWord.setError( "Field must not be empty!!" );
                        etMeaning.setError( "Field must not be empty!!" );
                    }else{
                        long id = helper.InsertData(etWord.getText().toString(),etMeaning.getText().toString(),sqLiteDatabase);
                        if(id>0){
                            Toast.makeText(MainActivity.this,"Successful"+id,Toast.LENGTH_SHORT).show();
                            etWord.getText().clear();
                            etMeaning.getText().clear();
                        }else{
                            Toast.makeText(MainActivity.this,"Error"+id,Toast.LENGTH_SHORT).show();
                        }

                    }

                }
            });

    }
}
