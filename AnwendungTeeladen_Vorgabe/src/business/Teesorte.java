package business;

public class Teesorte {
	
    private int  identnummer;
    private String bezeichnung;
    private String kategorie;
    private String mitKoffein;
    private String[] enthalteneKraeuter;
    
    public Teesorte(int identnummer, String bezeichnung, String kategorie,
       	String mitKoffein, String[] enthalteneKraeuter){
    	this.identnummer = identnummer;
      	this.bezeichnung = bezeichnung;
       	this.kategorie = kategorie;
       	this.mitKoffein = mitKoffein;
       	this.enthalteneKraeuter = enthalteneKraeuter;
    }

	public int getIdentnummer() {
		return identnummer;
	}

	public void setIdentnummer(int identnummer) {
		this.identnummer = identnummer;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public String getKategorie() {
		return kategorie;
	}

	public void setKategorie(String kategorie) {
		this.kategorie = kategorie;
	}

	public String getMitKoffein() {
		return mitKoffein;
	}

	public void setMitKoffein(String mitKoffein) {
		this.mitKoffein = mitKoffein;
	}

	public String[] getEnthalteneKraeuter() {
		return enthalteneKraeuter;
	}

	public void setEnthalteneKraeuter(String[] enthalteneKraeuter) {
		this.enthalteneKraeuter = enthalteneKraeuter;
	}
	
 	public String getEnthalteneKraeuterAlsString(char trenner) {
		String ergebnis = "";
		int i = 0;
		for(i = 0; i < this.getEnthalteneKraeuter().length - 1; i++) {
			ergebnis = ergebnis + this.getEnthalteneKraeuter()[i] + trenner; 
		}
		return ergebnis	+ this.getEnthalteneKraeuter()[i];
	}
	
	public String gibTeesorteZurueck(char trenner){
  		return this.getIdentnummer() + trenner 
  			+ this.getBezeichnung() + trenner
  			+ this.getKategorie() + trenner
  		    + this.getMitKoffein() + trenner + "\n"
  		    + this.getEnthalteneKraeuterAlsString(trenner) + "\n";
  	}
}

