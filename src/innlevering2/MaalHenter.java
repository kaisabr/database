package innlevering2;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.Statement;

public class MaalHenter extends DBConn{
  MaalHenter() throws SQLException{	
	  connect();
	  
    System.out.println("For hvilke øvelser ønsker du å hente ut mål?");
		String exercise = Main.sc.nextLine();
		System.out.println("Skriv inn startdato for progresjonsmålingen, på formen åååå-mm-dd: ");
		String periodStart = Main.sc.nextLine();
		System.out.println("Skriv inn sluttdato for progresjonsmålingen, på formen åååå-mm-dd: ");
		String periodEnd = Main.sc.nextLine();
		
		goalForStrength(exercise, periodStart, periodEnd);
		goalforEndurance(exercise, periodStart, periodEnd);   
      
    }   

private void goalforEndurance(String exercise, String periodStart, String periodEnd) throws SQLException {
	Statement stmt = conn.createStatement();
    String query = "SELECT O.NAVN, M.DATOFRA, M.DATOTIL, MF.AVSTAND, MF.VARIHETIMINUTTER, MF.HASTIGHET, MF.PULS"
    		+ "FROM TRENINGSOEVELSE AS O, MAAL AS M, MAALFORUTHOLDENHET AS MF"
    		+ "WHERE O.NAVN='" + exercise + "'AND M.OEVELSESID=O.OEVELSESID AND MF.OEVELSESID=M.OEVELSESID AND MF.DATOFRA=M.DATOFRA"
    		+ "AND M.DATOFRA=>" + periodStart + "AND M.DATOTIL<=" + periodEnd + ";";
    ResultSet rs = stmt.executeQuery(query);
    while (rs.next()) {
    	   System.out.println(String.format("Navn på øvelse: %s \n" 
    			   +"Øvelsesnavn: %s \n"
    			   +"Dato fra %s \n"
    			   +"Dato til: %s \n"
    			   +"Avstand: %s \n"
    			   +"Varighet i minutter: %s \n"
    			   +"Hastighet: %s \n"
    			   +"Hastighet: %s \n"
    			   +rs.getString("NAVN"), rs.getString("DATOFRA"),rs.getString("DATOTIL"),rs.getString("AVSTAND"),rs.getString("VARIHETIMINUTTER"),rs.getString("HASTIGHET"),rs.getString("PULS")));
    }
}

private void goalForStrength(String exercise, String periodStart, String periodEnd) throws SQLException{
	Statement stmt1 = conn.createStatement();
    String query1 = "SELECT O.NAVN AS ØVELSESNAVN, M.DATOFRA, M.DATOTIL, MF.BELASTNING, MF.SETT, MF.REPETISJONER"
    		+ "FROM TRENINGSOEVELSE AS O, MAAL AS M, MAALFORSTYRKE AS MF"
    		+ "WHERE O.NAVN='" + exercise + "'AND M.OEVELSESID=O.OEVELSESID AND MF.OEVELSESID=M.OEVELSESID AND MF.DATOFRA=M.DATOFRA"
    		+ "AND M.DATOFRA>='" + periodStart + "' AND M.DATOTIL<='" + periodEnd + "';";
    ResultSet rs1 = stmt1.executeQuery(query1);
    while (rs1.next()) {
 	   System.out.println(String.format("Navn på øvelse: %s \n" 
 			   +"Øvelsesnavn: %s \n"
 			   +"Dato fra %s \n"
 			   +"Dato til: %s \n"
 			   +"Belastning: %s \n"
 			   +"Sett: %s \n"
 			   +"Repetisjoner: %s \n"
 			   +rs1.getString("NAVN"), rs1.getString("DATOFRA"),rs1.getString("DATOTIL"),rs1.getString("BELASTNING"),rs1.getString("SETT"),rs1.getString("REPETISJONER")));
		}
	}
}
