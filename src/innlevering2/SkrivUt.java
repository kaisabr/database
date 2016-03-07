/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package innlevering2;

import java.sql.*;

/**
 *
 * @author Svein Erik
 */
public class SkrivUt extends DBConn {
	// i denne klassen skrives innholdet i databasen ut til bruker.
    
    public void printTreningsoekt (String oektID) {
       try {
           Statement stmt = conn.createStatement();
           String query = "select * from UTENDOERSOEKT, TRENINGSOEKT";
           //System.out.println(query);
           
           ResultSet rs = stmt.executeQuery(query);
           System.out.println("Oversikt: " + oektID);
           while (rs.next()) {
	    	//System.out.println("dato: " + rs.getString(1) + " tidspunkt: " + rs.getString(2));
        	   System.out.println(String.format(" Tidspunkt: %s \n Varighet: %s \n Temperatur: %s \n Værtype: %s \n Kommentar: %s", rs.getString("TIDSPUNKT"), rs.getString("VARIGHETIMINUTTER"), rs.getString("TEMPERATUR"), rs.getString("VAERTYPE"), rs.getString("NOTATER")));
           }
            
     	} catch (Exception e) {
            System.out.println("db error during select of loper = "+e);
    	}

    }
    public void printResultat (String ResultatRegistrerer) {
    	/*try {
            Statement stmt = conn.createStatement();
            String query = "select * from UTENDØRSØKT, TRENINGSØKT";
            //System.out.println(query);
            
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Oversikt: " + øktID);
            while (rs.next()) {
 	    	//System.out.println("dato: " + rs.getString(1) + " tidspunkt: " + rs.getString(2));
         	   System.out.println(String.format(" Tidspunkt: %s \n Varighet: %s \n Temperatur: %s \n Værtype: %s \n Kommentar: %s", rs.getString("TIDSPUNKT"), rs.getString("VARIGHETIMINUTTER"), rs.getString("TEMPERATUR"), rs.getString("VÆRTYPE"),rs.getString("NOTATER")));
            }
             
      	} catch (Exception e) {
             System.out.println("db error during select of loper = "+e);
     	}*/


    }
    public void printAlleKlasserResultat () {

    }
    public void printAlleKlasserStrekktid () {

    }
}
