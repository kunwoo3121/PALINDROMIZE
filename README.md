# PALINDROMIZE

https://algospot.com/judge/problem/read/PALINDROMIZE

# 구현 방법
```
원래의 문자열과 그 문자열을 뒤집은 문자열을 비교한다.

원래 문자열의 뒷 부분과 뒤집은 문자열의 앞 부분이 일치하는 길이를 찾으면 가장 짧은 팰린드롬을 찾을 수 있다.

원래 문자열의 길이가 n이고 일치하는 길이가 k라고 할 때 가장 짧은 팰린드롬은 2n - k가 된다.

겹치는 문자열의 길이를 찾을 때는 kmp 알고리즘을 이용한다.

ex) there을 이용해서 만들 수 있는 가장 짧은 팰린드롬을 찾는다고 할 때,

    there을 뒤집으면 ereth,  there의 뒷 부분 ere와 ereth의 앞 부분 ere가 일치한다.
    
    길이 n = 5, 일치하는 길이 k = 3이므로 가장 짧은 팰린드롬의 길이는 7이 된다.
```

# 구현 코드
```java
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
```
