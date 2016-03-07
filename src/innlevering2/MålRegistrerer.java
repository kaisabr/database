package innlevering2;

public class MålRegistrerer {
	// I denne klassen legges målene brukeren har registrert i databasen.
	public void mål(String øvelsesID, String datoFra, String datoTil) {

	}
	
	public void målForUtholdenhet(String øvelsesID, String datoFra, String datoTil, String avstand, String varighetIMinutter, String hastighet, String puls) {
		mål(øvelsesID, datoFra,datoTil);
	}
	
	public void målForKondisjon(String øvelsesID, String datoFra, String datoTil, String intervaller, String varighetIMinutter) {
		mål(øvelsesID, datoFra,datoTil);
	}
	
	public void målForStyrke(String øvelsesID, String datoFra, String datoTil, String belastning, String antallSett, String antallRepetisjoner){
		mål(øvelsesID, datoFra,datoTil);
	}
	
	public void annetMål(String øvelsesID, String datoFra, String datoTil, String beskrivelse) {
		mål(øvelsesID, datoFra,datoTil);
	}

}
