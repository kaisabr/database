package innlevering2;

import java.sql.*;

public class OektRegistrerer extends DBConn {

	// I denne klassen legges Ã¸ktene brukeren har registrert i databasen.

	private int oektID;

	public OektRegistrerer() throws SQLException {
		connect(); // Her kobler programmet seg til databasen. Legg merke til at
					// connect-metoden arves fra DBConn.
		oektID = determineOektID();

		// Her skal det lages en treningsoekt.

		System.out.println("Er økten ute (1) eller inne (2)? Svar: ");
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
		case 1: // lag innendørstøkt
		case 2: // lag utendørsøkt
		}

	}

	public int determineOektID() throws SQLException {
		Statement stmt = conn.createStatement();
		String query = "SELECT MAX(OEKTID) FROM TRENINGSOEKT";
		ResultSet rs = stmt.executeQuery(query);
		int res = rs.getInt("OEKTID");
		return res++;
	}

	public void treningsoekt(String tidspunkt, String varighetIMinutter,
			String notater) {
		// registrerer en treningsÃ¸kt
		oektID++;
	}

	public void utendoersoekt(String tidspunkt, String varighetIMinutter,
			String notater, String temperatur, String vaertype) {
		// legger til i treningsÃ¸kt
		treningsoekt(tidspunkt, varighetIMinutter, notater);
	}

	public void innendoersoekt(String tidspunkt, String varighetIMinutter,
			String notater, String luftventilasjon, String luft) {
		// legger til i treningsÃ¸kt
		treningsoekt(tidspunkt, varighetIMinutter, notater);
	}
}
