import java.util.*;
import java.io.*;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;

public class Blob {
	
	public Blob(String fileName) {
		Path filePath = Path.of(fileName);
		try {
			String fileText = Files.readString(filePath);
			
			String sha1 = generateSha1(fileText);
		} catch (IOException e) {}
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
	
	public static void main (String [] args) {
		System.out.println(generateSha1("some content"));
	}
}
