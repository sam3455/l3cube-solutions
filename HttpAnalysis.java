//package sec_try;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;

import javax.swing.JTable;

public class HttpAnalysis {

	String iplist[]=new String[1000];
	int ipcount[]=new int[1000];

	String codelist[]=new String[50];
	int codecount[]=new int[50];
	
	HttpAnalysis() throws Exception{
		//FileReader f=new FileReader("weblog.txt");
		BufferedReader br=new BufferedReader(new FileReader("weblog.txt"));
		String line=null;


		line =br.readLine();
		System.out.println(line);
		String parts[]=line.split(" - - ");
		System.out.println("ip addres="+parts[0]);
		String sec[]=parts[1].split("\"");
		System.out.println("timeStamp is "+sec[0].subSequence(1, sec[0].length()-2));
		
		System.out.println("request to - "+sec[1].split(" ")[1]);
		
		System.out.println("result is - "+sec[2].split(" ")[1]);
		
		System.out.println("result is - "+sec[5]);
		
		analyser2();
	}
	
	
	//function for setting width of printing block. parallel to setw in cpp.
	
	private void setw(String str,int width){
		System.out.print(str);
		for(int i=str.length();i<width;i++)
			System.out.print(" ");
	}
	
	
	//function for summerising the results 
	private int isPresent(String[] arr,int [] TotalCount,String cur,int n) {
		for(int i=0;i<n;i++){
			if(arr[i].equals(cur)){
				TotalCount[i]++;
				
				return n;
			}
		}
		arr[n]=cur;
		TotalCount[n]=1;
		
		return n+1;
	}

	public void analyser2() throws Exception{
		BufferedReader br=new BufferedReader(new FileReader("weblog.txt"));
		String line=null;
		String ip,timestamp,url,code,info;
		int cnt=0,ipcnt=0,codecnt = 0;
		setw("IP address",18);

		setw("Timestamp",30);
		setw("URL",75);
		setw("Status",7);
		
		setw("Additional Information",2);
		System.out.println();
		
		while((line =br.readLine())!=null){
		System.out.println();
			cnt++;
		String parts[]=line.split(" - - ");
		ip=parts[0];
		
		ipcnt=isPresent(iplist,ipcount,ip,ipcnt);
		
		setw(ip,18);
		
		String sec[]=parts[1].split("\"");
		timestamp=sec[0].subSequence(1, sec[0].length()-2).toString();
		
		setw(timestamp,30);
		
		url=sec[1].split(" ")[1];
		
		setw(url,75);
		
		code=sec[2].split(" ")[1];
		
		codecnt=isPresent(codelist, codecount, code, codecnt);
		
		setw(code,7);
		
		info=sec[5];
		
		setw(info,2);
		}

		System.out.println("\n");
		System.out.println("\nno of lines ="+cnt);
		//ip distribution
		System.out.println("press 1 to print IP wise distribution:-\n\n");
		
		Scanner sc=new Scanner(System.in);
		int c=sc.nextInt();
		if(c==1){
			c=0;
		System.out.println("\nIP wise distribution is-\n");
		setw("IP address",18);
		setw("count",4);

		System.out.println();
		diaplyTable(iplist,ipcount,ipcnt,18);
		System.out.println("\nno of different machines ="+ipcnt);

		}
		
		//status distribution
		
		System.out.println("press 1 to print Status wise distribution:-\n\n");
		c=sc.nextInt();
		if(c==1){
			c=0;
		System.out.println("\nStatus wise distribution is-\n");
		setw("Status",7);
		setw("count",4);
		System.out.println();
		diaplyTable(codelist,codecount,codecnt,7);
		System.out.println("no of different machines ="+codecnt);	

		}

	}
	
	
	private void diaplyTable(String[] iplist2, int[] ipcount2, int ipcnt,int w) {
		// TODO Auto-generated method stub
	
		for(int i=0;i<ipcnt;i++){
			setw(iplist2[i],w);
			setw(""+ipcount2[i],4);
			System.out.println();
		}
		
	}

	public void analyser() throws Exception{
		BufferedReader br=new BufferedReader(new FileReader("weblog.txt"));
		String line;
		
		
		while((line =br.readLine())!=null){
		System.out.println();
			
		String parts[]=line.split(" - - ");
		
		System.out.print(parts[0]+"\t");
		
		String sec[]=parts[1].split("\"");
		
		System.out.print(sec[0].subSequence(1, sec[0].length()-2)+"\t");
		
		System.out.print(sec[1].split(" ")[1]+"\t");
		
		System.out.print(sec[2].split(" ")[1]+"\t");
		
		System.out.print(sec[5]+"\t");
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
new HttpAnalysis();
	}

}
