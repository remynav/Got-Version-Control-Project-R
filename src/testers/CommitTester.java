package testers;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import git.*;

public class CommitTester {
	public static void main(String[] args) throws IOException {
		
		File sha1File = new File("file1.txt");
        sha1File.createNewFile();
        
        FileWriter myWriter = new FileWriter(sha1File.getName());
        myWriter.write("contents of file1");
        myWriter.close();
        
        
		Blob file1Blob= new Blob("file1.txt");
		Index indTest= new Index();
		indTest.init();
		indTest.add("file1.txt");
		
		
		
		Commit test = new Commit("bkjgvki", "ahgahag", "Kaos", null);//String pTree, String summary, String author, Commit previousCommit
		
	}

}
