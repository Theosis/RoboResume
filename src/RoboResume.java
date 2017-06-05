//	************************
//	*** RoboResume *********
//	*** A RESUME BUILDER ***
//	************************

import java.util.ArrayList;
import java.util.Scanner;

public class RoboResume {
	
		public static void main(String[] args) {
	
		final int maxDuties = 12; // max number of duties listed per job

		Scanner kb = new Scanner(System.in);
		//fVariables are for form-filling:
		String fName, fEmail, fEdAch, fEdAchOrg, fEdAchYr; 
		String fWrkXp, fWrkXpOrg, fWrkXpYrs, fDuty, duties4Print; 
		ArrayList<String> duties = new ArrayList<String>(maxDuties); // aux temp storage for duties of each job
		String fSkill, fSkillLvl;
		boolean done, doneDuties;
		int numItems, numDuty;
		Person fPerson;

		println("RESUME ENTRY FORM\n");
		
		fPerson = new Person();
		
		//	****************************
		//	*** INPUT NAME AND EMAIL ***
		//	****************************
		
		do {
			print("Name? ");
			fName = kb.nextLine();
			fPerson.name = fName;
		} while (fName.equals(""));	//No null entries allowed.
		
		do {//TODO: validate as email using one of the candidates in Class Email.
			print("Email? ");
			fEmail = kb.nextLine();
			fPerson.email = fEmail;
		} while (fEmail.equals(""));		//No null entries allowed.
		
		//	***********************
		//	*** INPUT EDUCATION ***
		//	***********************
		
		println("\nEnter one to ten educational achievements (\"DONE\" to exit this section)");
		done = false;
		numItems = 0;
		do {
			do {
				print("Educational Achievement #"+ (numItems+1) +" (or done): ");
				fEdAch = kb.nextLine();
			} while (fEdAch.equals(""));	//No null entries allowed.
			done = fEdAch.toUpperCase().equals("DONE");
			if (!done) {
				do {  //skip entering Educ. Achiev. Org. and Year if Educ. Achiev. is "DONE"
					print("Organization of Educational Achievement #"+ (numItems+1) +": ");
					fEdAchOrg = kb.nextLine();
				} while (fEdAchOrg.equals(""));	//No null entries allowed.
				do {  //TODO: use integer for year, validate as year.					
					print("Year of Educational Achievement #"+ (numItems+1) +": ");
					fEdAchYr = kb.nextLine();
				} while (fEdAchYr.equals(""));	//No null entries allowed.
				
				fPerson.edAch.add(fEdAch + ", \n" + fEdAchOrg + ", " + fEdAchYr);
			}
			numItems = fPerson.edAch.size();
		} while ( !(  (done && (numItems >= 1)) || (numItems > 9)  ) );
		
		//	*****************************
		//	*** INPUT WORK EXPERIENCE ***
		//	*****************************
		
		println("\nEnter up to ten work experiences (\"DONE\" to exit this section)");
		done = false;
		numItems = 0;
		do {
			do {
				print("Work Experience (Role) #"+ (numItems+1) +" (or done): ");
				fWrkXp = kb.nextLine();
			} while (fWrkXp.equals(""));	//No null entries allowed.
			done = fWrkXp.toUpperCase().equals("DONE");
			if (!done) {
				
				do {  //skip entering Work Experience Org. and Years if Work Exp. is "DONE"
					print("Organization of Work Experience #"+ (numItems+1) +": ");
					fWrkXpOrg = kb.nextLine();
				} while (fWrkXpOrg.equals(""));	//No null entries allowed.
				do {  //Work period					
					print("Period of Work Experience #"+ (numItems+1) +": ");
					fWrkXpYrs = kb.nextLine();
				} while (fWrkXpYrs.equals(""));	//No null entries allowed.
				
				//	*** INPUT DUTIES ***
				
				print("Enter up to twelve duties for Work Experience #"+ (numItems+1) +" (type \"DONE\" when done): ");
				doneDuties = false;
				numDuty = 0;
				duties.clear();
				duties4Print = "";
				do { // Input zero or more duties (up to 12)
					do {	
						print("Duty #"+ (numDuty+1) +" (or done): ");					
						fDuty = kb.nextLine();	//GET DUTY				
					} while (fDuty.equals(""));	//No null entries allowed.
					
					doneDuties = fDuty.toUpperCase().equals("DONE"); //Are we DONE?
					if (!doneDuties) { //No, not done yet, add the duty to the list.
						duties.add("- Duty " + (numDuty+1) + ", " + fDuty); 
					}		
					numDuty = duties.size();
				} while ( !(doneDuties || (numDuty > maxDuties-1)) );
						
				// Add each duty to a pretty-to-print string.
				for (String item : duties) {
					duties4Print += "\n" + item;
				}
								
				fPerson.wrkXp.add(fWrkXp + ", \n" + fWrkXpOrg + ", " + fWrkXpYrs + "\n" + duties4Print);				

				
				if (fPerson.wrkXp.size() == 0) {println(" ");}
			}
			numItems = fPerson.wrkXp.size();
		} while ( !(done || (numItems > 9)) );
		
		//	********************
		//	*** INPUT SKILLS ***
		//	********************
		
		println("\nEnter one to twenty skills (\"DONE\" to exit this section)");
		done = false;
		numItems = 0;
		do {
			do {
				print("Skill #"+ (numItems+1) +" (or done): ");
				fSkill = kb.nextLine();
			} while (fSkill.equals(""));	//No null entries allowed.
			done = fSkill.toUpperCase().equals("DONE");
			if (!done) {		
				do {  //skip entering Skill Level if Skill is "DONE"
					print("Level of Skill #"+ (numItems+1) +": ");
					fSkillLvl = kb.nextLine();
				} while (fSkillLvl.equals("") || fSkillLvl.toUpperCase().equals("DONE")); //No null entries nor blindly typing "done" allowed.
				fPerson.skills.add(fSkill + ", " + fSkillLvl);
			}
			numItems = fPerson.skills.size();
		} while ( !(  (done && (numItems >= 1)) || (numItems > 19)  ) );
		
		//	************************
		//	*** PRINT OUT RESUME ***
		//	************************
		
		println("\n=============================================================");
		println(fPerson.name);
		println(fPerson.email);
		
		println("\nEducation");
		for (String item : fPerson.edAch) {
			println(item);
			println(" ");
		}
		println("Experience");
		for (String item : fPerson.wrkXp) {
			println(item);
			println(" ");
		}
		if (fPerson.wrkXp.size() == 0) {println(" ");}
		println("Skills");
		for (String item : fPerson.skills) {
			println(item);
		}		
		kb.close();
		}

	public static void println(String msg) {
		System.out.println(msg);
	}
	
	public static void print(String msg) {
		System.out.println(msg);
	}
}
//	***********************
