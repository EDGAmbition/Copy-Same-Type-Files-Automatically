package edgambition.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;

public class IOUtil {
	/** 
	 * 向to（文件夹）复制一个from（文件）
	 * @param from 文件
	 * @param to 文件夹
	 */
	static void copyOneFile(File from,File to) {
		if(from.isDirectory() || to.isFile()) {
			return;
		}
		
		File dest = nameCheck(from,to);
		
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		try {
			fis = new FileInputStream(from);
			bis = new BufferedInputStream(fis);
			fos = new FileOutputStream(dest);
			bos = new BufferedOutputStream(fos);
			int len = 0;
			byte[] buf = new byte[1024]; 
			while((len=bis.read(buf)) != -1) {
				bos.write(buf, 0, len);
			}
			bos.flush();
			bos.close();
			bis.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 递归过程 复制dir文件夹下所有属于extension扩展名的内容到to文件夹
	 * @param dir 文件夹
	 * @param to  文件夹
	 * @param extension  扩展名字符串组  例 .java
	 */
	static void copyFiles(File dir,File to,String[] extension) {
		if(!dir.exists()) 
			return;
		if(dir.isFile()) 
			IOUtil.copyOneFile(dir, to);
		if(dir.isDirectory()) {
			File[] subFiles= getSubFiles(dir,extension);
			copySubFiles(subFiles,dir,to,extension);
			
		}
	}
	private static  File[] getSubFiles(File dir,String[] extension) {
		File[] subFiles= dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File src, String name) {
				if(new File(src,name).isDirectory()) {
					return true;
				}
				boolean flag = false;
				int len = extension.length;
				for(int i=0;i<len;i++) {
					if(name.endsWith(extension[i])) {
						flag = true;
						break;
					}
				}
				return flag;
			}				
		});
		return subFiles;
	}
	
	private static void copySubFiles(File[] subFiles,File dir,File to,String[] extension) {
		for(File sub:subFiles) {
			if(sub.isFile()) {
				System.out.println(sub.getAbsolutePath());
				IOUtil.copyOneFile(sub, to);
			}
			else {
				IOUtil.copyFiles(new File(dir,sub.getName()),to,extension);
			}
		}
	}
	private static File nameCheck(File from,File to) {
		String name =from.getName();
		File dest = new File(to,name);
		int i=0;
		while(dest.exists()) {
			i++;
			int idx = name.lastIndexOf(".");
			StringBuilder tempName= new StringBuilder(name);
			tempName.insert(idx, "(" + i + ")");
			dest = new File(to,tempName.toString());
		}
		return dest;
	}
}

