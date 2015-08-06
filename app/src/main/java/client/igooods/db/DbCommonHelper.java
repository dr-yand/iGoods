package client.igooods.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbCommonHelper extends SQLiteOpenHelper { 
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "igooods.db";
    
    public static final String USERS_TABLE_NAME = "users";
    private static final String USERS_TABLE_CREATE =
                "CREATE TABLE IF NOT EXISTS " + USERS_TABLE_NAME + " ("
                	+ " id integer primary key NOT NULL, "
	                + " employee_id TEXT, "
	                + " login TEXT);";
    
    public static final String STATS_TABLE_NAME = "stats";
    private static final String STATS_TABLE_CREATE =
                "CREATE TABLE IF NOT EXISTS " + STATS_TABLE_NAME + " ("
                	+ " id integer primary key NOT NULL, "
                	+ " group_id integer , "
	                + " title TEXT, "
	                + " ver TEXT);"; 
    
    public static final String ORDERS_TABLE_NAME = "orders";
    private static final String ORDERS_TABLE_CREATE =
                "CREATE TABLE IF NOT EXISTS " + ORDERS_TABLE_NAME + " ("
                	+ " id integer primary key NOT NULL, "                	
	                + " title TEXT, "
	                + " count TEXT);"; 
    
    public static final String ITEMS_TABLE_NAME = "items";
    private static final String ITEMS_TABLE_CREATE =
                "CREATE TABLE IF NOT EXISTS " + ITEMS_TABLE_NAME + " ("
                	+ " _id integer primary key  autoincrement, "
                	+ " id integer, "
                	+ " num TEXT,"
	                + " title TEXT, "
	                + " count TEXT);"; 

    
    private SQLiteDatabase mDb;

    public DbCommonHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USERS_TABLE_CREATE);
        db.execSQL(STATS_TABLE_CREATE);
        db.execSQL(ORDERS_TABLE_CREATE);
        db.execSQL(ITEMS_TABLE_CREATE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { 
        db.execSQL("drop table  IF EXISTS "+USERS_TABLE_NAME);
        db.execSQL("drop table  IF EXISTS "+STATS_TABLE_NAME);
        db.execSQL("drop table  IF EXISTS "+ORDERS_TABLE_NAME);
        db.execSQL("drop table  IF EXISTS "+ITEMS_TABLE_NAME);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    
    public SQLiteDatabase open(){
    	if(mDb==null)
    		mDb = getWritableDatabase();
    	return mDb; 
    }
}