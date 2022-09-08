import java.util.*;
import java.io.*;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Blob {
	private String sha1;
	
	public Blob(String fileName) throws IOException {
		Path filePath = Paths.get(fileName);
		System.out.println(filePath);
		String fileContent = Files.readString(filePath);
		
		this.sha1 = generateSha1(fileContent);
	
		//writing the file content to the sha1 file
		
		Path p = Paths.get("objects"+File.separator+sha1);
        try {
            Files.writeString(p, fileContent, StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	private static String generateSha1(String text) {
		String sha1 = "";
		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
	        digest.reset();
	        digest.update(text.getBytes("utf8"));
	        sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return sha1;
	}
	
	public String getSha1() {
		return sha1;
	}
	
	
}

