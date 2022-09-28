import java.util.*;
import java.io.*;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import git.*;

public class IndexTester {
	public static void main (String [] args) throws IOException{
		Index i = new Index();
		i.init();
		i.add("test.txt");
		i.add("oogabooga.txt.txt");
		i.remove("oogabooga.txt.txt");
	}
}
