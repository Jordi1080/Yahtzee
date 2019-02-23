package volgensopdracht;

import java.util.ArrayList;

public class Worp {
int array = 5;
ArrayList<Dobbelsteen> stenen = new ArrayList<Dobbelsteen>();
	
	
	public Worp(){
		
	}
	
	public String naarString(){
		//System.out.println("--------- WORP START HIER -------");
		String res = "";

		for (Dobbelsteen d : stenen){
			res = res + d.waarde;
		}
		//System.out.println("Deze worp: " + res);
		res += "\n";
		
		//System.out.println("stenen tostring" + stenen.toString());
		
		return res;
	}
	
	public String naarStringShort(){
		String res = "";
		
		for (Dobbelsteen d : stenen){
			res = res + d.waarde;
			//System.out.println("tot nu toe " + res); //test
		}
		res += "\n";
		
		return res;
	}
}
