package client.igooods.db;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import client.igooods.model.Order;

public class OrderDbAdapter {
	 
	public void addOrder(SQLiteDatabase db, Order order){
		Order orderTemp = getOrder(db, order.getId());
		try{
	    	db.beginTransaction(); 
	    	if(orderTemp==null){
	    		db.execSQL("insert into " + DbCommonHelper.ORDERS_TABLE_NAME + "(id, title, count) values(?,?,?)", 
	    				new String[]{order.getId()+"", order.getTitle()});
	    	}
	    	else{
	    		db.execSQL("update " + DbCommonHelper.ORDERS_TABLE_NAME + " set count=? where id=?", 
	    				new String[]{order.getId()+""});
	    	}
	    	db.setTransactionSuccessful();
    	} catch (SQLException e) {
    		e.printStackTrace();
    		throw new SQLException();
    	} finally {
    	  db.endTransaction();
    	}    
	}
	 
	public void updateOrder(SQLiteDatabase db, Order order){
		Order orderTemp = getOrder(db, order.getId());
		try{
	    	db.beginTransaction(); 
	    	if(orderTemp==null){
	    		db.execSQL("insert into " + DbCommonHelper.ORDERS_TABLE_NAME + "(id, title) values(?,?,?)",
	    				new String[]{order.getId()+"", order.getTitle()});
	    	}
	    	else{ 
	    		db.execSQL("update " + DbCommonHelper.ORDERS_TABLE_NAME + " set count=? where id=?", 
	    				new String[]{order.getId()+""});
	    	}
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
    		db.execSQL("update " + DbCommonHelper.ORDERS_TABLE_NAME + " set title=? where id=?", 
    				new String[]{item.getTitle(),item.getId()});
	    	db.setTransactionSuccessful();
    	} catch (SQLException e) {
    		e.printStackTrace();
    		throw new SQLException();
    	} finally {
    	  db.endTransaction();
    	}    
	}*/
	  
	public List<Order> getOrders(SQLiteDatabase db){
    	List<Order> result = new ArrayList<Order>();
    	String sql =  "select id, title, count from "+DbCommonHelper.ORDERS_TABLE_NAME;
        Cursor c = db.rawQuery(sql,new String[]{}); 
        if (c != null) {  
        	while (c.moveToNext()) {
        		Order order = getOrderFromCursor(c);
        		result.add(order);
        	}
        }
        c.close(); 
	  return result;
    }
	
	public String getAmount(SQLiteDatabase db){
    	int result = 0;
    	String sql =  "select count, price from "+DbCommonHelper.ORDERS_TABLE_NAME+" o "
    			+", "+DbCommonHelper.ITEMS_TABLE_NAME + " i "
    			+" where i.id=o.id ";
    			
        Cursor c = db.rawQuery(sql,new String[]{}); 
        if (c != null) {  
        	while (c.moveToNext()) {
        		final int PRICE = c.getColumnIndex("price");
            	final int COUNT = c.getColumnIndex("count");
            	 
        		String priceStr = c.getString(PRICE);
        		String countStr = c.getString(COUNT);
        		
        		int count = 0;
	    		try{
	    			count=Integer.parseInt(countStr); 
	    		}
	    		catch(Exception e){
	    			e.printStackTrace();
	    		}
	    		int price = 0;
	    		try{
	    			price=Integer.parseInt(priceStr); 
	    		}
	    		catch(Exception e){
	    			e.printStackTrace();
	    		}
	    		result += (count*price);
        	}
        }
        c.close(); 
	  return result+"";
    }
	
	public Order getOrder(SQLiteDatabase db, int id){
    	String sql =  "select id, title, count from "+DbCommonHelper.ORDERS_TABLE_NAME+" where id=?";
        Cursor c = db.rawQuery(sql,new String[]{id+""});
        if (c != null) {  
        	if (c.moveToNext()) {
        		Order order = getOrderFromCursor(c);
        		return order;
        	}
        }
        c.close(); 
	  return null;
    }
	   
	/*public Item getItem(SQLiteDatabase db, String id){
    	Item result = new Item();
    	
        Cursor c = db.rawQuery("select id, title, image from "+DbCommonHelper.ORDERS_TABLE_NAME+" where id=?",new String[]{id});	
        if (c != null) {  
        	if (c.moveToNext()) {
        		result = getItemFromCursor(c);
        	}
        }
        c.close(); 
	  return result;
    }*/
	
	private Order getOrderFromCursor(Cursor c){
		final int ID = c.getColumnIndex("id");
    	final int TITLE = c.getColumnIndex("title");
    	final int COUNT = c.getColumnIndex("count");
    	
    	
		Order order = new Order();
		order.setId(c.getInt(ID));
		order.setTitle(c.getString(TITLE));
		
		return order;
	}
	
	public void deleteOrders(SQLiteDatabase db){ 
    	try{
	    	db.beginTransaction();
    		db.execSQL("delete from " + DbCommonHelper.ORDERS_TABLE_NAME, 
    				new String[]{});
	    	db.setTransactionSuccessful();
    	} catch (SQLException e) {
    		e.printStackTrace();
    		throw new SQLException();
    	} finally {
    	  db.endTransaction();
    	}    	   
	}

	public void deleteOrder(SQLiteDatabase db, String id){ 
    	try{
	    	db.beginTransaction();
    		db.execSQL("delete from " + DbCommonHelper.ORDERS_TABLE_NAME +" where id=?", 
    				new String[]{id});
	    	db.setTransactionSuccessful();
    	} catch (SQLException e) {
    		e.printStackTrace();
    		throw new SQLException();
    	} finally {
    	  db.endTransaction();
    	}    	   
	}
	/*public void deleteItem(SQLiteDatabase db, String id){
    	try{
	    	db.beginTransaction();
    		db.execSQL("delete from " + DbCommonHelper.ORDERS_TABLE_NAME + " where id=?", 
    				new String[]{id});
	    	db.setTransactionSuccessful();
    	} catch (SQLException e) {
    		e.printStackTrace();
    		throw new SQLException();
    	} finally {
    	  db.endTransaction();
    	}    
    }*/
	 
}
