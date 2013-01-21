package molas.minesirklermobil;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MineSirkler";
    
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
        "CREATE TABLE " + IOContract.DBInfoKort.TABLE_NAME + " (" +
        IOContract.DBInfoKort._ID + " INTEGER PRIMARY KEY," +
        IOContract.DBInfoKort.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +		
        IOContract.DBInfoKort.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
        IOContract.DBInfoKort.COLUMN_NAME_SUBTITLE + TEXT_TYPE + COMMA_SEP +
        IOContract.DBInfoKort.COLUMN_NAME_FARGE + TEXT_TYPE + COMMA_SEP +
        IOContract.DBInfoKort.COLUMN_NAME_NIVÅ + TEXT_TYPE +
        " )";
    
	private String[] InfoKortAllColumns = {
			IOContract.DBInfoKort.COLUMN_NAME_ENTRY_ID,
			IOContract.DBInfoKort.COLUMN_NAME_TITLE,
	        IOContract.DBInfoKort.COLUMN_NAME_SUBTITLE,
	        IOContract.DBInfoKort.COLUMN_NAME_FARGE,
	        IOContract.DBInfoKort.COLUMN_NAME_NIVÅ
	};

	private static final String TABLE_NAME_ENTRIES = null;
	
    private static final String SQL_DELETE_ENTRIES =
        "DROP TABLE IF EXISTS " + TABLE_NAME_ENTRIES;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    
    //Legg til et nytt infokort i databasen
    public void addInfokort(InfoKort infokort_)
    {
    	SQLiteDatabase db = this.getWritableDatabase(); 
    	 
        ContentValues values = new ContentValues(); 
        values.put(IOContract.DBInfoKort.COLUMN_NAME_ENTRY_ID, String.valueOf(infokort_.getInfokortID())); 
        values.put(IOContract.DBInfoKort.COLUMN_NAME_TITLE, infokort_.getNavn()); // Infokort navn 
        values.put(IOContract.DBInfoKort.COLUMN_NAME_SUBTITLE, infokort_.getBeskrivelse());
        values.put(IOContract.DBInfoKort.COLUMN_NAME_FARGE, infokort_.getFarge());
        values.put(IOContract.DBInfoKort.COLUMN_NAME_NIVÅ, String.valueOf(infokort_.getHierarkiNivå()));
        
        // Inserting Row 
        db.insert(IOContract.DBInfoKort.TABLE_NAME, null, values); 
        db.close(); // Closing database connection 
    }
    
    //Henter et infokort, gitt id
    public InfoKort getInfoKort(int id)
    {
    	 SQLiteDatabase db = this.getReadableDatabase(); 
    	 
    	 
 	    Cursor cursor = db.query(
 	    		IOContract.DBInfoKort.TABLE_NAME, 
 	    		new String[] { IOContract.DBInfoKort.COLUMN_NAME_ENTRY_ID, 
 	    				IOContract.DBInfoKort.COLUMN_NAME_TITLE, 
 	    				IOContract.DBInfoKort.COLUMN_NAME_SUBTITLE,
 	    				IOContract.DBInfoKort.COLUMN_NAME_FARGE,
 	    				IOContract.DBInfoKort.COLUMN_NAME_NIVÅ},
 	    		IOContract.DBInfoKort.COLUMN_NAME_ENTRY_ID + "=?", 
 	            new String[] { String.valueOf(id) },
 	            null,
 	            null,
 	            null,
 	            null); 
 	    if (cursor != null) 
 	        cursor.moveToFirst(); 
 	 
 	    InfoKort infokort = new InfoKort(Integer.parseInt(cursor.getString(0)), 
 	            cursor.getString(1), cursor.getString(2), cursor.getString(3),
 	           Integer.parseInt(cursor.getString(4)),null); 
 	  
 	    // return contact 
 	    return infokort; 
    }
    
    //Henter listen over alle infokort i databasen(Ligger ikke hierarkisk organisert)
    public ArrayList<InfoKort> getAllCards()
    {
    	ArrayList<InfoKort> alleKort = new ArrayList();
    	
    	// Select All Query 
        String selectQuery = "SELECT  * FROM " + IOContract.DBInfoKort.TABLE_NAME; 
     
        SQLiteDatabase db = this.getWritableDatabase(); 
        Cursor cursor = db.rawQuery(selectQuery, null); 
     
        // looping through all rows and adding to list 
        if (cursor.moveToFirst()) { 
            do { 
                InfoKort card = new InfoKort(); 
                card.setInfokortID(Integer.parseInt(cursor.getString(1)));
                card.setNavn(cursor.getString(2)); 
                card.setBeskrivelse(cursor.getString(3)); 
                card.setFarge(cursor.getString(4)); 
                card.setHierarkiNivå(Integer.parseInt(cursor.getString(5))); 
                // Adding contact to list 
                alleKort.add(card); 
            } while (cursor.moveToNext()); 
        } 
     
 	
    	return alleKort;
    }
    
    //Henter en liste med alle kortene i Databasen, hierarkisk organisert
    public ArrayList<InfoKort> getCardsList()
    {
    	int maxNivå = 0;
    	ArrayList<InfoKort> alleKort = new ArrayList();
    	
    	//Velger antall nivåer
    	String maxNivåQuery = "SELECT MAX(nivå) from" + IOContract.DBInfoKort.TABLE_NAME;
    	//Velger alle kortene
        String selectQuery = "SELECT  * FROM " + IOContract.DBInfoKort.TABLE_NAME; 
       //Velger alle kortene fra et nivå i stigende rekkefølge
        String nivåQuery = "SELECT * FROM " + IOContract.DBInfoKort.TABLE_NAME +"ORDER BY nivå ASC";
        
        
        SQLiteDatabase db = this.getWritableDatabase(); 
        Cursor cursorAll = db.rawQuery(selectQuery, null); 
        Cursor cursorMaxNivå = db.rawQuery(maxNivåQuery, null);
        
        maxNivå = cursorMaxNivå.getInt(0);
        
        	
      
        /*
            for int x = 0-maxnivå
			select asc from InfoKort where nivå = x group by infokortID
			
			for all selection
			legg il i midlertidig liste for hierarkinivå x
			
			
			hvis nivå > 1
			
			Hent referanse til nivå - 1 liste-item som har samme farge, 
			og sett subliste = midlertidig liste
			
			
			end
			
			end
         */
        
        
        // looping through all rows and adding to list 
        if (cursorAll.moveToFirst()) { 
            do { 
                InfoKort card = new InfoKort(); 
                card.setInfokortID(Integer.parseInt(cursorAll.getString(1)));
                card.setNavn(cursorAll.getString(2)); 
                card.setBeskrivelse(cursorAll.getString(3)); 
                card.setFarge(cursorAll.getString(4)); 
                card.setHierarkiNivå(Integer.parseInt(cursorAll.getString(5))); 
                // Adding contact to list 
                alleKort.add(card); 
            } while (cursorAll.moveToNext()); 
        } 
     
 	
    	return alleKort;
    }
    
    //Hent antall infokort i databasen
    public int getCardsCount()
    {
    	  String countQuery = "SELECT  * FROM " + IOContract.DBInfoKort.TABLE_NAME; 
          SQLiteDatabase db = this.getReadableDatabase(); 
          Cursor cursor = db.rawQuery(countQuery, null); 
          cursor.close(); 
    
          // return count 
          return cursor.getCount(); 
    }
    
    //Oppdater et enkelt kort
    public int updateInfoKort(InfoKort card) { 
        SQLiteDatabase db = this.getWritableDatabase(); 
     
        ContentValues values = new ContentValues(); 
        values.put(IOContract.DBInfoKort.COLUMN_NAME_TITLE, card.getNavn()); 
        values.put(IOContract.DBInfoKort.COLUMN_NAME_SUBTITLE, card.getBeskrivelse()); 
        values.put(IOContract.DBInfoKort.COLUMN_NAME_FARGE,card.getFarge());
		values.put(IOContract.DBInfoKort.COLUMN_NAME_NIVÅ, card.getHierarkiNivå());
     
        // updating row 
        return db.update(IOContract.DBInfoKort.TABLE_NAME, values, 
        		IOContract.DBInfoKort.COLUMN_NAME_ENTRY_ID + " = ?", 
                new String[] { String.valueOf(card.getInfokortID()) }); 
    }
    
    //Slett et enkelt kort
    public void deleteCard(InfoKort card) { 
        SQLiteDatabase db = this.getWritableDatabase(); 
        db.delete(IOContract.DBInfoKort.TABLE_NAME, 
        		IOContract.DBInfoKort.COLUMN_NAME_ENTRY_ID + " = ?", 
                new String[] { String.valueOf(card.getInfokortID()) }); 
        db.close(); 
    }
    
    
    
    
    /*Cursor cursorNivå = 
	db.query(IOContract.DBInfoKort.TABLE_NAME,
			null,
			InfoKortAllColumns,
			IOContract.DBInfoKort.COLUMN_NAME_NIVÅ + " = " + 1,  
			groupby ,
			having,
			orderBy,
			null,
			null);*/


}
