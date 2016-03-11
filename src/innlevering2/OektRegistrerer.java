package innlevering2;
import java.sql.*;

public class OektRegistrerer extends DBConn {

	// I denne klassen legges øktene brukeren har registrert i databasen.
	
	private int oektID;
	
	public OektRegistrerer() throws SQLException{
		oektID = getOektID();
	}
	
	public int getOektID() throws SQLException{
		Statement stmt = conn.createStatement();
		String query = "SELECT MAX(OEKTID) FROM TRENINGSOEKT";
		ResultSet rs = stmt.executeQuery(query);
		
		return 0;
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
