package innlevering2;

public class ØktRegistrerer {

	// I denne klassen legges øktene brukeren har registrert i databasen.
	
	public static int øktID = 1;
	
	public void treningsøkt(String tidspunkt, String varighetIMinutter, String notater ) {
		// registrerer en treningsøkt
		øktID ++;
	}
	
	public void utendørsøkt(String tidspunkt, String varighetIMinutter, String notater, String temperatur, String værtype) {
		//legger til i treningsøkt
		treningsøkt(tidspunkt, varighetIMinutter, notater);
	}
	
	public void innendørsøkt(String tidspunkt, String varighetIMinutter, String notater, String luftventilasjon, String luft) {
		//legger til i treningsøkt
		treningsøkt(tidspunkt, varighetIMinutter, notater);
	}
}
