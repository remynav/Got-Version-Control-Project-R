
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.*;

class JUnitTest {
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		File test = new File("Test.txt");
		test.createNewFile();
		FileWriter testFWr = new FileWriter("Test.txt");
		testFWr.write("This is a test.");
		testFWr.close();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		Files.deleteIfExists(Paths.get("Test.txt"));
	}

	@Test
	void testInit() throws IOException {
		Index ind = new Index();
		ind.init();
		assertTrue(Files.exists(Paths.get("index")) && Files.exists(Paths.get("objects")));
	}
	
	@Test
	void testAddFile() throws IOException {
		System.out.println("testing add");
		Index ind = new Index();
		ind.add("Test.txt");
		Scanner sc = new Scanner("index");
		String scanned = "";
		for(scanned = ""; !scanned.equals("Test.txt : 8f0220cbd7ba37cd11f67dbd2bca2899e4368e80") && sc.hasNextLine(); scanned = sc.nextLine());
		sc.close();
		assertTrue(scanned.equals("Test.txt : 8f0220cbd7ba37cd11f67dbd2bca2899e4368e80"));
		
		
	}
	
	@Test
	void testRemoveFile() throws IOException {
		Index ind = new Index();
		ind.remove("Test.txt");
		assertFalse(Files.exists(Paths.get("8f0220cbd7ba37cd11f67dbd2bca2899e4368e80")));
		String scanned = "";
		Scanner sc = new Scanner("index");
		for(scanned = ""; !scanned.equals("Test.txt : 8f0220cbd7ba37cd11f67dbd2bca2899e4368e80") && sc.hasNextLine(); scanned = sc.nextLine());
		sc.close();
		assertFalse(scanned.equals("Test.txt : 8f0220cbd7ba37cd11f67dbd2bca2899e4368e80"));
	}
	
	@Test
	void testBlob() throws IOException{
		String scanned;
		Blob testBlob = new Blob("Test.txt");
		assertTrue(Files.exists(Paths.get("/objects/8f0220cbd7ba37cd11f67dbd2bca2899e4368e80")));
		Scanner scWr = new Scanner("8f0220cbd7ba37cd11f67dbd2bca2899e4368e80");
		for(scanned = scWr.nextLine(); scWr.hasNextLine(); scanned = scanned+"\n"+scWr.nextLine());
		scWr.close();
		System.out.println("Written in file: "+ scanned);
		//
		assertTrue(scanned.equals("This is a test."));
	}

}
