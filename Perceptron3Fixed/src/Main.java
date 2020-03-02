import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static ArrayList<Double> wektorWagNauczony = new ArrayList<Double>();
	public static void main(String[] args) {

		System.out.println("Podaj parametr uczenia:");
		Scanner skaner = new Scanner(System.in);
		double parametrUczenia = skaner.nextDouble();
		
		ListaTreningowa lista = new ListaTreningowa("training.txt");
		ListaTreningowa lista2 = new ListaTreningowa("test.txt");
		
		String pozytywna = lista.getPozytywnaWartosc();	
		String negatywna = lista.getNegatywna();
		System.out.println("Wektor wag pierwszy = " + lista.getWektorWag().toString());
		
		for (int x = 0; x <500; x++) {
			Uczenie(lista, parametrUczenia, wektorWagNauczony, pozytywna, negatywna);
		}
		System.out.println("wektor " +lista.getWektorWag().toString());
		
			UczenieTest(lista2, lista.getWektorWag(), wektorWagNauczony, pozytywna, negatywna);
		
		
		System.out.println("1 - Podaj Wektor\n2 - Zakoncz dzialanie");
		int choose = skaner.nextInt();
			switch (choose) {
			case 1:
				System.out.println("Podaj wektor");
				String str = skaner.next();
				String[] strSplit = str.split(",");
				List<Double> list = new ArrayList<>();
				for(String s : strSplit)
					list.add(Double.parseDouble(s));
				double net = 0;
				for(int i = 0; i<lista.getWektorWag().size()-1; i++) {
					net +=list.get(i) * lista.getWektorWag().get(i);
				}
				if(net > lista.getWektorWag().get(lista.getWektorWag().size()-1)) 
					System.out.println("Klasa: "+lista.getPozytywnaWartosc());
				else System.out.println("Klasa: "+lista.getNegatywna());
				break;
			case 2:
				System.out.println("Koncze dzialanie programu");
				System.exit(0);
				break;
			default:
				System.out.println("Nie ma takiej komendy\nPodaj raz jeszcze");
				break;
			}
			System.out.println("1 - Podaj Wektor\n2 - Zakoncz dzialanie");
			choose = skaner.nextInt();
		}

	public static void Uczenie(ListaTreningowa listaTrain, double parametrUczenia, ArrayList<Double> wektorWagNauczony, String pozytywna, String negatywna) {

		 int TP=0; 
		 int TN=0; 
		 int FP=0; 
		 int FN=0;
		double net = 0;
		for (int x = 0; x < listaTrain.getListaDanych().size(); x++) {
			ArrayList<Double> wektor = listaTrain.getWektorWag();
			String aktualnaWartosc;
			for (int y = 0; y < listaTrain.getWektorWag().size(); y++) {
				net += (wektor.get(y)) * (listaTrain.getListaDanych().get(x).getWektor().get(y));
			}
			if (net > 0) {
				aktualnaWartosc = pozytywna;
				if (aktualnaWartosc.equals(listaTrain.listaDanych.get(x).getOczekiwanaWartosc())) {
					TP++;
				} else {
					ArrayList<Double> list = new ArrayList<>();
					FP++;
					for (int r = 0; r < listaTrain.getWektorWag().size(); r++) {
						
						list.add(listaTrain.getWektorWag().get(r)
								+ ((0 - 1) * parametrUczenia * listaTrain.listaDanych.get(x).getWektor().get(r)));
					}					
					listaTrain.setWektorWag(list);
					wektorWagNauczony.clear();
					for(int d = 0;d<list.size();d++) {
					wektorWagNauczony.add(list.get(d));
					}
				}
			} else  {				
				aktualnaWartosc = negatywna;
				if (aktualnaWartosc.equals(listaTrain.listaDanych.get(x).getOczekiwanaWartosc())) {
					TN++;
				} else {
					FN++;
					ArrayList<Double> list2 = new ArrayList<>();
					for (int r = 0; r < listaTrain.getWektorWag().size(); r++) {
						list2.add(listaTrain.getWektorWag().get(r)
								+ ((1 - 0) * parametrUczenia * listaTrain.listaDanych.get(x).getWektor().get(r)));
					}					
					listaTrain.setWektorWag(list2);
					wektorWagNauczony.clear();
					for(int d = 0;d<list2.size();d++) {
					wektorWagNauczony.add(list2.get(d));
					}
				}
			}
			net = 0;
			
			
		}

		System.out.println("Dokładność = " + (double)(TP/(double)(TP+FP))+ ", Pełność = "+ (double)(TP /(double)(TP + FN)));// + ", wektor wag: "+listaTrain.getWektorWag());
	}
	
	public static void UczenieTest(ListaTreningowa listaTrain,ArrayList<Double> wektor, ArrayList<Double> wektorWagNauczony, String pozytywna, String negatywna) {
		System.out.println("Wektor wag w uczeniu test = " + wektorWagNauczony);
		 int TP=0; 
		 int TN=0; 
		 int FP=0; 
		 int FN=0;
		double net = 0;
		for (int x = 0; x < listaTrain.getListaDanych().size(); x++) {
			String aktualnaWartosc;
			for (int y = 0; y < listaTrain.getWektorWag().size(); y++) {
				net += (wektorWagNauczony.get(y)) * (listaTrain.getListaDanych().get(x).getWektor().get(y));
			}
			if (net > 0) {
				aktualnaWartosc = pozytywna;
				if (aktualnaWartosc.equals(listaTrain.listaDanych.get(x).getOczekiwanaWartosc())) {
					TP++;
				} else {
					FP++;					
				}
			} else  {				
				aktualnaWartosc = negatywna;
				if (aktualnaWartosc.equals(listaTrain.listaDanych.get(x).getOczekiwanaWartosc())) {
					TN++;
				} else {
					FN++;
				}
			}
			net = 0;
			
		}
		System.out.println("TP = "+TP+" "+"FP = "+FP+" "+"TN = "+TN+" "+"FN = "+FN);
		double RealAccuracy = (double)((TP + TN)/ (double)(TP + TN + FP + FN));
		double reCall = (double)(TP/(double)TP+FN);
		double precision = (double)(TP/(double)(TP+FP));
		double fMiara = (double)(2*RealAccuracy*precision/(double)(RealAccuracy+precision));
		System.out.println("RealAccuracy = " + RealAccuracy);
		System.out.println("Precision = " + (double)(TP/(double)(TP+FP)));
		System.out.println("ReCall = "+ (double)(TP /(double)(TP + FN)));
		System.out.println("F-miara = "+ fMiara);
		
	}


}
