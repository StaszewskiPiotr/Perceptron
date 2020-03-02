import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ListaTreningowa {

	ArrayList<Double> wektorWag = new ArrayList<Double>();
	ArrayList<Dana> listaDanych = new ArrayList<>();

	public ListaTreningowa(String path) {

		FileReader fileReader;
		
		try {
			fileReader = new FileReader(path);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String[] dane = null;
			String textLine = bufferedReader.readLine();
			ArrayList<Double> daneWektora;
			do {
				daneWektora = new ArrayList<>();
				String klasa;
				dane = textLine.split(",");
				for (int x = 0; x < dane.length - 1; x++) {					
					daneWektora.add(Double.valueOf(dane[x]));
				}

				daneWektora.add((double)(-1));
				klasa = dane[dane.length - 1];
				listaDanych.add(new Dana(daneWektora, klasa));
				textLine = bufferedReader.readLine();
			} while (textLine != null);
			bufferedReader.close();
			for(int y = 0; y<daneWektora.size()-1;y++) {
				wektorWag.add((double)Math.random());
			}
				wektorWag.add((double)2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getPozytywnaWartosc() {
		
		//System.out.println("Metoda Pozytywna Wartość = "+ listaDanych.get(0).getOczekiwanaWartosc().toString());
		return listaDanych.get(0).getOczekiwanaWartosc();
		
	}
	
	public String getNegatywna() {
		String pozytywna = getPozytywnaWartosc();
		String negatywna = "";
		
		for(int z = 0; z<listaDanych.size();z++) {
			if(!(pozytywna.equals(listaDanych.get(z).oczekiwanaWartosc))){
				negatywna = listaDanych.get(z).oczekiwanaWartosc;
				//return listaDanych.get(z).oczekiwanaWartosc;
			}
		}
		//System.out.println("Metoda Negatywna Wartość = " + negatywna);
		return negatywna;
		
	}

	public ArrayList<Double> getWektorWag() {
		return wektorWag;
	}

	public void setWektorWag(ArrayList<Double> wektorWag) {
		this.wektorWag = wektorWag;
	}

	public ArrayList<Dana> getListaDanych() {
		return listaDanych;
	}

	public void setListaDanych(ArrayList<Dana> listaDanych) {
		this.listaDanych = listaDanych;
	}
	
}
