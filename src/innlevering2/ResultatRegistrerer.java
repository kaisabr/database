package innlevering2;

public class ResultatRegistrerer {
	// I denne klassen legges resultatene brukeren har registrert i databasen.
	
	public void resultat(String øvelsesID, String øktID) {

	}
	
	public void utholdenhetsresultat(String øvelsesID, String øktID, String tidspunkt, String lengde, String varighetIMinutter, String hastighet, String puls) {
		resultat(øvelsesID, øktID);
	}
	
	public void kondisjonsresultat(String øvelsesID, String øktID, String belastning, String antallRepetisjoner, String antallSett) {
		resultat(øvelsesID, øktID);
	}
	
	public void styrkeresultat(String øvelsesID, String øktID, String belastning, String antallRepetisjoner, String antallSett) {
		resultat(øvelsesID, øktID);
	}
}
