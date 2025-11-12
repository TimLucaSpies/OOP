package gui;

import business.TeeladenModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ownUtil.MeldungsfensterAnzeiger;

public class TeeladenView {
	
	// Attribute / Objekte
	private TeeladenModel tm;
	private TeeladenControl tc;
	
	//---Anfang Attribute der grafischen Oberflaeche---
    private Pane pane     					= new  Pane();
    private Label lblEingabe    	 		= new Label("Eingabe");
    private Label lblAnzeige   	 	    	= new Label("Anzeige");
    private Label lblIdentnummer 			= new Label("Identnummer:");
    private Label lblBezeichnung   		 	= new Label("Bezeichnung:");
    private Label lblKategorie  	 	 	= new Label("Kategorie:");
    private Label lblMitKoffein   		 	= new Label("Mit Koffein:");
    private Label lblEnthalteneKraeuter  	= new Label("Enthaltene Kraeuter:");
    private TextField txtIdentnummer 	 	= new TextField();
    private TextField txtBezeichnung		= new TextField();
    private TextField txtKategorie			= new TextField();
    private TextField txtMitKoffein			= new TextField();
    private TextField txtEnthalteneKraeuter	= new TextField();
    private TextArea txtAnzeige  			= new TextArea();
    private Button btnEingabe 		 		= new Button("Eingabe");
    private Button btnAnzeige 		 		= new Button("Anzeige");
    private MenuBar mnbrMenuLeiste  		= new MenuBar();
    private Menu mnDatei             		= new Menu("Datei");
    private MenuItem mnItmCsvImport = new MenuItem("csv-Import");
    private MenuItem mnItmTxtImport = new MenuItem("txt-Import");
    private MenuItem mnItmCsvExport = new MenuItem("csv-Export");    
    //-------Ende Attribute der grafischen Oberflaeche-------

    // Konstruktor
    public TeeladenView(Stage primaryStage, TeeladenControl tc, TeeladenModel tm){
    	
    	this.tc = tc;
    	this.tm = tm;
    	
    	Scene scene = new Scene(this.pane, 700, 340);
    	primaryStage.setScene(scene);
    	primaryStage.setTitle("Verwaltung von Tees");
    	primaryStage.show();
    	this.initKomponenten();
		this.initListener();
    }
    
    private void initKomponenten(){
       	// Labels
    	lblEingabe.setLayoutX(20);
    	lblEingabe.setLayoutY(40);
    	Font font = new Font("Arial", 24); 
    	lblEingabe.setFont(font);
    	lblEingabe.setStyle("-fx-font-weight: bold;"); 
    	lblAnzeige.setLayoutX(400);
    	lblAnzeige.setLayoutY(40);
      	lblAnzeige.setFont(font);
       	lblAnzeige.setStyle("-fx-font-weight: bold;"); 
       	lblIdentnummer.setLayoutX(20);
    	lblIdentnummer.setLayoutY(90);
    	lblBezeichnung.setLayoutX(20);
    	lblBezeichnung.setLayoutY(130);
    	lblKategorie.setLayoutX(20);
    	lblKategorie.setLayoutY(170);
    	lblMitKoffein.setLayoutX(20);
    	lblMitKoffein.setLayoutY(210);
    	lblEnthalteneKraeuter.setLayoutX(20);
    	lblEnthalteneKraeuter.setLayoutY(250);    	
       	pane.getChildren().addAll(lblEingabe, lblAnzeige, 
       		lblIdentnummer, lblBezeichnung, lblKategorie,
       		lblMitKoffein, lblEnthalteneKraeuter);
    
    	// Textfelder
     	txtIdentnummer.setLayoutX(170);
    	txtIdentnummer.setLayoutY(90);
    	txtIdentnummer.setPrefWidth(200);
    	txtBezeichnung.setLayoutX(170);
    	txtBezeichnung.setLayoutY(130);
    	txtBezeichnung.setPrefWidth(200);
    	txtKategorie.setLayoutX(170);
    	txtKategorie.setLayoutY(170);
    	txtKategorie.setPrefWidth(200);
      	txtMitKoffein.setLayoutX(170);
    	txtMitKoffein.setLayoutY(210);
    	txtMitKoffein.setPrefWidth(200);
    	txtEnthalteneKraeuter.setLayoutX(170);
    	txtEnthalteneKraeuter.setLayoutY(250);
    	txtEnthalteneKraeuter.setPrefWidth(200);
      	pane.getChildren().addAll( 
     		txtIdentnummer, txtBezeichnung, txtKategorie,
     		txtMitKoffein, txtEnthalteneKraeuter);
     	
        // Textbereich	
        txtAnzeige.setEditable(false);
     	txtAnzeige.setLayoutX(400);
    	txtAnzeige.setLayoutY(90);
     	txtAnzeige.setPrefWidth(270);
    	txtAnzeige.setPrefHeight(185);
       	pane.getChildren().add(txtAnzeige); 
       	
        // Buttons
        btnEingabe.setLayoutX(20);
        btnEingabe.setLayoutY(290);
        btnAnzeige.setLayoutX(400);
        btnAnzeige.setLayoutY(290);
        pane.getChildren().addAll(btnEingabe, btnAnzeige); 
        
 		// Menue
  	    this.mnbrMenuLeiste.getMenus().add(mnDatei);
  	    this.mnDatei.getItems().add(mnItmCsvImport);
  	    this.mnDatei.getItems().add(mnItmTxtImport);
  	    this.mnDatei.getItems().add(new SeparatorMenuItem());
  	    this.mnDatei.getItems().add(mnItmCsvExport);
 	    pane.getChildren().add(mnbrMenuLeiste);
   }
   
   private void initListener() {
	    btnEingabe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
        	    tc.nehmeTeeAuf();
            }
	    });
	    btnAnzeige.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent e) {
	            zeigeTeesorteAn();
	        } 
   	    });
	    mnItmCsvImport.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent e) {
	       	 	tc.leseAusDatei("csv");
	    	}
	    });
	    mnItmTxtImport.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		     	tc.leseAusDatei("txt");
		    }
    	});
	    mnItmCsvExport.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				tc.schreibeTeesInCsvDatei();
			}	
	    });
    }	
   
   void zeigeInformationsfensterAn(String meldung){
   	new MeldungsfensterAnzeiger(AlertType.INFORMATION,
   		"Information", meldung).zeigeMeldungsfensterAn();
   }	
   
   void zeigeFehlermeldungsfensterAn(String meldung){
      	new MeldungsfensterAnzeiger(AlertType.ERROR,
       	"Fehler", meldung).zeigeMeldungsfensterAn();
   }
   
	private void zeigeTeesorteAn() {
		if (tm.getTs() != null) {
			txtAnzeige.setText(tm.getTs().gibTeesorteZurueck(' '));
		} else {
			zeigeInformationsfensterAn("Bisher wurde keine Teesorte aufgenommen!");
		}
	}

	public TeeladenModel getTm() {
		return tm;
	}

	public void setTm(TeeladenModel tm) {
		this.tm = tm;
	}

	public TeeladenControl getTc() {
		return tc;
	}

	public void setTc(TeeladenControl tc) {
		this.tc = tc;
	}

	public Pane getPane() {
		return pane;
	}

	public void setPane(Pane pane) {
		this.pane = pane;
	}

	public Label getLblEingabe() {
		return lblEingabe;
	}

	public void setLblEingabe(Label lblEingabe) {
		this.lblEingabe = lblEingabe;
	}

	public Label getLblAnzeige() {
		return lblAnzeige;
	}

	public void setLblAnzeige(Label lblAnzeige) {
		this.lblAnzeige = lblAnzeige;
	}

	public Label getLblIdentnummer() {
		return lblIdentnummer;
	}

	public void setLblIdentnummer(Label lblIdentnummer) {
		this.lblIdentnummer = lblIdentnummer;
	}

	public Label getLblBezeichnung() {
		return lblBezeichnung;
	}

	public void setLblBezeichnung(Label lblBezeichnung) {
		this.lblBezeichnung = lblBezeichnung;
	}

	public Label getLblKategorie() {
		return lblKategorie;
	}

	public void setLblKategorie(Label lblKategorie) {
		this.lblKategorie = lblKategorie;
	}

	public Label getLblMitKoffein() {
		return lblMitKoffein;
	}

	public void setLblMitKoffein(Label lblMitKoffein) {
		this.lblMitKoffein = lblMitKoffein;
	}

	public Label getLblEnthalteneKraeuter() {
		return lblEnthalteneKraeuter;
	}

	public void setLblEnthalteneKraeuter(Label lblEnthalteneKraeuter) {
		this.lblEnthalteneKraeuter = lblEnthalteneKraeuter;
	}

	public TextField getTxtIdentnummer() {
		return txtIdentnummer;
	}

	public void setTxtIdentnummer(TextField txtIdentnummer) {
		this.txtIdentnummer = txtIdentnummer;
	}

	public TextField getTxtBezeichnung() {
		return txtBezeichnung;
	}

	public void setTxtBezeichnung(TextField txtBezeichnung) {
		this.txtBezeichnung = txtBezeichnung;
	}

	public TextField getTxtKategorie() {
		return txtKategorie;
	}

	public void setTxtKategorie(TextField txtKategorie) {
		this.txtKategorie = txtKategorie;
	}

	public TextField getTxtMitKoffein() {
		return txtMitKoffein;
	}

	public void setTxtMitKoffein(TextField txtMitKoffein) {
		this.txtMitKoffein = txtMitKoffein;
	}

	public TextField getTxtEnthalteneKraeuter() {
		return txtEnthalteneKraeuter;
	}

	public void setTxtEnthalteneKraeuter(TextField txtEnthalteneKraeuter) {
		this.txtEnthalteneKraeuter = txtEnthalteneKraeuter;
	}

	public TextArea getTxtAnzeige() {
		return txtAnzeige;
	}

	public void setTxtAnzeige(TextArea txtAnzeige) {
		this.txtAnzeige = txtAnzeige;
	}

	public Button getBtnEingabe() {
		return btnEingabe;
	}

	public void setBtnEingabe(Button btnEingabe) {
		this.btnEingabe = btnEingabe;
	}

	public Button getBtnAnzeige() {
		return btnAnzeige;
	}

	public void setBtnAnzeige(Button btnAnzeige) {
		this.btnAnzeige = btnAnzeige;
	}

	public MenuBar getMnbrMenuLeiste() {
		return mnbrMenuLeiste;
	}

	public void setMnbrMenuLeiste(MenuBar mnbrMenuLeiste) {
		this.mnbrMenuLeiste = mnbrMenuLeiste;
	}

	public Menu getMnDatei() {
		return mnDatei;
	}

	public void setMnDatei(Menu mnDatei) {
		this.mnDatei = mnDatei;
	}

	public MenuItem getMnItmCsvImport() {
		return mnItmCsvImport;
	}

	public void setMnItmCsvImport(MenuItem mnItmCsvImport) {
		this.mnItmCsvImport = mnItmCsvImport;
	}

	public MenuItem getMnItmTxtImport() {
		return mnItmTxtImport;
	}

	public void setMnItmTxtImport(MenuItem mnItmTxtImport) {
		this.mnItmTxtImport = mnItmTxtImport;
	}

	public MenuItem getMnItmCsvExport() {
		return mnItmCsvExport;
	}

	public void setMnItmCsvExport(MenuItem mnItmCsvExport) {
		this.mnItmCsvExport = mnItmCsvExport;
	}
}