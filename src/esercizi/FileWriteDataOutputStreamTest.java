package esercizi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class FileWriteDataOutputStreamTest {
	
	static FileWriteDataOutputStream fwdos;
	String fileToWriteName = "src/text_files/fatturaNonChar.dat";
	String fileToReadName = "src/text_files/fatturaNonChar.dat";

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		fwdos = new FileWriteDataOutputStream();
	}

	@Test
	void testWriteOnFile() {
		fwdos.writeOnFile(this.fileToWriteName);
	}
	
	@Test
	void testReadWritedFiles() {
		fwdos.readWritedFile(this.fileToReadName);
	}

}
