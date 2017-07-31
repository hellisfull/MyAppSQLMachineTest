package com.example.hp.myappsql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 28-06-2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME="CONTACT MANAGER";
    private static final String TABLE_CONTACTS="CONTACTS";
    private static final String KEY_ID="ID";
    private static final String KEY_NAME="Name";
    private static final String KEY_PH_NO="phonenumber";
    private static final String KEY_AGE="age";


    public DatabaseHandler(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT,"
                + KEY_AGE + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_CONTACTS);
        onCreate(sqLiteDatabase);

    }
    void addContact(Contact contact)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, contact.getName());
        contentValues.put(KEY_PH_NO, contact.getPhoneno());
        contentValues.put(KEY_AGE, contact.getAge());


        sqLiteDatabase.insert(TABLE_CONTACTS, null, contentValues);
        sqLiteDatabase.close();
    }
    Contact getContact(int id)
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.query(TABLE_CONTACTS,new String[]{KEY_ID,KEY_NAME,KEY_PH_NO,KEY_AGE},KEY_ID +"=?",new String[]{String.valueOf(sqLiteDatabase)},null,null,null,null);
        if (cursor!=null)
        cursor.moveToFirst();
        Contact contact=new Contact(cursor.getString(1),cursor.getString(2),cursor.getString(3));
        return contact;
    }
    public List<Contact> getAllContact(){
        List<Contact> listview = new ArrayList<Contact>();
        String SelectQuery="SELECT * FROM " + TABLE_CONTACTS;
        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery(SelectQuery,null);

        if (cursor.moveToFirst()){
            do{
            Contact contact= new Contact();
            contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneno(cursor.getString(2));
                contact.setAge(cursor.getString(3));
                listview.add(contact);
        }while (cursor.moveToNext());

    }return listview;
}

     public int UpdateContact(Contact contact){
         SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
         ContentValues contentValues = new ContentValues();
         contentValues.put(KEY_NAME,contact.getName());
         contentValues.put(KEY_PH_NO,contact.getPhoneno());
         contentValues.put(KEY_AGE,contact.getAge());
         return sqLiteDatabase.update(TABLE_CONTACTS,contentValues,KEY_ID + "=?",new String[]{String.valueOf(contact.getId())});

     }
     public void deleteContact(Contact contact)
     {
         SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
         sqLiteDatabase.delete(TABLE_CONTACTS,KEY_ID + "=?" ,new String[]{String.valueOf(contact.getId())});
         sqLiteDatabase.close();
     }
     public int ContactCount(){
         String countQuery="SELECT * FROM" + TABLE_CONTACTS;
         SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
         Cursor cursor = sqLiteDatabase.rawQuery(countQuery,null);
         cursor.close();
         return cursor.getCount();
     }

}
