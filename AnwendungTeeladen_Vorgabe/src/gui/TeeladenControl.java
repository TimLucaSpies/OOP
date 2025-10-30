package gui;

import java.io.IOException;

import business.TeeladenModel;
import javafx.stage.Stage;

public class TeeladenControl {

	TeeladenModel model;
	TeeladenView view;

	public TeeladenControl(Stage primaryStage) {
		super();
		this.model = new TeeladenModel();
		this.view = new TeeladenView(this, primaryStage, model);
	}

	public void nehmeTeeAuf() {
		try {
			this.model.nehmeTeeAuf(Integer.parseInt(this.view.getTxtIdentnummer().getText()),
					this.view.getTxtBezeichnung().getText(), this.view.getTxtKategorie().getText(),
					this.view.getTxtMitKoffein().getText(), this.view.getTxtEnthalteneKraeuter().getText().split(";"));
			this.view.zeigeInformationsfensterAn("Die Teesorte wurde aufgenommen!");
		} catch (Exception exc) {
			this.view.zeigeFehlermeldungsfensterAn(exc.getMessage());
		}
	}

	public void zeigeTeesorteAn() {
		if (this.model.getTeesorte() != null) {
			this.view.getTxtAnzeige().setText(this.model.getTeesorte().gibTeesorteZurueck(' '));
		} else {
			this.view.zeigeInformationsfensterAn("Bisher wurde keine Teesorte aufgenommen!");
		}
	}

	public void leseAusDatei(String typ) {
		try {
			if ("csv".equals(typ)) {
				model.leseAusDatei(typ);
				view.zeigeInformationsfensterAn("Die Teesorte wurde gelesen!");
			} else {
				view.zeigeInformationsfensterAn("Noch nicht implementiert!");
			}
		} catch (IOException exc) {
			view.zeigeFehlermeldungsfensterAn("IOException beim Lesen!");
		} catch (Exception exc) {
			view.zeigeFehlermeldungsfensterAn("Unbekannter Fehler beim Lesen!");
		}
	}

	public void schreibeTeesInCsvDatei() {
		try {
			model.schreibeTeesInCsvDatei();
			view.zeigeInformationsfensterAn("Die Teesorten wurden gespeichert!");
		} catch (IOException exc) {
			view.zeigeFehlermeldungsfensterAn("IOException beim Speichern!");
		} catch (Exception exc) {
			view.zeigeFehlermeldungsfensterAn("Unbekannter Fehler beim Speichern!");
		}
	}

}
