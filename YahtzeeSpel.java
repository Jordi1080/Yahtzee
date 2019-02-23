package volgensopdracht;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;


// een passief-agressief spel
public class YahtzeeSpel {
	Scanner sc = new Scanner(System.in);
	
	ArrayList<Dobbelsteen> dobbelstenen = new ArrayList<Dobbelsteen>();
	ArrayList<Speler> spelers = new ArrayList<Speler>();
	boolean[] blokkeerArray = {false, false, false, false, false};
	boolean gameIsActive = true;
	Worp laatsteWorp = new Worp();
	int herkansingen = 2;
	
	
	public static void main(String[] args) {
		
		YahtzeeSpel ys = new YahtzeeSpel();
		
		// ENTER PASSWORD
		System.out.println("--------------------TOP SECRET-------------------------");
		System.out.println("VOER WACHTWOORD IN");
		ys.sc.nextLine();
		System.out.println("CONGRATULATIONS!!!");
		System.out.println("JE HEBT HET GOEDE WACHTWOORD GETYPT\n");
		
		// START GAME
		System.out.println("Nou, fantastisch, je gaat Yahtzee spelen.");
		System.out.println("Fijn dat je zulke interessante dingen doet met je vrije tijd.\n");
		
		
		Speler bob = new Speler("Bob");
		Speler cookie = new Speler("Cookie");
		ys.spelers.add(bob);
		ys.spelers.add(cookie);
		
		ys.spelen();
		
	}
	
	public YahtzeeSpel(){
		// maak alle dobbelstenen, en voeg ze toe aan de ArrayList
		Dobbelsteen d1 = new Dobbelsteen();
		Dobbelsteen d2 = new Dobbelsteen();
		Dobbelsteen d3 = new Dobbelsteen();
		Dobbelsteen d4 = new Dobbelsteen();
		Dobbelsteen d5 = new Dobbelsteen();
		
		dobbelstenen.add(d1);
		dobbelstenen.add(d2);
		dobbelstenen.add(d3);
		dobbelstenen.add(d4);
		dobbelstenen.add(d5);
	
	}
	
	public void spelen(){
		
		// GAME LOOP
		
		while(gameIsActive){
			for(Speler s : spelers){
				spelerBeurt(s);
			}
		}
	}
	
	public void spelerBeurt(Speler s){
		herkansingen = 2;
		System.out.println(s.naam + ", je bent aan de beurt.");
		System.out.println("geef je input: (g) om te gooien, (t) om je worpgeschiedenis te bekijken, (q) for quitters");
		String spelerInput = sc.nextLine();
		switch (spelerInput){
		case "g": 
			resetBlokkeerArray();
			System.out.println("Je hebt gekozen voor een worp doen.");
			Worp dezeWorp = new Worp();
			
			dezeWorp = werpAlleDobbelstenen();
			updateWorp(dezeWorp);
			
			while (herkansingen > 0){
				vraagOpnieuwGooien(s);
			}
			
			voegWorpToeAanGeschiedenis(s);
			s.updateWorpGeschiedenis();
			
			// optionele informatie
			// System.out.println(s.naam + "'s worpgeschiedenis:\n" + s.tempWG);
			// System.out.println("laatste worp tot nu toe: " + laatsteWorp.naarStringShort());
			break;
		case "t":
			// toon worpgeschiedenis
			System.out.println(s.naam + "'s worpgeschiedenis:\n" + s.tempWG);
			spelerBeurt(s);
			break;
		case "q":
			// stop spel
			System.out.println("Wil je stoppen?");
			System.out.println("Ach ja, met jouw scores had ik hetzelfde gedaan.");
			System.exit(0);
			break;
		default:
			System.out.println("Kijk nog eens goed naar wat de opties waren...");
			// break of geen break?
		}
	}
	
	public Worp werpAlleDobbelstenen(){
		Worp deWorp = new Worp();
		System.out.print("Worp: ");
		
		for (int i = 0; i < dobbelstenen.size(); i++){
			dobbelstenen.get(i).waarde = dobbelstenen.get(i).werpen();
			System.out.print(dobbelstenen.get(i).waarde);
		}
		
		System.out.println("\n");
		
		deWorp.stenen = dobbelstenen; 
		
		return deWorp;
	}
	
	public Worp werpGekozenDobbelstenen(){
		//System.out.println(Arrays.toString(nieuweWorp)); //test
		Worp deWorp = new Worp();
		System.out.print("Worp: ");
		
		for (int i = 0; i < dobbelstenen.size(); i++){
			if (blokkeerArray[i] == false){
				dobbelstenen.get(i).waarde = dobbelstenen.get(i).werpen();
			}
			System.out.print(dobbelstenen.get(i).waarde);
		}
		
		System.out.println("\n");
		
		deWorp.stenen = dobbelstenen; // geef de huidge dobbel waardes mee aan de nieuwe worp
		
		return deWorp;
	}
	
	public void updateWorp(Worp wo){
		laatsteWorp.stenen = wo.stenen;
	}
	

	public void vasthouden(){
		
		System.out.println("Op welke posities wil je dobbelstenen vasthouden? (typ 0 voor geen)");

		String input = sc.nextLine();
		String vastgehoudenStenen = "";
		if (input.equals("0")){
				// alle stenen opnieuw gooien
				System.out.println("Geen enkele dobbelsteen is goed genoeg voor je?");
		} else{
				// alleen de gekozen stenen opnieuw gooien
				for (int i = 0; i < input.length(); i++){
					//input komt binnen als String, maar moet eerst een integer worden
					int convertedInput = Integer.parseInt(input.substring(i, i+1)) - 1; 
					// geef door aan de blokkeerArray welke stenen opnieuw moeten
					blokkeerArray[convertedInput] = true;
					vastgehoudenStenen += dobbelstenen.get(convertedInput).waarde;
				}
				System.out.println("vastgehouden dobbelstenen: " + vastgehoudenStenen);
		}
	}
	
	// de blokkeerarray moet voor elke nieuwe worp weer helemaal op false
	public void resetBlokkeerArray(){
		for (int i = 0; i < blokkeerArray.length; i++){
			blokkeerArray[i] = false;
		}
	}
	
	public void vraagOpnieuwGooien(Speler s){
		
		System.out.println("Ben je tevreden met deze worp? [Y/N]");
		String wilOpnieuwGooien = sc.nextLine();
		
		switch (wilOpnieuwGooien){
			case "n":
				herkansingen--;
				vasthouden();
				werpGekozenDobbelstenen();
				updateWorp(laatsteWorp);
			break;
		case "y":
			herkansingen = 0;
			System.out.println("Met deze worp zal je het moeten doen.\n");
			updateWorp(laatsteWorp);
			break;
		default:
			System.out.println("Kies (y) of (n), het lijkt moeilijk, maar is best simpel!");
			break;
		}
	}
	
	public void voegWorpToeAanGeschiedenis(Speler sp){
		sp.worpGeschiedenis.add(laatsteWorp);
		//System.out.println("dit wordt toegevoegd aan geschiedenis: " + laatsteWorp.stenen);
	}

}
