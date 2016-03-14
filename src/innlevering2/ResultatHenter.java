package innlevering2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultatHenter extends DBConn{
	
	ResultSet rs1;
	ResultSet rs2;
	ResultSet rs3;
	String oektID;
	String newline = System.getProperty("line.separator");
	
	public ResultatHenter() throws SQLException{
		connect();
		
		String input;
		boolean loopContinues = false;
		Integer oektantall;
		
		
		System.out.println("Oensker du resultater fra en oekt [1] eller flere [2]?");
		do{
			input = Main.sc.nextLine();
			oektantall = Character.getNumericValue(input.charAt(0));
			if(oektantall != 1 && oektantall != 2){
				System.out.println("Ugyldig input.");
				loopContinues = true;
			}
		
		} while(loopContinues);
		
		switch(oektantall) {
		case 1: //hent ut resultat fra én økt
			System.out.println("Hvilken oektID oensker du resultatet fra? Skriv et tall oektID.");
			oektID = Main.sc.nextLine();
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM RESULTAT, TRENINGSOEKT WHERE RESULTAT.OEKTID = TRENINGSOEKT.OEKTID AND TRENINGSOEKT.OEKTID ="+ oektID;
			ResultSet rs = stmt.executeQuery(query);
			
			if (! rs.next()){
				System.out.println("OektID finnes ikke.");
			}
			
			System.out.println(resultatType(oektID));
			return;
			
		case 2: // hent ut resultat fra flere økter
			System.out.println("Skriv inn tidspunkt til foeste oekt du ønsker resultat fra, og faa ut alle paafoelgende oekter. Tidspunkt paa format yyyy-mm-dd tt.mm.ss.");
			String tidspunkt = Main.sc.nextLine();
			Statement stmt2 = conn.createStatement();
			String finnOektID = "SELECT RESTULTAT.OEKTID FROM TRENINGSOEKT, RESULTAT WHERE TRENINGSOEKT.TIDSPUNKT >="+tidspunkt;
			ResultSet result = stmt2.executeQuery(finnOektID);
			while (result.next()){
				System.out.println(resultatType(finnOektID));
			}			
			
		}
	}
	
	
	// Finner ut om oektIDen horer til en styrkeoekt, en utholdenshetsoekt eller en kondisjonsoekt

	public String resultatType(String oektID) throws SQLException{
		
		if (checkStyrkeresultat()){
			String styrkeoekt = "";
			styrkeoekt = (String.format("Resultat for styrkeoekt:"));
			styrkeoekt += newline;
			styrkeoekt += (String.format("Belastning: %s", rs1.getString("BELASTNING")));
			styrkeoekt += newline;
			styrkeoekt += (String.format("Antall repetisjoner: %s", rs1.getString("ANTALLREPETISJONER")));
			styrkeoekt += newline;
			styrkeoekt += (String.format("Antall sett: %s", rs1.getString("ANTALLSETT")));
			return styrkeoekt;
		}
		
		else if(checkUtholdenhetsresultat()){
			String utholdenhetsoekt = "";
			utholdenhetsoekt += (String.format("Resultat for utholdenhetsresultat:"));
			utholdenhetsoekt += newline;
			utholdenhetsoekt += (String.format("Lengde: %s", rs2.getString("LENGDE")));
			utholdenhetsoekt += newline;
			utholdenhetsoekt += (String.format("Varighet i minutter: %s", rs2.getString("VARIGHETIMINUTTER")));
			utholdenhetsoekt += newline;
			utholdenhetsoekt += (String.format("Hastighet: %s", rs2.getString("HASTIGHET")));
			utholdenhetsoekt += newline;
			utholdenhetsoekt += (String.format("Puls: %s", rs2.getString("PULS")));
			return utholdenhetsoekt;
		}
		
		else if(checkKondisjonsresultat()){
			String kondisjonsoekt = "";
			kondisjonsoekt += (String.format("Resultat for kondisjonsresultat:"));
			kondisjonsoekt += newline;
			kondisjonsoekt += (String.format("Belastning: %s", rs3.getString("BELASTNING")));
			kondisjonsoekt += newline;
			kondisjonsoekt += (String.format("Antall repetisjoner: %s", rs3.getString("ANTALLREPETISJONER")));
			kondisjonsoekt += newline;
			kondisjonsoekt += (String.format("Antall sett: %s", rs3.getString("ANTALLSETT")));
			return kondisjonsoekt; 
		}
		
		else {
			return "Ingen registerte oekter";
		}
	}
	
	public boolean checkStyrkeresultat() throws SQLException{
		String query1 = "SELECT * FROM RESULTAT, TRENINGSOEKT, STYRKERESULTAT WHERE RESULTAT.OEKTID = TRENINGSOEKT.OEKTID AND STYRKERESULTAT.OEKTID = TRENINGSOEKT.OEKTID AND RESULTAT.OEKTID = "+oektID;
		Statement stmt = conn.createStatement();
		rs1 = stmt.executeQuery(query1);
		if(rs1.next()){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean checkUtholdenhetsresultat() throws SQLException{
		String query2 = "SELECT * FROM RESULTAT, TRENINGSOEKT, UTHOLDENSHETRESULTAT WHERE RESULTAT.OEKTID = TRENINGSOEKT.OEKTID AND UTHOLDENSHETRESULTAT.OEKTID = TRENINGSOEKT.OEKTID AND RESULTAT.OEKTID="+oektID;
		Statement stmt = conn.createStatement();
		rs2 = stmt.executeQuery(query2);
		if(rs2.next()){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean checkKondisjonsresultat() throws SQLException{
		String query3 = "SELECT * FROM RESULTAT, TRENINGSOEKT, KONDISJONSRESULTAT WHERE RESULTAT.OEKTID = TRENINGSOEKT.OEKTID AND KONDISJONSRESULTAT.OEKTID = TRENINGSOEKT.OEKTID AND RESULTAT.OEKTID="+oektID;
		Statement stmt = conn.createStatement();
		rs3 = stmt.executeQuery(query3);
		if(rs3.next()){
			return true;
		}
		else{
			return false;
		}
	}
	
}
