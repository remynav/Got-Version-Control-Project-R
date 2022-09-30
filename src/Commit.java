import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;

public class Commit {
	private Commit previousCommit;
	private Commit nextCommit;
	private String summary;
	private String author;
	private String date;
	private String pTree;
	private String commitSha1;
	private ArrayList<String> fileNames;
	
	public Commit (String pTree, String summary, String author, Commit previousCommit) {
		this.pTree=pTree;
		this.summary=summary;
		this.author=author;
		this.previousCommit=previousCommit;
		nextCommit=null;
		commitSha1=generateSha1ForCommit();
		Date d = new Date();
		date = d.toString();
		System.out.println(date);
	}
	
	public Commit (String summary, String author, Commit previousCommit) throws IOException, NoSuchAlgorithmException {// w/o ptree
		//when testing at first dont add parent commit
		//when getting a parent commit get the sha1 of it
		//convert index into an array list and write it into the tree
		//String currentFileName;
		
		String currFileName;
		fileNames= new ArrayList<String>();
		ArrayList<String> indexIntoArray= new ArrayList<String>();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("/index"));
			String line = reader.readLine();
			String separator =":";
			int sepPos = line.indexOf(separator);
			currFileName= line.substring(0, sepPos-1);
			fileNames.add(currFileName);
			indexIntoArray.add("blob : "+line.substring(sepPos) );
			while (line != null) {
				System.out.println(line);
				// read next line
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Tree t= new Tree(indexIntoArray, fileNames);
		
		
//		File sha1File = new File("");
//      sha1File.createNewFile();
//        
//      FileWriter myWriter = new FileWriter(sha1File.getName());
//      myWriter.write("");
//      myWriter.close();
		
		
		this.summary=summary;
		this.author=author;
		this.previousCommit=previousCommit;
		nextCommit=null;
		commitSha1=generateSha1ForCommit();
		Date d = new Date();
		date = d.toString();
		System.out.println(date);
	}
	
	
	public String generateSha1ForCommit() {
		String commitString = summary+"\n"+date+"\n"+author+"\n"+previousCommit;
		return generateSha1(commitString);
	}
	
	
	
	public void writeToFile() {
		String fileContent = pTree+"\n";
		if (previousCommit!=null) {
			fileContent+="objects"+File.separator+previousCommit.getCommitSha1();
		}
		fileContent+="\n";
		if (nextCommit!=null) {
			fileContent+="objects"+File.separator+nextCommit.getCommitSha1();
		}
		fileContent+="\n";
		fileContent+=author+"\n";
		fileContent+=date+"\n";
		fileContent+=summary;
		
		Path p = Paths.get("objects"+File.separator+commitSha1);
        try {
            Files.writeString(p, fileContent , StandardCharsets.ISO_8859_1);
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
	
	public String getCommitSha1() {
		return commitSha1;
	}
	
	public void setNext(Commit next) {
		nextCommit = next;
	}
	
	public void setPrevious(Commit previous) {
		previousCommit = previous;
	}
}
