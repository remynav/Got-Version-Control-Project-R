import java.util.*;
import java.io.*;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Index {
	private HashMap<String,String> files;
	
	public Index() {
		files = new HashMap<String, String>();		
	}
	
	public void init() throws IOException {
		Path p = Paths.get("index");
		try {
            Files.writeString(p, "", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		Files.createDirectories(Paths.get("objects"));
	}
	
	public void add(String fileName) throws IOException {
		Path p = Paths.get("index");
		Blob b = new Blob(fileName);
	    try {
	        Files.writeString(p, fileName + " : "+b.getSha1(), StandardCharsets.ISO_8859_1);
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}
	    
	public void remove(String fileName) {
		System.out.println();
	}
}
