package testers;
import git.Blob;
import java.util.*;
import java.io.*;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*; 


public class BlobTester {
	public static void main (String [] args) throws IOException {
		 Path p = Paths.get("test.txt");
	     try {
	    	 Files.writeString(p, "example", StandardCharsets.ISO_8859_1);
	     } catch (IOException e) {
	    	 // TODO Auto-generated catch block
	    	 e.printStackTrace();
	     }
	     Blob blobby = new Blob("test.txt");
	}
}
