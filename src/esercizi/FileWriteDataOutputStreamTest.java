package esercizi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class FileWriteDataOutputStreamTest {
	
	static FileWriteDataOutputStream fwdos;
	final String fileToWriteName = "src/text_files/fatturaNonChar.dat";
	final String fileToReadName = "src/text_files/fatturaNonChar.dat";
	static record Article(String productName, double price, int units) {}

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		fwdos = new FileWriteDataOutputStream();
		fwdos.run();
	}

	@Test
	void testReadWritedFile() {
		
	}
	
	@Test
	void testWriteOnFile() {
		
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
