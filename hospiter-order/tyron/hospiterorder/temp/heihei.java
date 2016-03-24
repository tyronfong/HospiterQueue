package tyron.hospiterorder.temp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * @author Administrator
 *
 */
public class heihei {
	public static void main(String[] args) throws IOException{
		String a = "/nimei/hehe/a/b/56/c/2.u";
		String b = "/nimei/hehe/ni/mei/3.4";
		
		System.out.println(new heihei().fun1(a, b));
	}
	
	public String fun1(String a, String b){
		String[] aa = a.split("/");
		String[] bb = b.split("/");
		int flag=0;
		int j = aa.length;
		int k = bb.length;
		int max = k;
		int min = j;
		if(j > k){
			max = j;
			min = k;
		}
		for(int i = 1; i < min;i++){
			if(aa[i].equals(bb[i])){
				flag++;
			}
		}
		
		StringBuffer aaa = new StringBuffer();
		for(int i = 0; ; i++){
			if(i < aa.length-flag-2){
			aaa.append("../");
			}
			else
			{
				for(int ii = flag+1; ii < bb.length-flag+2; ii++){
				aaa.append(bb[ii]+"/");
				}
				break;
			}
		
		}
		aaa.deleteCharAt(aaa.length()-1);
		return aaa.toString();
		
	}
	
	public static void create(File file, long length) throws IOException{
		long start = System.currentTimeMillis();
		RandomAccessFile r = null;
		try {
			r = new RandomAccessFile(file, "rw");
			r.setLength(length);
		} finally{
			if (r != null) {
				try {
					r.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		long end = System.currentTimeMillis();
		System.out.println(end-start);
		
	}
}