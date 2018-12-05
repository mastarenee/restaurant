package com.gist.bajantech.restaurantmenu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class myDbAdapter{

    myDbHelper myhelper;

    public myDbAdapter(Context context)
    {
        myhelper = new myDbHelper(context);
    }

    static class myDbHelper extends SQLiteOpenHelper
    {
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context=context;
        }

        // Database Name
        private static final String DATABASE_NAME = "MenuDatabase";

        // Table Names
        private static final String TABLE_NAME = "myTable";
        private static final String RESTAURANT_TABLE = "myRestaurant";
        private static final String PRODUCT_TABLE = "myProduct";
        private static final String ORDER_TABLE = "myOrder";

        private static final String ADMIN_TABLE = "myAdmin";
        private static final String CANTEEN_TABLE = "myCanteen";

        // Database Version
        private static final int DATABASE_VERSION = 2;

        // Create Table Infrastructure for DB Tables Listed Above
        // TABLE_NAME ( EXAMPLE ) - DO NOT EDIT
        private static final String UID="_id";     // Column I (Primary Key)
        private static final String NAME = "Name";    //Column II
        private static final String MyPASSWORD= "Password";    // Column III
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255) ,"+ MyPASSWORD+" VARCHAR(225));";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;

        // RESTAURANT_TABLE_NAME
        // Define Columns
        private static final String URID="_idr";     // Column I (Primary Key)
        private static final String RESTAURANT_NAME = "restaurant_name";
        private static final String RESTAURANT_NUMBER = "restaurant_number";
        private static final String RESTAURANT_CANTEEN = "restaurant_canteen";
        private static final String RESTAURANT_STATUS = "restaurant_status";

        // RESTAURANT ADMIN TABLE
        private static final String UAID = "_ida";
        private static final String ADMIN_USERNAME = "admin_username";
        private static final String ADMIN_PASSWORD = "admin_password";
        private static final String ADMIN_TIMESTAMP = "admin_timestamp";
        private static final String ADMIN_STATUS = "admin_status";

        // Create Table or Drop if existent
        // Naming Convention: CREATE_{ENTITY - REPLACE WITH YOUR NAME}_TABLE
        private static final String CREATE_RESTAURANT_TABLE = "CREATE TABLE "+RESTAURANT_TABLE+
                " ("+URID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                RESTAURANT_NAME+" VARCHAR(255) ,"+
                RESTAURANT_NUMBER+" VARCHAR(20) ,"+
                RESTAURANT_CANTEEN+" VARCHAR(255) ,"+
                RESTAURANT_STATUS+" VARCHAR(2));";

        private static final String DROP_RESTAURANT_TABLE ="DROP TABLE IF EXISTS "+RESTAURANT_TABLE;

        // Create table
        private static final String CREATE_ADMIN_TABLE = "CREATE TABLE "+ADMIN_TABLE+
                " ("+UAID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ADMIN_USERNAME+" VARCHAR(255) ,"+
                ADMIN_PASSWORD+" VARCHAR(255) ,"+
                ADMIN_TIMESTAMP+" VARCHAR(255) ,"+
                ADMIN_STATUS+" VARCHAR(2));";

        private static final String DROP_ADMIN_TABLE ="DROP TABLE IF EXISTS "+ ADMIN_TABLE;

        // PRODUCT_TABLE
        // Define Columns
        private static final String UTID="_idit";     // Column I (Primary Key)
        private static final String ITEM_NAME = "item_name";
        private static final String ITEM_PHOTO = "item_photo";
        private static final String ITEM_DESC = "item_desc";
        private static final String ITEM_PRICE = "item_price";
        private static final String ITEM_RESTAURANT = "item_restaurant";
        private static final String ITEM_STATUS = "item_status";

        // Create Table or Drop if existent
        // Naming Convention: CREATE_{ENTITY - REPLACE WITH YOUR NAME}_TABLE
        private static final String CREATE_PRODUCT_TABLE = "CREATE TABLE "+ PRODUCT_TABLE+
                " ("+UTID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ITEM_NAME+" VARCHAR(255) ,"+
                ITEM_PHOTO+" VARCHAR(255) ,"+
                ITEM_DESC+" VARCHAR(255) ,"+
                ITEM_PRICE+" VARCHAR(255) ,"+
                ITEM_RESTAURANT+" VARCHAR(2) ,"+
                ITEM_STATUS+" VARCHAR(2));";

        private static final String DROP_PRODUCT_TABLE ="DROP TABLE IF EXISTS " + PRODUCT_TABLE;

        public void onCreate(SQLiteDatabase db) {
            try {

                db.execSQL(CREATE_RESTAURANT_TABLE);
                db.execSQL(CREATE_PRODUCT_TABLE);
                db.execSQL(CREATE_ADMIN_TABLE);
                db.execSQL(CREATE_TABLE);
                
            } catch (Exception e) {
                Message.message(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE);
                db.execSQL(DROP_PRODUCT_TABLE);
                db.execSQL(DROP_RESTAURANT_TABLE);
                db.execSQL(DROP_ADMIN_TABLE);
                onCreate(db);
            }catch (Exception e) {
                Message.message(context,""+e);
            }
        }
    }

    public long insertData(String name, String pass)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME, name);
        contentValues.put(myDbHelper.MyPASSWORD, pass);
        long id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
        return id;
    }

    // RESTAURANT DB METHODS
    public long insertRestaurantData(String name, String number, String canteen, String status)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(myDbHelper.RESTAURANT_NAME, name);
        contentValues.put(myDbHelper.RESTAURANT_NUMBER, number);
        contentValues.put(myDbHelper.RESTAURANT_CANTEEN, canteen);
        contentValues.put(myDbHelper.RESTAURANT_STATUS, status);

        long id = dbb.insert(myDbHelper.RESTAURANT_TABLE, null , contentValues);
        return id;
    }

    public boolean restaurantDataInstalled(){

        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.URID};
        Cursor cursor = db.query(myDbHelper.RESTAURANT_TABLE, columns,null,null,null,null,null);
        String rid = "null";

        while (cursor.moveToNext()) {
            rid = cursor.getString(cursor.getColumnIndex(myDbHelper.URID));
        }

        if( rid != "null" ){
            return true;
        }else {
            return false;
        }
    }

    public boolean restaurantProductInstalled(){

        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UTID};
        Cursor cursor = db.query(myDbHelper.PRODUCT_TABLE, columns,null,null,null,null,null);
        String rid = "null";

        while (cursor.moveToNext()) {
            rid = cursor.getString(cursor.getColumnIndex(myDbHelper.UTID));
        }

        if( rid != "null" ){
            return true;
        }else {
            return false;
        }
    }

    public boolean restaurantAdminInstalled(){

        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UAID};
        Cursor cursor = db.query(myDbHelper.ADMIN_TABLE, columns,null,null,null,null,null);
        String rid = "null";

        while (cursor.moveToNext()) {
            rid = cursor.getString(cursor.getColumnIndex(myDbHelper.UAID));
        }

        if( rid != "null" ){
            return true;
        }else {
            return false;
        }
    }

    public ArrayList getRestaurantData()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.URID,myDbHelper.RESTAURANT_NAME,myDbHelper.RESTAURANT_NUMBER,myDbHelper.RESTAURANT_CANTEEN};
        Cursor cursor = db.query(myDbHelper.RESTAURANT_TABLE,columns,null,null,null,null,null);

        StringBuffer buffer = new StringBuffer();
        ArrayList<Restaurant> list = new ArrayList<>();

        while (cursor.moveToNext())
        {
            int rid = cursor.getInt(cursor.getColumnIndex(myDbHelper.URID));
            String restaurant_name = cursor.getString(cursor.getColumnIndex(myDbHelper.RESTAURANT_NAME));
            String restaurant_number = cursor.getString(cursor.getColumnIndex(myDbHelper.RESTAURANT_NUMBER));
            String restaurant_canteen = "one canteen";
            String restaurant_status = "1";

            list.add(new Restaurant(rid,restaurant_name,restaurant_number,restaurant_canteen, restaurant_status));
        }

        return list;
    }

    public ArrayList getRestaurantMenu(String restaurant_name){

        try{

            SQLiteDatabase db = myhelper.getWritableDatabase();


            //SQLiteDatabase db = myhelper.getWritableDatabase();
            String[] columns = {myDbHelper.UTID,myDbHelper.ITEM_NAME,myDbHelper.ITEM_PHOTO,myDbHelper.ITEM_RESTAURANT, myDbHelper.ITEM_DESC, myDbHelper.ITEM_PRICE, myDbHelper.ITEM_STATUS};
            Cursor cursor = db.query(myDbHelper.PRODUCT_TABLE,columns,"item_restaurant = ?", new String[] { restaurant_name },null,null,null);

            StringBuffer buffer = new StringBuffer();
            ArrayList<RestaurantMenu> list = new ArrayList<>();

            while (cursor.moveToNext())
            {
                int iid = cursor.getInt(cursor.getColumnIndex(myDbHelper.UTID));
                String product_name = cursor.getString(cursor.getColumnIndex(myDbHelper.ITEM_NAME));
                String product_desc = cursor.getString(cursor.getColumnIndex(myDbHelper.ITEM_DESC));
                String product_price = cursor.getString(cursor.getColumnIndex(myDbHelper.ITEM_PRICE));
                String product_photo = cursor.getString(cursor.getColumnIndex(myDbHelper.ITEM_PHOTO));
                String product_restaurant = cursor.getString(cursor.getColumnIndex(myDbHelper.ITEM_RESTAURANT));
                String product_status = cursor.getString(cursor.getColumnIndex(myDbHelper.ITEM_STATUS));
    
                list.add(new RestaurantMenu(iid,product_name,product_photo,product_restaurant, product_desc, product_price, product_status));
            }

        return list;
        } catch (Exception e) {


            return new ArrayList<>();
        }

    }


    public  int deleteRestaurant(String resname)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={resname};

        int count = db.delete(myDbHelper.RESTAURANT_TABLE,myDbHelper.RESTAURANT_NAME+" = ?",whereArgs);
        return count;
    }

    public int updateRestaurantName(String oldName , String newName)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.RESTAURANT_NAME,newName);
        String[] whereArgs= {oldName};
        int count =db.update(myDbHelper.RESTAURANT_TABLE,contentValues, myDbHelper.RESTAURANT_NAME+" = ?",whereArgs );
        return count;
    }
    // END RESTAURANT OPTIONS

    // RESTAURANT MENU ITEMS DB METHODS
    public long insertRestaurantMenuData(String name, String photo, String restaurant, String Description, String Price, String status)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(myDbHelper.ITEM_NAME, name);
        contentValues.put(myDbHelper.ITEM_PHOTO, photo);
        contentValues.put(myDbHelper.ITEM_DESC, Description);
        contentValues.put(myDbHelper.ITEM_PRICE, Price);
        contentValues.put(myDbHelper.ITEM_RESTAURANT, restaurant);
        contentValues.put(myDbHelper.ITEM_STATUS, status);

        long id = dbb.insert(myDbHelper.PRODUCT_TABLE, null , contentValues);
        return id;
    }

    // RESTAURANT ADMIN DB METHODS
    public long insertRestaurantAdminData(String name, String password, String status)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(myDbHelper.ADMIN_USERNAME, name);
        contentValues.put(myDbHelper.ADMIN_PASSWORD, password);
        contentValues.put(myDbHelper.ADMIN_STATUS, status);
        contentValues.put(myDbHelper.ADMIN_TIMESTAMP, "TODAY");

        long id = dbb.insert(myDbHelper.ADMIN_TABLE, null , contentValues);
        return id;
    }

    public Boolean validateUser(String username, String password)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UAID, myDbHelper.ADMIN_USERNAME, myDbHelper.ADMIN_PASSWORD};
        String[] whereArgs= {username};
        Cursor cursor = db.query(myDbHelper.ADMIN_TABLE,columns,myDbHelper.ADMIN_USERNAME + " = ?", whereArgs,null,null,null);

        while (cursor.moveToNext())
        {
            final int i = cursor.getCount();
            if( i == 0 ){
                return false;
            }else{

                int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.UAID));
                String dbpassword = cursor.getString(cursor.getColumnIndex(myDbHelper.ADMIN_PASSWORD));
                if( password == dbpassword ){
                    return true;
                }else{
                    return false;
                }
            }

        }
        return true;
    }

    public String getData()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.NAME,myDbHelper.MyPASSWORD};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String name =cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
            String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
            buffer.append(cid+ "   " + name + "   " + password +" \n");
        }
        return buffer.toString();
    }

    public  int delete(String uname)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={uname};

        int count =db.delete(myDbHelper.TABLE_NAME ,myDbHelper.NAME+" = ?",whereArgs);
        return  count;
    }

    public int updateName(String oldName , String newName)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME,newName);
        String[] whereArgs= {oldName};
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.NAME+" = ?",whereArgs );
        return count;
    }


}
