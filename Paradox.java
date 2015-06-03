//package sec_try;

import java.util.Random;

public class Paradox {
	
	Paradox(int n,int experiments){
		int cnt=0;
		
		for(int k=0;k<experiments;k++){
		
		cnt+=experiment(n);
		}
		System.out.println("\nProbability for n="+n+" is "+(cnt*100.0/experiments)+" %");
		
	}
	
	
	public int experiment(int n){
int dates[]=new int[n];

Random r=new Random();
		for(int i=0;i<n;i++)
		{
			dates[i]=r.nextInt(365)+1;
			//System.out.print(dates[i]+" ");
		}
//		System.out.println();
		
		for(int i=0;i<n-1;i++){
			for(int j=i+1;j<n;j++){
				if(dates[i]==dates[j]){
		//			System.out.println("paradox"+dates[i]+" "+i+" "+j);
					return 1;
					}
			}
		}
	return 0;	
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				System.out.println("According to the birthday paradox in 23 peoples"
				+ "\n there is 50% chances that there is one pair such that "
				+ "they have same birthdate and in 70 peoples there is 99.9% probabilty.\n"
				+ " for 1000 random experiments the percentage comes to be following.\n"
				+ " these values are approximately coming near to the above values.");

new Paradox(23,1000);
new Paradox(70,1000);
	}

}
