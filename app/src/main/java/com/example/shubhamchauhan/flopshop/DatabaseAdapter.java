package com.example.shubhamchauhan.flopshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 * Created by Shubham chauhan on 23-07-2016.
 */
public class DatabaseAdapter {

    DataHelper db;

    public DatabaseAdapter(Context context){
        db = new DataHelper(context);
    }

    public void addStock(String category,String item,int quantity,int price){
        SQLiteDatabase temp;
        temp = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("QUANTITY",quantity);
        cv.put("PRICE",price);
        temp.update("STOCK_TABLE",cv,"CATEGORY=? AND ITEM=?",new String[]{category,item});
        temp.close();
    }

    public int getStock(String category,String item){
        SQLiteDatabase temp;
        temp = db.getReadableDatabase();
        Cursor cursor = temp.query("STOCK_TABLE",null,"CATEGORY=?,ITEM=?",new String[]{category,item},null,null,null);
        if(cursor.getCount()<1){
            return 0;
        }
        else{
            cursor.moveToFirst();
            return cursor.getInt(cursor.getColumnIndex("QUANTITY"));
        }
    }

    public void footInit(){
        SQLiteDatabase temp;
        temp = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("CATEGORY","FOOTWEAR");
        cv.put("ITEM","PUMA");
        cv.put("PRICE",1200);
        cv.put("QUANTITY",20);
        temp.insert("STOCK_TABLE",null,cv);
        cv.put("CATEGORY","FOOTWEAR");
        cv.put("ITEM","REEBOK");
        cv.put("PRICE",1100);
        cv.put("QUANTITY",20);
        temp.insert("STOCK_TABLE",null,cv);
        cv.put("ITEM","BATA");
        cv.put("PRICE",1150);
        temp.insert("STOCK_TABLE",null,cv);
        cv.put("ITEM","NIKE");
        cv.put("PRICE",2100);
        temp.insert("STOCK_TABLE",null,cv);
        temp.close();
    }

    public void furInit(){
        SQLiteDatabase temp;
        temp = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("CATEGORY","FURNITURE");
        cv.put("ITEM","SOFA");
        cv.put("PRICE",8000);
        cv.put("QUANTITY",20);
        temp.insert("STOCK_TABLE",null,cv);
        cv.put("CATEGORY","FURNITURE");
        cv.put("ITEM","CHAIR");
        cv.put("PRICE",1100);
        cv.put("QUANTITY",20);
        temp.insert("STOCK_TABLE",null,cv);
        cv.put("ITEM","SHOWCASE");
        cv.put("PRICE",11500);
        temp.insert("STOCK_TABLE",null,cv);
        cv.put("ITEM","TABLE");
        cv.put("PRICE",5400);
        temp.insert("STOCK_TABLE",null,cv);
        temp.close();
    }

    public void toyInit(){
        SQLiteDatabase temp;
        temp = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("CATEGORY","TOYS");
        cv.put("ITEM","CAR");
        cv.put("PRICE",1200);
        cv.put("QUANTITY",20);
        temp.insert("STOCK_TABLE",null,cv);
        cv.put("CATEGORY","TOYS");
        cv.put("ITEM","MONSTER TRUCK");
        cv.put("PRICE",3100);
        cv.put("QUANTITY",20);
        temp.insert("STOCK_TABLE",null,cv);
        cv.put("ITEM","GOKU");
        cv.put("PRICE",950);
        temp.insert("STOCK_TABLE",null,cv);
        cv.put("ITEM","MINION");
        cv.put("PRICE",600);
        temp.insert("STOCK_TABLE",null,cv);
        temp.close();
    }

    public void elecInit(){
        SQLiteDatabase temp;
        temp = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("CATEGORY","ELECTRONICS");
        cv.put("ITEM","LAPTOP");
        cv.put("PRICE",120000);
        cv.put("QUANTITY",20);
        temp.insert("STOCK_TABLE",null,cv);
        cv.put("CATEGORY","ELECTRONICS");
        cv.put("ITEM","SMARTPHONE");
        cv.put("PRICE",58000);
        cv.put("QUANTITY",20);
        temp.insert("STOCK_TABLE",null,cv);
        cv.put("ITEM","GAMING CONSOLE");
        cv.put("PRICE",39998);
        temp.insert("STOCK_TABLE",null,cv);
        cv.put("ITEM","TELEVISION");
        cv.put("PRICE",26799);
        temp.insert("STOCK_TABLE",null,cv);
        temp.close();
    }

    public int insertItemsToCart(String category,String item){
        SQLiteDatabase temp;
        temp = db.getWritableDatabase();
        Cursor crs = temp.query("STOCK_TABLE",null,"CATEGORY=? AND ITEM=?",new String[]{category,item},null,null,null);
        crs.moveToFirst();
        if(crs.getInt(crs.getColumnIndex("QUANTITY"))==0){
            crs.close();
            temp.close();
            return 0;
        }
        else{
            Cursor cr = temp.query("CART",null,"CATEGORY=? AND ITEM=?",new String[]{category,item},null,null,null);
            if(cr.getCount()>=1){
                temp.close();
                cr.close();
                return 2;
            }
            else {
                ContentValues cv = new ContentValues();
                cv.put("CATEGORY", category);
                cv.put("ITEM", item);
                cv.put("PRICE", crs.getInt(crs.getColumnIndex("PRICE")));
                cv.put("QUANTITY", 1);
                temp.insert("CART", null, cv);
                crs.close();
                temp.close();
            }
        }
        return 1;
    }


    public int getItemsInCart(){
        SQLiteDatabase temp;
        temp = db.getReadableDatabase();
        Cursor cr = temp.rawQuery("SELECT * FROM CART;",null);
        cr.moveToFirst();
        int i = cr.getCount();
        cr.close();
        temp.close();
        return i;
    }

    public int getTableStock(){
        SQLiteDatabase temp;
        temp = db.getReadableDatabase();
        Cursor cr = temp.rawQuery("SELECT * FROM STOCK_TABLE;",null);
        cr.moveToFirst();
        int i = cr.getCount();
        cr.close();
        temp.close();
        return i;
    }
    public String[] getTable(){
        SQLiteDatabase temp;
        temp = db.getReadableDatabase();
        Cursor cr = temp.rawQuery("SELECT * FROM CART;",null);
        cr.moveToFirst();
        String[] cat = new String[cr.getCount()];
        cr.moveToFirst();
        int i = 0;
        while(!(cr.isAfterLast())){
            cat[i] = cr.getString(cr.getColumnIndex("ITEM"));
            i++;
            cr.moveToNext();
        }
        return cat;
    }

    public int incCart(String itm){
        SQLiteDatabase temp = db.getWritableDatabase();
        Cursor cr = temp.query("CART",null,"ITEM=?",new String[]{itm},null,null,null);
        Cursor Cr = temp.query("STOCK_TABLE",null,"ITEM=?",new String[]{itm},null,null,null);
        int cartqty,stockqty;
        cr.moveToFirst();
        Cr.moveToFirst();
        cartqty = cr.getInt(cr.getColumnIndex("QUANTITY"));
        stockqty = Cr.getInt(Cr.getColumnIndex("QUANTITY"));
        if(cartqty==stockqty){
            cr.close();
            Cr.close();
            temp.close();
            return 1;
        }
        else{
            cartqty++;
            ContentValues cv = new ContentValues();
            cv.put("QUANTITY",cartqty);
            temp.update("CART",cv,"ITEM=?",new String[]{itm});
        }
        cr.close();
        Cr.close();
        temp.close();
        return 0;
    }

    public int decCart(String itm){
        SQLiteDatabase temp = db.getWritableDatabase();
        Cursor cr = temp.query("CART",null,"ITEM=?",new String[]{itm},null,null,null);
        Cursor Cr = temp.query("STOCK_TABLE",null,"ITEM=?",new String[]{itm},null,null,null);
        int cartqty,stockqty;
        cr.moveToFirst();
        Cr.moveToFirst();
        cartqty = cr.getInt(cr.getColumnIndex("QUANTITY"));
        stockqty = Cr.getInt(Cr.getColumnIndex("QUANTITY"));
        if(cartqty==1){
            cr.close();
            temp.close();
            return 1;
        }
        else{
            cartqty--;
            ContentValues cv = new ContentValues();
            cv.put("QUANTITY",cartqty);
            temp.update("CART",cv,"ITEM=?",new String[]{itm});
        }
        cr.close();
        temp.close();
        return 0;
    }

    public void remove(String itm){
        SQLiteDatabase temp = db.getWritableDatabase();
        temp.delete("CART","ITEM=?",new String[]{itm});
        temp.close();
    }

    public String getqty(String itm){
        SQLiteDatabase temp = db.getReadableDatabase();
        Cursor cr = temp.query("CART",null,"ITEM=?",new String[]{itm},null,null,null);
        cr.moveToFirst();
        int qty = cr.getInt(cr.getColumnIndex("QUANTITY"));
        return String.valueOf(qty);
    }

    public String getStockqty(String itm){
        SQLiteDatabase temp = db.getReadableDatabase();
        Cursor cr = temp.query("STOCK_TABLE",null,"ITEM=?",new String[]{itm},null,null,null);
        cr.moveToFirst();
        int qty = cr.getInt(cr.getColumnIndex("QUANTITY"));
        return String.valueOf(qty);
    }

    public void changeStock(String itm,int qty){
        SQLiteDatabase temp;
        temp = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ITEM",itm);
        cv.put("QUANTITY",qty);
        temp.update("STOCK_TABLE",cv,"ITEM=?",new String[]{itm});
        temp.close();
    }

    public void changeStockInCart(String itm,int qty){
        SQLiteDatabase temp;
        temp = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ITEM",itm);
        cv.put("QUANTITY",qty);
        temp.update("CART",cv,"ITEM=?",new String[]{itm});
        temp.close();
    }

    public int[] getPriceTable(){
        SQLiteDatabase temp;
        temp = db.getReadableDatabase();
        Cursor cr = temp.rawQuery("SELECT * FROM CART;",null);
        cr.moveToFirst();
        int[] cat = new int[cr.getCount()];
        cr.moveToFirst();
        int i = 0;
        while(!(cr.isAfterLast())){
            cat[i] = cr.getInt(cr.getColumnIndex("PRICE"));
            i++;
            cr.moveToNext();
        }
        return cat;
    }

    public int[] getQtyTable(){
        SQLiteDatabase temp;
        temp = db.getReadableDatabase();
        Cursor cr = temp.rawQuery("SELECT * FROM CART;",null);
        cr.moveToFirst();
        int[] cat = new int[cr.getCount()];
        cr.moveToFirst();
        int i = 0;
        while(!(cr.isAfterLast())){
            cat[i] = cr.getInt(cr.getColumnIndex("QUANTITY"));
            i++;
            cr.moveToNext();
        }
        return cat;
    }

    public int getPrice(String itm){
        SQLiteDatabase temp;
        temp = db.getReadableDatabase();
        Cursor cr = temp.query("STOCK_TABLE",null,"ITEM=?",new String[]{itm},null,null,null);
        cr.moveToFirst();
        return cr.getInt(cr.getColumnIndex("PRICE"));
    }

}

