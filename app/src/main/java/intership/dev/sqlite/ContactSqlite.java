package intership.dev.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import intership.dev.fragment.ContactsFragment;
import intership.dev.item.ContactItem;

/**
 * Created by hoangthuan on 7/22/2015.
 */
public class ContactSqlite extends SQLiteOpenHelper {
    public static String DATABASE = "contact";
    public static  String TABLE = "contact";
    public static  int VERSiON = 1;
    public static  String C_ID = "_id";
    public static  String C_USERNAME = "_username";
    public static  String C_DESCRIPTION = "_description";
    public static  String C_AVATA = "_avata";

    public ContactSqlite (Context context){
        super(context, DATABASE, null, VERSiON);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE + " ( " + C_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + C_USERNAME + " text, "
                + C_DESCRIPTION + " text, " + C_AVATA + " INTEGER )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop table " + TABLE);
        onCreate(db);

    }

    public ArrayList<ContactItem> getAllContact(String limit)
    {
        final  ArrayList<ContactItem> mContacts = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =db.query(TABLE,null,null,null,null,null,null,limit);
        if (cursor.moveToFirst()) {
            do {
                ContactItem contactItem = new ContactItem(cursor.getString(1),cursor.getString(2),Integer.parseInt(cursor.getString(3)));
                mContacts.add(contactItem);
            } while (cursor.moveToNext());
        }
        db.close();
        return mContacts;
    }
    public int updateContact(ContactItem contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(C_USERNAME, contact.getmUsername());
        values.put(C_DESCRIPTION, contact.getmDecreption());

        // updating row
        return db.update(TABLE, values, C_ID + " = ?",
                new String[] { String.valueOf(0) });
    }



}
