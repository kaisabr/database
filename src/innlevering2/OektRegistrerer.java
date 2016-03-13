package innlevering2;

import java.sql.*;

public class OektRegistrerer extends DBConn {

	// I denne klassen legges oektene brukeren har registrert i databasen.

	private int oektID;

	public OektRegistrerer() throws SQLException {
		connect(); // Her kobler programmet seg til databasen. Legg merke til at connect-metoden arves fra DBConn.
		treningsoekt();
	}

	public int determineOektID() throws SQLException {
		Statement stmt = conn.createStatement();
		String query = "SELECT MAX(OEKTID) FROM TRENINGSOEKT";
		ResultSet rs = stmt.executeQuery(query);
		int res = rs.getInt("OEKTID");
		return res++;
	}

	public void treningsoekt() throws SQLException{
		oektID = determineOektID();

		String timeStamp = getTimeStamp();
		String duration = getDuration();
		String personalForm = getPersonalForm();
		String notes = getNotes();
		
		System.out.println("Er ¯kten ute (1) eller inne (2)? Svar: ");
		Integer oektType;
		String input;
		boolean loopContinues = false;

		do {
			input = Main.sc.nextLine();
			oektType = Character.getNumericValue(input.charAt(0));
			if (oektType != 1 && oektType != 2) {
				System.out.println("Ugyldig input.");
				loopContinues = true;
			}
		} while (loopContinues);

		switch (oektType) {
			case 1: // lag innend¯rst¯kt
			case 2: // lag utend¯rs¯kt
		}
	}

	private String getNotes() {
		System.out.println("Skriv inn notater pÂ en linje: \n");
		return Main.sc.nextLine();
	}

	private String getTimeStamp() {
		String input;
		boolean loopContinues = false;
		
		System.out.println("Skriv inn starttidspunkt for trenings¯kten pÂ formatet ≈≈≈≈-MM-DD TT-MM-SS:");
		
		do {
			input = Main.sc.nextLine();
			if (input.length() != 19) {
				System.out.println("Ugyldig input. Skriv inn pÂ nytt.");
				loopContinues = true;
			}
		} while (loopContinues);
		
		return input;
	}

	private String getDuration() {
		String input;
		boolean loopContinues = false;
		
		System.out.println("Skriv inn starttidspunkt for trenings¯kten pÂ formatet ≈≈≈≈-MM-DD TT-MM-SS:");
		
		do {
			input = Main.sc.nextLine();
			if (input.length() != 5) {
				System.out.println("Ugyldig input. Skriv inn pÂ nytt.");
				loopContinues = true;
			}
		} while (loopContinues);
		
		return input;
	}

	private String getPersonalForm() {
		String input;
		boolean loopContinues = false;

		do {
			input = Main.sc.nextLine();
			if (input.length() > 1 || !Character.isDigit(input.charAt(0))) {
				System.out.println("Ugyldig input.");
				loopContinues = true;
			}
		} while (loopContinues);
		
		return form;
	}

	public void utendoersoekt(String tidspunkt, String varighetIMinutter,
			String notater, String temperatur, String vaertype) {
		// legger til i trenings√∏kt
		treningsoekt(tidspunkt, varighetIMinutter, notater);
	}

	public void innendoersoekt(String tidspunkt, String varighetIMinutter,
			String notater, String luftventilasjon, String luft) {
		// legger til i trenings√∏kt
		treningsoekt(tidspunkt, varighetIMinutter, notater);
	}
}
