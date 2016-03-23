package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class hiho1015 {
	public static int[] getArray(char[] word){
		int len = word.length;
		int[] array = new int[len];
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		array[0] = 0;
		
		for(int i = 1; i < len; i++){
			StringBuffer a = new StringBuffer();
			StringBuffer b = new StringBuffer();
			StringBuffer c = new StringBuffer();
			for(int j = 0; j < i; j++){
				a.append(word[j]);
				b.append(word[i-j]);
				c = b.reverse();
				list1.add(a.toString());
				list2.add(c.toString());
			}
			list2.retainAll(list1);
			array[i] = list2.toString().length()-2;
			list1.clear();
			list2.clear();
		}
		
		return array;
	}
	public static int indexof(StringBuffer father, StringBuffer son, int loc){
		int falen = father.length();
		int sonlen = son.length();
		int tt = 0;
		int[] array = getArray(son.toString().toCharArray());
//		for(int i = 0; i < sonlen; i++)
//			System.out.print(array[i]+" ");
		
		int i = loc;
		for(int j = 0; j <= sonlen; j++){
			if(j == sonlen)
				return i-sonlen;
			else if(father.charAt(i) != son.charAt(j)){
				tt = j-1;
				if(tt < 0){
					tt = 0;
					i++;
				}
				j = array[tt]-1;
				i--;					
			}
			i++;
			if(i >= falen)
				return -1;
		}
		return -1;
	}
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		int[] res = new int[num];
		StringBuffer[][] input = new StringBuffer[num][2];
		
		for(int i = 0; i < num; i++){
			input[i][0] = new StringBuffer(in.next());
			input[i][1] = new StringBuffer(in.next());
		}
		
		for(int i = 0; i < num; i++){
			res[i] = 0;
			int loc = 0;
			int temp = 0;
			
			while(true){
				temp = indexof(input[i][1],input[i][0],loc);
				
//				System.out.println(temp);
				if(temp != -1){
					res[i]++;
					loc = temp+1;
				}
				else
					break;
			}
		}
		
		for(int i = 0; i < num; i++)
			System.out.println(res[i]);
	}
}
