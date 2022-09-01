import java.util.*;
import java.io.*;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;

public class Blob {
	private String sha1;
	
	public Blob(String fileName) throws IOException {
		Path filePath = Path.of(fileName);
		String fileText = Files.readString(filePath);
		
		this.sha1 = generateSha1(fileText);
	
		//writing the file content to the sha1 file
		writeFile(sha1);
		FileWriter output = new FileWriter(System.getProperty("user.dir")+File.separator+"objects"+File.separator+sha1);
		output.write(fileText);
		output.close();
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
	
	private static void writeFile(String fileName) {
		try {
		      File file = new File(fileName);
		      if (file.createNewFile()) {
		        System.out.println("File created: " + file.getName());
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		  }
	}
}
