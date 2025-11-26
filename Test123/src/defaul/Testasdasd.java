package defaul;

public class Testasdasd {

	public static void main(String[] args) {
		String eins[] = { "Hallo", "welt" };
		String ausgabe = "leer";
		for (int i = 0; i < eins.length; i++) {
			ausgabe = eins[i] + ", " + ausgabe;
			System.out.println(ausgabe);
		}

	}

}

Singelton:
Im Model: 	
	private static TeeladenModel instanz = null;
	private TeeladenModel() {

	}

	public static TeeladenModel getInstanz() {
		if (instanz == null) {
			instanz = new TeeladenModel();
		}
		return instanz;
	}

In den Controls:
	this.tm = TeeladenModel.getInstanz();

________________________________________________________________________________________________________
Observer:
Interface Observer: 
	public void update();

Interface Observable:
	public void addObserver(Observer obs);
	public void removeObserver(Observer obs);
	public void notifiyObserver();

Im Model -> implements Observable:
	private Vector<Observer> obs = new Vector<Observer>();

	//public void createTeesorte(int identnummer, String bezeichnung, String kategorie, String mitKoffein,
		//	String[] enthalteneKraeuter) {
		//this.ts = new Teesorte(identnummer, bezeichnung, kategorie, mitKoffein, enthalteneKraeuter);
		notifiyObserver();
	}

	@Override
	public void addObserver(Observer obs) {
		this.obs.add(obs);

	}

	@Override
	public void removeObserver(Observer obs) {
		this.obs.remove(obs);
	}

	@Override
	public void notifiyObserver() {
		for (Observer obs : obs) {
			obs.update();
		}
	}

Views ändern:
In Views ->  implements Observer

	Im Konstruktor:{
	    	//this.tm = tm;
    		this.tm.addObserver(this);
	}	
		@Override
	public void update() {
		zeigeTeesorteAn();
		
	}

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
Oder Control ändern:
Im Konstruktor:
	this.model.addObserver(this);

	@Override
	public void update() {
		this.tv.zeigeTeesorteAn();
	}
	
	
	


























