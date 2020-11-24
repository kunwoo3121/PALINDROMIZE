import java.util.Scanner;
public class PALINDROMIZE {
	
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		
		int C = sc.nextInt();
		
		for(int i = 0; i < C; i++)
		{
			String S = sc.next();
			String S_reverse = (new StringBuffer(S)).reverse().toString();
			
			int overLap = maxOverlap(S, S_reverse);
			
			System.out.println(S.length() * 2 - overLap);
		}
		
		sc.close();
	}
	
	public static int maxOverlap(String S1, String S2)
	{
		int n = S1.length();
		int m = S2.length();
		
		int[] pi = getPartialMatch(S2);
		
		int begin = 0, matched = 0;
		
		while(begin < n)
		{
			if(matched < m && S1.charAt(begin + matched) == S2.charAt(matched))
			{
				matched++;
				if(matched + begin == n)
					return matched;
			}
			
			else
			{
				if(matched == 0)
					begin++;
				else
				{
					begin += matched - pi[matched - 1];
					matched = pi[matched - 1];
				}
			}
		}
		
		return 0;
	}
	
	public static int[] getPartialMatch(String S)
	{
		int m = S.length();
		
		int[] pi = new int[m];
		
		int begin = 1, matched = 0;
		
		while(begin + matched < m)
		{
			if(S.charAt(begin + matched) == S.charAt(matched))
			{
				matched++;
				pi[begin + matched -1] = matched;
			}
			
			else
			{
				if(matched == 0)
					begin++;
				else
				{
					begin += matched - pi[matched - 1];
					matched = pi[matched - 1];
				}
			}
		}
		
		return pi;
	}
}			

