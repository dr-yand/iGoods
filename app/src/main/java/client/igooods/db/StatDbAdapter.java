package client.igooods.db;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import client.igooods.model.Stat;


public class StatDbAdapter {

	public void addStat(SQLiteDatabase db, Stat stat){
		try{
	    	db.beginTransaction();
    		db.execSQL("insert into " + DbCommonHelper.STATS_TABLE_NAME + "(employee_id, login) values(?,?)",
    				new String[]{stat.getTitle()});
	    	db.setTransactionSuccessful();
    	} catch (SQLException e) {
    		e.printStackTrace();
    		throw new SQLException();
    	} finally {
    	  db.endTransaction();
    	}
	}


	public void updateStat(SQLiteDatabase db, Stat stat){
		try{
	    	db.beginTransaction();
    		db.execSQL("update " + DbCommonHelper.STATS_TABLE_NAME + " set title=? where id=?",
    				new String[]{stat.getTitle()});
	    	db.setTransactionSuccessful();
    	} catch (SQLException e) {
    		e.printStackTrace();
    		throw new SQLException();
    	} finally {
    	  db.endTransaction();
    	}
	}

	public Stat getStatById(SQLiteDatabase db, String id){
    	String sql =  "select id, employee_id, login from "
				+DbCommonHelper.STATS_TABLE_NAME+" where id=?";
        Cursor c = db.rawQuery(sql,new String[]{id});
        if (c != null) {  
        	if (c.moveToNext()) {
				Stat item = getStatFromCursor(c);
        		return item;
        	}
        }
        c.close(); 
	  return null;
    }

	private Stat getStatFromCursor(Cursor c){
		final int ID = c.getColumnIndex("id");
		Stat stat = new Stat();
		stat.setTitle(c.getString(ID));
		
		return stat;
	}

	 
}
