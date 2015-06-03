//package sec_try;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DuplicateFile {
	
	
	//we can use linked list also here.. 
	File all_files[]=new File[1000000];
	long sizes[]=new long[1000000];
	//String names[]=new String[1000000];
	
	DuplicateFile() throws IOException{
	
		System.out.println("give folder path for searching-");
		DataInputStream din=new DataInputStream(System.in);
		String root=din.readLine();
		File f=new File(root);
		long start=System.currentTimeMillis();
		int cnt=recursive_fun(f,0);
		System.out.println(cnt+" files are present in specified directory.\n");
		System.out.println("Time required for listing is "+(System.currentTimeMillis()-start)/1000.0+" s.\n");

		start=System.currentTimeMillis();
		
		sort(cnt);
		
		System.out.println("Time required for sorting is "+(System.currentTimeMillis()-start)/1000.0+" s.\n");
		start=System.currentTimeMillis();
		//display(cnt);
		System.out.println("Duplicate files are "+duplication_count(cnt)+"\n");
		System.out.println("Time required for finding duplication is "+(System.currentTimeMillis()-start)/1000.0+" s.\n");
		
		System.out.println("Now Delete the duplicate files using options \n 1 for 1st \n 2 for 2nd \n 0 for skip \n 5 for exit");
		System.out.println("***Note that - if you delete any file already and it shows you the same file pls skip it.\n");
		duplication(cnt);
		System.out.println("completed");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new DuplicateFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void sort(int cnt){
		File temp;
		long temp1;
		for(int i=0;i<cnt;i++)
			for(int j=0;j<cnt-1;j++){
				if(sizes[j]>sizes[j+1]){
//				if(all_files[j].length()>all_files[j+1].length()){
					temp=all_files[j];
					all_files[j]=all_files[j+1];
					all_files[j+1]=temp;
					temp1=sizes[j];
					sizes[j]=sizes[j+1];
					sizes[j+1]=temp1;
					
					
				}
			}
	}

	public void display(int no){
		for(int i=0;i<no;i++)
			System.out.println(all_files[i].getAbsolutePath()+" "+all_files[i].length());
	}
	
	
	public int recursive_fun(File fp,int no){
	
		try {
			String ls[]=fp.list();
			if(fp.isFile()){
				all_files[no]=fp;
				sizes[no]=fp.length();
			//	names[no]=fp.getName();
//			insertion_sort(fp,no);
				no++;
				return no;
			}
			if(fp.isDirectory() )
			for(int i=0;i<ls.length;i++)
			{
				File temp=new File(fp.getAbsolutePath()+"\\"+ls[i]);
				no=recursive_fun(new File(fp.getAbsolutePath()+"\\"+ls[i]),no);
				//long size=fp.length();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
return no;

	}
	
	public int duplication_count(int cnt){
		int i=0;
		int ret=0;
		while(i<cnt-1){
			if(sizes[i]==sizes[i+1] && all_files[i].getName().equals(all_files[i+1].getName())){
				ret++;
			}
		i++;	
		}
			return ret;
	}
	
	public void duplication(int cnt){
		int i=0;
		while(i<cnt-1){
			if(sizes[i]==sizes[i+1] && all_files[i].getName().equals(all_files[i+1].getName())){
				System.out.println("1. "+all_files[i].getAbsolutePath());
				System.out.println("2. "+all_files[i+1].getAbsolutePath());
				System.out.println("press id to delete the file");
				Scanner sc=new Scanner(System.in);
				int no=sc.nextInt();
				if(no==1 || no==2)
					System.out.println(all_files[i+no-1].delete());
				if(no==5)
					return;
			
			}
			i++;
		}
	}
}



