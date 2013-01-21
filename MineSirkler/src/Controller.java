import java.util.ArrayList;




public class Controller {
	
/*Eksempel p� bruk av Infokort. Viser hvordan en lager enkle underkategorier*/
	public Controller()
	{
		
	ArrayList<InfoKort> alleInfoKort = new ArrayList(8);
    	
    	/*Vi skal hente denne informasjonen fra database, men forel�pig kan vi initialisere den manuelt med test-data, stubs*/
		InfoKort meg;
		InfoKort familie;
		InfoKort venner;
		InfoKort jobb;
		InfoKort bekjente;
		InfoKort andre;
		
		ArrayList aFamilie;
		InfoKort familiebes�k;
		InfoKort onkel_fredrik;
		InfoKort onkel_fredrik_full;
		
		familiebes�k = new InfoKort(2, "Familiebes�k", "P� familiebes�k, sp�r en om hva folk har gjort og pr�ver � huske det","GREEN", 2, new ArrayList());
		onkel_fredrik = new InfoKort(2, "Onkel Fredrik", "Onkel fredrik snakker for mye. Nikk og smil, ha klar unnskyldninger for � komme deg bort","GREEN", 2, new ArrayList());
		onkel_fredrik_full = new InfoKort(2, "Onkel Fredrik n�r han er full", "Unng� hvis mulig, hvis ikke, konfonter","GREEN", 2, new ArrayList());
		
		aFamilie = new ArrayList();
		aFamilie.add(familiebes�k);
		aFamilie.add(onkel_fredrik);
		aFamilie.add(onkel_fredrik_full);
		
		meg = new InfoKort(1, "Meg", "N�r en er alene i huset","CYAN", 1, new ArrayList());
		familie = new InfoKort(2, "Familie", "N�r familien er i huset","MAGENTA", 1, aFamilie);
		venner = new InfoKort(3, "Venner", "Venner som en snakker med et par ganger i �ret, eller til julebord/nytt�rsfester. Venner som en trener med, eller har middager med regelmessig. Tidligere kj�rester som en kan ha kontakt med","GREEN", 1, new ArrayList());
		jobb = new InfoKort(4, "Kolleger", "Kolleger p� jobb","YELLOW", 1, aFamilie);
		bekjente = new InfoKort(5, "Bekjente", "Folk som en kjenner litt p� jobb eller andre steder. Kan v�re tidligere venner som en ikke har snakket med p� en stund, venner av venner/kj�reste, eller tidligere kollegaer","BLUE", 1, new ArrayList());
		andre = new InfoKort(1, "Andre", "Folk som en ikke vet noe om, treffer p� gaten eller bussen. Kassadamer og ekspedit�rer, telefonselgere og kundest�tte-medarbeidere.","RED", 1, new ArrayList());
		
		alleInfoKort.add(meg); 
		alleInfoKort.add(familie);
		alleInfoKort.add(venner);
		alleInfoKort.add(jobb);
		alleInfoKort.add(bekjente);
		alleInfoKort.add(andre);
		
		//Slik henter du ut elementer fra familie-underlisten
		//Lager en ArrayList med type InfoKort
		ArrayList<InfoKort> testFam = new ArrayList<InfoKort>();
		testFam = familie.getSubkort();
		
		System.out.println("Listen for familie-elementer skrevet ut ved iterasjon:");		
		for (int i = 0; i < testFam.size();i++)		
			System.out.println(testFam.get(i).getNavn());
		
		System.out.println("");
		System.out.println("Listen for familie-elementer skrevet ut manuelt");
		System.out.println(testFam.get(0).getNavn());
		System.out.println(testFam.get(1).getNavn());
		
	}

}
