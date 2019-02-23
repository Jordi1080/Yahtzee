package volgensopdracht;
import java.util.Random;

public class Dobbelsteen {
	int waarde = -1;
	
	public Dobbelsteen(){
	}
	
	// gooi de dobbelsteen
	public int werpen(){
		int worp;
		Random rng = new Random();
		
		worp = rng.nextInt(6) + 1;
		return worp;
	}
	
	/*
	public String naarString(){
		
	}
	*/
	
	/* misschien
	public int getWaarde(){
		return this.waarde;
	}
	*/
	
}
