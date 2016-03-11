package innlevering2;
import java.sql.*;

public class OektRegistrerer extends DBConn {

	// I denne klassen legges øktene brukeren har registrert i databasen.
	
	private int oektID;
	
	public OektRegistrerer() throws SQLException{
		oektID = getOektID();
		deter
		
		
		
		
		System.out.println("Er �kten ute (1) eller inne (2)? Svar: ");
		Integer oektType;
		String input;
		boolean loopContinues = false;
		
		do{
			input = Main.sc.nextLine();
			oektType = Character.getNumericValue(input.charAt(0));
			if (oektType != 1 && oektType != 2){
				System.out.println("Ugyldig input.");
				loopContinues = true;}
			}while (loopContinues);
		
		switch(oektType){
			case 1:
		}
		
	}
	
		
	
	public int getOektID() throws SQLException{
		Statement stmt = conn.createStatement();
		String query = "SELECT MAX(OEKTID) FROM TRENINGSOEKT";
		ResultSet rs = stmt.executeQuery(query);
		return rs.getInt("OEKTID");
	}
	
	public void treningsoekt(String tidspunkt, String varighetIMinutter, String notater ) {
		// registrerer en treningsøkt
		oektID ++;
	}
	
	public void utendoersoekt(String tidspunkt, String varighetIMinutter, String notater, String temperatur, String vaertype) {
		//legger til i treningsøkt
		treningsoekt(tidspunkt, varighetIMinutter, notater);
	}
	
	public void innendoersoekt(String tidspunkt, String varighetIMinutter, String notater, String luftventilasjon, String luft) {
		//legger til i treningsøkt
		treningsoekt(tidspunkt, varighetIMinutter, notater);
	}
}
