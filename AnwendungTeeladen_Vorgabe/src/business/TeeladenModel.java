package business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class TeeladenModel {

	private Teesorte teesorte;

	public void nehmeTeeAuf(int i1, String s1, String s2, String s3, String[] arr) {
		this.setTeesorte(new Teesorte(i1, s1, s2, s3, arr));
	}

	public Teesorte getTeesorte() {
		return teesorte;
	}

	public void setTeesorte(Teesorte teesorte) {
		this.teesorte = teesorte;
	}

	public void leseAusDatei(String typ) throws Exception {
		BufferedReader ein = new BufferedReader(new FileReader("Teesorte.csv"));
		String[] zeile = ein.readLine().split(";");
		this.teesorte = new Teesorte(Integer.parseInt(zeile[0]), zeile[1], zeile[2], zeile[3], zeile[4].split("_"));
		ein.close();
	}
	
	public void schreibeTeesInCsvDatei() throws IOException {
		BufferedWriter aus 
		= new BufferedWriter(new FileWriter("TeesortenAusgabe.csv", true));
	aus.write(this.teesorte.gibTeesorteZurueck(';'));
	aus.close();
	}
}
