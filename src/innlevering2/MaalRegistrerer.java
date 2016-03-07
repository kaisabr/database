package innlevering2;

public class MaalRegistrerer {
	// I denne klassen legges målene brukeren har registrert i databasen.
	public void maal(String oevelsesID, String datoFra, String datoTil) {

	}
	
	public void maalForUtholdenhet(String oevelsesID, String datoFra, String datoTil, String avstand, String varighetIMinutter, String hastighet, String puls) {
		maal(oevelsesID, datoFra,datoTil);
	}
	
	public void maalForKondisjon(String oevelsesID, String datoFra, String datoTil, String intervaller, String varighetIMinutter) {
		maal(oevelsesID, datoFra,datoTil);
	}
	
	public void maalForStyrke(String oevelsesID, String datoFra, String datoTil, String belastning, String antallSett, String antallRepetisjoner){
		maal(oevelsesID, datoFra,datoTil);
	}
	
	public void annetMaal(String oevelsesID, String datoFra, String datoTil, String beskrivelse) {
		maal(oevelsesID, datoFra,datoTil);
	}

}
