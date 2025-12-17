Klasse Teesorte:
Attribut:	
private ArrayList <String> enthalteneKraeuter;

Konstruktor: 
public Teesorte(int identnummer, String bezeichnung, String kategorie,
       	String mitKoffein, String[] enthalteneKraeuter){
    	this.identnummer = identnummer;
      	this.bezeichnung = bezeichnung;
       	this.kategorie = kategorie;
       	this.mitKoffein = mitKoffein;
       	setEnthalteneKraeuterAusStringArray(enthalteneKraeuter);
    }

Methoden:
private void setEnthalteneKraeuterAusStringArray(String [] kraeuter) {
    this.enthalteneKraeuter = new ArrayList <String>();
    	for(int i = 0; i<kraeuter.length;i++) {
    		this.enthalteneKraeuter.add(kraeuter[i]);
    	}
}

	public ArrayList <String> getEnthalteneKraeuter() {
		return enthalteneKraeuter;
	}

	public void setEnthalteneKraeuter(ArrayList <String> enthalteneKraeuter) {
		this.enthalteneKraeuter = enthalteneKraeuter;
	}

 	public String getEnthalteneKraeuterAlsString(char trenner) {
		String ergebnis = "";
		int i = 0;
		for(String kraut: enthalteneKraeuter) {
			ergebnis = ergebnis + kraut+"\n";
		}
		return ergebnis;
}
__________________________________________________________________________________________________________________________

Klasse TeeladenModel:
Attribut: 	private ArrayList <Teesorte> ts = new ArrayList <Teesorte>();

Methoden: 
	public void createTeesorte(int identnummer, String bezeichnung, String kategorie, String mitKoffein,
			String[] enthalteneKraeuter) {
		Teesorte neu = new Teesorte(identnummer, bezeichnung, kategorie, mitKoffein, enthalteneKraeuter);
		setTs(neu);
		notifyObserver();
}
		public ArrayList <Teesorte> getTs() {
		return ts;
	}

	public void setTs(Teesorte ts) {
		this.ts.add(ts);
	}

	public void schreibeTeesInCsvDatei() throws IOException {
		BufferedWriter aus = new BufferedWriter(new FileWriter("TeesortenAusgabe.csv", true));
		for(Teesorte sorte: ts) {
			aus.write(sorte.gibTeesorteZurueck(';'));
		}
		aus.close();
	}
________________________________________________________________________________________________________________________
	
Klassen View:
Methoden:
		public void zeigeTeesAn() {
		if (teesModel.getTs().size() > 0) {
			String text = "";
			for(Teesorte ts: teesModel.getTs()) {
				text = text + ts.gibTeesorteZurueck(' ')+"\n";
			}
			txtAnzeigeTees.setText(text);
		} else {
			zeigeInformationsfensterAn("Bisher wurde kein Tee aufgenommen!");
		}
	}

	mnItmCsvExport.setOnAction(e -> tc.schreibeTeesInCsvDatei());  


!!!Hier Achtung beim Kopieren, da die jeweiligen Controls ander s heißen -> evtl Namen der Attribute anpassen
!!!Außerdem muss in einem der beiden Controls der Name der Methode angepasst werden, da die Methode zeigeTeesorteAn() von 
	zeigeTeeAn() überschrieben wird


___________________________________________________________________________________________________________________
_____________________________________________________________________________________________________________________

TestKlasse:
	!!!Beim erstellen Haken bei setUp() und tearDown()

	
	
	
	class TeeladenTest {

	private Teesorte tee;
	@BeforeEach
	void setUp() throws Exception {
		String[] arr = { "IC", "RB" };
		ArrayList<String> ts = new ArrayList<String>();
		for (String arten : ts) {
			ts.add(arten);
		}
		this.tee = new Teesorte(2, "3", "3", "3", arr);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		assertFalse(()-> tee.getIdentnummer()==3);
	}
	
	void test2() {
		assertThrows(IllegalArgumentException.class, ()-> tee = new Teesorte(2, "3", "3", "3", null));
	}

}


Teesorte
Konstruktor:
	       	if(enthalteneKraeuter==null) {
       		throw new IllegalArgumentException("");
       	}
       	setEnthalteneKraeuterAusStringArray(enthalteneKraeuter);
    }































	

