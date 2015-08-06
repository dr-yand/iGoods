package client.igooods.db;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import client.igooods.model.User;


public class UserDbAdapter {
	 
	public void addUser(SQLiteDatabase db, User user){
		try{
	    	db.beginTransaction(); 
    		db.execSQL("insert into " + DbCommonHelper.USERS_TABLE_NAME + "(employee_id, login) values(?,?)",
    				new String[]{user.getEmployeeId()+"", user.getLogin()});
	    	db.setTransactionSuccessful();
    	} catch (SQLException e) {
    		e.printStackTrace();
    		throw new SQLException();
    	} finally {
    	  db.endTransaction();
    	}    
	}

	
	/*public void updateUser(SQLiteDatabase db, User stat){
		try{
	    	db.beginTransaction();
    		db.execSQL("update " + DbCommonHelper.USERS_TABLE_NAME + " set title=? where id=?",
    				new String[]{item.getTitle(),item.getId()});
	    	db.setTransactionSuccessful();
    	} catch (SQLException e) {
    		e.printStackTrace();
    		throw new SQLException();
    	} finally {
    	  db.endTransaction();
    	}    
	}*/

	public User getUserById(SQLiteDatabase db, String id){
    	String sql =  "select id, employee_id, login from "
				+DbCommonHelper.USERS_TABLE_NAME+" where id=?";
        Cursor c = db.rawQuery(sql,new String[]{id});
        if (c != null) {  
        	if (c.moveToNext()) {
                User item = getUserFromCursor(c);
        		return item;
        	}
        }
        c.close(); 
	  return null;
    }

	private User getUserFromCursor(Cursor c){
		final int ID = c.getColumnIndex("id");
		User user = new User();
        user.setId(c.getInt(ID));
		
		return user;
	}

	public void deleteUserById(SQLiteDatabase db, String id){
    	try{
	    	db.beginTransaction();
    		db.execSQL("delete from " + DbCommonHelper.USERS_TABLE_NAME + " where id=?",
    				new String[]{id});
	    	db.setTransactionSuccessful();
    	} catch (SQLException e) {
    		e.printStackTrace();
    		throw new SQLException();
    	} finally {
    	  db.endTransaction();
    	}    
    }
	 
}
