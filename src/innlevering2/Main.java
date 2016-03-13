/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package innlevering2;
import java.util.Scanner;
import java.sql.*;

/**
 *
 * @author sveinbra
 */

public class Main extends DBConn {
	
	public static Scanner sc = new Scanner(System.in);
	
    public static void main(String[] args) throws SQLException{
    	Main program = new Main();
		String input;
		
		System.out.println("Skriv inn din forespørsel:\n");
		System.out.println("    (1) Legge til ny treningsøkt");
		System.out.println("    (2) Hente resultater");
		System.out.println("    (3) Hente mål");
		
		boolean loopContinues = false;
		
		do {
			input = sc.nextLine();
			if (input.length() > 1 || !Character.isDigit(input.charAt(0))) {
				System.out.println("Ugyldig input. Skriv inn et tall 1-3.");
				loopContinues = true;
			}else{ loopContinues = false;}
		} while (loopContinues);
		
		program.parseRequest(input);
		
		sc.close();
    }
    
    public void parseRequest(String request) throws SQLException{
    		
    	switch(request){
    		case "1": {new OektRegistrerer();
    			return;
    		}
    		case "2":{ new ResultatHenter();
			return;
		}
    		case "3":{ new MaalHenter();
			return;
		}
    		}
    	}
    }
    
    
 

