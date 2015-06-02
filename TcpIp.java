//package sec_try;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TcpIp {
	
	//first 64 bytes are waste or something different
	
	//16byte packet info is with each packet. 9th and 8th size.
	//24 byte page file info.
	
byte b[]=null;
int pointer=24;
int size=0;




	TcpIp() throws Exception{
		//unsigned long a=0;
		File f[]=new File[4];
		f[0]=new File("tcp-ecn-sample.pcap");	//for arp and bgp orked properly. problem with http and tcp takes wrong length.
		f[1]=new File("bgp.pcap");
		f[2]=new File("arp-storm.pcap");
		f[3]=new File("http_witp_jpegs.cap");
		System.out.println("Select file to parse for TcpIp- \n"
				+ "1.tcp-ecn-sample.pcap\n"
				+ "2.bgp.pcap\n"
				+ "3.arp-storm.pcap\n"
				+ "4.http_witp_jpegs.cap\n\n");
		Scanner sc=new Scanner(System.in);
		int i=0;
		while(i>5 || i<1)
			i=sc.nextInt();
		i--;
		int length=(int) f[i].length();
		System.out.println(length);
		b=new byte[length];
		FileInputStream fin=new FileInputStream(f[i]);
		
		fin.read(b);
		fin.close();
		//System.out.print();
	
		
		
		
		
		
		
		int pre=0;
		int cnt=0;
//		for(int i=0;i<6;i++)
		while(pointer<length)
		{
			cnt++;
			int temp=0;
			
			pointer+=7;
			temp=((b[pointer+2]&0xff)<<8) + (b[pointer+1] & 0xff);			//note this line.

			pointer+=9;
			pre=pointer;
			System.out.println("size before start is ="+temp);
			packetizer();
		
			pointer=pre+temp;
		
		System.out.println("cnt is "+cnt);
		}
		
		
		
		System.out.println();
/*		for(int i=0;i<64;i++){
			int temp = b[pointer++] & 0xff;
			System.out.print(temp+" ");
			
		}
*/
	
	}

	
	private int ethernet(){
		System.out.print("Destination MAC is ");
		int temp;
		int nextheader=0;
		for(int i=0;i<6;i++){
			temp= b[pointer++] & 0xff;
			System.out.print(temp+":");
			
		}
		System.out.print("\b\n");	//if backslash dont work pls cooperate.
		
		
		System.out.print("Source MAC is ");
		for(int i=0;i<6;i++){
			temp= b[pointer++] & 0xff;
			System.out.print(temp+":");
		}
		System.out.print("\b\n");	//if backslash dont work pls cooperate.
		
		
		System.out.print("IP type is ");
		for(int i=0;i<2;i++){
			temp= b[pointer++] & 0xff;
			 nextheader += temp;
			System.out.print(temp+" ");
		}
		System.out.println();
		return nextheader;
	}
	
	private int IP(){

		int nextheader = 0;
		 int temp = b[pointer++] & 0xff;			//version and size
		if((temp & 0xf0) == 0x40){
			System.out.println("IP version is IPv4 ");
			int headerlength= (temp & 0xf) *4;		//size in bytes
			pointer++;			//services fields
			
		
			size=((b[pointer]&0xff)<<8) + (b[pointer+1] & 0xff);
			System.out.println("Full size is "+size);
			//System.out.println("sizeis = "+size);
			for(int i=0;i<2;i++){
			 temp= b[pointer++] & 0xff;
				//System.out.print(temp+" ");
			}
			//System.out.println();
			
			//starts from here identification etc... skipped 4 less imp things.
			pointer+=4;
			
			//TTL
			System.out.println("TTL is "+(b[pointer++] & 0xff));
			
			//nextheader tcp=6 udp=17 icmp=1
			nextheader=(b[pointer++] & 0xff);
			
			System.out.println("next header is = "+nextheader);
			
			//skip checksum
			pointer+=2;
			
			//src ip
			System.out.print("Source IP is ");
			for(int i=0;i<4;i++){
			 temp= b[pointer++] & 0xff;
				System.out.print(temp+".");
			}
			System.out.print("\b\n");	//if backslash dont work pls cooperate.
			
			
			//destination IP
			System.out.print("Destination IP is  ");
			for(int i=0;i<4;i++){
			 temp= b[pointer++] & 0xff;
				System.out.print(temp+".");
			}
			System.out.print("\b\n");	//if backslash dont work pls cooperate.
			
			//skip if options present.
			size-=headerlength;
			pointer+=headerlength-20;
			
			
			
		}
		else
		System.out.println("IPv6 can't handle");
		
	/*
	System.out.print("Source IP is");
	for(int i=0;i<6;i++){
	 temp= b[pointer++] & 0xff;
		System.out.print(temp+" ");
	}
	System.out.println();
	
	
	for(int i=0;i<64;i++){
		 temp= b[pointer++] & 0xff;
		System.out.print(temp+" ");
	}
*/
	return nextheader;
	}

	public void packetizer(){
		int temp=0;
		
		int nextheader=0;
	//ethernet...	
	nextheader=ethernet();	
//IP... 
		if(nextheader==8){
			
			nextheader=IP();
			if(nextheader==6){
				nextheader=TCP();
				Data();
			}
			else if(nextheader==17){
				nextheader=UDP();
			}
			else if(nextheader==1){
				nextheader=ICMP();
			}
		}		
		
		
	}
	
	private void DataAscii() {
		// TODO Auto-generated method stub
		int temp;
		for(int i=0;i<size;i++){
			 temp = b[pointer++]  & 0xff;
			System.out.print((char)temp+"");
			
		}
	System.out.println();
	}

	private void Data() {
		// TODO Auto-generated method stub
		int temp;
		for(int i=0;i<size;i++){
			 temp = b[pointer++]  & 0xff;
			System.out.print(temp+" ");
			
		}
	System.out.println();
	}

	private int ICMP() {
		// TODO Auto-generated method stub
		return 0;
	}


	private int UDP() {
		// TODO Auto-generated method stub
		return 0;
	}


	private int TCP() {
		// TODO Auto-generated method stub
		int nextheader=0;
		 int temp ;
		 
		 //src port
		 temp=((b[pointer++]&0xff)<<8) + (b[pointer++] & 0xff);
		 System.out.println("Source Port is ="+temp);			
			
		 //dst port
		 temp=((b[pointer++]&0xff)<<8) + (b[pointer++] & 0xff);
		 System.out.println("Destination Port is ="+temp);
		 
		 
		 pointer+=8; //skip seq and ack no.  I may print this if time is there.
		 int headerlength=b[pointer++]>>4;
		 headerlength*=4;
		 System.out.println("length is ="+headerlength);
		 
		 
		 //skip remaining info also...
		 pointer+=(headerlength-13);
		 size-=headerlength;
		 System.out.println("size is "+size);
		// System.out.println("final is "+(b[pointer++] & 0xff));
//		 System.out.println("final is "+(b[pointer++] & 0xff));
		 
		return 0;
	}


	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
new TcpIp();
	}

}
