//package sec_try;
//need more changes this is just outline


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HttpAnalysis {

	HttpAnalysis() throws Exception{
		//FileReader f=new FileReader("weblog.txt");
		BufferedReader br=new BufferedReader(new FileReader("weblog.txt"));
		String line;
		
		
		line =br.readLine();
		System.out.println(line);
		String parts[]=line.split(" - - ");
		System.out.println("ip addres="+parts[0]);
		String sec[]=parts[1].split("\"");
		System.out.println("timeStamp is "+sec[0].subSequence(1, sec[0].length()-2));
		
		System.out.println("request to - "+sec[1].split(" ")[1]);
		
		System.out.println("result is - "+sec[2].split(" ")[1]);
		
		System.out.println("result is - "+sec[5]);
		
		analyser();
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
