import java.util.ArrayList;

public class Dana {
	
	ArrayList<Double> wektor = new ArrayList<Double>();
	String oczekiwanaWartosc;
	
	public Dana(ArrayList<Double> wektor, String oczekiwanaWartosc) {
		this.wektor = wektor;
		this.oczekiwanaWartosc=oczekiwanaWartosc;
	}

	public ArrayList<Double> getWektor() {
		return wektor;
	}

	public void setWektor(ArrayList<Double> wektor) {
		this.wektor = wektor;
	}

	public String getOczekiwanaWartosc() {
		return oczekiwanaWartosc;
	}

	public void setOczekiwanaWartosc(String oczekiwanaWartosc) {
		this.oczekiwanaWartosc = oczekiwanaWartosc;
	}
	

}
