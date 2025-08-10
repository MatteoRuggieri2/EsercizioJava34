package esercizi;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class FileWriteDataOutputStreamTest {
	
	static FileWriteDataOutputStream fwdos;
	final String invoiceFile = "src/text_files/fatturaNonChar.dat";
	static record Article(String productName, double price, int units) {}
	List<esercizi.FileWriteDataOutputStream.Article> articleList = List.of(
			new FileWriteDataOutputStream.Article("T-shirt", 19.99, 12),
			new FileWriteDataOutputStream.Article("Mug", 9.99, 8),
			new FileWriteDataOutputStream.Article("Duke", 15.99, 13),
			new FileWriteDataOutputStream.Article("Pin", 3.99, 29),
			new FileWriteDataOutputStream.Article("Key-Chain", 4.99, 50)
	);

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		fwdos = new FileWriteDataOutputStream();
		fwdos.run();
	}

	@Test
	void testReadWritedFile() {
		assertArrayEquals(articleList.toArray(), fwdos.readWritedFile(invoiceFile).toArray());
	}
	
	@Test
	void testWriteOnFile() throws IOException {
		
		// Svuoto file
		Files.write(Paths.get("src/text_files/fatturaNonChar.dat"), new byte[0]);
		
		// Scrivo il file
		fwdos.writeOnFile(invoiceFile);
		
		// Leggo il file e confronto
		assertArrayEquals(articleList.toArray(), fwdos.readWritedFile(invoiceFile).toArray());
	}
	
	@Test
	void testArticleToString() {
		
		String expArtToString1 = "Prodotto: T-shirt\n"
				+ "Prezzo: 19.99\n"
				+ "Quantità: 12";
		
		String expArtToString2 = "Prodotto: Mug\n"
				+ "Prezzo: 9.99\n"
				+ "Quantità: 8";
		
		String expArtToString3 = "Prodotto: Duke\n"
				+ "Prezzo: 15.99\n"
				+ "Quantità: 13";

		FileWriteDataOutputStream.Article art1 =
		        new FileWriteDataOutputStream.Article("T-shirt", 19.99, 12);
		
		FileWriteDataOutputStream.Article art2 =
		        new FileWriteDataOutputStream.Article("Mug", 9.99, 8);
		
		FileWriteDataOutputStream.Article art3 =
		        new FileWriteDataOutputStream.Article("Duke", 15.99, 13);

		assertEquals(expArtToString1, fwdos.articleToString(art1));
		assertEquals(expArtToString2, fwdos.articleToString(art2));
		assertEquals(expArtToString3, fwdos.articleToString(art3));
		
	}
	

}
