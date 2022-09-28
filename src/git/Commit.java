package git;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;

public class Commit {
	private Commit previousCommit;
	private Commit nextCommit;
	private String summary;
	private String author;
	private String date;
	private String pTree;
	private String commitSha1;
	
	public Commit (String pTree, String summary, String author, Commit previousCommit) {//old constructor
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
	
	public Commit (String summary, String author, Commit previousCommit) {//new constructor 
		
		File sha1File = new File("Tree");
        sha1File.createNewFile();
        
//        FileWriter myWriter = new FileWriter(sha1File.getName());
//        myWriter.write();
//        myWriter.close();
		
		this.summary=summary;
		this.author=author;
		this.previousCommit=previousCommit;
		nextCommit=null;
		commitSha1=generateSha1ForCommit();
		Date d = new Date();
		date = d.toString();
		System.out.println(date);
	}
	
	public void writeFiles(String name, String contents) throws IOException {
		File sha1File = new File(name);
        sha1File.createNewFile();
        
        FileWriter myWriter = new FileWriter(sha1File.getName());
        myWriter.write(contents);
        myWriter.close();
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
		/*
		Path p = Paths.get("objects"+File.separator+commitSha1);
        try {
            Files.writeString(p, fileContent , StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        */
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
