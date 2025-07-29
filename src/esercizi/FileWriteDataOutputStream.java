package esercizi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
	
	public void readWritedFile(String fileToRead) {
		
		
		double price = 0;
		int units = 0;
		
		DataInputStream in = null;
		try {
			in = new DataInputStream(new FileInputStream(fileToRead));
			
			StringBuilder sb = null;
			System.out.println("\n--- STAMPA DEI PRODOTTI ---");
			while (in.available() > 0) {
				
				price = in.readDouble();
				units = in.readInt();
				sb = new StringBuilder();

				
				
				char charD = in.readChar();
				while (charD != this.lineSeparator.charAt(0)) {
					sb.append(charD);
					charD = in.readChar();
				}
				
				if (this.lineSeparator.length() > 1) {
					in.readChar();
				}
				
				sb.append(' ');
//				System.out.println("Prodotto " + i + " scritto");
				System.out.println("Prodotto: " + sb.toString());
				System.out.println("Prezzo: " + price + " | " + "Quantità: " + units);
				System.out.println("------------------------------------");
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
