//package sec_try;

//check whether the file really get deleted or not.


import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DuplicateFile {
	
	
	//we can use linked list also here.. 
	File all_files[]=new File[1000000];
	long sizes[]=new long[1000000];
	DuplicateFile() throws IOException{
	
		System.out.println("give folder path for searching-");
		DataInputStream din=new DataInputStream(System.in);
		String root=din.readLine();
		File f=new File(root);
		int cnt=recursive_fun(f,0);
		System.out.println("listed");
		sort(cnt);
		System.out.println("sorted");
		//display(cnt);
		
		System.out.println("count is "+	cnt);
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
		for(int i=0;i<cnt;i++)
			for(int j=0;j<cnt-1;j++){
				if(sizes[j]>sizes[j+1]){
//				if(all_files[j].length()>all_files[j+1].length()){
					temp=all_files[j];
					all_files[j]=all_files[j+1];
					all_files[j+1]=temp;
				}
			}
	}

	public void display(int no){
		for(int i=0;i<no;i++)
			System.out.println(all_files[i].getAbsolutePath()+" "+all_files[i].length());
	}
	
	public void insertion_sort(File fp,int no)
	{
		System.out.println(fp.getAbsolutePath());
		int i=0;
		all_files[no]=fp;
		while(i<=no && sizes[i]<fp.length()){
			
			i++;
		}
		for(int j=no;j>i;j--){
			File temp=all_files[j];
			all_files[j]=all_files[j-1];
			all_files[j-1]=temp;
		}
	}
	
	public int recursive_fun(File fp,int no){
	
		try {
			String ls[]=fp.list();
			if(fp.isFile()){
				all_files[no]=fp;
				sizes[no]=fp.length();
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
					System.out.println(all_files[no-1].delete());
				
			
			}
			i++;
		}
	}
}
