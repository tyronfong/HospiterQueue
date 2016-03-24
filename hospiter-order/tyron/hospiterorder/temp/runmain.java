package tyron.hospiterorder.temp;

import java.util.Random;

public class runmain {
	public static void main(String[] args){
		Random random = new Random();
		int b;
		System.out.println(100000);
		for(int i = 0; i < 100000; i++){
			do{
				b = random.nextInt(11);
			}while(b==0);
			
			System.out.println(runmain.RandomString(b));
		}
		System.out.println(100000);

		for(int i = 0; i < 100000; i++){
			do{
				b = random.nextInt(11);
			}while(b==0);
			
			System.out.println(runmain.RandomString(b));
		}
		
	}
	/** 产生一个随机的字符串*/
	public static String RandomString(int length) {
		String str = "abcdefghijklmnopqrstuvwxyz";
		Random random = new Random();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int num = random.nextInt(25);
			buf.append(str.charAt(num));
		}
		return buf.toString();
	}
}
