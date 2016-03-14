package innlevering2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultatHenter2 extends DBConn{
	

	String oektID;
	String newline = System.getProperty("line.separator");
	
	public ResultatHenter2() throws SQLException{
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
				System.out.println("Resultat for denne �ktID finnes ikke.");
			}
			
			System.out.println(resultatType(oektID, rs));
			return;
			
		case 2: printHistory();
				return;
		}
	}

	// Finner ut om oektIDen horer til en styrkeoekt, en utholdenshetsoekt eller en kondisjonsoekt

	private void printHistory() throws SQLException{
		System.out.println("Skriv inn tidspunkt til foeste oekt du oensker resultat fra, og faa ut alle paafoelgende oekter. Tidspunkt paa format yyyy-mm-dd tt:mm:ss.");
		String tidspunkt = Main.sc.nextLine();
		Statement stmt2 = conn.createStatement();
		String query1 = "SELECT O.NAVN, T.TIDSPUNKT, S.BELASTNING, S.ANTALLREPETISJONER, S.ANTALLSETT FROM RESULTAT AS R, TRENINGSOEKT AS T, STYRKERESULTAT AS S, TRENINGSOEVELSE AS O "
				+"WHERE R.OEKTID = T.OEKTID AND T.TIDSPUNKT>='" + tidspunkt +"' AND S.OEKTID = R.OEKTID AND S.OEVELSESID = R.OEVELSESID AND R.OEVELSESID = O.OEVELSESID;";
		ResultSet result = stmt2.executeQuery(query1);
		while (result.next()){
			System.out.println(printStrength(result));
		}
		
		String query2 = "SELECT O.NAVN, T.TIDSPUNKT, S.LENGDE, S.VARIGHETIMINUTTER, S.HASTIGHET, S.PULS FROM RESULTAT AS R, TRENINGSOEKT AS T, UTHOLDENSHETRESULTAT AS S, TRENINGSOEVELSE AS O "
				+"WHERE R.OEKTID = T.OEKTID AND T.TIDSPUNKT>='" + tidspunkt +"' AND S.OEKTID = R.OEKTID AND S.OEVELSESID = R.OEVELSESID AND R.OEVELSESID = O.OEVELSESID;";
		ResultSet result2 = stmt2.executeQuery(query2);
		while (result2.next()){
			System.out.println(printEndurance(result2));
		}
		
		String query3 = "SELECT O.NAVN, T.TIDSPUNKT, S.BELASTNING, S.ANTALLREPETISJONER, S.ANTALLSETT FROM RESULTAT AS R, TRENINGSOEKT AS T, KONDISJONSRESULTAT AS S, TRENINGSOEVELSE AS O "
				+"WHERE R.OEKTID = T.OEKTID AND T.TIDSPUNKT>='" + tidspunkt +"' AND S.OEKTID = R.OEKTID AND S.OEVELSESID = R.OEVELSESID AND R.OEVELSESID = O.OEVELSESID;";
		ResultSet result3 = stmt2.executeQuery(query3);
		while (result3.next()){
			System.out.println(printStamina(result3));
		}
	}


	public String resultatType(String oektID, ResultSet rs) throws SQLException{
		
		if (checkStyrkeresultat(rs)){return printStrength(rs);}
		
		if(checkUtholdenhetsresultat(rs)){return printEndurance(rs);}
		
		if(checkKondisjonsresultat(rs)){return printStamina(rs);}
		
		return "Ingen registerte oekter";
	}

	public String printStrength(ResultSet rs) throws SQLException{
		String styrkeoekt = "";
		styrkeoekt = (String.format("Resultat for styrkeoekt:"));
		styrkeoekt += newline;
		styrkeoekt += (String.format("Belastning: %s", rs.getString("BELASTNING")));
		styrkeoekt += newline;
		styrkeoekt += (String.format("Antall repetisjoner: %s", rs.getString("ANTALLREPETISJONER")));
		styrkeoekt += newline;
		styrkeoekt += (String.format("Antall sett: %s\n", rs.getString("ANTALLSETT")));
		return styrkeoekt;
	}
	
	public String printEndurance(ResultSet rs) throws SQLException{
		String utholdenhetsoekt = "";
		utholdenhetsoekt += (String.format("Resultat for utholdenhetsresultat:"));
		utholdenhetsoekt += newline;
		utholdenhetsoekt += (String.format("Lengde: %s", rs.getString("LENGDE")));
		utholdenhetsoekt += newline;
		utholdenhetsoekt += (String.format("Varighet i minutter: %s", rs.getString("VARIGHETIMINUTTER")));
		utholdenhetsoekt += newline;
		utholdenhetsoekt += (String.format("Hastighet: %s", rs.getString("HASTIGHET")));
		utholdenhetsoekt += newline;
		utholdenhetsoekt += (String.format("Puls: %s \n", rs.getString("PULS")));
		return utholdenhetsoekt;
	}
	
	public String printStamina(ResultSet rs) throws SQLException{
		String kondisjonsoekt = "";
		kondisjonsoekt += (String.format("Resultat for kondisjonsresultat:"));
		kondisjonsoekt += newline;
		kondisjonsoekt += (String.format("Belastning: %s", rs.getString("BELASTNING")));
		kondisjonsoekt += newline;
		kondisjonsoekt += (String.format("Antall repetisjoner: %s", rs.getString("ANTALLREPETISJONER")));
		kondisjonsoekt += newline;
		kondisjonsoekt += (String.format("Antall sett: %s \n", rs.getString("ANTALLSETT")));
		return kondisjonsoekt; 
	}
	
	public boolean checkStyrkeresultat(ResultSet rs) throws SQLException{
		String query1 = "SELECT * FROM RESULTAT, TRENINGSOEKT, STYRKERESULTAT WHERE RESULTAT.OEKTID = TRENINGSOEKT.OEKTID AND STYRKERESULTAT.OEKTID = RESULTAT.OEKTID AND RESULTAT.OEKTID = "+oektID;
		Statement stmt = conn.createStatement();
		rs = stmt.executeQuery(query1);
		if(rs.next()){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean checkUtholdenhetsresultat(ResultSet rs) throws SQLException{
		String query2 = "SELECT * FROM RESULTAT, TRENINGSOEKT, UTHOLDENSHETRESULTAT WHERE RESULTAT.OEKTID = TRENINGSOEKT.OEKTID AND UTHOLDENSHETRESULTAT.OEKTID = RESULTAT.OEKTID AND RESULTAT.OEKTID="+oektID;
		Statement stmt = conn.createStatement();
		rs = stmt.executeQuery(query2);
		if(rs.next()){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean checkKondisjonsresultat(ResultSet rs) throws SQLException{
		String query3 = "SELECT * FROM RESULTAT, TRENINGSOEKT, KONDISJONSRESULTAT WHERE RESULTAT.OEKTID = TRENINGSOEKT.OEKTID AND KONDISJONSRESULTAT.OEKTID = RESULTAT.OEKTID AND RESULTAT.OEKTID="+oektID;
		Statement stmt = conn.createStatement();
		rs = stmt.executeQuery(query3);
		if(rs.next()){
			return true;
		}
		else{
			return false;
		}
	}
	
}
