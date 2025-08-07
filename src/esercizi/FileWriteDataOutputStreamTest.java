package esercizi;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class FileWriteDataOutputStreamTest {
	
	static FileWriteDataOutputStream fwdos;
	String fileToWriteName = "src/text_files/fatturaNonChar.dat";
	String fileToReadName = "src/text_files/fatturaNonChar.dat";

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
		
	}
	

}
