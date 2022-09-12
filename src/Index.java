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
	}
	
	public void init() throws IOException {
		files = new HashMap<String, String>();	
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
		Blob b = new Blob(fileName);
		files.put(fileName,b.getSha1());
		FileWriter fw = new FileWriter("index");
		for (String key: files.keySet()) {
			fw.write(key+" : "+files.get(key)+"\n");
		}
		fw.close();
	}
	    
	public void remove(String fileName) {
		
	}
}
