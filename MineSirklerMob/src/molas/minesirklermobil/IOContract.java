package molas.minesirklermobil;

import android.provider.BaseColumns;

public class IOContract {
	DBInfoKort infokort;

	private IOContract() {}

	public DBInfoKort getInfokort() {
		return infokort;
	}

	public void setInfokort(DBInfoKort infokort) {
		this.infokort = infokort;
	}
	
	public static  abstract class DBInfoKort implements BaseColumns {
	    public static final String TABLE_NAME = "InfoKort";
	   
	    public static final String COLUMN_NAME_ENTRY_ID = "id";
	    public static final String COLUMN_NAME_TITLE = "tittel";
	    public static final String COLUMN_NAME_SUBTITLE = "beskrivelse";
	    public static final String COLUMN_NAME_FARGE = "farge";
	    public static final String COLUMN_NAME_NIVÅ = "nivå";
	    public static final String COLUMN_NAME_NULLABLE = "";
	}
	
}
