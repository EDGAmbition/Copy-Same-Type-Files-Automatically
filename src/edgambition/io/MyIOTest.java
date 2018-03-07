package edgambition.io;
import java.io.File;
public class MyIOTest {
	public static void main(String[] args) {
		File a = new File("E:\\ioTest\\copy01.java");
		File b = new File("E:\\ioTest\\copytest");
		File c = new File("E:\\ioTest");
		File d = new File("E:\\copytest");
		IOUtil.copyOneFile(a, b);
		String[] ex = new String[2];
		ex[0]=".java";
		ex[1]=".txt";
//		IOUtil.copyFiles(c,d,ex);
	}
}
