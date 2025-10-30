package gui;
   
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import business.Teesorte;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ownUtil.*;

public class TeeladenAnwendungssystem {
	  
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
    
    // speichert temporaer ein Objekt vom Typ tee
    private Teesorte teesorte;
    
    public TeeladenAnwendungssystem(Stage primaryStage){
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
        	    nehmeTeeAuf();
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
	       	 	leseAusDatei("csv");
	    	}
	    });
	    mnItmTxtImport.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		     	leseAusDatei("txt");
		    }
    	});
	    mnItmCsvExport.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				schreibeTeesInCsvDatei();
			}	
	    });
    }
    
    private void nehmeTeeAuf(){
    	try{ 
    		this.teesorte = new Teesorte(
    			Integer.parseInt(txtIdentnummer.getText()), 
   	            txtBezeichnung.getText(),
   	            txtKategorie.getText(),
   	        	txtMitKoffein.getText(),
    		    txtEnthalteneKraeuter.getText().split(";"));
    		zeigeInformationsfensterAn("Die Teesorte wurde aufgenommen!");
       	}
       	catch(Exception exc){
       		zeigeFehlermeldungsfensterAn(exc.getMessage());
     	}
    }
   
    private void zeigeTeesorteAn(){
    	if(this.teesorte != null){
    		txtAnzeige.setText(
    			this.teesorte.gibTeesorteZurueck(' '));
    	}
    	else{
    		zeigeInformationsfensterAn("Bisher wurde keine Teesorte aufgenommen!");
    	}
    }    
		  
    private void leseAusDatei(String typ){
    	try {
      		if("csv".equals(typ)){
      			BufferedReader ein = new BufferedReader(new FileReader("Teesorte.csv"));
      			String[] zeile = ein.readLine().split(";");
      			this.teesorte = new Teesorte(Integer.parseInt(zeile[0]), 
      				zeile[1], 
      				zeile[2], 
      				zeile[3], 
      				zeile[4].split("_"));
      				ein.close();
      	  			zeigeInformationsfensterAn(
      	  	   			"Die Teesorte wurde gelesen!");
      		}
       		else{
	   			zeigeInformationsfensterAn(
	   				"Noch nicht implementiert!");
	   		}
		}
		catch(IOException exc){
			zeigeFehlermeldungsfensterAn(
				"IOException beim Lesen!");
		}
		catch(Exception exc){
			zeigeFehlermeldungsfensterAn(
				"Unbekannter Fehler beim Lesen!");
		}
	}
		
	private void schreibeTeesInCsvDatei() {
		try {
			BufferedWriter aus 
				= new BufferedWriter(new FileWriter("TeesortenAusgabe.csv", true));
			aus.write(this.teesorte.gibTeesorteZurueck(';'));
			aus.close();
   			zeigeInformationsfensterAn(
	   			"Die Teesorten wurden gespeichert!");
		}	
		catch(IOException exc){
			zeigeFehlermeldungsfensterAn(
				"IOException beim Speichern!");
		}
		catch(Exception exc){
			zeigeFehlermeldungsfensterAn(
				"Unbekannter Fehler beim Speichern!");
		}
	}

    private void zeigeInformationsfensterAn(String meldung){
    	new MeldungsfensterAnzeiger(AlertType.INFORMATION,
    		"Information", meldung).zeigeMeldungsfensterAn();
    }	
    
    void zeigeFehlermeldungsfensterAn(String meldung){
       	new MeldungsfensterAnzeiger(AlertType.ERROR,
        	"Fehler", meldung).zeigeMeldungsfensterAn();
    }

}
