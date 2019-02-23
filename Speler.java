package volgensopdracht;
import java.util.ArrayList;


public class Speler {
	
	String naam;
	ArrayList<Worp> worpGeschiedenis = new ArrayList<Worp>();
	int[] laatsteWorp;
	int worpTeller = 0;
	
	String tempWG = "";
	
	public Speler(String naam){
		this.naam = naam;
	}

	public String updateWorpGeschiedenis(){
		String res = "";
		
		res += worpGeschiedenis.get(0).naarString();
			
		// optioneel:	
		//System.out.println("Dit was de " + worpGeschiedenis.size() + "e worp van " + naam);
		tempWG += res;
		
		//System.out.println("tempWG:" + tempWG);
		
		return res;
	}
	
	public void toonWorpGeschiedenis(){
		System.out.println(tempWG); 
	}

}
