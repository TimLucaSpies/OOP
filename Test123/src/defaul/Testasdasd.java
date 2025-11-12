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
