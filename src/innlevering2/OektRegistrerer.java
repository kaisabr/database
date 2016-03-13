package innlevering2;

import java.sql.*;

public class OektRegistrerer extends DBConn {

	// I denne klassen legges oektene brukeren har registrert i databasen.

	private int oektID;

	public OektRegistrerer() throws SQLException {
		connect(); // Her kobler programmet seg til databasen. Legg merke til at connect-metoden arves fra DBConn.
		treningsoekt();
	}
	
	public void treningsoekt() throws SQLException{
		oektID = determineOektID();

		String timeStamp = getTimeStamp();
		String duration = getDuration();
		String personalForm = getPersonalForm();
		String notes = getNotes();
		
		Statement stmt = conn.createStatement();
		String query = "INSERT INTO TRENINGSOEKT VALUES (" + oektID + ",'" + timeStamp + "'," + duration + "," + personalForm + ",'" + notes + "');";
		stmt.executeUpdate(query);
		
		boolean outdoorSession = isSessionOutDoors();

		if (outdoorSession){ utendoersoekt(oektID, timeStamp);}
		else{innendoersoekt(oektID, timeStamp);}
	}
	
	public void innendoersoekt(int oektID, String timeStamp) throws SQLException{
		String ventilation = getVentilation();
		String air = getAir();
		
		Statement stmt = conn.createStatement();
		String query = "INSERT INTO UTENDOERSOEKT VALUES (" + oektID + ",'" + timeStamp + "','" + ventilation + "','" + air + "');";
		stmt.executeUpdate(query);
	}

	public void utendoersoekt(int oektID, String timeStamp) throws SQLException{
		String temperature = getTemperature();
		String weatherType = getWeatherType();
		
		Statement stmt = conn.createStatement();
		String query = "INSERT INTO UTENDOERSOEKT VALUES (" + oektID + ",'" + timeStamp + "'," + temperature + ",'" + weatherType + "');";
		stmt.executeUpdate(query);
	}
	
	public int determineOektID() throws SQLException {
		Statement stmt = conn.createStatement();
		String query = "SELECT MAX(OEKTID) FROM TRENINGSOEKT;";
		ResultSet rs = stmt.executeQuery(query);
		rs.next();
		int res = rs.getInt("MAX(OEKTID)") + 1;
		return res;
	}

	private boolean isSessionOutDoors() {
		System.out.println("Er �kten ute (1) eller inne (2)? Svar: ");
		Integer oektType;
		String input;
		boolean loopContinues = false;

		do {
			input = Main.sc.nextLine();
			oektType = Character.getNumericValue(input.charAt(0));
			if (oektType != 1 && oektType != 2) {
				System.out.println("Ugyldig input.");
				loopContinues = true;
			}else{ loopContinues = false;}
		} while (loopContinues);
		
		if (oektType == 1){return true;}
		else{ return false;}
	}

	private String getNotes() {
		System.out.println("Skriv inn notater p� en linje: \n");
		return Main.sc.nextLine();
	}

	private String getTimeStamp() {
		String input;
		boolean loopContinues = false;
		
		System.out.println("Skriv inn starttidspunkt for trenings�kten p� formatet ����-MM-DD TT:MM:SS:");
		
		do {
			input = Main.sc.nextLine();
			if (input.length() != 19) {
				System.out.println("Ugyldig input. Skriv inn p� nytt.");
				loopContinues = true;
			}else{ loopContinues = false;}
		} while (loopContinues);
		
		return input;
	}

	private String getDuration() {
		String input;
		boolean loopContinues = false;
		
		System.out.println("Skriv inn varigheten for trenings�kten i antall minutter p� formatet iii.i:");
		
		do {
			input = Main.sc.nextLine();
			if (input.length() != 5) {
				System.out.println("Ugyldig input. Skriv inn p� nytt.");
				loopContinues = true;
			}else{ loopContinues = false;}
		} while (loopContinues);
		
		return input;
	}

	private String getPersonalForm() {
		String input;
		boolean loopContinues = false;

		System.out.println("Angi et siffer 0-9 for � beskrive din personlige form under �kten:");
		
		do {
			input = Main.sc.nextLine();
			if (input.length() > 1 || !Character.isDigit(input.charAt(0))) {
				System.out.println("Ugyldig input.");
				loopContinues = true;
			}else{ loopContinues = false;}
		} while (loopContinues);
		
		return input;
	}

	private String getTemperature() {
		String input;
		boolean loopContinues = false;
		
		System.out.println("Skriv inn temperaturen under trenings�kten i antall grader p� formatet ii.i:");
		
		do {
			input = Main.sc.nextLine();
			if (input.length() != 4) {
				System.out.println("Ugyldig input. Skriv inn p� nytt.");
				loopContinues = true;
			}else{ loopContinues = false;}
		} while (loopContinues);
		
		return input;
	}

	private String getWeatherType() {
		String input;
		boolean loopContinues = false;
		
		System.out.println("Skriv inn v�rtypen under trenings�kten, maks 20 tegn: ");
		
		do {
			input = Main.sc.nextLine();
			if (input.length() > 20) {
				System.out.println("Ugyldig input. M� v�re 20 tegn eller mindre. Skriv inn p� nytt.");
				loopContinues = true;
			}else{ loopContinues = false;}
		} while (loopContinues);
		
		return input;
	}

	private String getVentilation(){
		String input;
		boolean loopContinues = false;

		System.out.println("Angi et siffer 0-9 for � beskrive ventilasjonen under �kten:");
		
		do {
			input = Main.sc.nextLine();
			if (input.length() > 1 || !Character.isDigit(input.charAt(0))) {
				System.out.println("Ugyldig input.");
				loopContinues = true;
			}else{ loopContinues = false;}
		} while (loopContinues);
		
		return input;
	}
	
	private String getAir(){
		String input;
		boolean loopContinues = false;
		
		System.out.println("Angi et siffer 0-9 for � beskrive luftkvaliteten under �kten:");

		do {
			input = Main.sc.nextLine();
			if (input.length() > 1 || !Character.isDigit(input.charAt(0))) {
				System.out.println("Ugyldig input.");
				loopContinues = true;
			}else{ loopContinues = false;}
		} while (loopContinues);
		
		return input;
	}
}
