package gui;

import java.io.IOException;

import business.TeeladenModel;
import javafx.stage.Stage;

public class TeeladenControl {

	// Attribute / Objekte
	private TeeladenView tv;
	private TeeladenModel tm;
	
	// Konstruktor
	public TeeladenControl(Stage primaryStage) {
		
		this.tm = new TeeladenModel();
		this.tv = new TeeladenView(primaryStage, this, tm);
		
	}
	
	// Methoden / Fehler abfangen
	void nehmeTeeAuf() {
		try {
			tm.createTeesorte(Integer.parseInt(tv.txtIdentnummer.getText()), tv.txtBezeichnung.getText(),
							  tv.txtKategorie.getText(), tv.txtMitKoffein.getText(), tv.txtEnthalteneKraeuter.getText().split(";"));
			tv.zeigeInformationsfensterAn("Die Teesorte wurde aufgenommen!");
		} catch (Exception exc) {
			tv.zeigeFehlermeldungsfensterAn(exc.getMessage());
		}
	}
	
	void leseAusDatei(String typ) {
		try {
			if ("csv".equals(typ)) {
				tm.leseAusDatei(typ);
				tv.zeigeInformationsfensterAn("Die Teesorte wurde gelesen!");
			} else {
				tv.zeigeInformationsfensterAn("Noch nicht implementiert!");
			}
		} catch (IOException exc) {
			tv.zeigeFehlermeldungsfensterAn("IOException beim Lesen!");
		} catch (Exception exc) {
			tv.zeigeFehlermeldungsfensterAn("Unbekannter Fehler beim Lesen!");
		}
	}	
	
	void schreibeTeesInCsvDatei() {
		try {
			tm.schreibeTeesInCsvDatei();
   			tv.zeigeInformationsfensterAn("Die Teesorten wurden gespeichert!");
		}	
		catch(IOException exc){
			tv.zeigeFehlermeldungsfensterAn(
				"IOException beim Speichern!");
		}
		catch(Exception exc){
			tv.zeigeFehlermeldungsfensterAn(
				"Unbekannter Fehler beim Speichern!");
		}
	}
}