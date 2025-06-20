package exetastiki;		

import java.util.Random;	
import java.util.Scanner; 	

public class Nafmaxia {		

	static int[][] pinakas = new int[7][7];		 
	static int[][] krymenaploia;				
	static int xtypimenaploia = 0;			
	static final int synolikaploia = 6;			
	static int attempts = 0;					 
	static int totalhits = 0;					
	static int totalmisses = 0;
	
		static void arxikopinakas() {		
			for (int a = 0; a < 7; a++) {				  
				for (int b = 0; b < 7; b++)	{			
					pinakas[a][b] = -1;					
			}
		}
	}
		
		static void arxikoploia() {		  
			krymenaploia = new int[4][3]; 				
	        Random rand = new Random();					
			
	        int grammi; int stili;						
	        for (int i = 0; i < 2; i++) {				
	            do {									
	                grammi = rand.nextInt(7);			
	                stili = rand.nextInt(7);			
	            } while (ploiatamplo(grammi, stili)); 	
	            krymenaploia[i][0] = grammi;	 		
	            krymenaploia[i][1] = stili;				
	        }

	        for (int i = 2; i < 4; i++) {																				
	            boolean thesi = false;																					
	            while (!thesi) {																					
	                grammi = rand.nextInt(7);																		
	                stili = rand.nextInt(7);																		
	                int katefthinsi = rand.nextInt(2);															
	                if (katefthinsi == 0) { 																	
	                    if (stili < 6 && !ploiatamplo(grammi, stili) && !ploiatamplo(grammi, stili + 1)) {	    
	                        krymenaploia[i][0] = grammi;	 													
	                        krymenaploia[i][1] = stili;
	                        krymenaploia[i][2] = stili + 1;
	                        thesi = true;																		
	                    }
	                } else { 																						
	                    if (grammi < 6 && !ploiatamplo(grammi, stili) && !ploiatamplo(grammi + 1, stili)) {	  		 
	                        krymenaploia[i][0] = grammi;																
	                        krymenaploia[i][1] = stili;
	                        krymenaploia[i][2] = grammi + 1;
	                        thesi = true;																				
	                    }
	                }
	            }
	        }
	    }
		
		static void printsimvouli(int grammi, int stili) {		
	        int countgrammi = 0; int countstili = 0;																						
	        for (int[] ploio : krymenaploia) {																								
	        	if (ploio[0] == grammi || (ploio.length > 2 && ploio[2] == grammi)) countgrammi++;											
	        	if (ploio[1] == stili || (ploio.length > 2 && ploio[2] == stili)) countstili++;	   											
	        }
	        System.out.printf("Grammi %d has %d ships. Stili %d has %d ships.\n", grammi + 1, countgrammi, stili + 1, countstili);			
	    }
		
		static void ektipositamplo() {             					 
			System.out.println("  1  2  3  4  5  6  7");  			
	        for (int i = 0; i < 7; i++) {							
	            System.out.print(i + 1);						
	            for (int j = 0; j < 7; j++) {					
	                switch (pinakas[i][j]) {						 
	                    case 1 -> System.out.print(" 💥"); 			
	                    case 0 -> System.out.print(" ❌"); 			
	                    default -> System.out.print(" 🌊"); 			
	                }
	            }
	            System.out.println();									
	        }
	    }
		
		static boolean ploiatamplo(int grammi, int stili) {			 
	        for (int[] ploio : krymenaploia) {										
	        	if ((ploio[0] == grammi && ploio[1] == stili) ||					
	        		(ploio.length > 2 && 											
	        	      ((ploio[1] == stili && ploio[2] == stili + 1) || 				
	        	      (ploio[1] == grammi && ploio[2] == grammi + 1)))) { 			
	        	      return true;													
	            }
	        }
	        return false;															
	    }
		
		static void enimerosiripseon(int grammi, int stili) {	
			if (pinakas[grammi][stili] == -1) {																								
				attempts++;																													
				if (ploiatamplo(grammi, stili)) {																							
	            pinakas[grammi][stili] = 1; 																								
	            xtypimenaploia++;																											
	            totalhits++;																												
	            System.out.println("Well done you hit a ship!");																			
	        } else {																														
	            pinakas[grammi][stili] = 0; 																								
	            totalmisses++;																												
	            System.out.println("Unfortunately you didn't hit a ship.");																	
	        }
	        ektipositamplo();																												
	        printsimvouli(grammi, stili);																									
			} else {
				System.out.println("You have already entered a target at the given coordinates! Try aiming at a different target.");  		
			}
		}
		
	public static void main(String[] args) {	
		arxikopinakas();								
		arxikoploia();									
		ektipositamplo();								
		System.out.println("---------------------");  
		
																																	
		while (xtypimenaploia < synolikaploia) {																				
			Scanner scan = new Scanner(System.in);	 																			
			try {																											
				System.out.print("Please enter the coordinate of the row 1-7 to shoot: ");								
				int targetgrammi = scan.nextInt() - 1;																	
				System.out.print("Please enter the coordinate of the column 1-7 to shoot: ");							
				int targetstili = scan.nextInt() - 1;																	
		
		if (targetgrammi >= 0 && targetgrammi < 7 && targetstili >= 0 && targetstili < 7) {										
            enimerosiripseon(targetgrammi, targetstili);																			
            System.out.println("---------------------");		
		} else {																													
            System.out.println("Invalid coordinates. Please enter a positive integer for row and column from 1 to 7.");
        }
      } catch (Exception ex) {																										
    	  System.out.println("Invalid coordinates. Please enter numeric values only!");												
          scan.nextLine(); 																											
		ektipositamplo();																											
      }
	}
		System.out.println("Congratulations! You sank all the ships and it took " + attempts + " attempts.");   						
		System.out.println("Statistics:");  																							
        System.out.println("Total Hits: " + totalhits);																					
        System.out.println("Total Misses: " + totalmisses);																				
  }
}