/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package innlevering2;
/**
 *
 * @author sveinbra
 */
import java.sql.*;
import java.util.*;

public class RegMaalCtrl extends DBConn {
    private int brikkeNr;
    private static final int INGEN_BRIKKE = -1;

    public RegMaalCtrl () {
        brikkeNr = INGEN_BRIKKE;
    }

    public void startReg(int brikkeNr) {
        this.brikkeNr = brikkeNr;
    }
    public void regPost (int sekvNr, int postNr, int tid) {
        if (brikkeNr != INGEN_BRIKKE) {
            try {
                Statement statement = conn.createStatement();
                statement.executeUpdate("INSERT INTO Reg " + "VALUES (" + sekvNr + "," + brikkeNr + "," + postNr + "," + tid + ")");
            } catch (Exception e) {
                System.out.println("db error during insert of Reg sekvnr= "+sekvNr+" postNr="+postNr);
            }
        }
    }

    static class Reg {
        public int sekv;
        public int reg;
        public Reg(int s, int r) {
            sekv = s;
            reg = r;
        }
    }
    
    public boolean sluttReg () {
        ArrayList<Reg> loperPoster = new ArrayList<Reg>();
        int startTid = -1;
        int sluttTid = -1;
        int lopsTid = -1;

    	try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select sekvnr, postnr, tid from Reg where brikkenr=" + brikkeNr + " order by sekvnr");
            while (rs.next()) {
                if (startTid == -1) {
                    startTid = rs.getInt("tid");
                }
                sluttTid = rs.getInt("tid");
                loperPoster.add(new Reg(rs.getInt("sekvnr"), rs.getInt("postnr")));
            }

        } catch (Exception e) {
            System.out.println("db error during select of loperposter = "+e);
            return false;
        }

 
        lopsTid = sluttTid - startTid;

        int[] loype = new int [100];
        int nPoster = 0;
        try {
            Statement stmt = conn.createStatement();
	    ResultSet rs = stmt.executeQuery("select postnr from Loype, Klasse, Loper where Loper.brikkenr="+brikkeNr+
                        " and Loper.klasse=Klasse.klassenavn and Klasse.lnr=Loype.lnr order by Loype.sekvnr");

	    while (rs.next())
	    {
	    	loype[nPoster++]=rs.getInt("postnr");
	    }
    	} catch (Exception e) {
            System.out.println("db error during select of postnr = "+e);
            return false;
    	}

        for (int i=0; i<nPoster; i++) {
            if (loype[i] != loperPoster.get(i).reg) {
                try {
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate("update Loper set status='dsq' where brikkenr="+brikkeNr);
                } catch (Exception e) {
                    System.out.println("db error during update of loper ="+e);
                }
                brikkeNr = INGEN_BRIKKE;
                return false;
            }
        }
        // alt ok
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("update Loper set status='ok', lopstid="+lopsTid+" where brikkenr="+brikkeNr);
        } catch (Exception e) {
            System.out.println("db error during update of loper ="+e);
        }
        brikkeNr = INGEN_BRIKKE;
        return true;
    }

}
