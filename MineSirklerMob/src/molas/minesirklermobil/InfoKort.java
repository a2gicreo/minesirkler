package molas.minesirklermobil;
import java.util.ArrayList;

/*Et Infokort lagrer informasjon(Detaljer, kategorier,underkategorier) om en av kategoriene, f.eks. 'Arbeid'  
 *Systemet vil derfor måtte opprettholde en ArrayList med 8 infokort, som igjen opprettholder lister med InfoKort-underkategorier 
 * */
public class InfoKort {
	
	private int infokortID;
	private String navn;
	private String beskrivelse;
	private String farge;
	private int hierarkiNivå;
	private ArrayList subkort;
	
	
	/*Standard-konstruktør. Hvis en bruker denne ved opprettelse av InfoKort, må en initialisere variablene manuelt*/
	public InfoKort()
	{
	}
	
	/*Konstruktør som initialiserer variablene( f.eks InfoKort mittKort = new InfoKort(1,'Andre','Folk&fe osv blabla',.,.) */
	public InfoKort(int infokortID_, String navn_, String beskrivelse_,String farge_, int hierarkiNivå_, ArrayList subkort_)
	{
	    infokortID = infokortID_;
	    navn = navn_;
	    beskrivelse = beskrivelse_;
	    farge = farge_;
	    hierarkiNivå = hierarkiNivå_;
	    subkort = subkort_;
	}

	
	//public setColor(OvalShape sirkel){}
	
	
	/* Legger til et kort i kortet. Her kan en legge til så mange en vil */
	public void addSubLevel(InfoKort kort)
	{
		subkort.add(kort);
	}
	
	/* Fjerner et underkort */
	public void deleteSubLevel(String navn)
	{
		subkort.remove(retrieveSubLevel(navn));
	}
	
	//Returnerer et undernivå gitt navnet på infokort-klassen. Hvis ikke funnet returnerer 'nothing'
	public InfoKort retrieveSubLevel(String navn)
	{
		InfoKort infokort_get = new InfoKort();
		
		if (subkort.size() == 0) 
		{return null;}
		
		for (int i = 0; i < subkort.size();i++)
		{
			String subkortnavn = "";
			infokort_get = (InfoKort) subkort.get(i);
			
			if (infokort_get.navn == navn)
			{
				return infokort_get;
			}
			
		}
				
		
		return null;
	}
	
	//Returnerer et undernivå gitt id-en på infokort-klassen. Hvis ikke funnet returnerer 'nothing'
	public InfoKort retrieveSubLevel(int id)
	{
		InfoKort infokort_get = new InfoKort();
		
		if (subkort.size() == 0) 
		{return null;}
		
		for (int i = 0; i < subkort.size();i++)
		{
			String subkortnavn = "";
			infokort_get = (InfoKort) subkort.get(i);
			
			if (infokort_get.getInfokortID() == id)
			{
				return infokort_get;
			}
			
		}
				
		
		return null;
	}
		
	
	/* Set og get metoder for alle variablene i klassen  */
	public int getInfokortID() {
		return infokortID;
	}

	public void setInfokortID(int infokortID) {
		this.infokortID = infokortID;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public String getBeskrivelse() {
		return beskrivelse;
	}

	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}

	public String getFarge() {
		return farge;
	}

	public void setFarge(String farge) {
		this.farge = farge;
	}

	public int getHierarkiNivå() {
		return hierarkiNivå;
	}

	public void setHierarkiNivå(int hierarkiNivå) {
		this.hierarkiNivå = hierarkiNivå;
	}

	public ArrayList getSubkort() {
		return subkort;
	}

	public void setSubkort(ArrayList subkort) {
		this.subkort = subkort;
	}
	
	


}
