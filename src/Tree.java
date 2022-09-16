import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Tree {
	String dir = "/iCloud Drive/⁨Desktop⁩/⁨All⁩/Honors Topics⁩/⁨Got-Version-Control-Project⁩/objects/";
	String treeSha1;
	String treeStr;
	ArrayList<String> arr;
	public Tree(ArrayList<String> arr) throws NoSuchAlgorithmException, IOException {
		this.arr = arr;
		treeStr = arrLstToStr(arr);
		treeSha1 = strToSha1(treeStr);
		File treeFile = new File("/objects", treeSha1);
		FileWriter fw = new FileWriter(treeFile);
		if(treeFile.createNewFile()) {	}else {
			Files.deleteIfExists(Paths.get("/objects/" + treeSha1));
			treeFile.createNewFile();
		}
		fw.write(treeStr);
		fw.close();
	}
	
	private String arrLstToStr(ArrayList<String> array) {
		String arrStr = array.get(0);
		if(array.size()>1) {
			for(int i = 1; i<array.size(); i++) {
				arrStr += array.get(i)+"\n";
			}
		}
		return(arrStr);
	}
	
	
	private String strToSha1(String convertme) throws NoSuchAlgorithmException {
		byte[] bytes = convertme.getBytes();
	    MessageDigest md = MessageDigest.getInstance("SHA-1");
	    return Base64.getEncoder().encodeToString(md.digest(bytes));
	}
}
