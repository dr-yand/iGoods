package client.igooods.db;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import client.igooods.model.Item;

public class ItemDbAdapter {
	 
	public void addItem(SQLiteDatabase db, Item item){
		try{
	    	db.beginTransaction(); 
    		db.execSQL("insert into " + DbCommonHelper.ITEMS_TABLE_NAME + "(id, ) values(?,??)",
    				new String[]{item.getTitle()});
	    	db.setTransactionSuccessful();
    	} catch (SQLException e) {
    		e.printStackTrace();
    		throw new SQLException();
    	} finally {
    	  db.endTransaction();
    	}    
	}

	
	/*public void updateItem(SQLiteDatabase db, Item item){
		try{
	    	db.beginTransaction();
    		db.execSQL("update " + DbCommonHelper.ITEMS_TABLE_NAME + " set title=? where id=?", 
    				new String[]{item.getTitle(),item.getId()});
	    	db.setTransactionSuccessful();
    	} catch (SQLException e) {
    		e.printStackTrace();
    		throw new SQLException();
    	} finally {
    	  db.endTransaction();
    	}    
	}*/
	  
	public List<Item> getItems(SQLiteDatabase db){
    	List<Item> result = new ArrayList<Item>();
    	String sql =  "select id, group_id, title, description, image, big_image, status, price, ves, colorii from "+DbCommonHelper.ITEMS_TABLE_NAME;
        Cursor c = db.rawQuery(sql,new String[]{}); 
        if (c != null) {  
        	while (c.moveToNext()) {
        		Item item = getItemFromCursor(c);
        		result.add(item);
        	}
        }
        c.close(); 
	  return result;
    }
	
	public Item getItem(SQLiteDatabase db, String itemId){
    	String sql =  "select id, group_id, title, description, image, big_image, status, price, ves, colorii from "+DbCommonHelper.ITEMS_TABLE_NAME+" where id=?";
        Cursor c = db.rawQuery(sql,new String[]{itemId}); 
        if (c != null) {  
        	if (c.moveToNext()) {
        		Item item = getItemFromCursor(c);
        		return item;
        	}
        }
        c.close(); 
	  return null;
    }
	  
	public List<Item> getItems(SQLiteDatabase db, String groupId, String action){
    	List<Item> result = new ArrayList<Item>();
    	String sql =  "select id, group_id, title, description, image, big_image, status, price, ves, colorii from "+DbCommonHelper.ITEMS_TABLE_NAME +" where group_id=?";
        Cursor c = db.rawQuery(sql,new String[]{groupId}); 
        if (c != null) {  
        	while (c.moveToNext()) {
        		Item item = getItemFromCursor(c);
        		result.add(item);
        	}
        }
        c.close(); 
	  return result;
    } 
	 

	
	private Item getItemFromCursor(Cursor c){
		final int ID = c.getColumnIndex("id");
		Item item = new Item();
		item.setId(c.getInt(ID));
		
		return item;
	}
	
	public void deleteItems(SQLiteDatabase db){ 
    	try{
	    	db.beginTransaction();
    		db.execSQL("delete from " + DbCommonHelper.ITEMS_TABLE_NAME, 
    				new String[]{});
	    	db.setTransactionSuccessful();
    	} catch (SQLException e) {
    		e.printStackTrace();
    		throw new SQLException();
    	} finally {
    	  db.endTransaction();
    	}    	   
	}

	public void deleteItemsByGroup(SQLiteDatabase db, String groupId){ 
    	try{
	    	db.beginTransaction();
    		db.execSQL("delete from " + DbCommonHelper.ITEMS_TABLE_NAME +" where group_id=?", 
    				new String[]{groupId});
	    	db.setTransactionSuccessful();
    	} catch (SQLException e) {
    		e.printStackTrace();
    		throw new SQLException();
    	} finally {
    	  db.endTransaction();
    	}    	   
	}
	
	public void deleteItem(SQLiteDatabase db, String id){
    	try{
	    	db.beginTransaction();
    		db.execSQL("delete from " + DbCommonHelper.ITEMS_TABLE_NAME + " where id=?", 
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
