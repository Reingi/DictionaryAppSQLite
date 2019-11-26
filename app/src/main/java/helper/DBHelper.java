package helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import models.Word;

public class DBHelper extends SQLiteOpenHelper {

    private static final String databaseName = "DictionaryDB";
    private static final int dbVersion = 1;

    //tblWord Field
    private static  final String tblWord = "tblWord";
    private static  final String WordId = "wordId";
    private static  final String Word = "word";
    private static  final String Meaning = "meaning";


    //constructor
    public DBHelper(Context context){
        super(context,databaseName, null,dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE "+ tblWord +
                "("
                + WordId + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Word +" TEXT, "
                + Meaning +" TEXT " +")";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long InsertData(String word, String meaning, SQLiteDatabase db){
           long id;
            ContentValues contentValues = new ContentValues();
            contentValues.put(Word,word);
            contentValues.put(Meaning,meaning);
            id = db.insert(tblWord, null,contentValues);
           return id;
    }

    public List<Word> GetAllWords(SQLiteDatabase db){
        List<Word> dictionaryList = new ArrayList<>();
        Cursor  cursor = db.rawQuery("select * from tblWord",null);
        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                dictionaryList.add(new Word(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
            }
        }
        return dictionaryList;
    }
}
