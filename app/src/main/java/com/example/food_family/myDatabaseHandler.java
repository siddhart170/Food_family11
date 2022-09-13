package com.example.food_family;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class myDatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CardDB";
    private static final int DATABSE_VERSION = 4;

    private static final String KEY_ID = "id";
    private static final String PRODUCT_NAME = "product_name";
    private static final String PRODUCT_PRISE = "product_prise";
    private static final String PRODUCT_IMAGE = "product_image";


    public myDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("CREATE TABLE  cart_Table1" +
                    "(" + KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT ," + PRODUCT_NAME + "TEXT," + PRODUCT_PRISE +"TEXT," + PRODUCT_IMAGE + "TEXT" + ")" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldversion, int newversion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS cart_Table1");

        onCreate(sqLiteDatabase);




    }

    public String addCart(String product_name, String product_prise, String product_image)  {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PRODUCT_NAME,product_name);
        values.put(PRODUCT_PRISE, product_prise);
        values.put(PRODUCT_IMAGE, product_image);
        float res = sqLiteDatabase.insert("cart_Table1", null, values);
        if (res == -1){
            return "Failed";
        }
        else
            return "Successfully Inserted";



    }
    public Cursor readAbleData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String qry = "SELECT * FROM cart_Table1 ORDER BY id ";
        Cursor cursor = db.rawQuery(qry,null);
        return  cursor;

    }

}
