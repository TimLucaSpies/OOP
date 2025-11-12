package business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TeeladenModel {

	// Attribute / Objekte
	private Teesorte ts;
	
	// Konstruktormethode
	public void createTeesorte(int identnummer, String bezeichnung, String kategorie,String mitKoffein, String[] enthalteneKraeuter) {
		this.ts = new Teesorte(identnummer, bezeichnung, kategorie, mitKoffein, enthalteneKraeuter);
	}
	
	// Getter & Setter
	public Teesorte getTs() {
		return ts;
	}

	public void setTs(Teesorte ts) {
		this.ts = ts;
	}
	
	// Methoden / Datenverarbeitung	
	public void leseAusDatei(String typ) throws IOException {
		BufferedReader ein = new BufferedReader(new FileReader("Teesorte.csv"));
		String[] zeile = ein.readLine().split(";");
		createTeesorte(Integer.parseInt(zeile[0]), zeile[1], zeile[2], zeile[3], zeile[4].split("_"));
		ein.close();
	}	
	
	public void schreibeTeesInCsvDatei() throws IOException {
		BufferedWriter aus = new BufferedWriter(new FileWriter("TeesortenAusgabe.csv", true));
		aus.write(ts.gibTeesorteZurueck(';'));
		aus.close();
	}
}