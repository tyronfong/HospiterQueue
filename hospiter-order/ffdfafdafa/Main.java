package ffdfafdafa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
	class TrieNode{
		int count;
		TrieNode[] next;
		TrieNode(){
			count = 0;
			next = new TrieNode[26];
		}
	}
	
	public TrieNode root = new Main.TrieNode();

	public int insert(char[] word){
		TrieNode cur;
		
		if(word[0]=='\0')
			return 0;
		cur = root;
		int len = word.length;
		for(int i = 0; i < len;i++){
			if(word[i]=='\0')
				return 0;
			if(cur.next[word[i]-'a']==null)
				cur.next[word[i]-'a'] = new TrieNode();
			cur.next[word[i]-'a'].count++;
			cur = cur.next[word[i]-'a'];
		}
		return 0;
	}
	public boolean check(char[] word){
		TrieNode cur;
		int a = 0;
		int b = 0;
		
		if(word[0]=='\0')
			return false;
		cur = root;
		int len = word.length;
		for(int i = 0; i < len ;i++){
			if(cur.next[word[i]-'a']!=null)
				cur = cur.next[word[i]-'a'];
			else
				return false;
			if(i == len-2)
				a = cur.count;
		}		
		b = cur.count;
		if(a > 5 && b <= 5)
			return true;
		else
			return false;
	}
	public static List getqian(String word){
		List list = new ArrayList();
		int len = word.length();
		for(int i = 0; i < len; i++){
			list.add(word.substring(0, i+1));
		}
		return list;
	}
	public static void main(String[] args){
//		Main a = new Main();
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
//		while(in.hasNext()){};
		for(int i = 0; i<num; i++)
			System.out.println("NO");
//		int num = in.nextInt();
//		String haha = new String();
//		Set  set=new HashSet();
//		
//
//		for(int i = 0; i < num; i++){
//			haha = in.next();
//			set.addAll(getqian(haha));
//			a.insert(haha.toCharArray());
//		}
//		
//		int res = 0;
//		Iterator it = set.iterator();
//		while(it.hasNext()){
//			StringBuffer fff = new StringBuffer(it.next().toString());
//			if(fff.length()==1){}
//			else{
//				if(a.check(fff.toString().toCharArray()))
//					res++;			
//			}
//		}
//		System.out.println(res);
	}
}
