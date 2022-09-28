import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TreeTester {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	private static String readFile(String fileName) throws IOException {
		Path filePath = Path.of(fileName);
		return Files.readString(filePath);
	}
	
	@Test
	void test() throws NoSuchAlgorithmException, IOException {
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f");
		arr.add("blob : 01d82591292494afd1602d175e165f94992f6f5f");
		arr.add("blob : f1d82236ab908c86ed095023b1d2e6ddf78a6d83");
		arr.add("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
		arr.add("tree : e7d79898d3342fd15daf6ec36f4cb095b52fd976");
		Tree forntite = new Tree (arr);
		File rick = new File("objects"+File.separator+forntite.getSha1());
		assertTrue(rick.exists());
		assertTrue(readFile(forntite.getSha1()).equals("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f\nblob : 01d82591292494afd1602d175e165f94992f6f5f\nblob : f1d82236ab908c86ed095023b1d2e6ddf78a6d83\ntree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b\ntree : e7d79898d3342fd15daf6ec36f4cb095b52fd976"));
		
	}

}
