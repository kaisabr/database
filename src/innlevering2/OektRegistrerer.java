package innlevering2;

public class OektRegistrerer {

	// I denne klassen legges øktene brukeren har registrert i databasen.
	
	public static int oektID = 1;
	
	public void treningsoekt(String tidspunkt, String varighetIMinutter, String notater ) {
		// registrerer en treningsøkt
		oektID ++;
	}
	
	public void utendørsøkt(String tidspunkt, String varighetIMinutter, String notater, String temperatur, String vaertype) {
		//legger til i treningsøkt
		treningsoekt(tidspunkt, varighetIMinutter, notater);
	}
	
	public void innendørsøkt(String tidspunkt, String varighetIMinutter, String notater, String luftventilasjon, String luft) {
		//legger til i treningsøkt
		treningsoekt(tidspunkt, varighetIMinutter, notater);
	}
}
