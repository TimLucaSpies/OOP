1. Package anlegen mit business.Entity

-----------------------------------------------------------------------------------------------------------------------------------

2. Darin die Klasse der Entity anlegen und EntityModel anlegen
  EntityModel:
    package business.motorrad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import ownUtil.Observable;
import ownUtil.Observer;

public class MotorradModel2 {
	private ArrayList<Motorrad2> motorrads = new ArrayList<Motorrad2>() ;
	private static MotorradModel2 instans = null;

	
	public static MotorradModel2 getInstance() {
		if (instance == null) {
			instance = new MotorradModel2();
		}
		return instance;
		
	}
	

	private MotorradModel2() {
		super();
	}
	
	public void leseMotorradAusCsvDatei()
			throws IOException {
			BufferedReader ein = new BufferedReader(new FileReader("Motorrad.csv"));
	 		ArrayList<Motorrad2> ergebnis = new ArrayList<Motorrad2>(); 
			String zeileStr = ein.readLine();
			while(zeileStr != null) {
				String[] zeile = zeileStr.split(";");
				
	           		ergebnis.add( new Motorrad2(zeile[0], zeile[1], zeile[2]));
	           		zeileStr = ein.readLine();
			}    
	 		ein.close();
	 		this.motorrads = ergebnis;
	 	}


	public ArrayList<Motorrad2> getMotorrads() {
		return motorrads;
	}


	public void setMotorrads(ArrayList<Motorrad2> motorrads) {
		this.motorrads = motorrads;
	}

----------------------------------------------------------------------------------------------------------------
3. WarenübersichtView:
3.1 neues Attribut vom neuen Model
3.2 Label, TextAre und Button auch für zweite Entity anlegen:
          private Label lblAnzeigeAutos = new Label("Anzeige Autos");
          private TextArea txtAnzeigeAutos = new TextArea();
          NEU: private Label lblAnzeigeMotorad = new Label("Anzeige Motorad");
          NEU: private TextArea txtAnzeigeMotorad = new TextArea();
          private Button btnAnzeigeAutos = new Button("Anzeige");
          NEU: private Button btnAnzeigeMotor = new Button("csv import");

3.3 im Konstruktor das neue Model übergeben und initialisieren:
        public FahrzeuguebersichtView(FahrzeuguebersichtControl fahrzeuguebersichtControl, Stage primaryStage, AutosModel autosModel, MotorradModel motModel)
        this.autosModel = autosModel;
	      NEU: this.motModel = motModel;

3.4 Init Methoden und init listener:
	private void initKomponenten() {
		// Label
		initKomponentenTee();
		initKomponentenMotrad();
		/*
		Font font = new Font("Arial", 20);
		lblAnzeigeAutos.setLayoutX(310);
		lblAnzeigeAutos.setLayoutY(40);
		lblAnzeigeAutos.setFont(font);
		lblAnzeigeAutos.setStyle("-fx-font-weight: bold;");
		*/
		pane.getChildren().addAll(lblAnzeigeTees,lblAnzeigeMotorad);
		// Textbereich
		/*
		txtAnzeigeAutos.setEditable(false);
		txtAnzeigeAutos.setLayoutX(310);
		txtAnzeigeAutos.setLayoutY(90);
		txtAnzeigeAutos.setPrefWidth(220);
		txtAnzeigeAutos.setPrefHeight(185);
		*/
		pane.getChildren().addAll(txtAnzeigeTees,txtAnzeigeMotorad);
		// Button
		pane.getChildren().addAll(btnAnzeigeTees,btnAnzeigeMotor);
	}
	
	private void initKomponentenTee() {
		Font font = new Font("Arial", 20);
		lblAnzeigeTees.setLayoutX(310); //20
		lblAnzeigeTees.setLayoutY(40);
		lblAnzeigeTees.setFont(font);
		lblAnzeigeTees.setStyle("-fx-font-weight: bold;");
		txtAnzeigeTees.setEditable(false);
		txtAnzeigeTees.setLayoutX(310);
		txtAnzeigeTees.setLayoutY(90);
		txtAnzeigeTees.setPrefWidth(220);
		txtAnzeigeTees.setPrefHeight(185);
		btnAnzeigeTees.setLayoutX(310);
		btnAnzeigeTees.setLayoutY(290);
	}
	
	private void initKomponentenMotrad() {
		Font font = new Font("Arial", 20);
		lblAnzeigeMotorad.setLayoutX(20);
		lblAnzeigeMotorad.setLayoutY(40);
		lblAnzeigeMotorad.setFont(font);
		lblAnzeigeMotorad.setStyle("-fx-font-weight: bold;");
		txtAnzeigeMotorad.setEditable(false);
		txtAnzeigeMotorad.setLayoutX(20);
		txtAnzeigeMotorad.setLayoutY(90);
		txtAnzeigeMotorad.setPrefWidth(220);
		txtAnzeigeMotorad.setPrefHeight(185);
		btnAnzeigeMotor.setLayoutX(20);
		btnAnzeigeMotor.setLayoutY(290);
	}


	private void initListener() {
		btnAnzeigeTees.setOnAction(e -> zeigeTeesAn());
		btnAnzeigeMotor.setOnAction(e->zeigeMotorradAn());
	}

3.5 Als letztes dann neue Methode zeigeEntityAn() einfügen:
  private void zeigeMotorradAn() {
		warenuebersichtControl.leseMotorradAusCsvDatei();
		if (motModel.getMotorrads().size() > 0) {
			StringBuffer text = new StringBuffer();
			for (Motorrad2 sh : motModel.getMotorrads()) {
				text.append(sh.gibMotorradZurueck(' ') + "\n");
			}
			this.txtAnzeigeMotorad.setText(text.toString());
		} else {
			zeigeInformationsfensterAn("Es gibt keine Sporthalle in der csv-Datei!");
		}
	}
-------------------------------------------------------------------------------------------------------------------------------------------------
4 Warenuebersicht Control
- Neues Attribut vom neuen Model
- Im Konstruktor mit Singleton initialsieren

public class WarenuebersichtControl implements Observer{
	private WarenuebersichtView warenuebersichtView;
	private TeeladenModel teesModel;
	private MotorradModel2 mModel;

	public WarenuebersichtControl(Stage primaryStage) {
		this.teesModel = TeeladenModel.getInstanz();
		this.mModel = MotorradModel2.getInstance();
		this.warenuebersichtView = new WarenuebersichtView(this, primaryStage, teesModel, mModel);
		this.teesModel.addObserver(this);
	}

	@Override
	public void update() {
		this.warenuebersichtView.zeigeTeesAn();
	}
	
	public void leseMotorradAusCsvDatei() {
		try {
			this.mModel.leseMotorradAusCsvDatei();
		} catch (IOException exc) {
			this.warenuebersichtView.zeigeInformationsfensterAn("IOException beim Lesen von Mototrrad!");
		} catch (Exception exc) {
			this.warenuebersichtView.zeigeInformationsfensterAn("Unbekannter Fehler beim Lesen von " + " Mototrrad!");
		}
	}
}





}

