import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class CommitTester {
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		Index i=new Index();
		i.init();
		i.add("stuff.txt.txt");
		
		Commit parentCommit = new Commit("first com", "remy", null);
		
		
	}

}
