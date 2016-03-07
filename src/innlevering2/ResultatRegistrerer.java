package innlevering2;

public class ResultatRegistrerer {
	// I denne klassen legges resultatene brukeren har registrert i databasen.
	
	public void resultat(String oevelsesID, String oektID) {

	}
	
	public void utholdenhetsresultat(String oevelsesID, String oektID, String tidspunkt, String lengde, String varighetIMinutter, String hastighet, String puls) {
		resultat(oevelsesID, oektID);
	}
	
	public void kondisjonsresultat(String oevelsesID, String oektID, String belastning, String antallRepetisjoner, String antallSett) {
		resultat(oevelsesID, oektID);
	}
	
	public void styrkeresultat(String oevelsesID, String oektID, String belastning, String antallRepetisjoner, String antallSett) {
		resultat(oevelsesID, oektID);
	}
}
