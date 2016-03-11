package innlevering2;


import java.sql.ResultSet;
import java.sql.Statement;

public class Maalhenter {
  Maalhenter() {	
    System.out.println("For hvilke øvelse ønsker du å hente ut mål?");
		String exercise = Main.sc.nextLine();
		System.out.println("Skriv inn startdato for progresjonsmålingen, på formen åååå-mm-dd: ");
		String periodStart = Main.sc.nextLine();
		System.out.println("Skriv inn sluttdato for progresjonsmålingen, på formen åååå-mm-dd: ");
		String periodEnd = Main.sc.nextLine();
		
		
		Statement stmt = conn.createStatement();
        String query = "SELECT * FROM TRENINGSOEVELSE WHERE NAVN = " + exercise;
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
        	   System.out.println(String.format("Navn på øvelse: %s", rs.getString("NAVN")));
        }
        
        Statement stmt1 = conn.createStatement();
        String query1 = "SELECT * FROM TRENINGSØVELSE WHERE DATO >=" + periodEnd + "AND DATO <=" + periodStart;
        ResultSet rs1 = stmt.executeQuery(query);
        while (rs1.next()) {
        	   System.out.println(String.format("Navn på øvelse: %s", rs.getString("NAVN")));
        }
        
	}
}
