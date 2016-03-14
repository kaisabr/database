package innlevering2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultatHenter extends DBConn{
	
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
			String oektID = Main.sc.nextLine();
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM RESULTAT, TRENINGSOEKT WHERE RESULTAT."+oektID+"= TRENINGSOEKT."+ oektID;
			ResultSet rs = stmt.executeQuery(query);
			
			if (! rs.next()){
				System.out.println("OektID finnes ikke.");
			}
			
			System.out.println(resultatType(oektID));
			return;
			
		case 2: // hent ut resultat fra flere økter
			System.out.println("Skriv inn dato til foeste oekt du ønsker resultat fra, og faa ut alle paafoelgende oekter. Dato paa format yyyy-mm-dd.");
			String dato = Main.sc.nextLine();
			Statement stmt2 = conn.createStatement();
			String finnOektID = "SELECT R.OEKTID FROM TRENINGSOEKT AS T, RESULTAT AS R WHERE T.DATO >=" + dato;
			ResultSet result = stmt2.executeQuery(finnOektID);
			while (result.next()){
				System.out.println(resultatType(finnOektID));
			}			
			
		}
	}
	
	
	// Finner ut om oektIDen horer til en styrkeoekt, en utholdenshetsoekt eller en kondisjonsoekt

	public String resultatType(String oektID) throws SQLException{
		String query1 = "SELECT * FROM RESULTAT WHERE RESULTAT."+oektID+"= TRENINGSOEKT."+ oektID+"JOIN STYRKERESULTAT";
		String query2 = "SELECT * FROM RESULTAT WHERE RESULTAT."+oektID+"= TRENINGSOEKT."+ oektID+"JOIN UTHOLDENSHETSRESULTAT";
		String query3 = "SELECT * FROM RESULTAT WHERE RESULTAT."+oektID+"= TRENINGSOEKT."+ oektID+"JOIN KONDISJONSRESULTAT";
		Statement stmt = conn.createStatement();
		ResultSet rs1 = stmt.executeQuery(query1);
		ResultSet rs2 = stmt.executeQuery(query2);
		ResultSet rs3 = stmt.executeQuery(query3);
		
		if (rs1.next()){
			String styrkeoekt = "";
			styrkeoekt = (String.format("Resultat for styrkeoekt:"));
			styrkeoekt += (String.format("Belastning:", rs1.getString("BELASTNING")));
			styrkeoekt += (String.format("Antall repetisjoner:", rs1.getString("ANTALLREPETISJONER")));
			styrkeoekt += (String.format("Antall sett:", rs1.getString("ANTALLSETT")));
			return styrkeoekt;
		}
		
		else if(rs2.next()){
			String utholdenhetsoekt = "";
			utholdenhetsoekt += (String.format("Resultat for utholdenhetsresultat:"));
			utholdenhetsoekt += (String.format("Lengde:", rs2.getString("LENGDE")));
			utholdenhetsoekt += (String.format("Varighet i minutter:", rs2.getString("VARIGHETIMINUTTER")));
			utholdenhetsoekt += (String.format("Hastighet:", rs2.getString("HASTIGHET")));
			utholdenhetsoekt += (String.format("Puls:", rs2.getString("PULS")));
			return utholdenhetsoekt;
		}
		
		else if(rs3.next()){
			String kondisjonsoekt = "";
			kondisjonsoekt += (String.format("Resultat for kondisjonsresultat:"));
			kondisjonsoekt += (String.format("Belastning:", rs3.getString("BELASTNING")));
			kondisjonsoekt += (String.format("Antall repetisjoner:", rs3.getString("ANTALLREPETISJONER")));
			kondisjonsoekt += (String.format("Antall sett:", rs3.getString("ANTALLSETT")));
			return kondisjonsoekt; 
		}
		
		else {
			return "Ingen registerte oekter";
		}
	}
	
}
