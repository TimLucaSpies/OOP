package main;

import gui.TeeladenControl;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		new TeeladenControl(primaryStage);
	}	
	
	public static void main(String[] args){
		launch(args);
	}
}