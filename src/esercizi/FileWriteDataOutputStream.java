package esercizi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileWriteDataOutputStream {

	String fileName = "src/text_files/fatturaNonChar.dat";
	double[] prices = {19.99, 9.99, 15.99, 3.99, 4.99};
	int[] units = {12, 8, 13, 29, 50};
	String[] descs = {"T-shirt", "Mug", "Duke", "Pin", "Key-Chain"};
	String lineSeparator = System.getProperty("line.separator");
	
	
	//TODO
	/* - Suddividere in metodi più specializzati
	 * - Analizzare il corretto funzionamento del programma
	 * - Scrivere test JUnit
	 * */
	
	public static void main(String[] args) {
		new FileWriteDataOutputStream().run();
		
	}
	
	public void run() {
		writeOnFile(this.fileName);
		readWritedFile(this.fileName);
	}
	
	// Scrivo per ogni elemento nel file
	public void writeOnFile(String fileName) {
		
		File fileToWrite = new File(fileName);
		try {
			DataOutputStream out = new DataOutputStream(new FileOutputStream(fileToWrite));
			
			for (int i = 0; i < descs.length; i++) {
				out.writeDouble(this.prices[i]);
				out.writeInt(this.units[i]);
				out.writeChars(this.descs[i]);
				out.writeChars(this.lineSeparator);
				System.out.println("Prodotto " + i + " scritto");
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("---> SCRITTURA COMPLETATA <---");
	}
	
	// Questo metodo legge il file ".dat" con il nome fornito
	public void readWritedFile(String fileToRead) {
		
		double price = 0;
		int units = 0;
		
		DataInputStream in = null;
		try {
			in = new DataInputStream(new FileInputStream(fileToRead));
//			
//			StringBuilder sb = null;
//			System.out.println("\n--- STAMPA DEI PRODOTTI ---");
			while (in.available() > 0) {
//				
//				price = in.readDouble();
//				units = in.readInt();
//				sb = new StringBuilder();
//
//				
//				
//				char charD = in.readChar();
//				while (charD != this.lineSeparator.charAt(0)) {
//					sb.append(charD);
//					charD = in.readChar();
//				}
//				
//				if (this.lineSeparator.length() > 1) {
//					in.readChar();
//				}
//				
//				sb.append(' ');
////				System.out.println("Prodotto " + i + " scritto");
//				System.out.println("Prodotto: " + sb.toString());
//				System.out.println("Prezzo: " + price + " | " + "Quantità: " + units);
//				System.out.println("------------------------------------");
				
				System.out.println(mapProductToString(readFileLine(in)));
				System.out.println("------------------------------------");
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/* Questo metodo legge una riga da un DataInputStream fornito come parametro,
	 e restituisce una mappa con tutti i dati della riga */
	private Map<String, Object> readFileLine(DataInputStream dis) throws IOException {
		
		Map<String, Object> result = new HashMap<>();
		
		double price = 0;
		int units = 0;
		StringBuilder sb = new StringBuilder();

		// Leggo prezzo e unità
		price = dis.readDouble();
		units = dis.readInt();

		
		// Leggo la descrizione del prodotto (nome)
		char charD = dis.readChar();
		while (charD != this.lineSeparator.charAt(0)) {
			sb.append(charD);
			charD = dis.readChar();
		}
		
		if (this.lineSeparator.length() > 1) {
			dis.readChar();
		}
		
		sb.append(' ');
		
		// Salvo i dati nella Map
		result.put("price", price);
		result.put("units", units);
		result.put("productName", sb.toString());
		
		return result;
	}
	
	//TODO
	/* Invece di avere 3 elementi dentro la mappa di un solo articolo, fa si che ogni
	 * elemento all'interno della mappa sia un articolo diverso, anche se in realtà la 
	 * mappa verrà sempre ricreata e fornita con un solo prodotto dato che si trova nel
	 * ciclo while */
	
	// Questo metodo stampa il prodotto presente nella mappa (deve essere una mappa con 1 solo prodotto)
	private String mapProductToString(Map<String, Object> article) {
		return "Prodotto: " + article.get("productName") + "\n"
				+ "Prezzo: " + article.get("price") + "\n"
				+ "Quantità: " + article.get("units");
	}
}
