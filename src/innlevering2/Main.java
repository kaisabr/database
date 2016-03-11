/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package innlevering2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.sql.*;

/**
 *
 * @author sveinbra
 */
public class Main extends DBConn {
	
	private ArrayList <String> codeWords = new ArrayList<String>(); //Et HashMap som lager sammenhengen mellom forskjellige forespørsler og tall.
	public static Scanner sc = new Scanner(System.in);
	
	// i denne klassen kommuniserer programmet med brukeren via scanner.

    /**
     * @param args the command line arguments
     */
	
	public void fillArray(){
		codeWords.add("addSession");
		codeWords.add("retrieveResults");
		codeWords.add("retrieveGoals");
	}
	
	
    public static void main(String[] args) throws SQLException{
    	Main program = new Main();
    	program.fillArray();
		String input;
		
		do{
			input = sc.nextLine();
			program.parseRequest(input); //Programmet tar inn mer og mer input helt til brukeren gir en blank linje. For hver linje, prøver programmet å tolke inputen.
		}while (!(input.equals("")));
		
		sc.close();
    	
        // TODO code application logic here

       /*
        RegMaalCtrl maalCtrl = new RegMaalCtrl ();
        maalCtrl.connect();
        maalCtrl.startReg(123123);
        maalCtrl.regPost(0, 0, 70);
        maalCtrl.regPost(1, 31, 100);
        maalCtrl.regPost(2, 32, 120);
        maalCtrl.regPost(3, 33, 140);
        maalCtrl.regPost(4, 34, 160);
        maalCtrl.regPost(5, 35, 180);
        maalCtrl.regPost(6, 36, 200);
        maalCtrl.regPost(7, 37, 220);
        maalCtrl.regPost(8, 150, 230);
        maalCtrl.regPost(9, 175, 245);
        if (maalCtrl.sluttReg()) {
            System.out.println("Profit!!");
        }
       */
       
       
        SkrivUt skrivUt = new SkrivUt ();
        skrivUt.connect();
        skrivUt.printTreningsoekt("1");
    }
    
    public void parseRequest(String input) throws SQLException{
    	String request = input;
    	
    	if (!(codeWords.contains("request"))) System.out.println("\n" + request + " er en ugyldig forespørsel."); //Skriver ut feilmelding hvis programmet ikke gjenkjenner kodeordet.
    	
    	switch(request){
    		case "addSession": new OektRegistrerer();
    		case "retrieveResults": new ResultatHenter();
    		case "retriveGoals": new MaalHenter();
    	}
    }
    
    
 
    

}
