package esercizi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileWriteDataOutputStream {

	final String fileName = "src/text_files/fatturaNonChar.dat";
	final double[] prices = {19.99, 9.99, 15.99, 3.99, 4.99};
	final int[] units = {12, 8, 13, 29, 50};
	final String[] descs = {"T-shirt", "Mug", "Duke", "Pin", "Key-Chain"};
	final String lineSeparator = System.getProperty("line.separator");
	
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
		
		System.out.println("\n---> SCRITTURA COMPLETATA <---\n");
	}
	
	// Questo metodo legge il file ".dat" con il nome fornito
	public void readWritedFile(String fileToRead) {
		
		DataInputStream in = null;
		try {
			in = new DataInputStream(new FileInputStream(fileToRead));

			while (in.available() > 0) {
				// Prendo il primo ed unico elemento della lista
				System.out.println(mapProductToString(readFileLine(in).get(0)));
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
	private ArrayList<Map<String, Object>> readFileLine(DataInputStream dis) throws IOException {
		
		ArrayList<Map<String, Object>> result = new ArrayList<>();
		Map<String, Object> mapElement = new HashMap<>();
		
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
		mapElement.put("price", price);
		mapElement.put("units", units);
		mapElement.put("productName", sb.toString());
		result.add(mapElement);
		
		return result;
	}
	
	// Questo metodo stampa il prodotto presente nella mappa (deve essere una mappa con 1 solo prodotto)
	private String mapProductToString(Map<String, Object> article) {
		
		/* Questo metodo in questo caso può andare bene perchè non
		abbiamo tante concatenazioni, però è meglio utilizzare la
		classe StringBuilder che è ottimizzata per questo tipo di operazioni. */
//		return "Prodotto: " + article.get("productName") + "\n"
//				+ "Prezzo: " + article.get("price") + "\n"
//				+ "Quantità: " + article.get("units");
		
		// Metodo ottimizzato, ma in queso caso (di output molto semplice) non è necessario
		StringBuilder sb = new StringBuilder();
		sb.append("Prodotto: ").append(article.get("productName")).append("\n")
		  .append("Prezzo: ").append(article.get("price")).append("\n")
		  .append("Quantità: ").append(article.get("units"));
		
		return sb.toString();
	}
}
