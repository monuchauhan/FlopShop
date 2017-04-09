package com.example.shubhamchauhan.flopshop;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shubham chauhan on 23-07-2016.
 */
public class DataHelper extends SQLiteOpenHelper {

    public static String DB_NAME = "SHOP.db";
    public static String TABLE_NAME = "STOCK_TABLE";
    public static String TABLE2_NAME = "CART";
    public static String category = "CATEGORY";
    public static String itemName = "ITEM";
    public static String price = "PRICE";
    public static String quantity = "QUANTITY";
    public static int DB_VER = 8;

    public DataHelper(Context context){
        super(context,DB_NAME,null,DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String create = "CREATE TABLE "+TABLE_NAME+" ( "+category+" text not null,"+itemName+" text not null,"
                +price+" int not null,"+quantity+" int not null);";
        String create2 = "CREATE TABLE "+TABLE2_NAME+" ( "+category+" text not null,"+itemName+" text not null,"
                +price+" int not null,"+quantity+" int not null);";

        sqLiteDatabase.execSQL(create);
        sqLiteDatabase.execSQL(create2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME+";");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE2_NAME+";");
        onCreate(sqLiteDatabase);

    }
}
