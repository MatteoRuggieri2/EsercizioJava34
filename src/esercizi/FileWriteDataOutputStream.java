package esercizi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileWriteDataOutputStream {

	final String fileName = "src/text_files/fatturaNonChar.dat";
	final double[] prices = {19.99, 9.99, 15.99, 3.99, 4.99};
	final int[] units = {12, 8, 13, 29, 50};
	final String[] descs = {"T-shirt", "Mug", "Duke", "Pin", "Key-Chain"};
	final String lineSeparator = System.getProperty("line.separator");
	
	/* Un record è una forma compatta per creare classi immutabili:
	Questo sostituisce tutta la definizione della classe Product e
	fornisce automaticamente costruttore, getter, equals, hashCode, toString. */
    private record Article(String productName, double price, int units) {}
	
    
    
//	public static void main(String[] args) {
//		new FileWriteDataOutputStream().run();
//		
//	}
	
	public void run() {
		writeOnFile(this.fileName);
		List<Article> articleList = readWritedFile(this.fileName);
		printArticleList(articleList);
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
			out.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("\n---> SCRITTURA COMPLETATA <---\n");
	}
	
	// Questo metodo legge il file ".dat" con il nome fornito
	public List<Article> readWritedFile(String fileToRead) {
		
		List<Article> articleList = new ArrayList<>();
		DataInputStream in = null;
		try {
			in = new DataInputStream(new FileInputStream(fileToRead));

			while (in.available() > 0) {
				Article currentArticle = readArticleFromFileLine(in);
				articleList.add(currentArticle);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return articleList;
	}
	
	/* Questo metodo legge una riga da un DataInputStream fornito come parametro,
	 e restituisce una mappa con tutti i dati della riga */
	private Article readArticleFromFileLine(DataInputStream dis) throws IOException {
		
		StringBuilder sb = new StringBuilder();

		// Leggo prezzo e unità
		double price = dis.readDouble();
		int units = dis.readInt();

		
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
		
		return new Article(sb.toString(), price, units);
	}
	
	// Questo metodo stampa il prodotto presente nella mappa (deve essere una mappa con 1 solo prodotto)
	private String articleToString(Article article) {
		
		/* Questo metodo in questo caso può andare bene perchè non
		abbiamo tante concatenazioni, però è meglio utilizzare la
		classe StringBuilder che è ottimizzata per questo tipo di operazioni. */
//			return "Prodotto: " + article.get("productName") + "\n"
//					+ "Prezzo: " + article.get("price") + "\n"
//					+ "Quantità: " + article.get("units");
		
		// Metodo ottimizzato, ma in queso caso (di output molto semplice) non è necessario
		StringBuilder sb = new StringBuilder();
		sb.append("Prodotto: ").append(article.productName).append("\n")
		  .append("Prezzo: ").append(article.price).append("\n")
		  .append("Quantità: ").append(article.units);
		
		return sb.toString();
	}
	
	// Questo metodo stampa la lista di articoli
	private void printArticleList(List<Article> articleList) {
		articleList.stream().forEach((article) -> {
			System.out.println(articleToString(article) + "\n------------------------------------");
		});
	}
	
}
