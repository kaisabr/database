package innlevering2;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.Statement;

public class MaalHenter extends DBConn{
  MaalHenter() throws SQLException{	
	  connect();
	  
    System.out.println("For hvilke �velser �nsker du � hente ut m�l?");
		String exercise = Main.sc.nextLine();
		System.out.println("Skriv inn startdato for progresjonsm�lingen, p� formen ����-mm-dd: ");
		String periodStart = Main.sc.nextLine();
		System.out.println("Skriv inn sluttdato for progresjonsm�lingen, p� formen ����-mm-dd: ");
		String periodEnd = Main.sc.nextLine();
		
		
		Statement stmt = conn.createStatement();
        String query = "SELECT OEVELSESID FROM TRENINGSOEVELSE WHERE NAVN = " + exercise;
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
        	   System.out.println(String.format("Navn p� �velse: %s", rs.getString("NAVN")));
        }
        
        Statement stmt1 = conn.createStatement();
        String query1 = "SELECT * FROM MAAL AS M WHERE DATO >= " + periodEnd + " AND DATO <= " + periodStart + " AND M.OEVELSESID = TRENINGSOEVELSE.OEVELSESID WHERE ";
        ResultSet rs1 = stmt1.executeQuery(query1);
        while (rs1.next()) {
        	   System.out.println(String.format("Navn p� �velse: %s", rs.getString("NAVN")));
        }
        
	}
}
